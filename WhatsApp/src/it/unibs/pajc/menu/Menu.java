package it.unibs.pajc.menu;

import it.unibs.pajc.WAProtocollProcessor;

public class Menu {
    private String s;
    private WAProtocollProcessor clienProcessor;

    public Menu(){
        System.out.println("MENU DEL SERVER:\n Cosa vuoi fare?");
    
    }

    public void chatTo(){
        System.out.println("1)\tVuoi scrivere a qualcuno?");
    }

    public String toString(){
            return s;
    }
}
