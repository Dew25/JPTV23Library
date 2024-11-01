package ee.ivkhkdev;

import ee.ivkhkdev.intefaces.AppHelper;
import ee.ivkhkdev.helpers.AuthorAppHelper;
import ee.ivkhkdev.helpers.BookAppHelper;
import ee.ivkhkdev.helpers.UserAppHelper;
import ee.ivkhkdev.input.ConsoleInput;
import ee.ivkhkdev.intefaces.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.intefaces.Repository;
import ee.ivkhkdev.repositories.Storage;
import ee.ivkhkdev.services.AuthorService;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.intefaces.Service;
import ee.ivkhkdev.services.UserService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Repository<Author> authorRepository = new Storage<>("authors");
        Repository<User> userRepository = new Storage<>("users");
        Repository<Book> bookRepository = new Storage<>("books");
        Input input = new ConsoleInput(new Scanner(System.in));
        AppHelper<Author> appHelperAuthor = new AuthorAppHelper(input);
        AppHelper<User> appHelperUser = new UserAppHelper(input);
        Service<Author> authorService = new AuthorService(appHelperAuthor,authorRepository);;
        AppHelper appHelperBook = new BookAppHelper(input,authorService);
        Service<User> userService = new UserService(appHelperUser,userRepository);
        Service<Book> bookService = new BookService(appHelperBook,bookRepository);

        App app = new App(input, bookService,userService,authorService);
        app.run();
    }
}