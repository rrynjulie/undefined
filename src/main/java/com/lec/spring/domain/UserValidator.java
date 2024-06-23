package com.lec.spring.domain;

import com.lec.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        String nickname = user.getNickname();
        if (nickname == null || nickname.trim().isEmpty()){
            errors.rejectValue("nickname", "nickname은 필수입니다.");
        } else if (userService.isExist(nickname)) {
            errors.rejectValue("nickname", "이미 존재하는 닉네임입니다.");
        }

        // 에러코드 출력
        // 비밀번호
        // 전화번호
        // TODO

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phonenum", "phonenum는 필수입니다");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password는 필수입니다");


        // 입력 password, re_password 가 동일한지 비교
        if(!user.getPassword().equals(user.getRe_password())){
            errors.rejectValue("re_password", "비밀번호와 비밀번호 확인 입력값은 같아야 합니다");
        }


    }
}
