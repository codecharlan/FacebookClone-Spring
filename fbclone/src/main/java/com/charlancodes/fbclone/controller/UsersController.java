//package com.charlancodes.fbclone.controller;
//
//import com.charlancodes.fbclone.model.User;
//import com.charlancodes.fbclone.service.IUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//@RequestMapping("/api/users")
//public class UsersController {
//    private final IUserService userService;
//
//    @Autowired
//    public UsersController(IUserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/{id}")
//    public String getUserById(@PathVariable long id, Model model) {
//        Optional<User> user = userService.getUserById(id);
//        user.ifPresent(value -> model.addAttribute("user", value));
//        return "user-details";
//    }

//    @GetMapping("/email/{email}")
//    public String getUserByEmail(@PathVariable String email, Model model) {
//        Optional<User> user = userService.getUserByEmail(email);
//        user.ifPresent(value -> model.addAttribute("user", value));
//        return "user-details";
//    }
//
//    @GetMapping("/{get-users}")
//    public String getAllUsers(Model model) {
//        List<User> users = userService.getAllUser();
//        model.addAttribute("users", users);
//        return "user-list";
//    }
//
//    @PostMapping("/{create-user}")
//    public String createUser(@ModelAttribute("user") User user) {
//        User createdUser = userService.createUser(user);
//        return "redirect:/api/users";
//    }
//
//    @PutMapping("/{update-user}")
//    public ResponseEntity<User> updateUser(@RequestBody User user) {
//        User updatedUser = userService.updateUser(user);
//        return ResponseEntity.ok(updatedUser);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
//        Optional<User> user = userService.getUserById(id);
//        if (user.isPresent()) {
//            userService.deleteUser(user.get());
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
// UsersController.java
//+ createdUser.getId()

package com.charlancodes.fbclone.controller;

import com.charlancodes.fbclone.model.User;
import com.charlancodes.fbclone.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/users")
public class UsersController {
    private final IUserService userService;

    @Autowired
    public UsersController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }
    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") User user) {
        User createdUser = userService.createUser(user);
        return "signup-success";
    }
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user) {
        System.out.println("i am here");
        User foundUser = userService.getUserByEmail(user.getEmailAddress());
        if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
            return "dashboard";
        } else {
            return "login-error";
        }
    }
    @PutMapping("/update-user")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        System.out.println("hey you mehn");
        User updatedUser = userService.updateUser(user);
        System.out.println("hey you mehn");
        return ResponseEntity.ok(updatedUser);
    }
    @GetMapping("/{id}")
    public String getUserById(@PathVariable long id, Model model) {
        Optional<User> user = userService.getUserById(id);
        System.out.println("hey you mehn");
        user.ifPresent(value -> model.addAttribute("user", value));
        return "user-details";
    }

    @GetMapping("/email/{email}")
    public String getUserByEmail(@PathVariable String email, Model model) {
        User user = userService.getUserByEmail(email);
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "user-details";
    }


    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "user-list";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            userService.deleteUser(user.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

