package ee.ivkhkdev.helpers;

import ee.ivkhkdev.intefaces.AppHelper;
import ee.ivkhkdev.intefaces.Input;
import ee.ivkhkdev.intefaces.Service;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.LibraryCart;
import ee.ivkhkdev.model.User;

import java.time.LocalDate;
import java.util.List;

public class LibraryCartAppHelper implements AppHelper<LibraryCart> {
    private final Input input;
    private final Service<Book> bookService;
    private final Service<User> userService;

    public LibraryCartAppHelper(Input input, Service<Book> bookService, Service<User> userService) {
        this.input = input;
        this.bookService = bookService;
        this.userService = userService;
    }

    @Override
    public LibraryCart create() {
        if(!bookService.print()){
            return null;
        };
        System.out.print("Выберите номер книги: ");
        int numberBook = Integer.parseInt(input.nextLine());
        Book book = bookService.list().get(numberBook - 1);
        if(!userService.print()){
            return null;
        }
        System.out.print("Выберите номер читателя: ");
        int numberUser = Integer.parseInt(input.nextLine());
        User user = userService.list().get(numberUser-1);
        LibraryCart libraryCart = new LibraryCart();
        libraryCart.setBook(book);
        libraryCart.setUser(user);
        libraryCart.setBorrowdBookDate(LocalDate.now());
        return libraryCart;
    }

    @Override
    public boolean printList(List<LibraryCart> libraryCarts) {
        int count = 0;
        for(int i = 0; i< libraryCarts.size(); i++){
            LibraryCart libraryCart = libraryCarts.get(i);
            if(libraryCart.getReturnBookDate() == null){
                System.out.printf("%d. %s. %s. Читает %s %s%n",
                        i+1,
                        libraryCart.getBook().getTitle(),
                        libraryCart.getBook().getPublishedYear(),
                        libraryCart.getUser().getFirstName(),
                        libraryCart.getUser().getLastName()
                );
                count++;
            }
        }
        if(count > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<LibraryCart> edit(List<LibraryCart> libraryCarts) {
        return List.of();
    }

    public List<LibraryCart> returnBack(List<LibraryCart> libraryCarts){
        if(!this.printList(libraryCarts)){
            return null;
        }
        System.out.print("Выберите номер возвращаемой книги: ");
        int numberLibraryCard = Integer.parseInt(input.nextLine());
        libraryCarts.get(numberLibraryCard-1).setReturnBookDate(LocalDate.now());
        return libraryCarts;
    }
}
