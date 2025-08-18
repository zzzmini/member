package com.my.member.controller;

import com.my.member.dto.UserDto;
import com.my.member.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    // UserService를 생성자주입 방식으로 추가
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

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
        userService.saveUser(dto);
        return "redirect:/";
    }

    @GetMapping("list")
    public String userList(Model model) {
        List<UserDto> list = userService.findAllUser();
        model.addAttribute("list", list);
        return "/user/userList";
    }

    @PostMapping("delete")
    public String userDelete(@RequestParam("email") String email) {
        userService.deleteUser(email);
        return "redirect:/user/list";
    }

    @PostMapping("update")
    public String updateUserForm(@RequestParam("email") String email,
                                 Model model) {
        // 사용자 정보를 하나 찾아서 온다.
        UserDto user = userService.findOneUser(email);
        model.addAttribute("user", user);
        return "/user/userUpdate";
    }

    @PostMapping("updateUser")
    public String updateUser(@ModelAttribute("user")UserDto user) {
        userService.saveUser(user);
        return "redirect:/user/list";
    }

    @PostMapping("login")
    public String login(UserDto dto) {
        // 1. dto.email을 갖고 User 검색...
        UserDto loginResult = userService.findOneUser(dto.getEmail());
        // 2. 해당 유저가 있는지 확인한다.
        if (ObjectUtils.isEmpty(loginResult)) {
            // 로그인 실패
            return "/user/login";
        } else {
            // 3. password 가 맞는지 확인한다.
            // 틀리면 : login form 보여준다.
            // 맞으면 : 세션을 만들어 준다.
        }
        return null;
    }
}
