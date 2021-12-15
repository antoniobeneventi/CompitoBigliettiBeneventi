import java.io.*;
import java.net.*;

public class ClientBiglietti {
    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket miosocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;

    public Socket connetti() {
        System.out.println("CLIENT partito in esecuzione...");
        try {
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            miosocket = new Socket(nomeServer, portaServer);
            outVersoServer = new DataOutputStream(miosocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
            stringRicevutaDalServer = inDalServer.readLine();
            System.out.println("server: " + stringRicevutaDalServer);
        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione!");
            System.exit(1);
        }
        return miosocket;
    }

    public void comunica() {
        try {
            for (;;) {
             
                stringRicevutaDalServer = inDalServer.readLine();
                System.out.println("server: " + stringRicevutaDalServer);
                stringaUtente = tastiera.readLine();
                System.out.println("invio la stringa al server ");
                outVersoServer.writeBytes(stringaUtente + '\n');
                  
                stringRicevutaDalServer = inDalServer.readLine();
                    System.out.println("server: " + stringRicevutaDalServer);
                }
              
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione con il server!");
            System.exit(1);
        }

    }

    public static void main(String[] args) {
        ClientBiglietti cliente = new ClientBiglietti();
        cliente.connetti();
        cliente.comunica();
    }

}