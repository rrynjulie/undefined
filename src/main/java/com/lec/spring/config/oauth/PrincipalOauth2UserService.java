package com.lec.spring.config.oauth;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.config.oauth.provider.GoogleUserInfo;
import com.lec.spring.config.oauth.provider.KAKAOUserInfo;
import com.lec.spring.config.oauth.provider.NaverUserInfo;
import com.lec.spring.config.oauth.provider.OAuth2UserInfo;
import com.lec.spring.domain.User;
import com.lec.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
        System.out.println("OAuth2UserService.loadUser() 호출");
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("""
             ClientRegistration: %s
             RegistrationId: %s
             AccessToken: %s
             OAuth2User Attributes : %s
           """.formatted(
                userRequest.getClientRegistration()    // ClientRegistration
                , userRequest.getClientRegistration().getRegistrationId()   // id?
                , userRequest.getAccessToken().getTokenValue()  // access token
                , oAuth2User.getAttributes()    // Map<String, Object>  <- 사용자 프로필 정보
        ));


        String provider = userRequest.getClientRegistration().getRegistrationId();

        OAuth2UserInfo oAuth2UserInfo = switch(provider.toLowerCase()){
            case "kakao" -> new KAKAOUserInfo(oAuth2User.getAttributes());
            case "google" -> new GoogleUserInfo(oAuth2User.getAttributes());
            case "naver" -> new NaverUserInfo(oAuth2User.getAttributes());
            default -> null;
        };

        if (oAuth2UserInfo == null) {
            throw new IllegalArgumentException("Unsupported provider: " + provider);
        }

        String providerId = oAuth2UserInfo.getProviderId();
        String nickname = provider + "_" + providerId;
        String password = oauth2Password;
        String email = oAuth2UserInfo.getEmail();
        String name = oAuth2UserInfo.getName();


        User user = userService.findByProviderId(providerId);

        if (user == null) {
            User.UserBuilder userBuilder = User.builder()
                    .nickname(nickname)
                    .username(name)
                    .password(oauth2Password)
                    .provider(provider)
                    .providerId(providerId);

            if (email != null) {
                userBuilder.email(email);
            }

            User newUser = userBuilder.build();
            int cnt = userService.register(newUser);
            if (cnt > 0) {
                System.out.println("[OAuth2 인증. 회원 가입 성공]");
                user = userService.findByProviderId(providerId);
            } else {
                System.out.println("[OAuth2 인증. 회원 가입 실패!]");
            }
        } else {
            System.out.println("[OAuth2 인증. 이미 가입된 회원입니다]");
        }

        PrincipalDetails principalDetails = new PrincipalDetails(user, oAuth2User.getAttributes());
        principalDetails.setUserService(userService);
        return principalDetails;
    }


}
