package it.unibs.pajc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import it.unibs.pajc.color.Colorizer;
import it.unibs.pajc.menu.Menu;

public class WAProtocollProcessor implements Runnable {
    protected Socket client;
    protected BufferedReader in;
    protected PrintWriter out;
    protected boolean running = true;
    private static HashMap<String, Consumer<WAEvent>> commandMap = new HashMap<>();
    protected static HashMap<String, WAProtocollProcessor> clientMap = new HashMap<>();

    //blocco di inizializzazione statico
    static{
        commandMap.put("!TIME", e -> {
            try {
                e.getSender().sendMsg(null, e.getSender(), LocalDateTime.now().toString());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
       // commandMap.put("!USER_LIST", e -> e.getSender().printClientMap());
        commandMap.put("!USER_LIST", e -> {
            try {
                e.getSender().sendMsg(null, e.getSender(), 
                clientMap.keySet().stream().collect(Collectors.joining(", ")));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        commandMap.put("!SEND", e -> {
            try {
                e.getSender().sendTo(e.getSender(), e.getParameters(0), e.getParameters(1));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        commandMap.put("!SEND_ALL", e -> {
            try {
                e.getSender().sendToAll(e.getSender(), e.getParameters(0));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        commandMap.put("!QUIT", e -> {
            e.getSender().running = false;
            try {
                e.getSender().sendMsg(null, e.getSender(), Colorizer.ANSI_RED + "CHIUSURA CLIENT..." + Colorizer.ANSI_RESET);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

    }



    public WAProtocollProcessor(Socket client) {
        this.client = client;
    }

    public void run() {
        try {

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);

            login();
          //  new Menu(in, out, this);

          String request;
          while(running && (request = in.readLine()) != null){
            WAEvent e= new WAEvent(this, request);
            if(e.command == null){
                sendMsg(null, this, "Commando non riconosciuto!"); 
                continue;
          }

          Consumer<WAEvent> command = commandMap.get(e.getCommand());

          if(command == null){
            sendMsg(null, this, "Comando non valido!");
          } else{
            command.accept(e);
          }
         }
        }
         catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                clientMap.remove(name);
                in.close();
                out.close();
            } catch (Exception e) {
                ;
            }
        }
    }

    protected String name = null;

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
            out2.printf("[%s] to [%s]: %s\n\r", senderName, receiver.name, msg);
        } else {
            // Caso: invia il messaggio a tutti i client (broadcast)
            for (WAProtocollProcessor clientProcessor : clientMap.values()) {
                PrintWriter out2 = new PrintWriter(clientProcessor.getClient().getOutputStream(), true);
                out2.printf("[%s] to [ALL]: %s\n\r", senderName, msg);
            }
        }
    }

    public Socket getClient(){
        return this.client;
    }

    public void printClientMap() throws IOException{
        for(Map.Entry<String, WAProtocollProcessor> entry : clientMap.entrySet()){
            sendMsg(null, this, entry.getKey());
        }
    }

    public void sendTo(WAProtocollProcessor sender, String destName, String msg) throws IOException{ //sostituisce chat to
        WAProtocollProcessor dest = clientMap.get(destName);
        if(dest == null){ sender.sendMsg(null, sender, "Errore, utente non trovato");}
        else dest.sendMsg(sender, dest, msg);

    }

    public void sendToAll(WAProtocollProcessor sender, String msg) throws IOException{
        for(WAProtocollProcessor p : clientMap.values()){
            if(p!= this){
                p.sendMsg(sender, null, msg);
            }
        }

    }

}
