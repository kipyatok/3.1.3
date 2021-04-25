package web.controllers;

import org.springframework.web.bind.annotation.*;
import web.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView home() {
        List<User> userList = userService.listUser();
        ModelAndView mav = new ModelAndView();
        mav.addObject("userList", userList);
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView addUser(ModelAndView model) {
        model.addObject("user", new User());
        model.setViewName("new_user");
        return model;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/editUser{id}", method = RequestMethod.GET)
    public ModelAndView editUser(ModelAndView model,@PathVariable("id") Long id) {
        model.addObject("user", userService.getUser(id));
        model.setViewName("edit_user");
        return model;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ModelAndView("redirect:/");
    }


}
