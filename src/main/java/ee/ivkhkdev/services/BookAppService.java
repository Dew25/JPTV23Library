package ee.ivkhkdev.services;

import ee.ivkhkdev.intefaces.AppHelper;
import ee.ivkhkdev.intefaces.AppService;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.intefaces.AppRepository;


import java.util.List;

public class BookAppService implements AppService {

    private AppRepository<Book> repository;
    private AppHelper<Book> appHelperBook;

    public BookAppService(AppHelper<Book> appHelperBook, AppRepository<Book> repository) {
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
    public boolean edit() {
        List<Book> modifiedBooks = appHelperBook.edit(repository.load());
        if(modifiedBooks == null){
            return false;
        }
        repository.saveAll(modifiedBooks);
        return true;
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
