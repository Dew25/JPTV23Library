package ee.ivkhkdev.config;

import ee.ivkhkdev.App;
import ee.ivkhkdev.helpers.AppHelper;
import ee.ivkhkdev.helpers.AppHelperAuthor;
import ee.ivkhkdev.helpers.AppHelperBook;
import ee.ivkhkdev.helpers.AppHelperUser;
import ee.ivkhkdev.input.ConsoleInput;
import ee.ivkhkdev.input.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.repositories.Repository;
import ee.ivkhkdev.repositories.Storage;
import ee.ivkhkdev.services.AuthorService;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.services.Service;
import ee.ivkhkdev.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Scanner;

@Configuration
public class AppConfiguration {

    @Bean
    public Input input() {
        return new ConsoleInput(new Scanner(System.in));
    }
    @Bean
    public Repository<Author> authorRepository() {
        return new Storage<>("authors");
    }
    @Bean
    public Repository<User> userRepository() {
        return new Storage<>("users");
    }
    @Bean
    public Repository<Book> bookRepository() {
        return new Storage<>("books");
    }
    @Bean
    public AppHelper<Author> appHelperAuthor(Input input){
        return new AppHelperAuthor(input);
    }
    @Bean
    public AppHelper<User> appHelperUserr(Input input){
        return new AppHelperUser(input);
    }

    @Bean
    public Service<Author> authorService(AppHelper<Author> appHelperAuthor,Repository<Author> authorRepository){
        return new AuthorService<>(appHelperAuthor, authorRepository);
    }
    @Bean
    public AppHelper<Book> appHelperBook(Input input, Service<Author> authorService){
        return new AppHelperBook(input,authorService);
    }
    @Bean
    public Service<Book> bookService(AppHelper<Book> appHelperBook,Repository<Book> bookRepository){
        return new BookService<>(appHelperBook, bookRepository);
    }
    @Bean
    public Service<User> userService(AppHelper<User> appHelperUser,Repository<User> userRepository){
        return new UserService<>(appHelperUser, userRepository);
    }
    @Bean
    public App app(Input input,Service<User> userService,Service<Book> bookService,Service<Author> authorService){
        return new App(input, userService, bookService, authorService);
    }


}
