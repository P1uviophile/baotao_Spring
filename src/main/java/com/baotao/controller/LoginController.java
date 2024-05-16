package com.baotao.controller;

import com.baotao.bean.User;
import com.baotao.service.IUserService;
import com.baotao.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    private IUserService userService = new UserServiceImpl();

    private HttpSession session;

    @PostMapping("/loginController")
    public String authenticate(String userName, String password, Model model, HttpSession session) {
        LOGGER.info("Received userName: " + userName);
        LOGGER.info("Received password: " + password);

        // 调用Service的相关方法来实现处理
        User user = userService.checkUserNameAndPassword(userName, password);

        // 根据验证的结果来做出对应的相应
        if (user != null) {
            // 登录成功
            System.out.println("Login successful");
            user.setPassword(null);
            session.setAttribute("SESSION_LOGIN_USER", user);
            session.setAttribute("admin", user.getAdmin() == 1);
            session.setAttribute("loginUserId",user.getUser_id());
            session.setAttribute("loginUser","true");
            LOGGER.info("User: " + user);
            LOGGER.info("Admin: " + session.getAttribute("admin"));
            // 在用户登录成功后将用户信息存储到Session中
            session.setAttribute("user", user);
            return "userIndex";
        } else {
            // 登录失败
            System.out.println("Login failed");
            model.addAttribute("msg", "账号密码错误");
            return "login";
        }
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
