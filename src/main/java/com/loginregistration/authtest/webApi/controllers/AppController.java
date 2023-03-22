package com.loginregistration.authtest.webApi.controllers;

import com.loginregistration.authtest.business.CustomUserDetails;
import com.loginregistration.authtest.business.abstracts.WorkplaceService;
import com.loginregistration.authtest.dataAccess.UserRepository;
import com.loginregistration.authtest.dataAccess.WorkplaceRepository;
import com.loginregistration.authtest.entities.User;
import com.loginregistration.authtest.entities.Workplace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.Optional;

@Controller
public class AppController {
    @Autowired
    private UserRepository repo;

    @Autowired
    private WorkplaceRepository workplaceRepository;

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

    @GetMapping("/user_panel")
    public String viewUserPanel(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        int userId = 0;
        if (principal instanceof CustomUserDetails) {
            userId = ((CustomUserDetails) principal).getUser().getId();
        }

        model.addAttribute("userId", userId);

        return "user_panel";
    }

    @GetMapping("/workplace_panel/{workplaceId}")
    public String viewWorkplace(Model model, @PathVariable("workplaceId") int workplaceId) {

        Optional<Workplace> workplaceOptional = workplaceRepository.findById(workplaceId);

        if (workplaceOptional.isPresent()) {
            Workplace workplace = workplaceOptional.get();
            model.addAttribute("workplace", workplace);
        }

        return "workplace_panel";
    }

    @GetMapping("/create_workplace")
    public String viewCreateWorkplaceForm() {

        return "createworkplace_form";
    }

    @GetMapping("/trainings")
    public String viewWorkplaceTrainings() {

        return "workplace_trainings";
    }
}
