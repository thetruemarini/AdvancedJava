package it.unibs.pajc;

import it.unibs.pajc.shape.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BiomondoApp {

    public static void main(String[] args) {
        ArrayList<Shape2D> shapesList = new ArrayList<>();
        HashMap<String, Integer> shapesMap = new HashMap<String, Integer>();

        shapesList.add(new Circle(10.3));
        shapesList.add(new Rectangle(2, 3));
        shapesList.add(new Square(5.2));
        shapesList.add(new Circle(13));
        shapesList.add(new Diamond(4, 5));

        for(Shape2D shape : shapesList) {
            if(shapesMap.containsKey(shape.getClass().getSimpleName())) {
                int numberObjects = shapesMap.get(shape.getClass().getSimpleName());
                shapesMap.put(shape.getClass().getSimpleName(), numberObjects+1);
            } else {
                shapesMap.put(shape.getClass().getSimpleName(), 1);
            }
        }

        for(Shape2D shape : shapesList) {
            System.out.println("Shape: " + shape + "\t[" + shape.getClass().getSimpleName() +"]");
        }

        for(Map.Entry<String, Integer> kv : shapesMap.entrySet()) {
            System.out.println(kv.getKey() + ": " + kv.getValue());
        }
    }
}
