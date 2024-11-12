package it.unibs.pajc.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Menu {
    private String s;

    public Menu(BufferedReader in, PrintWriter out) throws IOException{
        while(true){
                out.println("MENU DEL SERVER:\n Cosa vuoi fare?\n1)\tScrivere a qualcuno");
            int x = Integer.parseInt(in.readLine());
            switch (x) {
                case 1:
                    chatTo(in, out);
                   break;
            
              default:
                  break;
         }

        }
        
    }

    public void chatTo(BufferedReader in, PrintWriter out) throws IOException{
        out.println("A chi vuoi scrivere?");
        String reciverName = in.readLine();
        
    }

    public String toString(){
            return s;
    }
}
