package edu.zucc.paperManageSys.Controller;

import edu.zucc.paperManageSys.Dao.UserDao;
import edu.zucc.paperManageSys.Entity.PaperEntity;
import edu.zucc.paperManageSys.Service.PaperService;
import edu.zucc.paperManageSys.util.FileUtil;
import edu.zucc.paperManageSys.util.HostHolder;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

@Controller
public class PaperController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    PaperService paperService;

    @Autowired
    UserDao userDao;

    @Autowired
    HostHolder hostHolder;

    @RequestMapping(path = "/teacher_up", method = RequestMethod.POST)
    public String teacherUp(Model model,
                            @RequestParam("fileToUpload") MultipartFile[] folder,
                            @RequestParam("paperType") String paperType,
                            @RequestParam("paperTitle") String paperName)
            throws Exception {
        if (hostHolder.getUser() == null) {
            throw new Exception("用户未登陆!");
        }
        try{
            String path = FileUtil.BASE_PATH + "users/" + hostHolder.getUser().getUsername()+"/papers/"+paperName;
            Map<String, String> result =  FileUtil.saveMultiFile(path, folder);
            if(result.containsKey("error")) throw new Exception(result.get("error"));
            paperService.paperUpload(paperName,folder[0].getOriginalFilename(),Integer.parseInt(paperType), hostHolder.getUser().getUsername());
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("上传失败!"+e.getMessage());
        }
        return "redirect:/index";
    }

    @RequestMapping(path = "/paper_download", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response,
                             @RequestParam("id") int id) {
        response.setContentType("application/octet-stream");
        FileInputStream fis = null;
        try {
            PaperEntity paper = paperService.paperQueryById(id);
            String path = FileUtil.BASE_PATH + "users/" + paper.getTeacherUsername()+"/papers/"+paper.getPaperName()+"/"+paper.getPaperUrl();

            File file = new File(path);
            fis = new FileInputStream(file);
            response.setCharacterEncoding("ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment; filename="+new String(paper.getPaperUrl().getBytes("UTF-8"), "ISO-8859-1"));
            IOUtils.copy(fis,response.getOutputStream());
            response.flushBuffer();
        }  catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
