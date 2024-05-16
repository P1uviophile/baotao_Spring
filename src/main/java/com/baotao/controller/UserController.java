package com.baotao.controller;
import com.baotao.bean.User;
import com.baotao.service.IUserService;
import com.baotao.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/user")
public class UserController {

    private IUserService userService = new UserServiceImpl();

    @GetMapping
    public String defaultHandler(Model model) {
        // 默认查询用户信息
        queryUserAll(model);
        return "user/user";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdateUser(@ModelAttribute User user, @RequestParam String user_name_root, Model model) {
        Integer count = -1;
        if ((user.getUser_id() != null)) {
            // 表示是更新
            System.out.println(user.getUser_id());
            count = userService.updateUser(user, user_name_root);
        } else {
            count = userService.addUser(user);
        }

        if (count > 0) {
            System.out.println("插入成功");
            // 查询成功，再做一次查询操作
            queryUserAll(model);
        } else {
            // 表示插入失败
            System.out.println("插入失败QAQ");
            // 跳转到失败页面
            return "wrongOperate";
        }

        return "user/user";
    }

    @GetMapping("/delete/{user_id}")
    public String deleteUser(@PathVariable Integer user_id) {
        // 通过Service处理删除操作
        Integer count = userService.deleteByUser_id(user_id);
        System.out.println(count);
        // 做一个重定向查询所有用户
        return "redirect:/user";
    }

    @GetMapping("/query")
    public String queryUserCondition(@RequestParam String condition, Model model) {
        // 通过service查询
        List<User> list = userService.getUserCondition(condition);
        // 把查询到的信息绑定在 model 中
        model.addAttribute("list", list);
        // 页面跳转到user.jsp中
        System.out.println(list);
        return "user/user";
    }
    @GetMapping("/userUpdate")
    public String showUserUpdatePage() {
        return "user/userUpdate";
    }
    private void queryUserAll(Model model) {
        // 通过service查询所有的用户信息
        List<User> list = userService.getUser(null);
        // 把查询到的信息绑定在 model 中
        model.addAttribute("list", list);
    }
    @GetMapping("/queryById/{user_id}")
    public String queryById(@PathVariable Integer user_id, Model model) {
        // 通过service查询
        User user = userService.queryByUser_id(user_id);
        // 把查询到的信息绑定在 model 中
        model.addAttribute("user", user);
        // 页面跳转到user.jsp中
        System.out.println(user.toString());
        return "user/userUpdate";
    }
    @PostMapping("/register")
    public String register(@RequestParam String user_name, String password) {
        // 通过service查询
        User user = new User();
        user.setUser_id(null);
        user.setUser_name(user_name);
        user.setPassword(password);
        user.setAdmin(0);
        int count = userService.addUser(user);
        System.out.println(user.toString());
        if (count > 0) {
            System.out.println("插入成功");
        } else {
            // 表示插入失败
            System.out.println("插入失败QAQ");
            // 跳转到失败页面
            return "wrongOperate";
        }
        return "login";
    }
}
