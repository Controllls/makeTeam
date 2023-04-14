package com.p2AE.pdf.controller;

import com.p2AE.pdf.domain.Member;
import com.p2AE.pdf.login.SessionManager;
import com.p2AE.pdf.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final SessionManager sessionManager;
    @GetMapping("/login/extraSignIn")
    public String addExtraMemberInfo(Model model,  @RequestParam(name = "email") String email){
        model.addAttribute("email" , email);

        return "login/extraSignIn";
    }

    @PostMapping("/login/extraSignIn")
    public String addExtraMemberInfoP(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Model model){
        String email = request.getParameter("email");
        String nickName = request.getParameter("nickName");
        log.info(email);
        log.info(nickName);
        memberService.update(email, nickName);

        List<Member> byEmail = memberService.findByEmail(email);
        String access_token = byEmail.get(0).getAccess_token();

        sessionManager.createSession(access_token,response);

        model.addAttribute("member" , byEmail.get(0));
        log.info("쿠키생성");
        log.info("add Member");
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request , Model model) {
        sessionManager.expire(request);
        model.addAttribute("member" , new Member());
        return "home";
    }

    @GetMapping("/login")
    public String login(@RequestParam(name = "email") String email,
                                      HttpServletResponse response,
                                      Model model){


        List<Member> byEmail = memberService.findByEmail(email);
        String access_token = byEmail.get(0).getAccess_token();

        sessionManager.createSession(access_token,response);

        model.addAttribute("member" , byEmail.get(0));
        log.info("쿠키생성");

        return "home";
    }

}
