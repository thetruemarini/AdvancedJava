package it.unibs.pajc;

import java.net.ServerSocket;
import java.net.Socket;

import it.unibs.pajc.color.Colorizer;

public class WAServer {
    public static final Colorizer ANSI_RED = new Colorizer("\033[0;31m"); // RED
    public static void main(String[] srgs){
        System.out.println(ANSI_RED + "Avvio del server...");

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
