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

    public boolean paperUpload(String papername, String filename, int type, String teacherUsername) {
        try{
            PaperEntity paperEntity = new PaperEntity();
            paperEntity.setPaperName(papername);
            paperEntity.setPaperType(type);
            paperEntity.setTeacherUsername(teacherUsername);
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

    public boolean check(int paperId,int checked, int adminId) {
        try{
            PaperEntity paperEntity = paperDao.findById(paperId);
            paperEntity.setChecked(checked);
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
                String teacherUserName = paperEntity.getTeacherUsername();
                UserEntity teacherEntity = userDao.findByUsername(teacherUserName);
                String teacherName = teacherEntity.getName().equals("NULL")?teacherUserName:teacherEntity.getName();
                int typeId = paperEntity.getPaperType();
                String typeName = typeId==0?"未选择":paperTypeDao.findById(typeId).getTypeName();
                String checked;
                switch (paperEntity.getChecked()){
                    case 0:checked="待审核";break;
                    case 1:checked="已通过";break;
                    case 2:checked="未通过";break;
                    default:checked="待审核";break;
                }
                ComplexPaper paper = new ComplexPaper(
                        paperEntity.getId(),
                        paperEntity.getPaperName(),
                        typeId,
                        typeName,
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

    public List<ComplexPaper> paperQueryAll(String username) {
        List<PaperEntity> paperlist = null;
        try{
            paperlist = paperDao.findAllByTeacherUsername(username);
        }catch (Exception e){
            logger.error("paperQueryAll:"+e.getMessage());
        }
        return mergePaperInfo(paperlist);
    }

    public List<ComplexPaper> paperQueryByIdAndTime(Date formerTime, Date laterTime, String username) {
        List<PaperEntity> paperlist = null;
        try{
            paperlist = paperDao.findByIdAndTime(formerTime, laterTime, username);
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
