package it.unibs.pajc.operations;

public class Sum implements Operation{

    @Override
    public double calcola(double x, double y) {
        return x + y;
    }
}
