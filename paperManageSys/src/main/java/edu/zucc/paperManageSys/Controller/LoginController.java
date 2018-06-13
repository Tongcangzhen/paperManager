package edu.zucc.paperManageSys.Controller;

import edu.zucc.paperManageSys.Service.UserService;
import edu.zucc.paperManageSys.util.HostHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    UserService userService;

    @Autowired
    HostHolder hostHolder;

    @RequestMapping(path = {"/reg/"}, method = {RequestMethod.POST})
    public String reg(Model model,
                      @RequestParam("username") String username,
                      @RequestParam("password") String password,
                      @RequestParam(value = "isAdmin", defaultValue = "false") boolean isAdmin) {

        try {
            Map<String, String> map = userService.register(username, password, isAdmin);
            if (map.containsKey("msg")) {
                model.addAttribute("msg", map.get("msg"));
                return "/login";
            }
            model.addAttribute("msg", "注册成功");
            return "/login";
        } catch (Exception e) {
            logger.error("注册错误" + e.getMessage());
            return "/login";
        }
    }

    @RequestMapping(path = {"/login/"}, method = {RequestMethod.POST})
    public String login(Model model,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletResponse response) {
        try {
            Map<String, String> map = userService.login(username, password);
            if (map.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket", map.get("ticket"));
                cookie.setPath("/");
                response.addCookie(cookie);
                return "redirect:/index";
            } else {
                model.addAttribute("msg", map.get("msg"));
                return "/login";
            }
        } catch (Exception e) {
            logger.error("登录错误" + e.getMessage());
            return "/login";
        }
    }

    @RequestMapping(path = {"/reglogin"}, method = {RequestMethod.GET})
    public String reglogin(Model model) {
        return "/login";
    }

    @RequestMapping(path = {"/logout"}, method = {RequestMethod.GET})
    public String logout(@CookieValue("ticket") String ticket) {
        userService.logout(ticket);
        return "redirect:/reglogin/";
    }



}

