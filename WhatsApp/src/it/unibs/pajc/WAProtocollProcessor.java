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
            Menu menu = new Menu(in, out);

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
        sendMsg(null, null, Colorizer.ANSI_GREEN + "Benvenuto sul server WAPP di PAJC:" + Colorizer.ANSI_RESET);

        while (name == null) {
            sendMsg(null, this, "Inserisci il tuo nome utente: ");
            name = in.readLine();
            if (name.length() < 3) {
                sendMsg(null, this,"Il nome deve essere almeno di tre caratteri!");
                ;
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
        sendMsg(null,null, String.format("Benvenuto %s!\n", name));

    }

    protected void chatTo() throws IOException {
        String reciverName = null;
        String msg = null;
        WAProtocollProcessor reciverProcessor = null;
        sendMsg(null,null, "Con chi vuoi comunicare?\n");
        while (reciverProcessor == null) {
            if(clientMap.containsKey(reciverName)){
                reciverProcessor = clientMap.get(reciverName);
                sendMsg(null,null, "Inserire il messaggio:\n");
                msg = in.readLine();
            } else ;

            

        }

    }

    protected void sendMsg(WAProtocollProcessor sender, WAProtocollProcessor reciver, String msg) {
        String senderName = sender != null ? sender.name : "*";
        String reciverName = reciver != null ? reciver.name : "*";
        out.printf("[%s] to [%s]\t%s\n", senderName, reciverName, msg);
        out.flush();
    }

}
