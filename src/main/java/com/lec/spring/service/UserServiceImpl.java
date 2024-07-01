package com.lec.spring.service;

import com.lec.spring.domain.Authority;
import com.lec.spring.domain.User;
import com.lec.spring.domain.UserAuthority;
import com.lec.spring.repository.AuthorityRepository;
import com.lec.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LodgingService roomRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean isExistNickname(String nickname) {
        User user = userRepository.findByNickname(nickname);
        return user != null;
    }


    @Override
    public boolean isExistPhonenum(String phonenum) {
        User user = userRepository.findByPhonenum(phonenum);
        return user != null;
    }

//    @Override
//    public User findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }

    public boolean emailExists(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

//    @Override
    public boolean isExist(String providerId) {
        User user = findByProviderId(providerId);
        return user != null;
    }

    @Override
    @Transactional
    public int register(User user) {
        // 사용자 비밀번호 암호화
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 사용자 정보 저장
        userRepository.save(user);

        // 기본 권한 설정
        Authority authority = new Authority();
        authority.setAuthority(1);
        authority.setUser(user);
        authority.setUserId(user.getUserId()); // user의 ID를 Authority에 설정

        // 권한 저장
        authorityRepository.save(authority);

        // 유저에 권한 추가
        if (user.getAuthorities() == null) {
            user.setAuthorities(new ArrayList<>());
        }
        user.getAuthorities().add(authority);

        return 1; // 성공적으로 저장된 경우 1을 반환
    }

    @Override
    public List<Authority> selectAuthoritiesById(Long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            System.out.println("User not found with id: " + id);
            return new ArrayList<>();  // or Collections.emptyList();
        }

        List<Authority> authorities = authorityRepository.findByUser(user);
        if (authorities == null) {
            System.out.println("Authorities not found for user with id: " + id);
            return new ArrayList<>();  // or Collections.emptyList();
        }

        return authorities;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public void logout() {
        // 세션을 종료하거나 로그아웃 처리
        SecurityContextHolder.clearContext(); // Spring Security에서 현재 사용자 Context를 지움
    }

    @Override
    public Authentication authenticate(String email, String password) {
        User user = findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (Authority authority : user.getAuthorities()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + authority.getName()));
            }
            return new UsernamePasswordAuthenticationToken(user, password, authorities);
        }
        throw new BadCredentialsException("Invalid credentials");
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getUserById(int userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, new UserRowMapper());
    }

    @Override
    public void updateUser(Long userId, String nickname, String password, String email, String phone) {
        StringBuilder sql = new StringBuilder("UPDATE user SET ");
        List<Object> params = new ArrayList<>();

        if (nickname != null && !nickname.isEmpty()) {
            sql.append("user_nickname = ?, ");
            params.add(nickname);
        }

        if (phone != null && !phone.isEmpty()) {
            sql.append("user_phonenum = ?, ");
            params.add(phone);
        }

        if (password != null && !password.isEmpty()) {
            sql.append("user_password = ?, ");
            params.add(passwordEncoder.encode(password)); // 비밀번호 암호화
        }

        if (email != null && !email.isEmpty()) {
            sql.append("user_email = ?, ");
            params.add(email);
        }

        sql.setLength(sql.length() - 2);

        sql.append(" WHERE user_id = ?");
        params.add(userId);

        jdbcTemplate.update(sql.toString(), params.toArray());
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public void deleteUserReferences(Long userId) {

    }



    @Override
    public List<UserAuthority> getAllUserAuthorities() {
        return null;
    }

    public boolean checkPassword(Long userId, String currentPassword) {
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        // 비밀번호 확인 로직 구현 (예: 암호화된 비밀번호 비교)
        return passwordEncoder.matches(currentPassword, user.getPassword());
    }

    @Override
    public User findByProviderId(String providerId) {
        return userRepository.findByProviderId(providerId);
    }

    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUserId(rs.getLong("user_id"));
            user.setPassword(rs.getString("user_password"));
            user.setUsername(rs.getString("user_name"));
            user.setEmail(rs.getString("user_email"));
            user.setNickname(rs.getString("user_nickname"));
            user.setPhonenum(rs.getString("user_phonenum"));
            return user;
        }
    }

    @Override
    public void deleteUserAndReferences(Long userId) {
        try {
            userRepository.deleteComments(userId);
            userRepository.deletePosts(userId);
            userRepository.deletePostsByBookingId(userId);  // post 테이블의 데이터를 먼저 삭제
            userRepository.deleteBookings(userId);  // 그 다음 booking 테이블의 데이터를 삭제
            userRepository.deleteRooms(userId);  // Room 데이터 삭제
            userRepository.deleteReservationsByUserId(userId); // reservation 테이블의 데이터를 삭제
            userRepository.deleteLodging(userId);
            userRepository.deleteUserAuthority(userId);
//            userRepository.deleteLikes(userId);
            userRepository.deleteUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("사용자 삭제 중 오류가 발생했습니다.");
        }
    }

    @Override
    public void deleteLodging(Long userId) {

    }

    @Override
    public void deleteUserAuthority(Long userId) {

    }

    @Override
    public void deleteLikes(Long userId) {

    }

    @Override
    public void deletePosts(Long userId) {

    }

    @Override
    public void deleteComments(Long userId) {

    }


    @Override
    public void deleteBookings(Long userId) {

    }

}
