package it.unibs.pajc;

import java.net.ServerSocket;
import java.net.Socket;

import it.unibs.pajc.color.Colorizer;

public class WAServer {
    
    public static void main(String[] srgs){
        Colorizer.ANSI_GREEN.print("Avvio del server...\n");

        try(
            ServerSocket server = new ServerSocket(1234);
        ){

            while(true){
                System.out.println("Attesa client...");
                Socket client = server.accept();
                System.out.println("Accettato client!");

                WAProtocollProcessor p = new WAProtocollProcessor(client);
                new Thread(p).start();
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
