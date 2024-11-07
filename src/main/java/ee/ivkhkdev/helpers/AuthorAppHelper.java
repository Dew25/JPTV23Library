package ee.ivkhkdev.helpers;

import ee.ivkhkdev.intefaces.AppHelper;
import ee.ivkhkdev.intefaces.Input;
import ee.ivkhkdev.model.Author;

import java.util.List;

public class AuthorAppHelper implements AppHelper<Author> {

    private final Input input;

    public AuthorAppHelper(Input input) {
        this.input = input;
    }

    @Override
    public Author create() {
        Author author = new Author();
        try {
            System.out.print("Имя: ");
            author.setFirstname(input.nextLine());
            System.out.print("Фамилия: ");
            author.setLastname(input.nextLine());
            return author;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean printList(List<Author> authors) {
        try {
            if(authors.size() < 1) return false;
            for(int i = 0; i < authors.size(); i++){
                System.out.printf("%d. %s %s%n", i+1,authors.get(i).getFirstname(),authors.get(i).getLastname());
            }
            return true;
        }catch (Exception e){
            System.out.println("Error: "+e.toString());
            return false;
        }
    }

    @Override
    public List<Author> edit(List<Author> authors) {
        return List.of();
    }
}
