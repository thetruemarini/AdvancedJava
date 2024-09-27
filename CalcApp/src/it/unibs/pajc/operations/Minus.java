package it.unibs.pajc.operations;

public class Minus implements Operation{

    @Override
    public double calcola(double x, double y) {
        return x - y;
    }
}
