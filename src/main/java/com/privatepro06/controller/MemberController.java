package com.privatepro06.controller;

import com.privatepro06.dto.MemberFormDTO;
import com.privatepro06.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member/")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("login")
    public String loginPage(){
        return "/member/login";
    }

    @GetMapping(value = "/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDTO", new MemberFormDTO());
        return "member/join";
    }

    @PostMapping(value = "/joinPro")
    public String newMember(@Valid MemberFormDTO memberFormDTO, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "/member/join";
        }
        
    }
}
