package com.neotys.protocol.GoldRequest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.neotys.extensions.action.ActionParameter;
import com.neotys.extensions.action.engine.Context;

public class GoldRequestActionTest {
	@Test
	public void shouldReturnType() {
		final GoldRequestAction action = new GoldRequestAction();
		assertEquals("GoldRequest", action.getType());
	}
	@Test
	public void SendandReceivePackets() throws InterruptedException  {
		
		 	
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						GoldMockServer server = new GoldMockServer();
						server.Listenning();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				}).start();;
				
				Thread.sleep(1000);
		 
		 	
			final GoldRequestActionEngine action = new GoldRequestActionEngine();
			List<ActionParameter> parameters = new ArrayList<>();
			parameters.add(new ActionParameter("Host","127.0.0.1"));
			parameters.add(new ActionParameter("Port","8888"));
			parameters.add(new ActionParameter("TimeOut","10"));
			parameters.add(new ActionParameter("FunctionName","outils.VOCGeneral.getSpokenName"));
			parameters.add(new ActionParameter("Param1","PdaRobotHomol001"));
			parameters.add(new ActionParameter("Param2","<NOT FOUND>"));
			parameters.add(new ActionParameter("Param3","98001"));
			parameters.add(new ActionParameter("Param4","5"));
			parameters.add(new ActionParameter("Param5","5"));
			parameters.add(new ActionParameter("Param6","STARTER"));
			
			

			action.execute(Mockito.mock(Context.class), parameters);
			
			
		
	}
}
