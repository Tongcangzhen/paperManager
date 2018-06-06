package edu.zucc.paperManageSys.Controller;

import edu.zucc.paperManageSys.Entity.UserEntity;
import edu.zucc.paperManageSys.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    @Autowired
    HostHolder hostHolder;

    @RequestMapping(path = {"/","/index"}, method = {RequestMethod.GET})
    public String index(Model model) {
        if (hostHolder.getUser() != null) {
            UserEntity user = hostHolder.getUser();
            model.addAttribute("username", user.getName());
            model.addAttribute("type", user.getType());
        }else {
            model.addAttribute("username", "未登录");
        }

        return "/index";
    }
}
