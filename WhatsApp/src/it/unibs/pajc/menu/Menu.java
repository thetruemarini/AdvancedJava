package it.unibs.pajc.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import it.unibs.pajc.WAProtocollProcessor;

public class Menu {
    private String s;

    public Menu(BufferedReader in, PrintWriter out, WAProtocollProcessor sender) throws IOException{
        boolean doWhile = true;
        while(doWhile){
            out.println("MENU DEL SERVER:\n Cosa vuoi fare?\n1)\tScrivere a qualcuno");
            int x = 0;
            try {
                x = Integer.parseInt(in.readLine());
            } catch (NumberFormatException e) {
                // Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // Auto-generated catch block
                e.printStackTrace();
            }
            switch (x) {
                case 1:
                    sender.chatTo();
                  break;
            
              default:
                    out.println("ERROR");
                  break;
         }

        }
        
    }

    public void closeMenu(){
        //TODO implement close menu
    }

    //TODO implement GUI of the menu

    // TODO adjust the showing messages/menu feature

    public String toString(){
            return s;
    }


}
