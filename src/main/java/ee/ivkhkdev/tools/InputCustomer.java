package ee.ivkhkdev.tools;

import ee.ivkhkdev.model.Customer;

import java.util.Scanner;

public class InputCustomer {
    public Customer newCustomer(){
        Customer customer = new Customer();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Имя пользователя: ");
        customer.setFirstName(scanner.nextLine());
        System.out.print("Фамилия пользователя: ");
        customer.setFirstName(scanner.nextLine());
        System.out.print("Телефон: ");
        customer.setFirstName(scanner.nextLine());
        return customer;
    }
}