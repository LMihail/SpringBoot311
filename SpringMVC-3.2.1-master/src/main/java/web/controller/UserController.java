package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import web.Model.User;

import web.service.UserServiceImpl;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value = "/")
    public String getHomePage() {
        return "index";
    }

    //страница авторизации
    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    //страница админа
    @GetMapping(value = "/admin")
    public ModelAndView allUsers(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("users",userService.listUser());
        return modelAndView;
    }

    //удаление
    @GetMapping("/admin/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        userService.delete(id);
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }

    //изменение
    @GetMapping("/admin/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") int id){
        User user = userService.getUserById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/admin/edit")
    public ModelAndView editUser(@ModelAttribute("user") User user){
        user.setRoles(userService.getUserById(user.getId()).getRoles());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        userService.saveUser(user);
        return modelAndView;
    }

    //добавление
    @GetMapping("/admin/add")
    public ModelAndView addUser() {
        ModelAndView  modelAndView = new ModelAndView();
        modelAndView.setViewName("add");
        modelAndView.addObject("user",new User());
        return modelAndView;
    }

    @PostMapping("/admin/add")
    public ModelAndView addUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        userService.saveUser(user);
        return modelAndView;
    }
    @GetMapping("/user")
    public String userPage(){
        return "user";
    }
}
