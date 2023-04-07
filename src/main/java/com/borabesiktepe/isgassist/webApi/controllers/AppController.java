package com.borabesiktepe.isgassist.webApi.controllers;

import com.borabesiktepe.isgassist.business.CustomUserDetails;
import com.borabesiktepe.isgassist.dataAccess.UserRepository;
import com.borabesiktepe.isgassist.dataAccess.WorkplaceRepository;
import com.borabesiktepe.isgassist.entities.User;
import com.borabesiktepe.isgassist.entities.Workplace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/login")
    public String viewLoginForm() {
        return "login_form";
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

    @GetMapping("/create_workplace")
    public String viewCreateWorkplaceForm() {

        return "user_createworkplace";
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

    @GetMapping("/riskassesment/{workplaceId}")
    public String viewWorkplaceRiskAssesment(Model model, @PathVariable("workplaceId") int workplaceId) {

        Optional<Workplace> workplaceOptional = workplaceRepository.findById(workplaceId);

        if (workplaceOptional.isPresent()) {
            Workplace workplace = workplaceOptional.get();
            model.addAttribute("workplace", workplace);
        }

        return "workplace_riskassesment";
    }

    @GetMapping("/trainings/{workplaceId}")
    public String viewWorkplaceTrainings(Model model, @PathVariable("workplaceId") int workplaceId) {

        Optional<Workplace> workplaceOptional = workplaceRepository.findById(workplaceId);

        if (workplaceOptional.isPresent()) {
            Workplace workplace = workplaceOptional.get();
            model.addAttribute("workplace", workplace);
        }

        return "workplace_trainings";
    }

    @GetMapping("/riskgraphs/{workplaceId}")
    public String viewWorkplaceRiskGraphs(Model model, @PathVariable("workplaceId") int workplaceId) {

        Optional<Workplace> workplaceOptional = workplaceRepository.findById(workplaceId);

        if (workplaceOptional.isPresent()) {
            Workplace workplace = workplaceOptional.get();
            model.addAttribute("workplace", workplace);
        }

        return "workplace_riskgraphs";
    }
}
