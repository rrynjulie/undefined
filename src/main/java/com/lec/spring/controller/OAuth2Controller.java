package com.lec.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lec.spring.domain.User;
import com.lec.spring.domain.oauth.KakaoOAuthToken;
import com.lec.spring.domain.oauth.KakaoProfile;
import com.lec.spring.service.UserService;
import com.lec.spring.util.U;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/oauth2")
public class OAuth2Controller {
    // kakao 로그인
    @Value("${app.oauth2.kakao.client-id}")
    private String kakaoClientId;
    @Value("${app.oauth2.kakao.redirect-uri}")
    private String kakaoRedirectUri;
    @Value("${app.oauth2.kakao.token-uri}")
    private String kakaoTokenUri;
    @Value("${app.oauth2.kakao.user-info-uri}")
    private String kakaoUserInfoUri;

    @Value("${app.oauth2.password}")
    private String oauth2Password;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/kakao/callback")
    public String kakaoCallBack(String code){
        System.out.println("\n<<카카오 인증 완료>>\ncode: " + code);
        KakaoOAuthToken token = kakaoAccessToken(code);

        KakaoProfile profile = kakaoUserInfo(token.getAccess_token());

        User kakaoUser = registerKakaoUser(profile);

        loginKakaoUser(kakaoUser);
        return "redirect:/";
    }
    public KakaoOAuthToken kakaoAccessToken(String code){
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoClientId);
        params.add("redirect_uri", kakaoRedirectUri);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        ResponseEntity<String> response = rt.exchange(
                kakaoTokenUri,
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        System.out.println("카카오 AccessToken 요청 응답: " + response);

        System.out.println("카카오 AccessToken 응답 body: " + response.getBody());

        ObjectMapper mapper = new ObjectMapper();
        KakaoOAuthToken token = null;

        try {
            token = mapper.readValue(response.getBody(), KakaoOAuthToken.class);
            System.out.println("카카오 Access Token: " + token.getAccess_token());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return token;

    } // end kakaoAccessToken()

    public KakaoProfile kakaoUserInfo(String accessToken){
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
                new HttpEntity<>(headers);

        ResponseEntity<String> response = rt.exchange(
                kakaoUserInfoUri,
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        System.out.println("카카오 사용자 profile 요청 응답: " + response);
        System.out.println("카카오 사용자 profile 응답 body: " + response.getBody());

        ObjectMapper mapper = new ObjectMapper();
        KakaoProfile profile = null;
        try {
            profile = mapper.readValue(response.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("""
                [카카오 회원정보]
                id: %s
                nickname: %s
                """.formatted(profile.getId(), profile.getKakaoAccount().getProfile().getNickname()));


        return profile;
    }


    public User registerKakaoUser(KakaoProfile profile){
        // 새로이 가입시킬 username 을 생성 (unique 해야 한다)
        String provider = "KAKAO";
        String providerId = "" + profile.getId();
        String nickname = provider + "_" + providerId;
        String name = profile.getKakaoAccount().getProfile().getNickname();
        String password = oauth2Password;

        User user = userService.findByNickname(nickname);
        if (user == null){  // 미가입자인 경우에만 회원가입 진행
            User newUser = User.builder()
                    .nickname(nickname)
                    .username(name)
                    .password(password)
                    .provider(provider)
                    .providerId(providerId)
                    .build();

            int cnt = userService.register(newUser);  // 회원가입 INSERT
            if (cnt > 0){
                System.out.println("[Kakao 인증 회원 가입 성공]");
                user = userService.findByNickname(nickname);    // 다시 읽어온다 id, regdate
            } else {
                System.out.println("[Kakao 인증 회원 가입 실패]");
            }
        } else {
            System.out.println("[Kakao 인증. 이미 가입된 회원입니다.]");
        }

        return user;
    }

    // ---------------------------------------------
    // 로그인 시키기
    public void loginKakaoUser(User kakaoUser) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                kakaoUser.getNickname(),    // nickname
                oauth2Password              // password
        );

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);

        HttpSession session = U.getSession();
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);

        System.out.println("Authentication: " + authentication);
        System.out.println("SecurityContext: " + sc);
        System.out.println("Session: " + session);
        System.out.println("Kakao 인증 로그인 처리 완료");
    }


} // end Controller
