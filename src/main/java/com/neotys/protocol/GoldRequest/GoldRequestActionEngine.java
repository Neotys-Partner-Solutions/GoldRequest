package com.neotys.protocol.GoldRequest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Strings;
import com.neotys.extensions.action.ActionParameter;
import com.neotys.extensions.action.engine.ActionEngine;
import com.neotys.extensions.action.engine.Context;
import com.neotys.extensions.action.engine.SampleResult;


public final class GoldRequestActionEngine implements ActionEngine {
	String HOST;
	int PORT;
	String FunctionName;
	long timeout = DEFAULT_TIMEOUT;
	private Socket GoldSocket = null;
	static InputStream  bufInStream; 
	static OutputStream bufoutStream;
	 // Buffer to receive the data
	 private final String ENDMEssage = ",\n\n";
	 private final String EndOfResponse="end";
	 
	 byte[] buf = null;
	 
    private BufferedReader GoldufferedReader = null;

	  // Used to receive GOld messages and data
	private BufferedWriter GoldBufferedWriter = null;	
	private static final long DEFAULT_TIMEOUT = 10;
	
	@Override
	public SampleResult execute(Context context, List<ActionParameter> parameters) {
		final SampleResult sampleResult = new SampleResult();
		final StringBuilder requestBuilder = new StringBuilder();
		final StringBuilder responseBuilder = new StringBuilder();

		String pattern = "Param(\\d+)";
		Pattern reg = Pattern.compile(pattern);
		HashMap< Integer,String> ParamList;
		ParamList = new HashMap< Integer,String>();
		byte[]  send_PackedRequestData;
		String request;
		String response;
		
		for(ActionParameter parameter:parameters) {
		
			switch(parameter.getName())
			{
				case GoldRequestAction.TimeOut:
					try
					{
						timeout = Long.parseLong(parameter.getValue());
					} 
					catch (final NumberFormatException e)
					{
						context.getLogger().warn("could not parse timeout " + parameter.getValue() + ", take default value "
								+ DEFAULT_TIMEOUT);
						timeout = DEFAULT_TIMEOUT;
					}
					break;
	
				case GoldRequestAction.FunctionName:
					FunctionName = parameter.getValue();
					break;
				case GoldRequestAction.Port:
					try
					{
						PORT=Integer.parseInt(parameter.getValue());
					}
					catch (final NumberFormatException e)
					{
						return getErrorResult(context, sampleResult, "could not parse Port " + parameter.getValue() , null);
					}
					break;
				case GoldRequestAction.Host:
					HOST = parameter.getValue();
					break;
				
				default:
					Matcher m = reg.matcher(parameter.getName());
					 if (m.find( ))
					 {
						 ParamList.put(Integer.valueOf(m.group(1)),parameter.getValue());
					 }
			}
			
		}
		
		if (Strings.isNullOrEmpty(HOST)) {
			return getErrorResult(context, sampleResult, "Invalid argument: Missing parameter "
					+ GoldRequestAction.Host + ".",null);
		}
		if (Strings.isNullOrEmpty(FunctionName)) {
			return getErrorResult(context, sampleResult, "Invalid argument: Missing parameter "
					+ GoldRequestAction.FunctionName + ".",null);
		}
		try
		{
			sampleResult.sampleStart();
			InitConnection();

			request=SendMessage(FunctionName, ParamList);

			response=receiveMessage(context);
			
			
			appendLineToStringBuilder(responseBuilder, response);
			appendLineToStringBuilder(requestBuilder, request);
			
			if(response==null)
			{
				
				return getErrorResult(context,sampleResult,"No response received from the server",null);
			}
			
			sampleResult.sampleEnd();

	
			sampleResult.setRequestContent(requestBuilder.toString());
			sampleResult.setResponseContent(responseBuilder.toString());
			
			if(response.contains("ko"))
			{
				
				return getErrorResult(context, sampleResult, "Response ERROR ", null);
			}
			

			closeConnection();
			// TODO perform execution.
			return sampleResult;
	
		}
		catch (IOException | InterruptedException | ExecutionException | TimeoutException  e) {
			return getErrorResult(context, sampleResult, "Technical ERROR ", e);
		}
		finally
		{
			try {
				closeConnection();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			
			}
		
		}
	}

	private void appendLineToStringBuilder(final StringBuilder sb, final String line){
		sb.append(line).append("\n");
	}

	/**
	 * This method allows to easily create an error result and log exception.
	 */
	private static SampleResult getErrorResult(final Context context, final SampleResult result, final String errorMessage, final Exception exception) {
		result.setError(true);
		result.setStatusCode("NL-GoldRequest_ERROR");
		result.setResponseContent(result.getResponseContent()+"\n"+errorMessage);
		if(exception != null){
			System.out.println(exception.getMessage());
			//context.getLogger().error(errorMessage, exception);
		} else{
			context.getLogger().error(errorMessage);
		}
		return result;
	}

