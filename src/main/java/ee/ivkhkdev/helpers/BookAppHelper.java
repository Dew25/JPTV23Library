package ee.ivkhkdev.helpers;

import ee.ivkhkdev.intefaces.AppHelper;
import ee.ivkhkdev.intefaces.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.intefaces.AppService;
import ee.ivkhkdev.services.AuthorService;

import java.util.ArrayList;
import java.util.List;

public class BookAppHelper implements AppHelper<Book> {

    private final Input input;
    private final AppService<Author> authorService;

    public BookAppHelper(Input input, AppService<Author> authorService) {
        this.input = input;
        this.authorService = authorService;
    }

    @Override
    public Book create() {
        Book book = new Book();
        try {
            System.out.print("Название книги: ");
            book.setTitle(input.nextLine());
            authorService.print();
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
                book.getAuthors().add(authorService.list().get(numberAuthor-1));
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

    @Override
    public List<Book> edit(List<Book> books) {
        try {
            System.out.println("---- Редактирование книги -----");
            this.printList(books);
            System.out.print("Выберите номер книги: ");
            int numberBook = Integer.parseInt(input.nextLine());
            System.out.print("Название книги: "+books.get(numberBook-1).getTitle());
            System.out.print("Изменить (y/n): ");
            String choice = input.nextLine();
            if(choice.equals("y")){
                System.out.print("Новое название книги: ");
                books.get(numberBook-1).setTitle(input.nextLine());
            }
            //Список авторов книги
            ((AuthorService) authorService).getAppHelperAuthor().printList(books.get(numberBook-1).getAuthors());
            System.out.print("Изменить авторов (y/n): ");
            choice = input.nextLine();
            if(choice.equals("y")){
                System.out.print("Количество афторов книги: ");
                int authorsCount = Integer.parseInt(input.nextLine());
                authorService.print();
                List<Author> listAllAuthors = authorService.list();
                List<Author> bookAuthors = new ArrayList<>();
                for(int i = 0; i < authorsCount; i++){
                    System.out.printf("Номер автора %d из %d: ",i+1, authorsCount);
                    int numberAuthor = Integer.parseInt(input.nextLine());
                    bookAuthors.add(listAllAuthors.get(numberAuthor-1));
                }
                books.get(numberBook-1).setAuthors(bookAuthors);
            }
            System.out.print("Год издания книги: "+books.get(numberBook-1).getPublishedYear());
            System.out.print("Изменить (y/n): ");
            choice = input.nextLine();
            if(choice.equals("y")){
                System.out.print("Новый год издания: ");
                books.get(numberBook-1).setPublishedYear(Integer.parseInt(input.nextLine()));
            }
            return books;
        }catch (Exception e){
            return null;
        }

    }
}
