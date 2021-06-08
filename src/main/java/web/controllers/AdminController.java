package web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import web.models.Role;
import web.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import web.service.EmailService;
import web.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUser")
    public User getUser(){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        return userService.getUserByName(username);
    }

    @GetMapping("/allUser")
    public List<User> allUser() {
        return userService.listUser();
    }

    @GetMapping("/allRoles")
    public List<Role> allRoles() {
        return userService.roleUser();
    }

    @GetMapping("/newUser")
    public User newUser() {
        return new User();
    }

//    @GetMapping("/user/{id}")
//    public User getUser(@PathVariable(name = "id") Long id) {
//        return userService.getUser(id);
//    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user){
        userService.add(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        userService.update(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        userService.delete(user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }



//    @RequestMapping("/admin")
//    public ModelAndView adminPage() {
//        ModelAndView mav = new ModelAndView();
//        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
//        String username = loggedInUser.getName();
//        User thisUser = userService.getUserByName(username);
//        mav.setViewName("admin");
//        mav.addObject("thisUser", thisUser);
//        mav.addObject("userList", userService.listUser());
//        mav.addObject("roleList", userService.roleUser());
//        mav.addObject("user", new User());
//        return mav;
//    }

//    @RequestMapping(value = "/new", method = RequestMethod.GET)
//    public ModelAndView addUser(ModelAndView model) {
//        model.addObject("user", new User());
//        model.addObject("roleList", userService.roleUser());
//        model.setViewName("new_user");
//        return model;
//    }

//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public ModelAndView saveUser(@ModelAttribute("user") User user) {
//        userService.add(user);
//        return new ModelAndView("redirect:/admin");
//    }

//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public ModelAndView editUser(@RequestParam(value = "idEdit", required = false) Long id,
//                           @RequestParam(value = "firstNameEdit", required = false) String name,
//                           @RequestParam(value = "lastNameEdit", required = false) String lastName,
//                           @RequestParam(value = "ageEdit", required = false) Integer age,
//                           @RequestParam(value = "emailEdit", required = false) String email,
//                           @RequestParam(value = "passwordEdit", required = false) String password,
//                           @RequestParam(value = "editRole", required = false) String role,
//                           ModelAndView model) {
//
//        model.addObject("roleList", userService.roleUser());
//        User user = userService.getUserById(id);
//        user.setName(name);
//        user.setLastName(lastName);
//        user.setAge(age);
//        user.setEmail(email);
//
//        if (password == null){
//            user.setPassword(user.getPassword());
//        } else {
//            user.setPassword(password);
//        }
//
//        Set<Role> roleSet = new HashSet<>();
//        if (role.contains("ROLE_USER")){
//            roleSet.add(new Role("ROLE_USER"));
//            user.setRoles(roleSet);
//        }
//        if (role.contains("ROLE_ADMIN")) {
//            roleSet.add(new Role("ROLE_ADMIN"));
//            user.setRoles(roleSet);
//        }
//        userService.update(user);
//        return new ModelAndView("redirect:/admin");
//    }
//    @RequestMapping(value = "/editUser{id}", method = RequestMethod.GET)
//    public ModelAndView editUser(ModelAndView model,@PathVariable("id") Long id) {
//        model.addObject("user", userService.getUserById(id));
//        model.addObject("roleList", userService.roleUser());
//        model.setViewName("edit_user");
//        return model;
//    }

//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public ModelAndView updateUser(@ModelAttribute("user") User user) {
//        userService.update(user);
//        return new ModelAndView("redirect:/admin");
//    }

//    @RequestMapping("/delete")
//    public ModelAndView deleteUser(@RequestParam(value = "idDelete", required = false) Long id) {
//        userService.delete(id);
//        return new ModelAndView("redirect:/admin");
//    }


}
