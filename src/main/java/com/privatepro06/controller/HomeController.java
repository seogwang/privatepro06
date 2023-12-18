package com.privatepro06.controller;

import com.privatepro06.entity.Member;
import com.privatepro06.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    @GetMapping("")
    public String Home(Principal principal, Model model){
        if(principal != null){
            String email = principal.getName();
            Member member = memberService.findByEmail(email);
            model.addAttribute("member", member);
        }
        return "index";
    }
}
