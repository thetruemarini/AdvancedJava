package it.unibs.pajc.operations;

public class Divided implements Operation {

    @Override
    public double calcola(double x, double y) {
        if (y!=0)
        return x / y;
        else throw new ArithmeticException("Divide by zero");
    }
}
