package it.unibs.pajc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import it.unibs.pajc.color.Colorizer;
import it.unibs.pajc.menu.Menu;

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
            new Menu(in, out, this);

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

    protected void login() throws IOException {
        sendMsg(null, this, Colorizer.ANSI_GREEN + "Benvenuto sul server WAPP di PAJC:" + Colorizer.ANSI_RESET);

        while (name == null) {
            sendMsg(null, this, "Inserisci il tuo nome utente: ");
            name = in.readLine();
            if (name.length() < 3) {
                sendMsg(null, this,"Il nome deve essere almeno di tre caratteri!");
                name = null;
            }

            synchronized (clientMap) {
                if (clientMap.containsKey(name)) {
                    sendMsg(null,this, "E' già presente questo nome per un client connesso, cambiare nome utente!");
                    name = null;
                }
            }
        }

        clientMap.put(name, this);
        sendMsg(null,this, String.format("Benvenuto %s!\n", name));

    }

    public void chatTo() throws IOException {
        String receiverName = null;
        String msg = null;
        WAProtocollProcessor receiverProcessor = null;
        while (receiverProcessor == null) {
            sendMsg(null,this, "Con chi vuoi comunicare?\n");
            receiverName = in.readLine();
            if(clientMap.containsKey(receiverName)){
                receiverProcessor = clientMap.get(receiverName);
                sendMsg(null,this, "Inserire il messaggio:\n");
                msg = in.readLine();
                sendMsg(null, receiverProcessor, Colorizer.ANSI_BLUE +"Hai un nuovo messagiio:" + Colorizer.ANSI_RESET);
                sendMsg(this, receiverProcessor, msg);
                
            } else {
                receiverName = null ;
                sendMsg(null, this, "Destinatario non trovato. Riprova.\n");
            }
        

        }

    }

    protected void sendMsg(WAProtocollProcessor sender, WAProtocollProcessor receiver, String msg) throws IOException {
        // Ottiene il nome del mittente
        String senderName = (sender != null) ? sender.name : "Server";  // Usa "Server" come nome mittente se è un messaggio del server
        
        if (receiver != null) {
            // Caso: invia il messaggio a un destinatario specifico
            PrintWriter out2 = new PrintWriter(receiver.getClient().getOutputStream(), true);
            out2.printf("[%s] to [%s]: %s\n", senderName, receiver.name, msg);
        } else {
            // Caso: invia il messaggio a tutti i client (broadcast)
            for (WAProtocollProcessor clientProcessor : clientMap.values()) {
                PrintWriter out2 = new PrintWriter(clientProcessor.getClient().getOutputStream(), true);
                out2.printf("[%s] to [ALL]: %s\n", senderName, msg);
            }
        }
    }

    public Socket getClient(){
        return this.client;
    }

}
