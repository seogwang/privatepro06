package com.privatepro06.controller;

import com.privatepro06.entity.Member;
import com.privatepro06.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.Principal;

@Aspect
@Component
@Log4j2
@RequiredArgsConstructor
public class AOP1 {

    private final MemberService memberService;

    @Pointcut("execution(String com.privatepro06..*Controller.*(..))")
    private void cut() {
        log.info("----포인트 컷 시작 위치----");
    }

    @Before("cut()")
    private void before(JoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Principal principal = request.getUserPrincipal();

        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof Model) {
                Model model = (Model) arg;
                if (principal != null) {
                    String email = principal.getName();
                    Member member = memberService.findByEmail(email);
                    model.addAttribute("member", member);
                    log.info("AOP1 Aspect 적용 메서드 정보 : {}", joinPoint.getSignature());
                }
            }
        }
    }
}
