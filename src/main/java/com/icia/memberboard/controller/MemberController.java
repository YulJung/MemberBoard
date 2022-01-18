package com.icia.memberboard.controller;

import com.icia.memberboard.dto.MemberDetailDTO;
import com.icia.memberboard.dto.MemberSaveDTO;
import com.icia.memberboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService ms;

    //로그인 페이지 호출
    @GetMapping("/login")
    public String loginForm (){
        return "member/login";
    }
    //로그인 실행
    @PostMapping("/login")
    public String login(@ModelAttribute MemberDetailDTO memberDetailDTO, HttpSession session){
        boolean result = ms.login(memberDetailDTO);
        if(!result) return "redirect:member/login";
        else {
            session.setAttribute("memberEmail",memberDetailDTO.getMemberEmail());
            return "board/findAll";

        }

    }
    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }
    //회원가입 페이지
    @GetMapping("/save")
    public String saveForm (){
        return "member/save";
    }
    //회원가입 실행
    @PostMapping("/save")
    public String save(@ModelAttribute MemberSaveDTO memberSaveDTO){
      ms.save(memberSaveDTO);
      return "member/login";
    }

}
