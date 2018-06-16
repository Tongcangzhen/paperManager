package edu.zucc.paperManageSys.Dao;

import edu.zucc.paperManageSys.Entity.PaperEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PaperDao extends CrudRepository<PaperEntity, Integer> {
    PaperEntity findById(int id);
    List<PaperEntity> findByTeacherId(int teacherId);

    @Query(value = "SELECT * FROM paper WHERE create_time > ?1 AND create_time < ?2 AND teacher_id = ?3", nativeQuery = true)
    List<PaperEntity> findByIdAndTime(Date formerTime, Date laterTime, int teacherId);

    @Query(value = "SELECT * FROM paper WHERE create_time > ?1 AND create_time < ?2", nativeQuery = true)
    List<PaperEntity> findByTime(Date formerTime, Date laterTime);
}
