package com.loginregistration.authtest.webApi.controllers;

import com.loginregistration.authtest.business.CustomUserDetails;
import com.loginregistration.authtest.dataAccess.UserRepository;
import com.loginregistration.authtest.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    private UserRepository repo;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        repo.save(user);

        return "register_success";
    }

    @GetMapping("/list_users")
    public String viewUsersList(Model model) {
        List<User> userList = repo.findAll();
        model.addAttribute("userList", userList);

        return "all_users";
    }

    @GetMapping("/user_panel")
    public String viewUserPanel(Model model) {
        List<User> userList = repo.findAll();
        model.addAttribute("userList", userList);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        String userId = "";
        if (principal instanceof CustomUserDetails) {
            userId = ((CustomUserDetails) principal).getUsername();
        } else {
            userId = principal.toString();
        }

        model.addAttribute("userId", userId);

        return "users";
    }
}
