package ee.ivkhkdev.services;

import ee.ivkhkdev.intefaces.AppHelper;
import ee.ivkhkdev.intefaces.AppService;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.intefaces.AppRepository;


import java.util.List;

public class UserAppService implements AppService<User> {

    private final AppRepository<User> repository;
    private AppHelper<User> appHelperUser;

    public UserAppService(AppHelper<User> appHelperUser, AppRepository<User> repository) {
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

    @Override
    public boolean edit() {
        List<User> modifiedUsers = appHelperUser.edit(repository.load());
        if(modifiedUsers == null || modifiedUsers.size() == 0){
            return false;
        }
        repository.saveAll(modifiedUsers);
        return true;
    }

    public boolean print() {
        return appHelperUser.printList(repository.load());
    }

    @Override
    public List list() {
        return repository.load();
    }
}
