package com.lec.spring.config.oauth;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.config.oauth.provider.GoogleUserInfo;
import com.lec.spring.config.oauth.provider.OAuth2UserInfo;
import com.lec.spring.domain.User;
import com.lec.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final UserService userService;
    private final String oauth2Password;

    @Autowired
    public PrincipalOauth2UserService(UserService userService, @Value("${app.oauth2.password}") String oauth2Password) {
        this.userService = userService;
        this.oauth2Password = oauth2Password;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2UserInfo oAuth2UserInfo = getOAuth2UserInfo(oAuth2User, userRequest.getClientRegistration().getRegistrationId());
        String nickname = oAuth2UserInfo.getProviderId();
        String email = oAuth2UserInfo.getEmail();
        String name = oAuth2UserInfo.getName();
        User user = userService.findByNickname(nickname);

        if (user == null) {
            User newUser = User.builder()
                    .nickname(nickname)
                    .username(name)
                    .email(email)
                    .password(oauth2Password)
                    .build();
            int cnt = userService.register(newUser);
            if (cnt > 0) {
                System.out.println("[OAuth2 인증. 회원 가입 성공]");
                user = userService.findByNickname(nickname);
            } else {
                System.out.println("[OAuth2 인증. 회원 가입 실패!]");
            }
        } else {
            System.out.println("[OAuth2 인증. 이미 가입된 회원입니다]");
        }

        PrincipalDetails principalDetails = new PrincipalDetails(user, oAuth2User.getAttributes());
        return principalDetails;
    }

    private OAuth2UserInfo getOAuth2UserInfo(OAuth2User oAuth2User, String registrationId) {
        switch (registrationId.toLowerCase()) {
            case "google":
                return new GoogleUserInfo(oAuth2User.getAttributes());
            default:
                throw new IllegalArgumentException("Unsupported provider: " + registrationId);
        }
    }
}
