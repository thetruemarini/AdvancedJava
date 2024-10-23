package it.unibs.pajc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CalcModel {
    
    private HashMap<String, Operator> operators = new HashMap<>();

    public CalcModel(){
        operators.put("+", (x, y) -> x + y);
        operators.put("-", (x, y) -> x - y);
        operators.put("*", (x, y) -> x * y);
        operators.put("/", (x, y) -> x / y);
        operators.put("^", Math::pow);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<String> listOperators(){
        return new ArrayList(operators.keySet());
    }
}
