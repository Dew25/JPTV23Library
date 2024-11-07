package ee.ivkhkdev.helpers;

import ee.ivkhkdev.intefaces.AppHelper;
import ee.ivkhkdev.intefaces.Input;
import ee.ivkhkdev.intefaces.Service;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.LibraryCard;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.services.UserService;

import java.time.LocalDate;
import java.util.List;

public class LibraryCardAppHelper implements AppHelper<LibraryCard> {
    private final Input input;
    private final Service<Book> bookService;
    private final Service<User> userService;

    public LibraryCardAppHelper(Input input, Service<Book> bookService, Service<User> userService) {
        this.input = input;
        this.bookService = bookService;
        this.userService = userService;
    }

    @Override
    public LibraryCard create() {
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
        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setBook(book);
        libraryCard.setUser(user);
        libraryCard.setBorrowdBookDate(LocalDate.now());
        return libraryCard;
    }

    @Override
    public boolean printList(List<LibraryCard> libraryCards) {
        int count = 0;
        for(int i = 0;i<libraryCards.size();i++){
            LibraryCard libraryCard = libraryCards.get(i);
            if(libraryCard.getReturnBookDate() == null){
                System.out.printf("%d. %s. %s. Читает %s %s%n",
                        i+1,
                        libraryCard.getBook().getTitle(),
                        libraryCard.getBook().getPublishedYear(),
                        libraryCard.getUser().getFirstName(),
                        libraryCard.getUser().getLastName()
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
    public List<LibraryCard> edit(List<LibraryCard> libraryCards) {
        return List.of();
    }

    public List<LibraryCard> returnBack(List<LibraryCard>libraryCards){
        if(!this.printList(libraryCards)){
            return null;
        }
        System.out.print("Выберите номер возвращаемой книги: ");
        int numberLibraryCard = Integer.parseInt(input.nextLine());
        libraryCards.get(numberLibraryCard-1).setReturnBookDate(LocalDate.now());
        return libraryCards;
    }
}
