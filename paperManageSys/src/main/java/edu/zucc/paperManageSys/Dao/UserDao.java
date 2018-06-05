package edu.zucc.paperManageSys.Dao;

import edu.zucc.paperManageSys.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<UserEntity, Long> {

    UserEntity findByName(String name);

    UserEntity findById(int id);
}
