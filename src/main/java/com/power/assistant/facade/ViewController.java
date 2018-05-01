package com.power.assistant.facade;

import com.power.assistant.base.Constants;
import com.power.assistant.base.DataModel;
import com.power.assistant.base.MD5;
import com.power.assistant.core.annotation.Authentication;
import com.power.assistant.core.service.MenuService;
import com.power.assistant.core.service.UserService;
import com.power.assistant.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ViewController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;

    @Value("${config.ueditor.serverPath}")
    private String serverPath;

    @RequestMapping("")
    public ModelAndView index(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.SESSION);
        if (user != null) {
            String menus = menuService.userMenus(user.getId());
            ModelAndView model = new ModelAndView("index");
            model.getModelMap().addAttribute("menu", menus)
                    .addAttribute("serverPath", serverPath);
            return new ModelAndView("index");
        }
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("username") String username,
                              @RequestParam("pwd") String pwd,
                              HttpServletRequest request,
                              HttpServletResponse response
    ) {
        ModelAndView model = new ModelAndView("login");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(pwd)) {
            model.getModelMap().addAttribute("msg","用户名或者密码为空");
        }
        try {
            User u = userService.authentication(username,pwd);
            request.getSession().setAttribute(Constants.SESSION,u);

            response.sendRedirect("home");
            return null;
        } catch (Exception e) {
            model.getModelMap().addAttribute("msg",e.getMessage());
        }
        return model;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute(Constants.SESSION);
        ModelAndView model = new ModelAndView("login");
        return model;
    }

    @Authentication
    @RequestMapping("/home")
    public ModelAndView home(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.SESSION);
        String menus = menuService.userMenus(user.getId());
        ModelAndView model = new ModelAndView("index");
        model.getModelMap().addAttribute("menu", menus)
                .addAttribute("serverPath", serverPath);
        return model;
    }

    @Authentication
    @RequestMapping("/jump")
    public ModelAndView jump(@RequestParam("source") String source, String param) {
        ModelAndView model = new ModelAndView(source);
        if (!StringUtils.isEmpty(param)) {
            model.getModelMap().addAttribute("param", param);
        }
        return model;
    }

    @PostMapping("updatePassword")
    public DataModel updatePassword(@RequestParam("password") String password,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(Constants.SESSION);
        try {
            return DataModel.ok(userService.updatePassword(user.getId(), MD5.crypt(password)));
        } catch (Exception e) {
            return DataModel.error(e.getMessage());
        }
    }
}
