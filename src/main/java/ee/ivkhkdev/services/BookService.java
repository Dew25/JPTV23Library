package ee.ivkhkdev.services;

import ee.ivkhkdev.intefaces.AppHelper;
import ee.ivkhkdev.intefaces.Service;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.intefaces.Repository;


import java.util.List;

public class BookService implements Service {

    private Repository<Book> repository;
    private AppHelper<Book> appHelperBook;

    public BookService(AppHelper<Book> appHelperBook, Repository<Book> repository) {
        this.appHelperBook = appHelperBook;
        this.repository = repository;
    }
    public boolean add(){
        try {
            Book book = appHelperBook.create();
            if(book == null) return false;
            repository.save(book);
            return true;
        }catch (Exception e){
            System.out.println("Error: "+e.toString());
            return false;
        }

    }

    @Override
    public boolean print() {
        return appHelperBook.printList(repository.load());
    }

    @Override
    public List list() {
        return repository.load();
    }
}
