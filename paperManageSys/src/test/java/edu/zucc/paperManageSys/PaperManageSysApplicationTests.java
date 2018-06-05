package edu.zucc.paperManageSys;

import edu.zucc.paperManageSys.Dao.UserDao;
import edu.zucc.paperManageSys.Entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaperManageSysApplicationTests {

	@Autowired
	UserDao userDao;
	@Test
	public void contextLoads() {
		Random random = new Random(10);
		for (int i = 0; i < 5; i++) {
			UserEntity userEntity = new UserEntity();
			userEntity.setName("test2" + i);
			userEntity.setPassword("pwd" + random.nextInt());
			userEntity.setCreatTime(new Date().toString());
			userDao.save(userEntity);
		}

	}

}
