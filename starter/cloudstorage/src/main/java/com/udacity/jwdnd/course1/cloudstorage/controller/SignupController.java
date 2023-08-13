package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;

    public SignupController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @GetMapping()
    public String signupView() {
        return "signup";
    }

    @PostMapping()
    public String signup(
            @ModelAttribute User user,
            Model model,
            RedirectAttributes redirectAttrs
    ) {
        String error = "";
        if (!userService.isUsernameAvailable(user.getUsername())) {
            error = "The username already exists!";
        }

        if (error.isEmpty()) {
            int numOfRows = userService.createUser(user);
            if (numOfRows < 0) {
                error = "Sign up is failed! Please try again.";
            }
        }

        if (error.isEmpty()) {
            redirectAttrs.addFlashAttribute("success", "Sign up is successfully!");
            return "redirect:/login";
        } else {
            model.addAttribute("error", error);
            return "signup";
        }
    }

}
