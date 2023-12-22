package com.privatepro06.service;

import com.privatepro06.dto.MemberFormDTO;
import com.privatepro06.entity.Member;
import com.privatepro06.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public Member findByEmail(String email){
        Member member = memberRepository.findByEmail(email);
        return member;
    }

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    public MemberFormDTO modify(MemberFormDTO dto){
        Member result = memberRepository.findByEmail(dto.getEmail());
        result.edit(dto, passwordEncoder);
        memberRepository.save(result);
        MemberFormDTO memberFormDTO = modelMapper.map(result, MemberFormDTO.class);
        return memberFormDTO;
    }

    public void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public boolean memberDupValidation(String email){
        Member findMember = memberRepository.findByEmail(email);
        if(findMember != null){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);
        if(member == null){
            throw new UsernameNotFoundException(email);
        }
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
