package it.unibs.pajc.shape;

public class Circle implements Shape2D{
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }


    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return Math.PI * radius * 2;
    }

    @Override
    public String draw() {
        return "Circle";
    }

    @Override
    public String toString() {
        return "Circle [radius=" + radius + "]";
    }
}
