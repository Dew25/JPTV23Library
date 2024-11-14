package ee.ivkhkdev.services;

import ee.ivkhkdev.helpers.AppHelper;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.repositories.Repository;


import java.util.List;

public class BookService<Book> implements Service<Book>{


    private Repository<Book> repository;
    private AppHelper<Book> appHelperBook;

    public BookService( AppHelper<Book> appHelperBook, Repository<Book> repository) {
        this.appHelperBook = appHelperBook;
        this.repository = repository;
    }
    @Override
    public Repository<Book> getRepository() {
        return repository;
    }

    public boolean add(){
        try {
            Book book = appHelperBook.create();
            List<Book> books = repository.load();
            if(book == null) return false;
            for (int i = 0; i <= books.size(); i++){
                if(i == 0 ){
                    books.add(book);
                    repository.save(book);
                    break;
                }else if(books.get(i) == null){
                    books.add(book);
                    repository.save(book);
                    break;
                }
            }
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
}
