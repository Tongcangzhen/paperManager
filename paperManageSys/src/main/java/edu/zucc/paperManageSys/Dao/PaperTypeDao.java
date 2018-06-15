package edu.zucc.paperManageSys.Dao;

import edu.zucc.paperManageSys.Entity.PaperTypeEntity;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaperTypeDao extends JpaRepository<PaperTypeEntity, Integer>{
    List<PaperTypeEntity> findAll();
}
