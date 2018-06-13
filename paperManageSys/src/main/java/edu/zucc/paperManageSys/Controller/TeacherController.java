package edu.zucc.paperManageSys.Controller;

import edu.zucc.paperManageSys.Entity.UserEntity;
import edu.zucc.paperManageSys.Service.TeacherService;
import edu.zucc.paperManageSys.util.FileUtil;
import edu.zucc.paperManageSys.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class TeacherController {
    @Autowired
    TeacherService teacherService;

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
            throw new Exception("上传失败!");
        }
        return "redirect:/index";
    }
}
