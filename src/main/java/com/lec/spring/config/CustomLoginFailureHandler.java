package com.lec.spring.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

/*
 * 실패한 Exception을 검사하여 해당 Exception에 맞는 에러 메시지를 로그인 페이지에 같이 전달하여
 * 로그인 실패 이유를 사용자에게 노출시킵니다.
 *
 * 아래에 구현된 Exception을 포함한 AuthenticationException의 종류는 다음과 같습니다.
 *    UsernameNotFoundException : 계정 없음
 *    BadCredentialsException : 비밀번호 불일치
 *    AccountExpiredException : 계정만료
 *    CredentialExpiredException : 비밀번호 만료
 *    DisabledException : 계정 비활성화
 *    LockedException : 계정잠김
 */
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

    private final String DEFAULT_FAILURE_FORWARD_URL = "/user/loginError";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        System.out.println("### 로그인 실패 : onAuthenticationFailure() 호출 ###");

        String errorMessage;

        if (exception instanceof BadCredentialsException) {
            errorMessage = "아이디가 맞지 않습니다. 다시 확인해 주세요.";
        } else if (exception instanceof InternalAuthenticationServiceException){
            errorMessage = "비밀번호가 맞지 않습니다. 다시 확인해 주세요.";
        }else if (exception instanceof DisabledException) {
            errorMessage = "계정이 비활성화 되었습니다. 관리자에게 문의하세요.";
        } else if (exception instanceof LockedException) {
            errorMessage = "계정이 잠겼습니다. 관리자에게 문의하세요.";
        } else if (exception instanceof AccountExpiredException) {
            errorMessage = "계정이 만료되었습니다. 관리자에게 문의하세요.";
        } else if (exception instanceof CredentialsExpiredException) {
            errorMessage = "비밀번호 유효기간이 만료 되었습니다. 관리자에게 문의하세요.";
        } else if (exception instanceof UsernameNotFoundException) {
            errorMessage = "사용자를 찾을 수 없습니다.";
        } else {
            errorMessage = "로그인 중 알 수 없는 오류가 발생했습니다. 관리자에게 문의하세요.";
        }

        String email = request.getParameter("email");
        System.out.println("로그인 시도한 이메일: " + email);

        String pw = request.getParameter("password");
        System.out.println("로그인 시도한 비밀번호: " + pw);

        request.setAttribute("errorMessage", errorMessage);
        request.setAttribute("email", request.getParameter("email"));

        // 로그인 실패 시 로그로 기록할 수도 있습니다.
        // logger.error("로그인 실패: {}", errorMessage);

        // 로그인 실패 시 기본 URL로 포워딩합니다.
        request.getRequestDispatcher(DEFAULT_FAILURE_FORWARD_URL).forward(request, response);
    }
}
