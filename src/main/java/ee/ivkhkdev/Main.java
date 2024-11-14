package ee.ivkhkdev;

import ee.ivkhkdev.factory.Factory;
import ee.ivkhkdev.factory.JavaConfiguration;
import ee.ivkhkdev.intefaces.AppHelper;
import ee.ivkhkdev.helpers.AuthorAppHelper;
import ee.ivkhkdev.helpers.BookAppHelper;
import ee.ivkhkdev.helpers.UserAppHelper;
import ee.ivkhkdev.intefaces.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.LibraryCart;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.intefaces.Repository;
import ee.ivkhkdev.repositories.Storage;
import ee.ivkhkdev.services.AuthorService;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.intefaces.Service;
import ee.ivkhkdev.services.UserService;

public class Main {

    public static void main(String[] args) {
        //Реализуем шаблон "ServiceLocator"
        Factory factory = Factory.getInstance(new JavaConfiguration());
        Repository<Author> authorRepository = (Storage) factory.getObject("authorRepository");
        Repository<User> userRepository = (Storage) factory.getObject("authorRepository");
        Repository<Book> bookRepository = (Storage) factory.getObject("authorRepository");
        Input input = (Input) factory.getObject("input");
        AppHelper<Author> authorAppHelper = (AuthorAppHelper) factory.getObject("authorAppHelper");
        AppHelper<User> userAppHelper = (UserAppHelper) factory.getObject("userAppHelper");
        Service<Author> authorService = (AuthorService) factory.getObject("authorService");
        AppHelper<Book> bookAppHelper =(BookAppHelper) factory.getObject("bookAppHelper");
        Service<User> userService =(UserService) factory.getObject("userService");
        Service<Book> bookService = (BookService) factory.getObject("bookService");
        Repository<LibraryCart> libraryCartRepository = (Storage) factory.getObject("libraryCartRepository");
        Service<LibraryCart> libraryCardService = (BookService) factory.getObject("bookService");

        ((App)factory.getObject("app")).run();
    }
}