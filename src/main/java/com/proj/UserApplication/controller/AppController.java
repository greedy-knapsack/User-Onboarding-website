package com.proj.UserApplication.controller;

import com.proj.UserApplication.model.User;
import com.proj.UserApplication.repository.UserRepository;
import com.proj.UserApplication.service.EmailSenderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
public class AppController {
    @Autowired
    private UserRepository repository;
    @Autowired
    private EmailSenderService emailSenderService;
    @GetMapping("")
    public String viewHomePage(){
        return "index";
    }

//    Logger logger = (Logger) LoggerFactory.getLogger(AppController.class);


    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user",new User());
        return "signup_form";
    }
    @PostMapping("/process_register")
    public String processRegistration(User user){
//        logger.info("user details"+ user);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword =encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repository.save(user);
        emailSenderService.sendSimpleEmail(user.getEmail(),
                "Hey! Welcome to Onboarding.com,Your account has been sent  for approval!","Welcome User");
        return "register_success";
    }
    @GetMapping("/api/users")
    public String user(){
        return "welcome_user";
    }
    @GetMapping("/api/admin/users")
    public String listUsers(Model model){
        List<User> listUsers = repository.findAll();
        model.addAttribute("listUsers",listUsers);
        return "users";
    }
//    @PostMapping("/approveUser")
//    public void approvedUser(@RequestParam Long id){
//        User user = repository.findById(id).orElse(null);
//        if(user!=null){
//            user.setApproved(true);
//            repository.save(user);
//            emailSenderService.sendSimpleEmail(user.getEmail(),
//                    "Your account has been successfully verified! You can now login","Verified");
//        }
//    }
//    @PostMapping("/unapproveUser")
//    public String unapprovedUser(@RequestParam Long id){
//        User user = repository.findById(id).orElse(null);
//        if(user==null){
//            user.setApproved(false);
//            repository.save(user);
//        }
//        return "Not Verified yet!";
//    }
}
