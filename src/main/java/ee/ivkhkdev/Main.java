package ee.ivkhkdev;

import ee.ivkhkdev.factory.Factory;
import ee.ivkhkdev.helpers.LibraryCardAppHelper;
import ee.ivkhkdev.intefaces.AppHelper;
import ee.ivkhkdev.helpers.AuthorAppHelper;
import ee.ivkhkdev.helpers.BookAppHelper;
import ee.ivkhkdev.helpers.UserAppHelper;
import ee.ivkhkdev.input.ConsoleInput;
import ee.ivkhkdev.intefaces.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.LibraryCard;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.intefaces.Repository;
import ee.ivkhkdev.repositories.Storage;
import ee.ivkhkdev.services.AuthorService;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.intefaces.Service;
import ee.ivkhkdev.services.LibraryCardService;
import ee.ivkhkdev.services.UserService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Factory factory = Factory.getInstance();
        Repository<Author> authorRepository = factory.getObject("authorRepository");
        Repository<User> userRepository = factory.getObject("userRepository");
        Repository<Book> bookRepository = factory.getObject("bookRepository");
        Input input = factory.getObject("input");
        AppHelper<Author> authorAppHelper = factory.getObject("authorAppHelper");
        AppHelper<User> userAppHelper = factory.getObject("userAppHelper");
        Service<Book> bookService = factory.getObject("bookService");
        Service<Author> authorService = factory.getObject("authorService");
        Service<User> userService = factory.getObject("userService");
        AppHelper<LibraryCard> libraryCardAppHelper = factory.getObject("libraryCardAppHelper");
        Repository<LibraryCard> libraryCardRepository = factory.getObject("libraryCardRepository");
        Service<LibraryCard> libraryCardService = factory.getObject("libraryCardService");

        App app = new App(input, bookService,userService,authorService,libraryCardService);
        app.run();
    }
}