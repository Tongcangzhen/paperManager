package edu.zucc.paperManageSys.Controller;

import edu.zucc.paperManageSys.Entity.PaperEntity;
import edu.zucc.paperManageSys.Entity.UserEntity;
import edu.zucc.paperManageSys.Service.PaperService;
import edu.zucc.paperManageSys.Service.TeacherService;
import edu.zucc.paperManageSys.util.FileUtil;
import edu.zucc.paperManageSys.util.HostHolder;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class TeacherController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    TeacherService teacherService;

    @Autowired
    PaperService paperService;

    @Autowired
    HostHolder hostHolder;

    @RequestMapping(path = {"/teacher_info"}, method = {RequestMethod.GET})
    public String teacherInfo(Model model) {
        if (hostHolder.getUser() != null) {
            UserEntity user = hostHolder.getUser();
        }else {
        }
        return "/personal_data";
    }

    @RequestMapping(path = {"/teacher_info_mod"}, method = {RequestMethod.POST})
    public String teacherInfoMod(Model model,
                                 @RequestParam("name") String name,
                                 @RequestParam("jobtitle") String jobtitle,
                                 @RequestParam("email") String email,
                                 @RequestParam("gender") int gender) {
        if (hostHolder.getUser() != null) {
            teacherService.modify(name,jobtitle,email,gender);
        }else {
        }
        return "redirect:/index";
    }

    @RequestMapping(path = "/teacher_up", method = RequestMethod.POST)
    public String teacherUp(Model model,
                            @RequestParam("fileToUpload") MultipartFile[] folder)
            throws Exception {
        if (hostHolder.getUser() == null) {
            throw new Exception("用户未登陆!");
        }
        String path = FileUtil.BASE_PATH + "users/" + hostHolder.getUser().getUsername()+"/papers";
        if (FileUtil.saveMultiFile(path, folder)) {
            paperService.paperupload("111",folder[0].getOriginalFilename(),0, hostHolder.getUser().getId());
        }else
            throw new Exception("上传失败!");
        return "redirect:/index";
    }

    @RequestMapping(path = "/history", method = RequestMethod.GET)
    public String listhistory(Model model) throws Exception {
        if (hostHolder.getUser() == null) {
            throw new Exception("用户未登陆!");
        }
        return "paper";
    }

    @RequestMapping(path = "/search", method = RequestMethod.POST)
    @ResponseBody
    public String searchhistory(Model model,
                              @RequestBody String data)
            throws Exception {
        if (hostHolder.getUser() == null) {
            throw new Exception("用户未登陆!");
        }
        logger.info("data:"+data);
        JSONObject jsonObj = (JSONObject)JSONValue.parseStrict(data);
        String timeFormerStr = jsonObj.getAsString("timeFormer");
        String timeLaterStr = jsonObj.getAsString("timeLater");
        logger.info("timeFormer:"+timeFormerStr);
        logger.info("timeLater:"+timeLaterStr);

        int teacherId=hostHolder.getUser().getId();
        List<PaperEntity> paperList = null;
        if(timeFormerStr.equals("none") || timeLaterStr.equals("none"))
            paperList = paperService.paperQueryAll(teacherId);
        else{
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            paperList = paperService.paperQueryByIdAndTime(formater.parse(timeFormerStr), formater.parse(timeLaterStr), teacherId);
        }

        if(paperList == null)   return null;
        JSONArray result = new JSONArray();
        for (PaperEntity paperEntity:paperList) {
            result.appendElement(paperEntity);
        }
        logger.info("Paper names:"+result.toJSONString());
        return result.toJSONString();
    }
}
