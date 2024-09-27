package it.unibs.pajc.shape;

public class Diamond implements Shape2D {
    double dmin;
    double dmax;

    public Diamond(double dmin, double dmax) {
        this.dmin = dmin;
        this.dmax = dmax;
    }

    @Override
    public double area() {
        return dmin*dmax;
    }

    @Override
    public double perimeter() {
        return (dmin + dmax) * 2 ;
    }

    @Override
    public String draw() {
        return "Diamond";
    }

    @Override
    public String toString() {
        return "Diamond [dmin=" + dmin + ", dmax=" + dmax + "]";
    }
}

