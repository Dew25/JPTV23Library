package ee.ivkhkdev.helpers;

import ee.ivkhkdev.App;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.User;

public class AppInputHelper {

    public User cerateUser(Input input){
        User user = new User();
        System.out.print("Имя пользователя: ");
        user.setFirstName(input.nextLine());
        System.out.print("Фамилия пользователя: ");
        user.setLastName(input.nextLine());
        System.out.print("Телефон: ");
        user.setPhone(input.nextLine());
        return user;
    }

    public boolean printListUsers() {
        try {
            System.out.println("Список пользователей:");
            for (int i=0;i< App.users.length;i++){
                User user = App.users[i];
                if(user != null){
                    System.out.printf("%d. %s %s. %s%n",
                            i+1,
                            user.getFirstName(),
                            user.getLastName(),
                            user.getPhone()
                    );
                }
            }
            return true;
        }catch (Exception e){
            System.out.println("Error: "+e.toString());
            return false;
        }
    }

    public Book cerateBook(Input input) {
        try {
            Book book = new Book();
            System.out.print("Название книги: ");
            book.setTitle(input.nextLine());
            System.out.print("Количество авторов книги: ");
            int countBookAuthors = Integer.parseInt(input.nextLine());
            for (int i = 0; i < countBookAuthors; i++){
                Author author = new Author();
                System.out.printf("автор №%d%n",i+1);
                System.out.print("Имя: ");
                author.setFirstname(input.nextLine());
                System.out.print("Фамилия: ");
                author.setLastname(input.nextLine());
                book.getAuthors()[i] = author;
            }
            System.out.print("Год издания: ");
            book.setPublishedYear(Integer.parseInt(input.nextLine()));
            return book;

        }catch (Exception e){
            return null;
        }
    }
}