package ee.ivkhkdev.services;

import ee.ivkhkdev.App;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.helpers.AppInputHelper;

public class UserService {
    private AppInputHelper appInputHelper = new AppInputHelper();

    public boolean add(Input input) {
        User user = appInputHelper.cerateUser(input);
        if(user == null ) return false;
        for (int i = 0; i < App.users.length; i++){
            if(App.users[i] == null){
                App.users[i] = user;
                break;
            }
        }
        return true;
    }

    public boolean printList() {
        return appInputHelper.printListUsers();
    }
}