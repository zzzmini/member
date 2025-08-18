package com.my.member.controller;

import com.my.member.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("login")
    public String loginForm() {
        return "/user/login";
    }

    @GetMapping("signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "/user/signup";
    }

    @PostMapping("signup")
    public String signup(@ModelAttribute("user") UserDto dto) {
        System.out.println(dto);
        return null;
    }

    @GetMapping("update")
    public String updateForm() {
        return "/user/userUpdate";
    }
}
