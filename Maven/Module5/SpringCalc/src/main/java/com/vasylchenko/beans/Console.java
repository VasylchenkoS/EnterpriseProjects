package com.vasylchenko.beans;

import com.vasylchenko.calclibrary.sources.Calculator;
import com.vasylchenko.annotation.Loggable;

import java.text.ParseException;
import java.util.Scanner;

public class Console {

    @Loggable
    public void readData(Calculator calculator) {
        System.out.println("[INFO:] Hi you! I`m a test calculator." +
                "\nI know only few operators, among them: +,-,*,/\n" +
                "But it`s not a big problem to add some new functions for you? Sure, if you want it =)");
        System.out.println("[INFO:] Please enter yours request in format <operator, number, number>");
        Scanner scanner = new Scanner(System.in);
        String currentLine = scanner.nextLine();
        while (!currentLine.equals("0")) {
            try {
                String answer = calculator.calculate(currentLine);
                System.out.println("[INFO:] Hm...Let me think a little...");
                Thread.sleep(500);
                System.out.println("The answer is :" + answer);
            } catch (InterruptedException | ParseException e) {
                e.printStackTrace();
            }
            System.out.println("[INFO:] Enter \"0\" to exit");
            currentLine = scanner.nextLine();
        }
        System.out.println("[INFO:] Thanks for using me. Come more! =)");
    }
}