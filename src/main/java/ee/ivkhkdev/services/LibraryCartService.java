package ee.ivkhkdev.services;

import ee.ivkhkdev.helpers.LibraryCartAppHelper;
import ee.ivkhkdev.intefaces.AppHelper;
import ee.ivkhkdev.intefaces.Repository;
import ee.ivkhkdev.intefaces.Service;
import ee.ivkhkdev.model.LibraryCart;

import java.util.List;

public class LibraryCartService implements Service<LibraryCart> {
    private final AppHelper<LibraryCart> libraryCartAppHelper;
    private final Repository<LibraryCart> repository;

    public LibraryCartService(AppHelper<LibraryCart> libraryCartAppHelper, Repository<LibraryCart> repository) {
        this.libraryCartAppHelper =libraryCartAppHelper;
        this.repository = repository;
    }

    @Override
    public boolean add() {
        LibraryCart libraryCart = libraryCartAppHelper.create();
        if(libraryCart == null) return false;
        try {
            repository.save(libraryCart);
            return true;
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            return false;
        }

    }

    @Override
    public boolean edit() {
        return false;
    }

    @Override
    public boolean print() {
        return libraryCartAppHelper.printList(repository.load());
    }

    @Override
    public List<LibraryCart> list() {
        return repository.load();
    }

    public boolean returnBook(){
        List<LibraryCart> modifiedLibraryCarts = ((LibraryCartAppHelper) libraryCartAppHelper).returnBack(this.list());
        if(modifiedLibraryCarts != null) {
            repository.saveAll(modifiedLibraryCarts);
            return true;
        }else{
            return false;
        }
    };

}
