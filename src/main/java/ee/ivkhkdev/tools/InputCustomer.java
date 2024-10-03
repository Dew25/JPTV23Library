package ee.ivkhkdev.tools;

import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Customer;

import java.util.Scanner;

public class InputCustomer {
    public Customer newCustomer(Input input){
        Customer customer = new Customer();
        System.out.print("Имя пользователя: ");
        customer.setFirstName(input.nextLine());
        System.out.print("Фамилия пользователя: ");
        customer.setLastName(input.nextLine());
        System.out.print("Телефон: ");
        customer.setPhone(input.nextLine());
        return customer;
    }
}
