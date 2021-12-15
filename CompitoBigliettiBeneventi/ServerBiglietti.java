import java.io.*;
import java.net.*;
import java.util.*;

public class ServerBiglietti{
    ServerSocket server =null;
    Socket client =null;
    String stringaRicevuta= null;
    String stringaModificata=null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;


    public Socket attendi(){
        try{
            System.out.println("SERVER partito in esecuzione");
            server =new ServerSocket(6789);
            client = server.accept();
            server.close();
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient= new DataOutputStream(client.getOutputStream());
            outVersoClient.writeBytes("Connessione riuscita"+'\n'); 

        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server!");
        }
        return client;
    }

    public void comunica(){
        try{
                int conta=5;
            for(;;){
            outVersoClient.writeBytes("Per visualizzare la disponibilita' dei biglietti digitare D, per acquistare un biglietto digitare A"+'\n');
          
              stringaRicevuta= inDalClient.readLine();
            if(stringaRicevuta.equals("D")){
                outVersoClient.writeBytes("i biglietti disponibili sono: "+ conta + '\n');
            }
            if(stringaRicevuta.equals("A") && conta>0)
                    {
                         outVersoClient.writeBytes("biglietto acquistato" + '\n');
                    conta--;
                  }
            if(conta==0){
            
              outVersoClient.writeBytes("biglietti esauriti " + '\n');
              server.close();
        }
    }
 }       catch(Exception e){
             System.out.println(e.getMessage());
             System.out.println("Errore durante la comunicazione con il client!");
             System.exit(1);
        }
    }
    public static void main( String[] args )
    {
        ServerBiglietti servente = new ServerBiglietti();
        servente.attendi();
        servente.comunica();
    }

}