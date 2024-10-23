package it.unibs.pajc.shape;

public class Square extends Rectangle{

    public Square(double size) {
        super(size, size);
    }

    public String draw(){
        return "Square";
    }

    @Override
    public String toString() {
        return "Square: [size=" + width + "]";
    }
}
