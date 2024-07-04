package com.lec.spring.config.oauth.provider;

import java.util.Map;

public class KAKAOUserInfo implements OAuth2UserInfo{

    // ↓ loadUser() 로 받아온 OAuth2User.getAttributes() 결과를 담을거다
    private Map<String, Object> attributes;

    public KAKAOUserInfo(Map<String, Object> attributes){
        this.attributes = attributes;
    }

    @Override
    public String getProvider() {
        return "KAKAO";
    }

    @Override
    public String getProviderId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getEmail() {
        return (String)attributes.get("email");
    }

    @Override
    public String getName() {
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
        if (properties != null) {
            return (String) properties.get("nickname");
        }
        return null;
    }

}
