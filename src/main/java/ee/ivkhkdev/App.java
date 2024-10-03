package ee.ivkhkdev;

import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Customer;
import ee.ivkhkdev.service.CustomizerService;

import java.beans.Customizer;

public class App {
    private Input input;
    public static Customer[] customers = new Customer[100];

    // Теперь в конструктор передается Input вместо Scanner
    public App(Input input) {
        this.input = input;
    }

    public void run() {
        boolean repeat = true;
        do {
            System.out.println("Список задач:");
            System.out.println("0. Выйти из программы");
            System.out.println("1. Добавить пользователя");
            System.out.print("Введите номер задачи: ");
            int task = Integer.parseInt(input.nextLine()); // Используем input
            switch (task) {
                case 0:
                    System.out.println("Выход из программы");
                    repeat = false;
                    break;
                case 1:
                    System.out.println("1. Добавить пользователя");
                        CustomizerService customizerService = new CustomizerService();
                        customizerService.createCustomer(input);
                    break;
                default:
                    System.out.println("Выберите номер из списка задач!");
                    break;
            }
        } while (repeat);
        System.out.println("До свидания! :)");
    }
}
