package com.icia.memberboard.controller;

import com.icia.memberboard.dto.MemberDetailDTO;
import com.icia.memberboard.dto.MemberSaveDTO;
import com.icia.memberboard.dto.MemberUpdateDTO;
import com.icia.memberboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

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
        if(!result) return "redirect:/login";
        else {
            session.setAttribute("memberEmail",memberDetailDTO.getMemberEmail());
            if(memberDetailDTO.getMemberEmail().equals("admin")){
                return "redirect:/member/admin";
            }else{
                return "redirect:/board/";
            }

        }

    }
    //관리자 페이지
    @GetMapping("/admin")
    public String adminPage(){
        return "member/admin";
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
    public String save(@ModelAttribute MemberSaveDTO memberSaveDTO) throws IOException {
      ms.save(memberSaveDTO);
      return "member/login";
    }
    //이메일 중복체크
    @PostMapping("/emailCheck")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail")String memberEmail){
        String result = ms.emailCheck(memberEmail);
        return result;
    }
    //전체 회원 조회
    @GetMapping("/findAll")
    public String findAll(Model model){
        List<MemberDetailDTO> memberList = ms.findAll();
        model.addAttribute("memberList",memberList);
        return "member/findAll";

    }
    //아이디 삭제
    @DeleteMapping("{memberId}")
    public ResponseEntity deleteById(@PathVariable Long memberId){
        ms.delete(memberId);
        return new ResponseEntity(HttpStatus.OK);
    }
    //마이페이지 호출
    @GetMapping("/mypage/{memberEmail}")
    public String mypage(@PathVariable String memberEmail,Model model){
        MemberDetailDTO member = ms.findByMemberEmail(memberEmail);
        System.out.println(member.toString()+"member");
        model.addAttribute("member",member );
        return "member/mypage";
    }
    @PostMapping("/update")
    public String Update(@ModelAttribute MemberUpdateDTO memberUpdateDTO) throws IOException {
        System.out.println("memberUpdateDTO = " + memberUpdateDTO.toString());
        Long memberId = ms.update(memberUpdateDTO);
        return "redirect:/member/mypage/"+memberUpdateDTO.getMemberEmail();
    }
}
