package edu.zucc.paperManageSys.Service;

import edu.zucc.paperManageSys.Dao.PaperDao;
import edu.zucc.paperManageSys.Dao.PaperTypeDao;
import edu.zucc.paperManageSys.Dao.UserDao;
import edu.zucc.paperManageSys.Entity.ComplexPaper;
import edu.zucc.paperManageSys.Entity.PaperEntity;
import edu.zucc.paperManageSys.Entity.PaperTypeEntity;
import edu.zucc.paperManageSys.Entity.UserEntity;
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
    private PaperTypeDao paperTypeDao;

    @Autowired
    private PaperDao paperDao;

    @Autowired
    private UserDao userDao;

    public boolean paperUpload(String papername, String filename, int type, int teacherId) {
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

    private List<ComplexPaper> mergePaperInfo(List<PaperEntity> paperlist){
        if(paperlist==null) return null;
        List<ComplexPaper> result = new ArrayList<>();
        try{
            for (PaperEntity paperEntity:paperlist) {
                int teacherId = paperEntity.getTeacherId();
                UserEntity teacherEntity = userDao.findById(teacherId);
                String teacherUserName = teacherEntity.getUsername();
                String teacherName = teacherEntity.getName().equals("NULL")?teacherUserName:teacherEntity.getName();
                int typeId = paperEntity.getPaperType();
                String typeName = typeId==0?"未选择":paperTypeDao.findById(typeId).getTypeName();
                String checked = paperEntity.getChecked()==0?"否":"是";
                ComplexPaper paper = new ComplexPaper(
                        paperEntity.getId(),
                        paperEntity.getPaperName(),
                        typeId,
                        typeName,
                        teacherId,
                        teacherName,
                        teacherUserName,
                        checked,
                        paperEntity.getCreateTime().toString(),
                        paperEntity.getPaperUrl()
                );
                result.add(paper);
            }
        }catch (Exception e){
            logger.error("paperQueryByIdAndTime:"+e.getMessage());
        }
        return result;
    }

    public List<ComplexPaper> paperQueryAll(int teacherId) {
        List<PaperEntity> paperlist = null;
        try{
            paperlist = paperDao.findByTeacherId(teacherId);
        }catch (Exception e){
            logger.error("paperQueryAll:"+e.getMessage());
        }
        return mergePaperInfo(paperlist);
    }

    public List<ComplexPaper> paperQueryByIdAndTime(Date formerTime, Date laterTime, int teacherId) {
        List<PaperEntity> paperlist = null;
        try{
            paperlist = paperDao.findByIdAndTime(formerTime, laterTime, teacherId);
        }catch (Exception e){
            logger.error("paperQueryByIdAndTime:"+e.getMessage());
        }
        return mergePaperInfo(paperlist);
    }

    public List<ComplexPaper> paperQueryByTime(Date formerTime, Date laterTime) {
        List<PaperEntity> paperlist = null;
        try{
            paperlist = paperDao.findByTime(formerTime, laterTime);
        }catch (Exception e){
            logger.error("paperQueryByIdAndTime:"+e.getMessage());
        }
        return mergePaperInfo(paperlist);
    }

    public PaperEntity paperQueryById(int id) {
        PaperEntity paper = null;
        try{
            paper = paperDao.findById(id);
        }catch (Exception e){
            logger.error("paperQueryById:"+e.getMessage());
        }
        return paper;
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
