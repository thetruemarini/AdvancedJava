import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ProtocolProcessor implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;

    public ProtocolProcessor(Socket client){
        this.client = client;
    }

    public void run (){
        try {
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.printf("Client connesso: %s: %d", client.getInetAddress(), client.getPort());
            out.println("Benvenuto nel server PAJC");
            
            String request;
            while((request = in.readLine()) != null){

                System.out.printf("Request: %s\n", request);
                String response = request.toUpperCase();

                if("!QUIT".equals(response)){
                    out.println("torna a trovarmi!");
                    break;
                }

                out.println(response);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
