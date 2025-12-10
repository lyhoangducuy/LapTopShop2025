package com.latptop.flexuy.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.latptop.flexuy.ConfigSecurity.Utility;
import com.latptop.flexuy.exception.UsernameNotFoundException;
import com.latptop.flexuy.model.User;
import com.latptop.flexuy.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MailSenderController {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserService userService;
    @PostMapping("/forgot-password")
    public String processForgotPasswordForm(HttpServletRequest request,Model m   ) {
        String email=request.getParameter("email");
        String token=RandomString.make(45);
        try {
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink=Utility.getSiteURL(request)+"/reset-password?token="+token;
            try {
                sendEmail(email,resetPasswordLink);
                m.addAttribute("error", "Da gui link reset mat khau toi email: " + email);
            } catch (UnsupportedEncodingException e) {
                m.addAttribute("error", e.getMessage());
            } catch (MessagingException e) {
                m.addAttribute("error", e.getMessage());
            }
        } catch (UsernameNotFoundException u) {
            m.addAttribute("error", u.getMessage());
        }
        return "auth/resetPassword";
    }
    @GetMapping("/reset-password")
    public String getFormReset(@Param(value = "token") String token, Model model) {
        User user=userService.findByResetPasswordToken(token);
        if (user==null){
            model.addAttribute("error","Invalid Token");
            return "redirect:/forgot-password";
        }

        return "auth/resetForm";
    }
    @PostMapping("/reset-password")
    public String changePass(Model model,HttpServletRequest request) throws UsernameNotFoundException {
        String newPassword=request.getParameter("password");
        String token=request.getParameter("token");
        if (newPassword!=null && !newPassword.isEmpty()){
            User user=userService.findByResetPasswordToken(token);
            if (user!=null){
                userService.updatePassword(user, newPassword);
                userService.updateResetPasswordToken(null, user.getEmail());
                userService.save(user);
                return "redirect:/login";
               
            } 
        }
         return "redirect:/forgot-password?token="+token;
    }
    private void sendEmail(String email, String resetPasswordLink) throws UnsupportedEncodingException, MessagingException {
        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message);
        helper.setFrom("fangame1711@gmail.com", "Laptopshop Support"); 
        helper.setTo(email);
        String subject="Here's the link to reset your password";
        String content="<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + resetPasswordLink + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }

}
