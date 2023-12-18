package com.privatepro06.controller;

import com.privatepro06.dto.MemberFormDTO;
import com.privatepro06.entity.Member;
import com.privatepro06.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member/")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

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
        try {
            Member member = Member.createdMember(memberFormDTO, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/join";
        }
        return "redirect:/";
    }

    @PostMapping(value = "/dup")
    @ResponseBody
    public boolean memberDupValidtion(@RequestBody MemberFormDTO data){
        boolean result = memberService.memberDupValidation(data.getEmail());
        return result;
    };

    @GetMapping(value = "/login")
    public String lodinMember(){
        return "member/login";
    }

    @GetMapping(value = "login/error")
    public String loginError(Model model) {
        model. addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "member/login";
    }
}
