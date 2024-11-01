package ee.ivkhkdev.services;

import ee.ivkhkdev.intefaces.Service;
import ee.ivkhkdev.model.LibraryCard;

import java.util.List;

public class LibraryCardService implements Service<LibraryCard> {
    @Override
    public boolean add() {
        LibraryCard libraryCard = libraryCardAppHelper.create();
        if(libraryCard == null) return false;
        try {
            repository.save(libraryCard);
            return true;
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            return false;
        }

    }

    @Override
    public boolean print() {
        return libraryCardAppHelper.printList(repository.load());
    }

    @Override
    public List<LibraryCard> list() {
        return List.of();
    }
    public List<LibraryCard> returnBook(){
        return repository.load();
    };

}
