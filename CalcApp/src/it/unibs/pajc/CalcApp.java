package it.unibs.pajc;

import it.kibo.fp.lib.InputData;
import it.unibs.pajc.operations.*;

import java.util.HashMap;

public class CalcApp {

    public static final String LEGGERE_VALORE = "Inserire il valore di A:";
    public static final String INSERIRE_L_OPERATORE = "Inserire l'operatore:";

    public static void main(String[] args) {
        double a = InputData.readDouble(LEGGERE_VALORE);
        String op = InputData.readString(INSERIRE_L_OPERATORE, false);
        double b = InputData.readDouble(LEGGERE_VALORE);
        double res = 0;

        HashMap<String, Operation> operations = new HashMap<>();
        operations.put("+", new Sum());
        operations.put("-", new Minus());
        operations.put("*", new For());
        operations.put("/", new Divided());

        res = operations.get(op).calcola(a, b);

        System.out.println(res);



    }
}