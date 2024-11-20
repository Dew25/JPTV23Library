package ee.ivkhkdev.configuration;

import ee.ivkhkdev.helpers.AuthorAppHelper;
import ee.ivkhkdev.helpers.BookAppHelper;
import ee.ivkhkdev.helpers.LibraryCartAppHelper;
import ee.ivkhkdev.helpers.UserAppHelper;
import ee.ivkhkdev.input.ConsoleInput;
import ee.ivkhkdev.intefaces.AppHelper;
import ee.ivkhkdev.intefaces.AppRepository;
import ee.ivkhkdev.intefaces.AppService;
import ee.ivkhkdev.intefaces.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.LibraryCart;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.repositories.Storage;
import ee.ivkhkdev.services.AuthorService;
import ee.ivkhkdev.services.BookAppService;
import ee.ivkhkdev.services.LibraryCartAppService;
import ee.ivkhkdev.services.UserAppService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class AppConfiguration {

    @Bean
    public Input input(){
        return new ConsoleInput(new Scanner(System.in));
    }
    @Bean
    public AppRepository<Author> authorRepository(){
        return new Storage<Author>("authors");
    }
    @Bean
    public AppRepository<Book> bookRepository(){
        return new Storage<Book>("books");
    }
    @Bean
    public AppRepository<User> userRepository(){
        return new Storage<User>("users");
    }
    @Bean
    public AppRepository<LibraryCart> libraryCartRepository(){
        return new Storage<LibraryCart>("libraryCarts");
    }
    @Bean
    public AppHelper<Author> authorAppHelper (){
        return new AuthorAppHelper(input());
    }
    @Bean
    public AppService<Author> authorService(){
        return new AuthorService(authorAppHelper(),authorRepository());
    }
    @Bean
    public AppService<Book> bookAppService(){
        return new BookAppService(bookAppHelper(),bookRepository());
    }
    @Bean
    public AppHelper<LibraryCart> libraryCartAppHelper (){
        return new LibraryCartAppHelper(input(),bookAppService(),userAppService());
    }
    @Bean
    public AppService<LibraryCart> libraryCartAppService(){
        return new LibraryCartAppService(libraryCartAppHelper(),libraryCartRepository());
    }
    @Bean
    public AppService<User> userAppService(){
        return new UserAppService(userAppHelper(),userRepository());
    }
    @Bean
    public AppHelper<Book> bookAppHelper (){
        return new BookAppHelper(input(),authorService());
    }
    @Bean
    public AppHelper<User> userAppHelper (){
        return new UserAppHelper(input());
    }

}
