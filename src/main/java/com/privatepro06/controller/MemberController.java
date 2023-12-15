package com.privatepro06.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/")
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("login")
    public String loginPage(){
        return "/member/login";
    }

}
