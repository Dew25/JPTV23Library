package ee.ivkhkdev.factory;

import ee.ivkhkdev.helpers.AuthorAppHelper;
import ee.ivkhkdev.helpers.BookAppHelper;
import ee.ivkhkdev.helpers.LibraryCardAppHelper;
import ee.ivkhkdev.helpers.UserAppHelper;
import ee.ivkhkdev.input.ConsoleInput;
import ee.ivkhkdev.intefaces.AppHelper;
import ee.ivkhkdev.intefaces.Input;
import ee.ivkhkdev.intefaces.Repository;
import ee.ivkhkdev.intefaces.Service;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.LibraryCard;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.repositories.Storage;
import ee.ivkhkdev.services.AuthorService;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.services.LibraryCardService;
import ee.ivkhkdev.services.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JavaConfigurator implements Configurator{

    private Map<String,Object> map = new HashMap<>();

    public JavaConfigurator() {
        try {
            this.map.put("authorRepository", new Storage("authors"));
            this.map.put("userRepository", new Storage<>("users"));
            this.map.put("bookRepository", new Storage<>("books"));
            this.map.put("input", new ConsoleInput(new Scanner(System.in)));
            this.map.put("authorAppHelper", new AuthorAppHelper((Input) map.get("input")));
            this.map.put("userAppHelper", new UserAppHelper((Input) map.get("input")));
            this.map.put("authorService", new AuthorService((AuthorAppHelper) map.get("authorAppHelper"), (Repository<Author>) map.get("authorRepository")));
            this.map.put("userService", new UserService((UserAppHelper) map.get("userAppHelper"), (Repository<User>) map.get("userRepository")));
            this.map.put("bookAppHelper", new BookAppHelper((Input) map.get("input"), (Service<Author>) map.get("authorService")));
            this.map.put("bookService", new BookService((AppHelper<Book>) map.get("bookAppHelper"), (Repository<Book>) map.get("bookRepository")));
            this.map.put("libraryCardAppHelper", new LibraryCardAppHelper((Input) map.get("input"), (Service<Book>) map.get("bookService"),(Service<User>)map.get("userService")));
            this.map.put("libraryCardRepository", new Storage<>("libraryCards"));
            this.map.put("libraryCardService", new LibraryCardService((LibraryCardAppHelper) map.get("libraryCardAppHelper"), (Repository<LibraryCard>) map.get("libraryCardRepository")));
        } catch (Exception e) {
            System.out.println("Ошибка в конфигураторе");
        }
    }

    @Override
    public Map<String, Object> getMap() {
        return map;
    }
}
