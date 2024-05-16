package com.baotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpSession session, SessionStatus sessionStatus) {
        // 清除session中的数据
        session.invalidate();

        // 标记当前会话完成
        sessionStatus.setComplete();

        // 重定向到登录页面
        return "login";
    }
}
