package ee.ivkhkdev;

import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.interfaces.impl.ConsoleInput;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Input input = new ConsoleInput(new Scanner(System.in));
        App app = new App(input);
        app.run();
    }
}