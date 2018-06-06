package edu.zucc.paperManageSys.Interceptor;

import edu.zucc.paperManageSys.Dao.LoginTicketDao;
import edu.zucc.paperManageSys.Dao.UserDao;
import edu.zucc.paperManageSys.Entity.LoginTicketEntity;
import edu.zucc.paperManageSys.Entity.UserEntity;
import edu.zucc.paperManageSys.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class PassportInterceptor implements HandlerInterceptor {

    @Autowired
    LoginTicketDao loginTicketDao;

    @Autowired
    UserDao userDao;

    @Autowired
    HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ticket = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("ticket")) {
                    ticket = cookie.getValue();
                    break;
                }
            }
        }

        if (ticket != null) {
            LoginTicketEntity loginTicketEntity = loginTicketDao.findByTicket(ticket);
            if (loginTicketEntity == null || loginTicketEntity.getExpired().before(new Date()) || loginTicketEntity.getStatus() != 0) {
                return true;
            }

            UserEntity userEntity = userDao.findById(loginTicketEntity.getUserId());
            hostHolder.setUsers(userEntity);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            modelAndView.addObject("user", hostHolder.getUser());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();
    }
}
