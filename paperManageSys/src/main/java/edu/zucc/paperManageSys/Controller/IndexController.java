package edu.zucc.paperManageSys.Controller;

import edu.zucc.paperManageSys.Entity.UserEntity;
import edu.zucc.paperManageSys.util.HostHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    HostHolder hostHolder;

    @RequestMapping(path = {"/","/index"}, method = {RequestMethod.GET})
    public String index(Model model) {
        try {
            UserEntity user = hostHolder.getUser();
            if(user==null){
                return "redirect:/reglogin";
            }else if(user.getType()==1){
                String name = user.getName();
                model.addAttribute("username", name.equals("NULL") ? user.getUsername() : name );
                return "/admin_index";
            }else{
                String name = user.getName();
                model.addAttribute("username", name.equals("NULL") ? user.getUsername() : name );
                int gender = user.getGender();
                model.addAttribute("gender", gender==-1?"未知": gender==1?"男":"女");
                return "/teacher_index";
            }
        } catch (Exception e) {
            logger.error("index错误:" + e.getMessage());
            return "/index";
        }
    }
}
