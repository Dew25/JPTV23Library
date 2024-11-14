package ee.ivkhkdev.services;

import ee.ivkhkdev.helpers.AppHelper;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.repositories.Repository;


import java.util.List;

public class UserService<User> implements Service<User>{

    private final Repository<User> repository;
    private AppHelper<User> appHelperUser;

    public UserService(AppHelper<User> appHelperUser, Repository<User> repository) {

        this.appHelperUser = appHelperUser;
        this.repository = repository;
    }
    @Override
    public Repository<User> getRepository() {
        return repository;
    }

    public boolean add() {
        User user = appHelperUser.create();
        List<User> users = repository.load();
        if(user == null ) return false;
        for (int i = 0; i <= users.size(); i++){
            if(i == 0 ){
                users.add(user);
                repository.save(user);
                break;
            }else if(users.get(i) == null) {
                users.add(user);
                repository.save(user);
                break;
            }
        }
        return true;
    }

    public boolean print() {
        return appHelperUser.printList(repository.load());
    }



}
