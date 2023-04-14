package com.p2AE.pdf.login;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 세션 관리
 */
@Component
public class SessionManager {

    // 쿠키를 쓸 곳이 많기 때문에 상수로 만듦
    public static final String SESSION_COOKIE_NAME = "access_token";

    // 스프링 아이디와 객체를 맵으로 저장
    // 동시성을 위해 ConcurrentHashMap<>() 사용
    private final Map<String, String> sessionStore = new ConcurrentHashMap<>();

    /**
     * 세션 생성
     */
    public void createSession(String value, HttpServletResponse response) {

        // 세션 id를 생성하고, 값을 세션에 저장
        // randomUUID() : 확실한 랜덤값을 얻을 수 있음. 자바가 제공
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        // 쿠키 생성
        Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        mySessionCookie.setPath("/");
        response.addCookie(mySessionCookie);
    }

    /**
     * 세션 조회
     */
    public String getSession(HttpServletRequest request) {
        // 쿠기를 찾음
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if (sessionCookie == null) {
            return null;
        }
        return sessionStore.get(sessionCookie.getValue());
    }

    /**
     * 세션 만료
     */
    public void expire(HttpServletRequest request) {
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if (sessionCookie != null) {
            // 만료된 쿠키를 지움
            sessionStore.remove(sessionCookie.getValue());
        }
    }


    // 쿠키를 찾는 로직
    public Cookie findCookie(HttpServletRequest request, String cookieName) {
        if (request.getCookies() == null) {
            return null;
        }
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }



}