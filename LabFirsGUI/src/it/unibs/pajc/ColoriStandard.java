package it.unibs.pajc;

import java.awt.Color;

public enum ColoriStandard {
	
		Rosso(Color.red), Verde(Color.green), Blu(Color.blue);

	    private Color color;

	    private ColoriStandard(Color c){
	        this.color = c;
	    }
	    
	    public Color getColor(){
	        return color;
	    }
}
