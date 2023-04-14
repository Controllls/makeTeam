package com.p2AE.pdf.controller;

import com.p2AE.pdf.domain.Member;
import com.p2AE.pdf.login.SessionManager;
import com.p2AE.pdf.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PageController {

    private final MemberService memberService;
    private final SessionManager sessionManager;


    // @SessionAttribute 사용
//    @GetMapping("/")
//    public String homeLoginV2(HttpServletRequest request, Model model) {
//        log.info("home controller");
//        //room 리스트 업
//        List<Member> members = memberService.findMembers();
//        model.addAttribute("memberList", members);
//
//        // 세션 관리자에 저장된 회원 정보 조회
//        // Member 로 캐스팅
//
//
//        // 로그인
////        if (member == null) {
////            model.addAttribute("member", new Member());
////            return "home";
////        }
////        model.addAttribute("member", new Member());
//        return "home";
//    }
//    @RequestMapping("/")
//    public String home(Model model) {
//
//
//
//
//        return "home";
//    }

    @RequestMapping("/room/{id}")
    public String shopId(@PathVariable("id") Long id , Model model) {
        log.info("room id controller");
        Member one = memberService.findOne(id);
        model.addAttribute("memberData", one);
        return "meet/MeetingRoom";
    }

    @RequestMapping("/room")
    public String shop(Model model) {
        log.info("room controller");
        List<Member> members = memberService.findMembers();
        model.addAttribute("memberList", members);
        return "meet/MeetList";
    }

    @RequestMapping("/admin/dashBoard")
    public String adminDashBoard(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("memberList", members);
        log.info("admin controller");
        return "admin/dashBoard";
    }

    @RequestMapping("/admin/orders")
    public String adminOrders(Model model) {

        return "admin/orders";
    }

    @RequestMapping("/qwe")
    public String qwe(Model model) {

        return "qwe";
    }

    @RequestMapping("/login/signIn")
    public String signIn(Model model) {

//        return "login/googleSignIn";
        return "login/modal";
    }



    @RequestMapping("/googleSignIn")
    public String googleSignIn(Model model) {
        return "login/googleSignIn";
    }

    @GetMapping("/uploads/inputForm")
    public String inputForm(Model model , HttpServletRequest request){
        String session = sessionManager.getSession(request);
        List<Member> byAccessToken = memberService.findByAccessToken(session);


        if (byAccessToken == null){
            model.addAttribute("member" , new Member());
            log.info("회원없음");
            return "home";
        }

        Member member = byAccessToken.get(0);

        model.addAttribute("member", member);

        return "uploads/inputForm";
    }

}



