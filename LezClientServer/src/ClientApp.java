import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApp {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1234;

        try{

            Socket client = new Socket(host, port);
            System.out.println("Client connesso!");
            
            ExecutorService exec = Executors.newFixedThreadPool(2);
            exec.submit(()-> clientToServer(client));
            exec.submit(()-> serverToClient(client));




        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    protected static void clientToServer(Socket client){
        try{

            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

            String line;

            while((line = stdin.readLine()) != null){
                out.println(line);
            }

        }catch(Exception ex){

        }
    }

    protected static void serverToClient(Socket client){
        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String response;
            while((response= in.readLine())!= null){
                System.out.printf(">%s\n", response);
            }
            
        } catch (Exception ex) {
            
        }
    }
}
