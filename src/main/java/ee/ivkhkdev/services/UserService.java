package ee.ivkhkdev.services;

import ee.ivkhkdev.intefaces.AppHelper;
import ee.ivkhkdev.intefaces.Service;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.intefaces.Repository;


import java.util.List;

public class UserService implements Service {

    private final Repository<User> repository;
    private AppHelper<User> appHelperUser;

    public UserService(AppHelper<User> appHelperUser, Repository<User> repository) {
        this.appHelperUser = appHelperUser;
        this.repository = repository;
    }

    public boolean add() {
        User user = appHelperUser.create();
        if(user == null ) return false;
        try {
            repository.save(user);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public boolean print() {
        return appHelperUser.printList(repository.load());
    }

    @Override
    public List list() {
        return repository.load();
    }
}
