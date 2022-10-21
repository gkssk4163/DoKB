package com.dokb.DoKB.login;

import com.dokb.DoKB.user.domain.User;
import com.mysql.cj.Session;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {
    public static final String SESSION_COOKIE_NAME = "name";

    public static final String SESSION_COOKIE_SESSION = "session";
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();
    // ConcurrentHashMap<>() 은 동시성(동시에 여러명이 로그인할때) 문제가 있을때 사용

    public void createSession(User user, HttpServletResponse response){
        String SessionId = UUID.randomUUID().toString();
        sessionStore.put(SessionId,user);


        Cookie nameCookie = new Cookie(SESSION_COOKIE_NAME,user.getName());
        Cookie sessionCookie = new Cookie(SESSION_COOKIE_SESSION, SessionId);
        response.addCookie(nameCookie);
        response.addCookie(sessionCookie);
    }

    public Object getSession(HttpServletRequest request){
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        return sessionStore.get(sessionCookie.getValue());
    }

    public Cookie findCookie(HttpServletRequest request, String cookieName){
        Cookie[] cookies = request.getCookies();

        if(cookies == null){
            throw new NullPointerException();
        }
        return Arrays.stream(cookies)
                .filter(cookie-> cookie.getName().equals((cookieName)))
                .findAny()
                .orElseThrow(NullPointerException::new);
    }

    public void expireCookie(HttpServletRequest request){
        Cookie nameCookie = findCookie(request,SESSION_COOKIE_NAME);
        Cookie sessionCookie = findCookie(request,SESSION_COOKIE_SESSION);

        sessionStore.remove(nameCookie.getValue());
        sessionStore.remove(sessionCookie.getValue());
    }
}
