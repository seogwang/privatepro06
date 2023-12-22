package com.privatepro06.controller;

import com.privatepro06.entity.Member;
import com.privatepro06.service.MemberService;
import jakarta.persistence.MappedSuperclass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
abstract class BaseController {

    private final MemberService memberService;

    public Member info(Principal principal, Model model){
        Member member = memberService.findByEmail(principal.getName());
        return member;
    }

}
