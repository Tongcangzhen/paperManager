package edu.zucc.paperManageSys.Service;

import edu.zucc.paperManageSys.Controller.LoginController;
import edu.zucc.paperManageSys.Dao.UserDao;
import edu.zucc.paperManageSys.Entity.UserEntity;
import edu.zucc.paperManageSys.util.HostHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TeacherService {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserDao userDao;

    @Autowired
    HostHolder hostHolder;

    public Map<String, String> modify(String name, String jobtitle, String email, int gender) {
        Map<String, String> map = new HashMap<String, String>();
        try{
            UserEntity userEntity = hostHolder.getUser();
            if (name!=null) userEntity.setName(name);
            if (jobtitle!=null) userEntity.setJobtitle(jobtitle);
            if (email!=null)    userEntity.setEmail(email);
            userEntity.setGender(gender);
            userDao.save(userEntity);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return map;
    }
}
