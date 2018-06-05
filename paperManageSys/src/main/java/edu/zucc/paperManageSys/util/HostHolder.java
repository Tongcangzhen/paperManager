package edu.zucc.paperManageSys.util;

import edu.zucc.paperManageSys.Entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class HostHolder {
    private static ThreadLocal<UserEntity> users = new ThreadLocal<UserEntity>();

    public UserEntity getUser() {
        return users.get();
    }

    public  void setUsers(UserEntity user) {
        users.set(user);
    }

    public void clear() {
        users.remove();
    }
}
