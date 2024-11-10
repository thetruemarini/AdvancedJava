package it.unibs.pajc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class WAProtocollProcessor implements Runnable {
    protected Socket client;
    protected BufferedReader in;
    protected PrintWriter out;

    public WAProtocollProcessor(Socket client) {
        this.client = client;
    }

    public void run() {
        try {

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);

            login();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (Exception e) {
                ;
            }
        }
    }

    protected String name = null;
    protected static HashMap<String, WAProtocollProcessor> clientMap = new HashMap<>();

    protected void login() throws IOException{
        sendMsg(null, "Benvenuto sul server WAPP di PAJC");

        while(name == null){
            sendMsg(null, "Inserisci il tuo nome utente: ");
            name = in.readLine();
            if(name.length()<3){
              sendMsg(null, "Il nome deve essere almeno di tre caratteri!");;
              name = null;
            }

            synchronized(clientMap){
                 if(clientMap.containsKey(name)){
                    sendMsg(null, "E' già presente questo nome per un client connesso, cambiare nome utente!");
                    name = null;
                 } 
            }
        }

        clientMap.put(name, this);
        sendMsg(null, String.format("Benvenuto %s!\n", name));
    
    }

    protected void sendMsg (WAProtocollProcessor sender, String msg){
        String senderName = sender != null ? sender.name : "*";
        out.printf("[%s]\t%s\n", senderName, msg);
        out.flush();
    }

    
}
