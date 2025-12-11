package com.latptop.flexuy.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.latptop.flexuy.ConfigSecurity.Utility;
import com.latptop.flexuy.exception.UsernameNotFoundException;
import com.latptop.flexuy.model.User;
import com.latptop.flexuy.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
        
    }
    @RequestMapping("/login")
    public String getLogin(){
        return "auth/login";
    }

    @GetMapping("/register")
    public String getRegister(Model model){ // 👈 Cần thêm tham số Model
        model.addAttribute("newUser", new User()); // 👈 Khởi tạo đối tượng User rỗng
        return "auth/register";
    }
    @PostMapping("/register")
    public String postRegister(@ModelAttribute("newUser") User user) {
        if(userService.createUser(user))
            return "redirect:/login";
        return "redirect:/register";
    }

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm(Model model) {
        model.addAttribute("pageTitle","Forgot Password");
        return "auth/resetPassword";
    }
 
    
    
    
}
