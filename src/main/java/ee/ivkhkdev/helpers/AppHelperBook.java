package ee.ivkhkdev.helpers;

import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.services.AuthorService;

import java.util.List;

public class AppHelperBook implements AppHelper<Book> {

    private final Input input;
    private final AuthorService authorService;

    public AppHelperBook(Input input, AuthorService authorService) {
        this.input = input;
        this.authorService = authorService;
    }

    @Override
    public Book create() {
        Book book = new Book();
        try {
            System.out.print("Название книги: ");
            book.setTitle(input.nextLine());
            authorService.printList();
            System.out.print("Добавить автора в список (y/n): ");
            String addAuthorChoose = input.nextLine();
            if(addAuthorChoose.equals("y")){
                System.out.println("Выход из задачи");
                return null;
            }
            System.out.print("Количество авторов книги: ");
            int countBookAuthors = Integer.parseInt(input.nextLine());
            for (int i = 0; i < countBookAuthors; i++){
                System.out.printf("Выберите номер автора из списка (%d автор из %d%n): ", i+1,countBookAuthors);
                int numberAuthor = Integer.parseInt(input.nextLine());
                book.getAuthors().add(authorService.getAuthors().get(numberAuthor-1));
            }
            System.out.print("Год издания книги: ");
            book.setPublishedYear(Integer.parseInt(input.nextLine()));
            return book;

        }catch (Exception e){
            return null;
        }
    }




    @Override
    public boolean printList(List<Book> books) {
        try {
            if(books.size() == 0) return false;
            for(int i = 0; i < books.size(); i++){
                StringBuilder sbAuthors = new StringBuilder();
                for (int j = 0; j < books.get(i).getAuthors().size(); j++) {
                    sbAuthors.append(books.get(i).getAuthors().get(j).getFirstname());
                    sbAuthors.append(" ");
                    sbAuthors.append(books.get(i).getAuthors().get(j).getLastname());
                    sbAuthors.append(". ");
                }
                System.out.printf("%d. %s. %s%d%n", i+1,books.get(i).getTitle(),sbAuthors.toString(),books.get(i).getPublishedYear());
            }
            return true;
        }catch (Exception e){
            System.out.println("Error: "+e.toString());
            return false;
        }
    }
}
