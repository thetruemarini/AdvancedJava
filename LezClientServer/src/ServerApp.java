
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    public static void main(String[] args) throws Exception {
        System.out.println("avvio del server...");
        int nclient = 1;

        try (
                ServerSocket server = new ServerSocket(1234);
            
                ) {

                    while(true){
                        System.out.printf("attesa del nuovo client... numero %d\n", nclient);

                        Socket client = server.accept();
                        ProtocolProcessor p = new ProtocolProcessor(client);
                        // p.run();
                        Thread t = new Thread(p);
                        t.start();
                    }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("Chiusura server...");
    }
}
