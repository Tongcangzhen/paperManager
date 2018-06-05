package edu.zucc.paperManageSys.Dao;

import edu.zucc.paperManageSys.Entity.LoginTicketEntity;
import org.springframework.data.repository.CrudRepository;

public interface LoginTicketDao extends CrudRepository<LoginTicketEntity, Long> {
    LoginTicketEntity findByTicket(String ticket);

}
