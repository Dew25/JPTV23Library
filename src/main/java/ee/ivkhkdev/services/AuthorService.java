package ee.ivkhkdev.services;

import ee.ivkhkdev.helpers.AppHelper;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.repositories.Repository;

import java.util.List;

public class AuthorService<Author> implements Service<Author>{

    private Repository<Author> repository;
    private AppHelper<Author> appHelperAuthor;

    public AuthorService(AppHelper<Author> appHelperAuthor, Repository<Author> repository) {

        this.appHelperAuthor = appHelperAuthor;
        this.repository = repository;
    }
    @Override
    public Repository<Author> getRepository() {
        return repository;
    }

    public boolean add(){
        Author author = appHelperAuthor.create();
        List<Author> authors = repository.load();
        if(author == null) return false;
        try {
            for (int i = 0; i <= authors.size(); i++){
                if(i == 0 ){
                    authors.add(author);
                    repository.save(author);
                    break;
                }else if(authors.get(i) == null){
                    authors.add(author);
                    repository.save(author);
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
        return appHelperAuthor.printList(repository.load());
    }
}
