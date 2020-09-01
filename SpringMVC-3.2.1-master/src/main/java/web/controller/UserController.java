package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import web.Model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String getHomePage() {
        return "index";
    }

    //страница авторизации
    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    //список юзеров
    @GetMapping(value = "/admin")
    public ModelAndView allUsers(){
        List<User> users = userService.listUser();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("users",users);
        return modelAndView;
    }

    //удаление
    @GetMapping("/delete/{id}")
    public String removeUser(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "admin";
    }

    //изменение
    @GetMapping("edit/{id}")
    public ModelAndView editPage(@PathVariable("id") int id){
        User user = userService.getUserById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editUser(@ModelAttribute("user") User user){
        user.setRoles(userService.getUserById(user.getId()).getRoles());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        userService.saveUser(user);
        return modelAndView;
    }

    //добавление
    @GetMapping("/add/")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "admin";
    }
    @GetMapping("/user")
    public String userPage(){
        return "user";
    }
}
