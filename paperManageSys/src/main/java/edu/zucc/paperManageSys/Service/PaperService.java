package edu.zucc.paperManageSys.Service;

import edu.zucc.paperManageSys.Dao.PaperDao;
import edu.zucc.paperManageSys.Dao.PaperTypeDao;
import edu.zucc.paperManageSys.Entity.PaperEntity;
import edu.zucc.paperManageSys.Entity.PaperTypeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class PaperService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private PaperDao paperDao;

    @Autowired
    private PaperTypeDao paperTypeDao;

    public boolean paperupload(String papername, String filename, int type, int teacherId) {
        try{
            PaperEntity paperEntity = new PaperEntity();
            paperEntity.setPaperName(papername);
            paperEntity.setPaperType(type);
            paperEntity.setTeacherId(teacherId);
            paperEntity.setChecked(0);
            paperEntity.setAdminId(0);
            paperEntity.setPaperUrl(filename);
            paperEntity.setCreateTime(new Date());
            paperDao.save(paperEntity);
        }catch (Exception e){
            logger.error("PaperService:"+e.getMessage());
        }
        return true;
    }

    public boolean check(int paperId, int adminId) {
        try{
            PaperEntity paperEntity = paperDao.findById(paperId);
            paperEntity.setChecked(1);
            paperEntity.setAdminId(adminId);
            paperDao.save(paperEntity);
        }catch (Exception e){
            logger.error("check:"+e.getMessage());
        }
        return true;
    }

    public List<PaperEntity> paperQueryAll(int teacherId) {
        List<PaperEntity> paperlist = null;
        try{
            paperlist = paperDao.findByTeacherId(teacherId);
        }catch (Exception e){
            logger.error("paperQueryAll:"+e.getMessage());
        }
        return paperlist;
    }

    public List<PaperEntity> paperQueryByIdAndTime(Date formerTime, Date laterTime, int teacherId) {
        List<PaperEntity> paperlist = null;
        try{
            paperlist = paperDao.findByIdAndTime(formerTime, laterTime, teacherId);
        }catch (Exception e){
            logger.error("paperQueryByIdAndTime:"+e.getMessage());
        }
        return paperlist;
    }

    public boolean typeAdd(String name) {
        try{
            PaperTypeEntity typeEntity = new PaperTypeEntity();
            typeEntity.setTypeName(name);
            paperTypeDao.save(typeEntity);
        }catch (Exception e){
            logger.error("typeAdd:"+e.getMessage());
        }
        return true;
    }

    public boolean typeDel(String ids) {
        try{
            List<Integer> idList = new ArrayList<>();
            for (String idStr:ids.split(",")) {
                idList.add(Integer.parseInt(idStr));
            }
            List<PaperTypeEntity> typeList = paperTypeDao.findAllById(idList);
            paperTypeDao.deleteInBatch(typeList);
        }catch (Exception e){
            logger.error("typeDel:"+e.getMessage());
        }
        return true;
    }

    public List<PaperTypeEntity> typeQueryAll() {
        List<PaperTypeEntity> typeList = null;
        try{
            typeList = paperTypeDao.findAll();

        }catch (Exception e){
            logger.error("PaperService:"+e.getMessage());
        }
        return typeList;
    }
}
