package com.appLogin.appLogin.controller;


import com.appLogin.appLogin.model.User;
import com.appLogin.appLogin.repository.UserRepository;
import com.appLogin.appLogin.service.CookieService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute("name", CookieService.getCookie(request, "name"));
        return "index";
    }

    @PostMapping("/logar")
    public String logar(User user, Model model, HttpServletResponse response) throws UnsupportedEncodingException {
        User userLogin = userRepository.Login(user.getEmail(), user.getPassword());

        if (userLogin != null) {
            CookieService.setCookie(response, "id", String.valueOf(userLogin.getId()), 10000);
            CookieService.setCookie(response, "name", userLogin.getName(), 10000);


            return "redirect:/";
        }

        model.addAttribute("msg", "Invalid email or password");
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@Valid User user, BindingResult result) {

        if (result.hasErrors()) {

            return "redirect:/register";
        }
        userRepository.save(user);

        return "redirect:/login";
    }
}
