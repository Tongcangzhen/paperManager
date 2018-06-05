package edu.zucc.paperManageSys.Dao;

import edu.zucc.paperManageSys.Entity.PaperEntity;
import org.springframework.data.repository.CrudRepository;

public interface PaperDao extends CrudRepository<PaperEntity, String> {

}
