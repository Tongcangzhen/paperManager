package edu.zucc.paperManageSys.Controller;

import edu.zucc.paperManageSys.Entity.ComplexPaper;
import edu.zucc.paperManageSys.Entity.PaperEntity;
import edu.zucc.paperManageSys.Entity.PaperTypeEntity;
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

    @RequestMapping(path = "/history", method = RequestMethod.GET)
    public String listhistory(Model model) throws Exception {
        if (hostHolder.getUser() == null) {
            throw new Exception("用户未登陆!");
        }
        return "paper";
    }

    @RequestMapping(path = "/search", method = RequestMethod.POST)
    @ResponseBody
    public String searchhistory(Model model, @RequestBody String data) throws Exception {
        if (hostHolder.getUser() == null) {
            throw new Exception("用户未登陆!");
        }
        JSONObject jsonObj = (JSONObject)JSONValue.parseStrict(data);
        String timeFormerStr = jsonObj.getAsString("timeFormer");
        String timeLaterStr = jsonObj.getAsString("timeLater");

        String teacherUsername=hostHolder.getUser().getUsername();
        List<ComplexPaper> paperList = null;
        if(timeFormerStr.equals("none") || timeLaterStr.equals("none"))
            paperList = paperService.paperQueryAll(teacherUsername);
        else{
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            if(jsonObj.getAsNumber("isAdmin").intValue()==0)
                paperList = paperService.paperQueryByIdAndTime(formater.parse(timeFormerStr), formater.parse(timeLaterStr), teacherUsername);
            else
                paperList = paperService.paperQueryByTime(formater.parse(timeFormerStr), formater.parse(timeLaterStr));
        }

        if(paperList == null)   return null;
        JSONArray result = new JSONArray();
        for (ComplexPaper paperEntity:paperList) {
            result.appendElement(paperEntity);
        }
        return result.toJSONString();
    }

    @RequestMapping(path = "/search_type", method = RequestMethod.GET)
    @ResponseBody
    public String searchType(Model model) throws Exception {
        if (hostHolder.getUser() == null) {
            throw new Exception("用户未登陆!");
        }

        List<PaperTypeEntity> typeList = paperService.typeQueryAll();

        JSONArray result = new JSONArray();
        for (PaperTypeEntity typeEntity:typeList) {
            result.appendElement(typeEntity);
        }
        return result.toJSONString();
    }
}
