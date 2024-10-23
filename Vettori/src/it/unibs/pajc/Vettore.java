package it.unibs.pajc;

import java.util.function.UnaryOperator;

public class Vettore {
    private int nel;
    private double values[];

    public Vettore(int nel){
        this.nel = nel;
        this.values = new double[nel];
    }


    public Vettore faiPerTutti(UnaryOperator<Double> operator){
        for(int i=0; i<nel ;i++)
                values[i] = operator.apply(values[i]);
        return this;
    }

    public Vettore zeros(){
        /* for(int i=0; i<nel ;i++)
                values[i] = 0;
        return this; */

        return faiPerTutti((v) -> 0.); //lambda expression per fare il loop sopra
    }

    public Vettore ones(){
       /*  for(int i=0; i<nel ;i++)
                values[i] = 1;
        return null; */

        return faiPerTutti((v) -> 1.); 
    }

    public Vettore add(double a){
        /* for(int i=0; i<nel ;i++)
                values[i] = values[i] + v;
        return null; */

        return faiPerTutti((v) -> v + a); 
    }

    public String toString(){
        return null;
    }
    
    //TODO definire il met toString
    //TODO estendere la classe con gli operatori e operazioni tra vettori
    //enjoy
}
