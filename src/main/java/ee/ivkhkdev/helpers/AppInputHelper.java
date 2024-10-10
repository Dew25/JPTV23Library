package ee.ivkhkdev.helpers;

import ee.ivkhkdev.App;
import ee.ivkhkdev.interfaces.Input;
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

    public void printListUsers() {
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
    }
}
