package it.unibs.pajc.shape;

public class Rectangle implements Shape2D {
     double width;
     double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public double perimeter() {
        return (width + height) * 2;
    }

    @Override
    public String draw() {
        return "Rectangle";
    }

    @Override
    public String toString() {
        return "Rectangle [width=" + width + ", height=" + height + "]";
    }

}
