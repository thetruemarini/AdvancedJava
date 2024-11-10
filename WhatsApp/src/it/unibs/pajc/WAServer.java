package it.unibs.pajc;

import java.net.ServerSocket;
import java.net.Socket;

public class WAServer {
    public static void main(String[] srgs){
        System.out.println("Avvio del server...");

        try(
            ServerSocket server = new ServerSocket(1234);
        ){

            while(true){
                System.out.println("Attesa client...");
                Socket client = server.accept();

                WAProtocollProcessor p = new WAProtocollProcessor(client);
                new Thread(p).start();
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
