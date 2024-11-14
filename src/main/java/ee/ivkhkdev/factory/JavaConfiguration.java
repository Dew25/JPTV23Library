package ee.ivkhkdev.factory;

import ee.ivkhkdev.App;
import ee.ivkhkdev.helpers.AuthorAppHelper;
import ee.ivkhkdev.helpers.BookAppHelper;
import ee.ivkhkdev.helpers.LibraryCartAppHelper;
import ee.ivkhkdev.helpers.UserAppHelper;
import ee.ivkhkdev.input.ConsoleInput;
import ee.ivkhkdev.intefaces.AppHelper;
import ee.ivkhkdev.intefaces.Input;
import ee.ivkhkdev.intefaces.Repository;
import ee.ivkhkdev.intefaces.Service;
import ee.ivkhkdev.repositories.Storage;
import ee.ivkhkdev.services.AuthorService;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.services.LibraryCartService;
import ee.ivkhkdev.services.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JavaConfiguration implements Configuration{
    private Map<String,Object> map = new HashMap<>();

    public JavaConfiguration() {
        init();
    }
    private void init(){
        try {
            this.map.put("authorRepository",new Storage<>("authors"));
            this.map.put("userRepository",new Storage<>("users"));
            this.map.put("bookRepository",new Storage<>("books"));
            this.map.put("input",new ConsoleInput(new Scanner(System.in)));
            this.map.put("authorAppHelper",new AuthorAppHelper((Input)map.get("input")));
            this.map.put("userAppHelper",new UserAppHelper((Input)map.get("input")));
            this.map.put("authorService",new AuthorService((AppHelper)map.get("authorAppHelper"),(Repository)map.get("authorRepository")));
            this.map.put("bookAppHelper",new BookAppHelper((Input)map.get("input"),(AuthorService)map.get("authorService")));
            this.map.put("userService",new UserService((AppHelper)map.get("userAppHelper"),(Repository)map.get("userRepository")));
            this.map.put("bookService",new BookService((BookAppHelper)map.get("bookAppHelper"),(Repository)map.get("bookRepository")));
            this.map.put("libraryCartAppHelper",new LibraryCartAppHelper((Input)map.get("input"),(BookService)map.get("bookService"),(UserService)map.get("userService")));
            this.map.put("libraryCartRepository",new Storage<>("libraryCarts"));
            this.map.put("libraryCardService",new LibraryCartService((LibraryCartAppHelper) map.get("libraryCartAppHelper"), (Repository) map.get("libraryCartRepository")));
            this.map.put("app", new App((Input)map.get("input"),(Service)map.get("bookService"),(Service) map.get("userService"),(Service)map.get("authorService"), (Service)map.get("libraryCartService")));
        }catch (Exception e){
            System.out.println("Ошибка в конфиге");
        }
    }

    @Override
    public Map<String, Object> getMap() {
        try {

        }catch (Exception e){
            throw new RuntimeException("Нет такого класса");
        }
        return this.map;
    }
}
