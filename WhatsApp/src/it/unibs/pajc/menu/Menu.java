package it.unibs.pajc.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import it.unibs.pajc.WAProtocollProcessor;

public class Menu {
    private String s;

    public Menu(BufferedReader in, PrintWriter out, WAProtocollProcessor sender) throws IOException{
        while(true){
            out.println("MENU DEL SERVER:\n Cosa vuoi fare?\n1)\tScrivere a qualcuno");
            int x = Integer.parseInt(in.readLine());
            switch (x) {
                case 1:
                    sender.chatTo();
                  break;
            
              default:
                  break;
         }

        }
        
    }

    public String toString(){
            return s;
    }
}