	private String receiveMessage(final Context context)
			throws InterruptedException, ExecutionException, TimeoutException {
		final ExecutorService executor = Executors.newCachedThreadPool();
		Callable<String> task = new Callable<String>() {
			public String call() {
				try {
					return ReceiveMessage();
				} catch (IOException  e) {
					System.out.println("error io");
					context.getLogger().error("error while receiving response", e);
					return null;
				}
				catch (GoldException  e) {
					System.out.println("error reception");
					context.getLogger().error("error while receiving response", e);
					return null;
				}
				
			}
		};
		Future<String> future = executor.submit(task);
		return future.get(timeout, TimeUnit.SECONDS);
	}

	private String SendMessage(String Function,HashMap< Integer,String> Parameters) throws IOException
	{
	    StringBuilder stringToSend = new StringBuilder();
	    stringToSend.append(Function);
	    for(Map.Entry<Integer, String> entry:Parameters.entrySet())
	    {
	    	stringToSend.append(","+entry.getValue());
	    }
	    
	    stringToSend.append(ENDMEssage);

        GoldBufferedWriter.write(stringToSend.toString());
      
        GoldBufferedWriter.flush();
      //  System.out.println("Message Sent :"+stringToSend.toString());
        return "Message Sent :"+stringToSend.toString();
	}
	
	private String ReceiveMessage() throws IOException, GoldException
	{
		StringBuilder output=new StringBuilder();
		String line;
		StringTokenizer tokens,token;
		String Patern;
		String StatusCode;
		int i=1;
		int j=1;
		
		output.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<Response>\n");
		
		while((line=GoldufferedReader.readLine())!=null)
		{
						
			tokens = new StringTokenizer(line, ",");
			if(tokens.hasMoreTokens())
			{
				
				StatusCode=tokens.nextToken();
				if(StatusCode.equals(EndOfResponse))
				{
					j--;
					output.append("<EndResponse numberOfMessage=\""+j+"\"/>");
					break;
				}
				output.append("<Message id=\""+j+"\">\n");	
				output.append("<statuscode>"+StatusCode+"</statuscode>\n");
				
				while(tokens.hasMoreTokens())
				{			  	
					Patern=tokens.nextToken();
					if(!Patern.equalsIgnoreCase(EndOfResponse))
					{
						if(!Patern.contains(EndOfResponse))
						{
							output.append("<output id=\""+i+"\">"+Patern+"</output>\n");
							i++;
						}
						else
							break;
							
					}
					else
						break;
				}
				output.append("</Message>\n");	
				j++;
				i=1;
			}
			else
				break;
		}
			   
		
		if(output.length()<=56)
		{
			//System.out.println(output.toString());
			throw new GoldException("No Response from Server");
		}
		output.append("</Response>\n");
		//System.out.println("Message Received :"+output.toString());
		return output.toString();
	}
	private void closeConnection() throws IOException
	{
	
		if(GoldSocket!=null)
		{
			if(!GoldSocket.isClosed())
			{
				//System.out.println("Close connection");
				GoldSocket.close();
				bufoutStream.close();
				bufoutStream=null;
				
				bufInStream.close();
				bufInStream=null;
			
				GoldSocket.close();
				GoldSocket=null;
			}
		}
	}
	private void InitConnection( ) throws IOException
	{
		  InetAddress ServerIPAddr = InetAddress.getByName(HOST);
	      GoldSocket = new Socket(ServerIPAddr, PORT);
	      
	      bufInStream= GoldSocket.getInputStream();
	      bufoutStream= GoldSocket.getOutputStream();

	    /*  if(GoldSocket.isConnected())
	    	  System.out.println("connecté");
	      else
	     	  System.out.println("Non connecté");*/
		   
	    //System.out.println("Set up read buffer");
	      GoldufferedReader = new BufferedReader(new InputStreamReader(bufInStream));
	      
	    //System.out.println("Set up write buffer");
	      GoldBufferedWriter = new BufferedWriter(new OutputStreamWriter(bufoutStream));
	      GoldBufferedWriter.flush();
	      buf=new byte[1024];
	      
	}
	@Override
	public void stopExecute() {
		// TODO add code executed when the test have to stop.
	}

}
class GoldException extends Exception
{
      //Parameterless Constructor
      public GoldException() {}

      //Constructor that accepts a message
      public GoldException(String message)
      {
         super(message);
      }
 }
