package ee.ivkhkdev;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Input scanner = new ConsoleInput(new Scanner(System.in));
        System.out.println("JPTV23Library");
        App app = new App(scanner);
        app.run();
    }
}