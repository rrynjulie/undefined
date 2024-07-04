package com.lec.spring.controller;

import com.lec.spring.domain.User;
import com.lec.spring.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oauth2")
public class OAuth2Controller {
    // kakao 로그인

    @Value("${app.oauth2.password}")
    private String oauth2Password;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // ---------------------------------------------
    // 로그인 시키기
    public void loginKakaoUser(User kakaoUser, HttpSession session) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                kakaoUser.getNickname(),    // 사용자명 또는 닉네임 (구글에서 제공하는 ID 등)
                ""                 // password
        );

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);

        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);

        System.out.println("Authentication: " + authentication);
        System.out.println("SecurityContext: " + sc);
        System.out.println("Session: " + session);
        System.out.println("Kakao 인증 로그인 처리 완료");
    }

    public void loginGoogleUser(User googleUser, HttpSession session) {
        // 사용자 인증 처리
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                googleUser.getNickname(),    // 사용자명 또는 닉네임 (구글에서 제공하는 ID 등)
                ""                          // 비밀번호 (사용하지 않는 경우 비움)
        );

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);

        // 세션에 SecurityContext 저장
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);

        System.out.println("Authentication: " + authentication);
        System.out.println("SecurityContext: " + sc);
        System.out.println("Session: " + session);
        System.out.println("카카오 사용자 로그인 처리 완료");
    }

    public void loginNaverUser(User naverUser, HttpSession session) {
        // 사용자 인증 처리
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                naverUser.getNickname(),    // 사용자명 또는 닉네임 (구글에서 제공하는 ID 등)
                ""                          // 비밀번호 (사용하지 않는 경우 비움)
        );

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);

        // 세션에 SecurityContext 저장
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);

        System.out.println("Authentication: " + authentication);
        System.out.println("SecurityContext: " + sc);
        System.out.println("Session: " + session);
        System.out.println("네이버 사용자 로그인 처리 완료");
    }


} // end Controller
