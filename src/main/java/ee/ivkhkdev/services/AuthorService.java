package ee.ivkhkdev.services;

import ee.ivkhkdev.intefaces.AppHelper;
import ee.ivkhkdev.intefaces.AppService;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.intefaces.AppRepository;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class AuthorService implements AppService<Author> {

    private AppRepository<Author> repository;
    private AppHelper<Author> appHelperAuthor;

    public AuthorService(AppHelper<Author> appHelperAuthor, AppRepository<Author> repository) {
        this.appHelperAuthor = appHelperAuthor;
        this.repository = repository;
    }

    public AppHelper<Author> getAppHelperAuthor() {
        return appHelperAuthor;
    }

    public boolean add(){
        Author author = appHelperAuthor.create();
        if(author == null) return false;
        try {
            repository.save(author);
            return true;
        }catch (Exception e){
            System.out.println("Error: "+e.toString());
            return false;
        }
    }

    @Override
    public boolean edit() {
        return false;
    }

    @Override
    public boolean print() {
        return appHelperAuthor.printList(repository.load());
    }

    @Override
    public List<Author> list() {
        return repository.load();
    }
}
