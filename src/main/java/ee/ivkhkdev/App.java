package ee.ivkhkdev;

import ee.ivkhkdev.helpers.AppInputHelper;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.services.UserService;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.storages.Storage;

import java.util.ArrayList;
import java.util.List;

public class App {
    private Input input;
    public List<User> users = new ArrayList<>();
    public List<Book> books = new ArrayList<>();
    private AppInputHelper appInputHelper;
    private Storage<User> storageUser;
    private UserService userService;
    private Storage<Book> storageBook;
    private BookService bookService;

    // Теперь в конструктор передается Input вместо Scanner
    public App(Input input) {
        this.input = input;
        appInputHelper = new AppInputHelper(input);
        storageUser = new Storage<>("users");
        userService = new UserService(appInputHelper,storageUser);
        storageBook = new Storage<>("books");
        bookService = new BookService(appInputHelper,storageBook);
    }

    public void run() {
        boolean repeat = true;
        System.out.println("======= JPTV23Library =========");
        do {
            System.out.println("Список задач:");
            System.out.println("0. Выйти из программы");
            System.out.println("1. Добавить пользователя");
            System.out.println("2. Список пользователей");
            System.out.println("3. Добавить книгу");
            System.out.print("Введите номер задачи: ");
            int task = Integer.parseInt(input.nextLine()); // Используем input
            switch (task) {
                case 0:
                    System.out.println("Выход из программы");
                    repeat = false;
                    break;
                case 1:
                    System.out.println("1. Добавить пользователя");
                        if(userService.add(users)){
                            System.out.println("Пользователь добавлен");
                        }else{
                            System.out.println("Пользователя добавить не удалось");
                        };
                    break;
                case 2:
                    if(userService.printList(users)){
                        System.out.println("----------- Конец списка -----------");
                    }
                    break;
                case 3:
                    System.out.println("1. Добавить книгу");
                    if(bookService.add(books)){
                        System.out.println("Книга добавлена");
                    }else{
                        System.out.println("Книгу добавить не удалось");
                    };
                    break;
                default:
                    System.out.println("Выберите номер из списка задач!");
                    break;
            }
            System.out.println("==============================");
        } while (repeat);
        System.out.println("До свидания! :)");
    }
}
