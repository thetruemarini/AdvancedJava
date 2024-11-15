package it.unibs.pajc;

import java.util.ArrayList;

public class WAEvent {
    String command;
    ArrayList <String> parameters = new ArrayList<>();
    WAProtocollProcessor sender;

    public WAEvent(WAProtocollProcessor sender, String msg){
        this.sender = sender;
        if (msg.startsWith("!")){

            String[] tokens = msg.split("@");
            this.command = tokens[0].toUpperCase();
            for (int i =1; i<tokens.length; i++){
                this.parameters.add(tokens[i]);
            }

        } else {
            this.command = null;
            parameters.add(msg);
        }

    }

    public String getCommand(){
        return this.command;
    }

    public WAProtocollProcessor getSender(){
        return this.sender;
    }

    public String getParameters(int indx){
        return this.parameters.get(indx);
    }

}
