//package com.p2AE.pdf.interceptor;
//
//import com.p2AE.pdf.domain.Member;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.util.List;
//
//public class AuthCheckInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 세션 획득 후 확인
//        String session = sessionManager.getSession(request);
//        List<Member> byAccessToken = memberService.findByAccessToken(session);
//
//
//        if (byAccessToken == null){
//            model.addAttribute("member" , new Member());
//            log.info("회원없음");
//            return "home";
//        }
//
//        Member member = byAccessToken.get(0);
//
//        model.addAttribute("member", member);
//
//
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            Object authInfo = session.getAttribute("authInfo");
//            if (authInfo != null) {
//                return true;
//            }
//        }
//        //세션이 비어있다면 or authInfo 속성이 없다면, 리다이렉트 후 false 리턴으로 컨트롤러가 실행되지 않도록 함.
//        response.sendRedirect(request.getContextPath() + "/login");
//        return false;
//    }
//
//}