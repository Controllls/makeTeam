package com.p2AE.pdf.controller;

import com.p2AE.pdf.config.SocialLoginType;
import com.p2AE.pdf.domain.GoogleOauth;
import com.p2AE.pdf.domain.Member;
import com.p2AE.pdf.dto.GetSocialOAuthRes;
import com.p2AE.pdf.dto.GoogleOAuthToken;
import com.p2AE.pdf.dto.GoogleSignIn;
import com.p2AE.pdf.dto.GoogleUser;
import com.p2AE.pdf.service.MemberService;
import com.p2AE.pdf.service.OAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    private final OAuthService oAuthService;
    private final HttpServletResponse response;
    private final MemberService memberService;
    private final GoogleOauth googleOauth;

    /**
     * 유저 소셜 로그인으로 리다이렉트 해주는 url
     * [GET] /auth
     * @return void
     */

    @GetMapping("auth/{socialLoginType}") //GOOGLE이 들어올 것이다.
    public void socialLoginRedirect(@PathVariable(name="socialLoginType") String SocialLoginPath) throws IOException {
        SocialLoginType socialLoginType= SocialLoginType.valueOf(SocialLoginPath.toUpperCase());
        log.info(">> 사용자로부터 SNS 로그인 요청을 받음 :: {} Social Login", socialLoginType);
        oAuthService.request(socialLoginType);
    }
    /**
     * 유저 소셜 로그인 후 리다이렉트
     * [GET] /auth/{}/callback
     *
     * @return void
     */

//    @GetMapping(value = "auth/{socialLoginType}/callback")
//    public GetSocialOAuthRes callback(
//            @PathVariable(name = "socialLoginType") String socialLoginPath,
//            @RequestParam(name = "code") String code
//            ) throws IOException{
//        log.info(">> 소셜 로그인 API 서버로부터 받은 code :: {}", code);
//        SocialLoginType socialLoginType= SocialLoginType.valueOf(socialLoginPath.toUpperCase());
////        GetSocialOAuthRes getSocialOAuthRes=oAuthService.oAuthLogin(socialLoginType,code);
//        GetSocialOAuthRes getSocialOAuthRes = oAuthService.oAuthLogin(socialLoginType, code);
////        Cookie idCookie = new Cookie("access_token", getSocialOAuthRes.getAccessToken());
////        response.addCookie(idCookie);
//
//        return getSocialOAuthRes;
//        //todo 검증완료면 닉네임받는 로직 front까지
//
//
////        Member member;
////        List<Member> byEmail = memberService.findByEmail(UserInfo.getEmail());
////        if (byEmail.size() != 0){
////            member = byEmail.get(0);
////
////        }else{
////            member = new Member();
////            member.setEmail(UserInfo.getEmail());
////            member.setName(UserInfo.getName());
////            member.setNickName(null);
//
////            memberService.join(member);
//
//
////        model.addAttribute("member" , member);
//
////        httpServletResponse.sendRedirect("http://localhost:8080/");
//    }


    @GetMapping(value = "auth/{socialLoginType}/callback")
    public void loginCallback(
            @PathVariable(name = "socialLoginType") String socialLoginPath,
            @RequestParam(name = "code") String code
    ) throws IOException {
        log.info(">> 소셜 로그인 API 서버로부터 받은 code :: {}", code);
        SocialLoginType socialLoginType = SocialLoginType.valueOf(socialLoginPath.toUpperCase());

        GoogleSignIn googleSignIn = oAuthService.requestLoginUserInfo(socialLoginType, code);

        ResponseEntity<String> userInfoResponse = googleOauth.requestUserInfo2(googleSignIn.getAccess_token());
        GoogleUser googleUser = googleOauth.getUserInfo(userInfoResponse);
        String user_id=googleUser.getEmail();

        List<Member> byEmail = memberService.findByEmail(user_id);
        if (byEmail.size() == 0){
            Member member = new Member();
            member.setEmail(user_id);
            member.setAccess_token(googleSignIn.getAccess_token());
            member.setRefresh_token(googleSignIn.getRefresh_token());
            member.setNickName(null);

            memberService.join(member);

            response.sendRedirect("/login/extraSignIn?email=" + user_id);
        }else{


            response.sendRedirect("/login?email=" + user_id);
        }







    }






}
