package ee.ivkhkdev.services;

import ee.ivkhkdev.helpers.LibraryCartAppHelper;
import ee.ivkhkdev.intefaces.AppHelper;
import ee.ivkhkdev.intefaces.AppRepository;
import ee.ivkhkdev.intefaces.AppService;
import ee.ivkhkdev.model.LibraryCart;

import java.util.List;

public class LibraryCartAppService implements AppService<LibraryCart> {
    private final AppHelper<LibraryCart> libraryCartAppHelper;
    private final AppRepository<LibraryCart> repository;

    public LibraryCartAppService(AppHelper<LibraryCart> libraryCartAppHelper, AppRepository<LibraryCart> repository) {
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
