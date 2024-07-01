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
        return "mypage/customer/ManageAccount";
    }

    @PostMapping("/ManageAccount")
    public String updateAccount(@RequestParam(required = false) String nickname,
                                @RequestParam(required = false) String password,
                                @RequestParam(required = false) String email,
                                @RequestParam(required = false) String phone,
                                @RequestParam(required = false) String currentPassword,
                                @RequestParam(required = false) String newPassword,
                                @RequestParam(required = false) String confirmPassword,
                                RedirectAttributes redirectAttributes) {
        User user = getLoggedUser();

        if (currentPassword != null && !currentPassword.isEmpty()) {
            if (newPassword == null || confirmPassword == null || !newPassword.equals(confirmPassword)) {
                redirectAttributes.addFlashAttribute("error", "새 비밀번호가 일치하지 않습니다.");
                return "redirect:/mypage/customer/ManageAccount";
            }

            password = newPassword;
        }

        userService.updateUser(user.getUserId(), nickname, password, email, phone);

        redirectAttributes.addFlashAttribute("success", "수정되었습니다.");

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
    public String BookingList(@PathVariable("userId") Long userId, Model model){
        List<Booking> books = bookingService.findBooksByUserId(userId);
        List<Booking> booksBefore = new ArrayList<>();
        List<Booking> booksAfter = new ArrayList<>();
        books.forEach(book -> {
            book.setFormattedPay(DecimalFormat.getInstance().format(book.getBookingPay()));
            book.setDateGap(Period.between(book.getBookingStartDate(), book.getBookingEndDate()).getDays());
            if(Period.between(LocalDate.now(), book.getBookingStartDate()).getDays() >= 0) booksBefore.add(book);
            else booksAfter.add(book);
        });
        model.addAttribute("booksBefore", booksBefore);
        model.addAttribute("booksAfter", booksAfter);

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
