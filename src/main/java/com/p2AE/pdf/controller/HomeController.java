package com.p2AE.pdf.controller;

import com.p2AE.pdf.domain.Member;
import com.p2AE.pdf.login.SessionManager;
import com.p2AE.pdf.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final MemberService memberService;
    private final SessionManager sessionManager;

    @GetMapping("/")
    public String homeLogin(HttpServletRequest request,
                            Model model) {
        String session = sessionManager.getSession(request);
        log.info(session);
        if (session == null){
            model.addAttribute("member" , new Member());
            log.info("세션 없음");
            return "home";
        }

        //로그인
        List<Member> byAccessToken = memberService.findByAccessToken(session);


        if (byAccessToken == null){
            model.addAttribute("member" , new Member());
            log.info("회원없음");
            return "home";
        }

        Member member = byAccessToken.get(0);

        model.addAttribute("member", member);
        log.info(member.getNickName());
        return "home";
    }


//    @GetMapping("/")
//    public String home(HttpServletRequest request, Model model) {
//
//
//
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


}

