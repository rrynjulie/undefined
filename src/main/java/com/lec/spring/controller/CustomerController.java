package com.lec.spring.controller;

import com.lec.spring.config.PrincipalDetails;
import com.lec.spring.domain.Booking;
import com.lec.spring.domain.Post;
import com.lec.spring.domain.User;
import com.lec.spring.domain.UserAuthority;
import com.lec.spring.service.BookingService;
import com.lec.spring.service.ManagerService;
import com.lec.spring.service.PostService;
import com.lec.spring.service.UserService;
import com.lec.spring.util.AuthenticationUtil;
import com.lec.spring.util.U;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/mypage/customer")
public class CustomerController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PostService postService;

    @GetMapping("/ManageAccount")
    public String manageAccount(Model model) {
        User user = getLoggedUser();
        model.addAttribute("user", user);

        List<UserAuthority> userAuthorities = userService.getAllUserAuthorities();
        model.addAttribute("userAuthorities", userAuthorities);
        if (user != null) {
            model.addAttribute("userId", user.getUserId());
        }
        AuthenticationUtil.addAuthenticationDetailsToModel(model);
        return "mypage/customer/ManageAccount";
    }

    private static final Pattern PHONENUM_PATTERN = Pattern.compile("^\\d{3}-\\d{4}-\\d{4}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,20}$");
    private static final Pattern NICKNAME_PATTERN = Pattern.compile("^[A-Za-z0-9가-힣_]{1,12}$");

    @PostMapping("/ManageAccount")
    public String updateAccount(@RequestParam(value = "nickname", required = true) String nickname,
                                @RequestParam(value = "password", required = false) String password,
                                @RequestParam(value = "email", required = false) String email,
                                @RequestParam(value = "phone", required = true) String phone,
                                @RequestParam(value = "currentPassword", required = false) String currentPassword,
                                @RequestParam(value = "newPassword", required = false) String newPassword,
                                @RequestParam(value = "confirmPassword", required = false) String confirmPassword,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        User user = U.getLoggedUser();

        boolean hasErrors = false;

        // Validate nickname
        if (nickname == null || nickname.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error_nickname", "닉네임은 필수입니다.");
            hasErrors = true;
        } else if (!NICKNAME_PATTERN.matcher(nickname).matches()) {
            redirectAttributes.addFlashAttribute("error_nickname", "닉네임은 1자리 이상 12자리 이하의 영문자, 숫자, 밑줄(_)만 사용할 수 있습니다.");
            hasErrors = true;
        } else if (!nickname.equals(user.getNickname()) && userService.isExistNickname(nickname)) {
            redirectAttributes.addFlashAttribute("error_nickname", "이미 존재하는 닉네임입니다.");
            hasErrors = true;
        }

//        else if (nickname.equals(user.getNickname())) {
//            hasErrors = false;
//        }
//
//        // Validate phone number
        if (phone != null && !phone.trim().isEmpty()) {
            if (!PHONENUM_PATTERN.matcher(phone).matches()) {
                redirectAttributes.addFlashAttribute("error_phonenum", "올바른 형식(예: 010-1234-5678)의 전화번호를 입력해주세요.");
                hasErrors = true;
            } else if (!phone.equals(user.getPhonenum()) && userService.isExistPhonenum(phone)) {
                redirectAttributes.addFlashAttribute("error_phonenum", "이미 가입된 전화번호입니다.");
                hasErrors = true;
            } else if (phone.equals(user.getPhonenum())) {
//                System.out.println();
            }
        } else if (phone == null || phone.trim().isEmpty()) {
            phone = null;
        }
////
////        // Validate password
        if (currentPassword == null || currentPassword.isEmpty()) {
            System.out.println("확인용");
            redirectAttributes.addFlashAttribute("error_password", "비밀번호를 입력해 주세요.");
            hasErrors = true;
        } else if (!userService.checkPassword(user.getUserId(), currentPassword)) {
            redirectAttributes.addFlashAttribute("error_password", "현재 비밀번호가 일치하지 않습니다.");
            hasErrors = true;
        } else if (newPassword != null && !newPassword.isEmpty()) {
            if (!PASSWORD_PATTERN.matcher(newPassword).matches()) {
                redirectAttributes.addFlashAttribute("error_newpassword", "비밀번호는 최소 하나 이상의 영문 대소문자, 숫자, 특수문자를 혼합하여 8~20자로 입력하여 주세요.");
                hasErrors = true;
            } else if (newPassword == null || confirmPassword == null || !newPassword.equals(confirmPassword)) {
                redirectAttributes.addFlashAttribute("error_confirmpassword", "새 비밀번호가 일치하지 않습니다.");
                hasErrors = true;
            } else {
                password = newPassword;
            }
        }

//
        if (hasErrors) {
            return "redirect:/mypage/customer/ManageAccount";
        }

        userService.updateUser(user.getUserId(), nickname, password, email, phone);
        redirectAttributes.addFlashAttribute("success", "수정되었습니다.");
        AuthenticationUtil.addAuthenticationDetailsToModel(model);


        return "redirect:/Home";
    }

    @PostMapping("/check-password")
    @ResponseBody
    public String checkPassword(@RequestParam String currentPassword) {
        User user = getLoggedUser();
        if (userService.checkPassword(user.getUserId(), currentPassword)) {
            return "success";
        } else {
            return "failure";
        }
    }

    @GetMapping("/getProvider")
    @ResponseBody
    public String getProvider() {
        User user = getLoggedUser();
        return user.getProvider();
    }

    private User getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof PrincipalDetails) {
            PrincipalDetails principalDetails = (PrincipalDetails) principal;
            return principalDetails.getUser();
        } else if (principal instanceof DefaultOAuth2User) {
            Map<String, Object> attributes = ((DefaultOAuth2User) principal).getAttributes();
            String nickname = (String) attributes.get("nickname");
            return userService.findByNickname(nickname);
        } else if (principal instanceof String) {
            String username = (String) principal;
            return userService.findByUsername(username);
        } else {
            throw new IllegalStateException("Unknown principal type: " + principal.getClass());
        }
    }

    @GetMapping("/BookingList/{userId}")
    public String BookingList(
            @PathVariable("userId") Long userId
            , @RequestParam(value = "lodgingType", required = false) String lodgingType
            , @RequestParam(value = "timePeriod", required = false) String timePeriod
            , Model model) {

        List<Booking> books = bookingService.findBooksByUserId(userId);  // 전체 예약 리스트
        List<Booking> booksBefore = new ArrayList<>();  // 이용 전 예약 리스트
        List<Booking> booksAfter = new ArrayList<>();  // 이용 후 예약 리스트

        LocalDate now = LocalDate.now();
        LocalDate startDate;

        // 기간 필터링
        if ("3months".equals(timePeriod)) {
            startDate = now.minusMonths(3);
        } else if ("6months".equals(timePeriod)) {
            startDate = now.minusMonths(6);
        } else if ("1year".equals(timePeriod)) {
            startDate = now.minusYears(1);
        } else {
            startDate = null;
        }

        books.forEach(book -> {
            // 필터링 변수
            boolean matchesLodgingType = lodgingType == null || lodgingType.isEmpty() || book.getLodging().getLodgingType().equals(lodgingType);
            boolean matchesTimePeriod = startDate == null || book.getBookingStartDate().isAfter(startDate);

            // 설정한 숙소 타입과 기간에 맞는 예약 리스트만 추리기
            if (matchesLodgingType && matchesTimePeriod) {
                book.setFormattedPay(DecimalFormat.getInstance().format(book.getBookingPay()));
                book.setDateGap(Period.between(book.getBookingStartDate(), book.getBookingEndDate()).getDays());

                if (book.getBookingStartDate().isAfter(now) || book.getBookingStartDate().isEqual(now)) {
                    booksBefore.add(book);
                } else {
                    booksAfter.add(book);
                }
            }
        });
        model.addAttribute("booksBefore", booksBefore);
        model.addAttribute("booksAfter", booksAfter);
        model.addAttribute("lodgingType", lodgingType);
        model.addAttribute("timePeriod", timePeriod);

        AuthenticationUtil.addAuthenticationDetailsToModel(model);

        return "mypage/customer/BookingList";
    }

    @PostMapping("/CancelBooking/{bookingId}")
    public String cancelBookingOk(@PathVariable int bookingId, Model model) {
        int result;
        Long userId = U.getLoggedUser().getUserId();
        try {
            bookingService.deleteBooking(bookingId);
            result = 1;
        } catch (Exception e) {
            result = 0;
        }

        System.out.println("로그인한 user -> " + userId);
        System.out.println("삭제 할 bookingId -> " + bookingId);
        model.addAttribute("result", result);
        model.addAttribute("userId", userId);
        return "mypage/customer/CancelBookingOk"; // 뷰 리졸버가 자동으로 경로를 찾도록 수정
    }

    @GetMapping("/Unregister")
    public String unregisterPage(Model model) {
        User user = getLoggedUser();
        model.addAttribute("user", user);
        return "mypage/customer/Unregister";
    }

    @PostMapping("/Unregister")
    @ResponseBody()
    public String unregister(@RequestParam String password) {
        User user = getLoggedUser();
        if (userService.checkPassword(user.getUserId(), password)) {
            return "success";
        } else {
            return "failure";
        }
    }

    @PostMapping("/UnregisterConfirm")
    public String unregisterConfirm(RedirectAttributes redirectAttributes) {
        User user = getLoggedUser();
        System.out.println("userData: " + user);
        try {
            userService.deleteUserAndReferences(user.getUserId());
//            userService.deleteUser(user.getUserId());
            // 로그아웃 처리
            request.logout();
            redirectAttributes.addFlashAttribute("success", "회원 탈퇴가 완료되었습니다.");
            return "redirect:/home";  // 로그아웃 후 홈 페이지로 리다이렉트
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "회원 탈퇴 처리 중 오류가 발생했습니다.");
            return "redirect:/mypage/customer/Unregister";
        }
    }


}
