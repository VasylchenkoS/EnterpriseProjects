package com.vasilchenko;

import com.vasilchenko.implementations.calc.SimpleCalcDouble;
import com.vasilchenko.implementations.calc.SimpleCalcFloat;
import com.vasilchenko.implementations.calc.SimpleCalcInteger;
import com.vasilchenko.implementations.calc.SimpleCalcLong;
import com.vasilchenko.interfaces.SimpleCalc;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Calculate {

    private SimpleCalc simpleCalc;

    public Calculate() {
    }

    public Calculate(SimpleCalc simpleCalc) {
        this.simpleCalc = simpleCalc;
    }

    public void setSimpleCalc(SimpleCalc simpleCalc) {
        this.simpleCalc = simpleCalc;
    }

    public String start(String expression) throws Exception {
        expression = expression.replace(" ", "");
        StringTokenizer stringTokenizer = new StringTokenizer(expression, ",");
        String operator = stringTokenizer.nextToken();
        String objectA = stringTokenizer.nextToken();
        String objectB = stringTokenizer.nextToken();

        if (simpleCalc == null) {
            if (objectA.matches("\\d+\\.\\d+") || objectB.matches("\\d+\\.\\d+")) {
                return new SimpleCalcDouble().
                        evaluate(operator, Double.valueOf(objectA), Double.valueOf(objectB));
            } else if (objectA.matches("\\d") & objectB.matches("\\d")) {
                return new SimpleCalcInteger().
                        evaluate(operator, Integer.valueOf(objectA), Integer.valueOf(objectB));
            } else if (objectA.matches("\\d+\\.\\d+[fF]") || objectB.matches("\\d+\\.\\d+[fF]")) {
                return new SimpleCalcFloat().
                        evaluate(operator, Float.valueOf(objectA), Float.valueOf(objectB));
            } else try {
                return new SimpleCalcLong().
                        evaluate(operator, Long.valueOf(objectA), Long.valueOf(objectB));
            } catch (IllegalArgumentException ignored) {
                throw new IllegalArgumentException("[Error:] Unregistered operation");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return simpleCalc.evaluate(operator, Double.valueOf(objectA), Double.valueOf(objectB));
        }
    }

    public void init() throws InterruptedException {
        System.out.println("[INFO:] Hi you! I`m a test calculator." +
                "\nI know only few operators, among them: +,-,*,/,^\n" +
                "But it`s not a big problem to add some new functions for you? Sure, if you want it =)");
        System.out.println("[INFO:] Please enter yours request in format <operator, number, number>");
        Scanner scanner = new Scanner(System.in);
        String answer = null;
        try {
            answer = start(scanner.nextLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("[INFO:] Hm...Let me think a little...");
        Thread.sleep(500);
        System.out.println("The answer is :" + answer);
        System.out.println("[INFO:] Thanks for using me. Come more! =)");
    }
}

