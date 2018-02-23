package com.neotys.protocol.GoldRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;
import javax.print.attribute.standard.OutputDeviceAssigned;

public class GoldMockServer {

	
	private static int port = 8888;
	private static String Localadress="127.0.0.1";
	private ServerSocket serverSocket;

	int clientNumber = 0;
	
	public GoldMockServer() throws IOException
	{
		 
		ServerSocketFactory serverSocketFactory =ServerSocketFactory.getDefault();
		InetAddress addr = InetAddress.getByName("127.0.0.1");
		// or
		serverSocket =serverSocketFactory.createServerSocket(port,50,addr);
	   
		
	}
	private void log(String message) {
	     System.out.println(message);
	 }
	public void Listenning() throws IOException
	{
		Socket client;
		String inputline;
		int test;
   		String Ouputmessage;
		 try {
	            while (true) {
	            	client=serverSocket.accept();
	                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
	   			    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
	   			    log("en cours d'écoute");
	   			    while (  in.ready()) {
	   			    	test = in.read();
	   			    	System.out.println(test);
	                 
	   			    }
	   			    Ouputmessage="ok,zone,TXT004,1\nok,allee,TXT005,2\nok,nombre de licence atteint,TXT018,3\nok,contacter votre responsable,TXT019,4\nok,voulez vous commencer prelevement,TXT022,5\nok,code pr.parateur,TXT023,6\nok,code pr.parateur inconnu contactez le responsable,TXT024,7\nok,code pr.parateur incorrect pour,TXT025,8\nok,vous avez un lot en cours,TXT026,9\nok,dites ok pour continuer,TXT027,10\nok,type de fourche,TXT028,11\nok,type de fourche incorrect,TXT029,12\nok,donnez le num.ro de zone,TXT030,13\nok,prochaine commande,TXT032,14\nok,pas de lot disponible,TXT033,15\nok,pr.parer et compl.ter,TXT035,16\nok,lot,TXT036,17\nok,up.,TXT037,18\nok,chiffre de controle .tiquette,TXT038,19\nok,attention chiffre de controle incorrect,TXT039,20\nok,associe,TXT040,21\nok,prise,TXT041,22\nok,des lots partiellements pr.par.s,TXT042,23\nok,meme adresse,TXT043,24\nok,attention,TXT045,25\nok,attention adresse,TXT046,26\nok,repeter le code detrompeur,TXT047,27\nok,depose sur support,TXT048,28\nok,prendre,TXT049,29\nok,voulez vous confirmer la fin de journ.e,TXT050,30\nok,nombre de support,TXT051,31\nok,nombre de support incorrect,TXT052,32\nok,support,TXT053,33\nok,dites ok pour confirmer,TXT054,34\nok,seulement,TXT055,35\nok,support pour ce lot,TXT056,36"
	   			    	+	"\nok,nombre d.tiquette par support,TXT057,37"
	   			        +	"\nok,num.ro imprimante,TXT058,38"
	   			    		+"\nok,est trop,TXT059,39"
	   			    		+"\nok,.tiquettes par support,TXT060,40"
	   			    		+"\nok,num.ro imprimante inconnu,TXT061,41"
	   			    		+"\nok,impossible de prendre,TXT065,42"
	   			    		+"\nok,maximum,TXT066,43"
	   			    		+"\nok,repeter et valider par ok,TXT067,44"
	   			    		+"\nok,a prendre,TXT068,45"
	   			    		+"\nok,au d.but,TXT069,46"
	   			    		+"\nok,reste,TXT070,47"
	   			    		+"\nok,nombre de,TXT071,48"
	   			    		+"\nok,pr.par.,TXT072,49"
	   			    		+"\nok,manquant est trop gros,TXT073,50"
	   			    		+"\nok,poids,TXT074,51"
	   			    		+"\nok,poids est trop gros,TXT076,52"
	   			    		+"\nok,poids est trop petit,TXT077,53"
	   			    		+"\nok,vous zetes sur,TXT078,54"
	   			    		+"\nok,kilo,TXT079,55"
	   			    		+"\nok,total,TXT080,56"
	   			    		+"\nok,il ny a plus assez de,TXT081,57"
	   			    		+"\nok,disponible a pr.parer,TXT082,58"
	   			    		+"\nok,depose,TXT084,59"
	   			    		+"\nok,chargement du lot,TXT085,60"
	   			    		+"\nok,code emballage,TXT086,61"
	   			    		+"\nok,description pour lemballage suppl.mentaire,TXT087,62"
	   			    		+"\nok,description pour modification code emballage,TXT088,63"
	   			    		+"\nok,naixiste pas pour,TXT089,64"
	   			    		+"\nok,nouveau support,TXT090,65"
	   			    		+"\nok,prises restantes,TXT091,66"
	   			    		+"\nok,veuillez prendre un nouveau support et continuer,TXT092,67"
	   			    		+"\nok,num.ro de support,TXT093,68"
	   			    		+"\nok,support d.ja en cours,TXT094,69"
	   			    		+"\nok,num.ro,TXT095,70"
	   			    		+"\nok,svp comptez les articles,TXT096,71"
	   			    		+"\nok,patienter svp envoi des donn.es,TXT099,72"
	   			    		+"\nok,svp placez vous dans une zone couverte et dites ok,TXT100,73"
	   			    		+"\nok,svp veuillez repr.parer les manquants,TXT101,74"
	   			    		+"\nok,erreur le programme ne peut continuer correctement,TXT102,75"
	   			    		+"\nok,invalide,TXT103,76"
	   			    		+"\nok,d l c,TXT104,77"
	   			    		+"\nok,jour,TXT105,78"
	   			    		+"\nok,mois,TXT106,79"
	   			    		+"\nok,annee,TXT107,80"
	   			    		+"\nok,est une date invalide,TXT108,81"
	   			    		+"\nok,est hors tol.rance,TXT109,82"
	   			    		+"\nok,tra.abilite,TXT110,83"
	   			    		+"\nok,plus darticle,TXT112,84"
	   			    		+"\nok,est trop,TXT113,85"
	   			    		+"\nok,changez de support,TXT124,86"
	   			    		+"\nok,lots a pr.parer,TXT129,87"
	   			    		+"\nok,manquant,TXT130,88"
	   			    		+"\nok,terminer la commande,TXT131,89"
	   			    		+"\nok,op.rateur en cours a change,TXT132,90"
	   			    		+"\nok,pr.parateur,TXT139,91"
	   			    		+"\nok,up. restantes,TXT142,92"
	   			    		+"\nok,vous avez un lot affecte,TXT155,93"
	   			    		+"\nok,voulez vous confirmer le refus du lot,TXT156,94"
	   			    		+"\nok,poids total trop gros,TXT157,95"
	   			    		+"\nok,lot en fin de pr.paration,TXT158,96"
	   			    		+"\nok,depose sur,TXT166,97"
	   			    		+"\nok,chiffre de controle,TXT170,98"
	   			    		+"\nok,poids moyen,TXT171,99"
	   			    		+"\nok,poids moyen est trop gros,TXT172,100"
	   			    		+"\nok,poids moyen est trop petit,TXT173,101"
	   			    		+"\nok,num.ro de zone de transfert choisie,TXT174,102"
	   			    		+"\nok,num.ro de zone inconnue,TXT175,103"
	   			    		+"\nok,depose obligatoire,TXT176,104"
	   			    		+"\nok,code op.rateur d.ja utilise,TXT205,105"
	   			    		+"\nok,attention d l c,TXT222,106"
	   			    		+"\nok,attention substitution,TXT226,107"
	   			    		+"\nok,colis pr.par.s total,TXT230,108"
	   			    		+"\nok,colis pr.par.s en picking,TXT231,109"
	   			    		+"\nok,num.ro de lot,TXT232,110"
	   			    		+"\nok,num.ro de lot incorrect,TXT233,111"
	   			    		+"\nok,num.ro de ligne,TXT234,112"
	   			    		+"\nok,ligne inconnue,TXT235,113"
	   			    		+"\nok,ligne d.j. pr.par.e,TXT236,114"
	   			    		+"\nok,il reste des lignes . pr.parer,TXT237,115"
	   			    		+"\nok,palette origine,TXT551,176"
	   			    		+"\nok,quai,TXT552,177"
	   			    		+"\nok,Chariot numero #1#,TXT570,178"
	   			    		+"\nok,Code chariot,TXT571,179"
	   			    		+"\nok,Affectation impossible du chariot,TXT572,180"
	   			    		+"\nok,afficheur,TXT596,181"
	   			    		+"\nok,code detrompeur afficheur,TXT597,182"
	   			    		+"\nok,attention code incorrect,TXT598,183"
	   			    		+"\nok,attention afficheur deja en cours,TXT599,184"
	   			    		+"\nok,code afficheur inexistant,TXT600,185"
	   			    		+"\nok,ramasse terminee,TXT607,186"
	   			    		+"\nok,scannez le ticket client,TXT608,187"
	   			    		+"\nok,il y a des produits manquants,TXT609,188"
	   			    		+"\nok,confirmer adresse,TXT610,189"
	   			    		+"\nok,bac numero,TXT611,190"
	   			    		+"\nok,veuillez appeler un responsable pour continuer,TXT612,191"
	   			    		+"\nok,commande terminee,TXT613,192"
	   			    		+"\nok,code responsable incorrect,TXT614,193"
	   			    		+"\nok,ticket incorrect,TXT615,194"
	   			    		+"\nok,lot en fin de ramasse,TXT616,195"
	   			    		+"\nok,depose support,TXT617,196"
	   			    		+"\nok,attention support,TXT618,197"
	   			    		+"\nok,casier,TXT619,198"
	   			    		+"\nok,attention casier,TXT620,199"
	   			    		+"\nok,bac,TXT621,200"
	   			    		+"\nok,sacs,TXT622,201"
	   			    		+"\nok,chariot #CODE# inconnu,TXT635,202"
	   			    		+"\nok,chariot #CODE# deja en cours,TXT636,203"
	   			    		+"\nok,regroupe,TXT645,204"
	   			    		+"\nok,vous avez un lot regroupe en cours zone,TXT646,205"
	   			    		+"\nok,numero de serie inconnu,TXT647,206"
	   			    		+"\nok,numero deja existant,TXT648,207"
	   			    		+"\nok,il n^y a rien a deposer,TXT671,208"
	   			    		+"\nok,Voulez vous confirmer abandon,TXT672,209"
	   			    		+"\nok,message picking non utilise,TXT673,210"
	   			    		+"\nok,poids #NUM# sur #TOTAL#,TXT674,211"
	   			    		+"\nok,sur,TXT675,212"
	   			    		+"\nok,plus,TXT676,213"
	   			    		+"\nok,client,TXT677,214"
	   			    		+"\nok,volume,TXT678,215"
	   			    		+"\nok,reference a preparer,TXT679,216"
	   			    		+"\nok,prendre,TXT680,217"
	   			    		+"\nok,numero de support,TXT681,218"
	   			    		+"\nok,nombre de colis,TXT682,219"
	   			    		+"\nok,etiquettes,TXT683,220"
	   			    		+"\nok,le support n existe pas,TXT684,221"
	   			    		+"\nok,type du support,TXT685,222"
	   			    		+"\nok,peser,TXT686,223"
	   			    		+"\nok,poids suivant,TXT687,224"
	   			    		+"\nok,litre,TXT688,225"
	   			    		+"\nok,declarer au moins un nouveau support,TXT689,226"
	   			    		+"\nok,attention veuillez saisir un #UNIT#,TXT690,227"
	   			    		+"\nok,support suivant pour,TXT691,228"
	   			    		+"\nok,support vide pour ce client,TXT692,229"
	   			    		+"\nok,probleme chariot #NUM#,TXT699,230"
	   			    		+"\nok,FIN,TXT700,231"
	   			    		+"\nok,MQT,TXT701,232"
	   			    		+"\nok,chariot deja affecte,TXT703,233"
	   			    		+"\nok,Chariot numero,TXT704,234"
	   			    		+"\nok,probleme chariot,TXT705,235"
	   			    		+"\nok,Confirmez la reedition des etiquettes,TXT706,236"
	   			    		+"\nok,num.ro de caisse,TXT707,237"
	   			    		+"\nok,annulation de la caisse,TXT708,238"
	   			    		+"\nok,chariot incoherent,TXT709,239"
	   			    		+"\nok,probleme pour recuperer le lot affecte,TXT710,240"
	   			    		+"\nok,Confirmer la saisie des poids en phase validation,TXT711,241"
	   			    		+"\nok,Date de livraison,TXT737,242"
	   			    		+"\nok,Code tourn.e,TXT738,243"
	   			    		+"\nok,Type de stockage,TXT739,244"
	   			    		+"\nok,Nombre de references,TXT740,245"
	   			    		+"\nok,Nombre de r.f.rences incorrectes #REF#,TXT741,246"
	   			    		+"\nok,Type de chargement,TXT746,247"
	   			    		+"\nok,Type de rupture,TXT747,248"
	   			    		+"\nend,,,\n\n";
	   			   // Ouputmessage="ok,,10.11.40.1\nend,,\n\n";
	   			 //   Ouputmessage="ok,LIBPERF01001,20171214104950,0,0,\nend,,,,\n\n";
	   			    out.write(Ouputmessage);
	   			    out.flush();
	            }
	        } finally {
	        	serverSocket.close();
	        }
	}
	
	
	 
}
class Capitalizer extends Thread 
{
       private Socket socket;
       private int clientNumber;

       public Capitalizer(Socket socket, int clientNumber) {
           this.socket = socket;
           this.clientNumber = clientNumber;
           log("New connection with client# " + clientNumber + " at " + socket);
       }
       public void run() 
       {
           try {
        	   Listen();
           }
       
           catch (IOException e) {
           log("Error handling client# " + clientNumber + ": " + e);
       } finally {
           try {
               socket.close();
           } catch (IOException e) {
               log("Couldn't close a socket, what's going on?");
           }
           log("Connection with client# " + clientNumber + " closed");
       }
   }

 
 private void log(String message) {
     System.out.println(message);
 }
       public void Listen() throws IOException
   	{
   		String inputline;
   		String Ouputmessage;
   	
   			    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
   			    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
   			    log("en cours d'écoute");
   			    while ((inputline = in.readLine()) != null) {
                
   			    	System.out.println(inputline);
                 
   			    }
   			    Ouputmessage="ok,1,0,,,,,,,,,,,,,,,,\nend,,,,,,,,,,,,,,,,,,\n\n";
   			    out.write(Ouputmessage);
   			    out.flush();
   			 
   			 
         
   	 
   	}
}
