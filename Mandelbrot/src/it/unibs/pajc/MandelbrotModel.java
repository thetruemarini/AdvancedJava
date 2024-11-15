package it.unibs.pajc;

public class MandelbrotModel {
    private double[][] data;

    public double[][] getData() {
        return data.clone(); // clone è utile per i threads
    }

    public void eval(Complex min, Complex max, int res) {
        data = new double[res][res];

        double dre = Math.abs(min.re - max.re) / res;
        double dim = Math.abs(min.im - max.im) / res;

        for (int i = 0; i < res; i++) {
            for (int j = 0; j < res; j++) {
                Complex c = new Complex(min.re * j * dre, min.im + i * dim);

                data[i][j] = fMandelbrot(c);
            }
        }
    }

    private static double fMandelbrot(Complex c) {
        int maxi = 100;

        Complex z = new Complex(c.re, c.im);

        for (int i = 0; i < maxi; i++) {
            z = z.sqr().sum(c);
            if (z.module2() > 1e5)
                return (maxi - i) / (double) maxi;
        }

        return 0.;
    }
}
