//package com.lec.spring.repository;
//
//import com.lec.spring.domain.Reservation;
//import org.apache.ibatis.session.SqlSession;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Date;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest
//public class ReservationRepositoryTest {
//    @Autowired
//    private SqlSession sqlSession;
//
//    @Test
//    @Transactional
//    void testInsertReservation() {
//        ReservationRepository reservationRepository = sqlSession.getMapper(ReservationRepository.class);
//
//        // Given
//        Reservation reservation = Reservation.builder()
//                //.reservationId(3)
//                //.reservationTime(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
//                .reservationAdult(4)
//                .reservationChild(2)
//                .visitorName("먕")
//                .visitorPhoneNum("010-1212-3333")
//                .reservationPayType("카드")
//                .reservationPay(230000)
//                .reservationFinalPay(200000)
//                .reservationStartDate(Date.from(LocalDateTime.of(2024, 11, 1, 13, 0).atZone(ZoneId.systemDefault()).toInstant()))
//                .reservationEndDate(Date.from(LocalDateTime.of(2024, 6, 5, 11, 0).atZone(ZoneId.systemDefault()).toInstant()))
//                .roomId(3)
//                .userId("3")
//                .build();
//        // When
//        //reservationRepository.insert(reservation);
//
//        // Then
//        assertNotNull(reservation.getReservationId()); // 아이디가 생성되었는지 확인 (Auto Increment id 가정)
//        System.out.println("[예약 입력받기]: " + reservation);
//    }
//
//    @Test
//    void testFindAllReservations() {
//        ReservationRepository reservationRepository = sqlSession.getMapper(ReservationRepository.class);
//
//        System.out.println("[모든 예약 정보]");
//        reservationRepository.findAll().forEach(System.out::println);
//    }
//
//    @Test
//    void testFindReservationById() {
//        ReservationRepository reservationRepository = sqlSession.getMapper(ReservationRepository.class);
//        int reservationId = 1;
//
//        System.out.println("[ID가 1인 예약 정보]");
//        Reservation reservation = reservationRepository.findById(reservationId);
//        System.out.println(reservation);
//    }
//
//
//}
//package com.lec.spring.repository;
//import com.lec.spring.config.PrincipalDetails;
//import com.lec.spring.controller.ReservationController;
//import com.lec.spring.domain.Lodging;
//import com.lec.spring.domain.Room;
//import com.lec.spring.domain.User;
//import com.lec.spring.service.LodgingService;
//import com.lec.spring.service.RoomService;
//import com.lec.spring.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.core.Authentication;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.ui.Model;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//public class ReservationControllerTest {
//
//    @Mock
//    private LodgingService lodgingService;
//
//    @Mock
//    private RoomService roomService;
//
//    @Mock
//    private UserService userService;
//
//    @InjectMocks
//    private ReservationController reservationController;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
//    }
//
//    @Test
//    public void testShowReservationForm() throws Exception {
//        Long lodgingId = 1L;
//        Long roomId = 1L;
//
//        // Mock PrincipalDetails
//        PrincipalDetails principalDetails = new PrincipalDetails();
//        principalDetails.setUser(new User("username", "password"));
//
//        // Mock Authentication
//        Authentication authentication = mock(Authentication.class);
//        when(authentication.isAuthenticated()).thenReturn(true);
//        when(authentication.getPrincipal()).thenReturn(principalDetails);
//
//        // Mock Lodging
//        Lodging lodging = new Lodging();
//        lodging.setId(lodgingId);
//        lodging.setName("Test Lodging");
//
//        // Mock Room
//        Room room = new Room();
//        room.setRoomId(roomId);
//        room.setRoomName("Test Room");
//
//        // Mock UserService
//        when(userService.findByUsername(anyString())).thenReturn(new User("username", "password"));
//
//        // Mock RoomService
//        when(roomService.getRoomById(eq(roomId))).thenReturn(room);
//
//        // Mock LodgingService
//        when(lodgingService.getLodgingById(eq(lodgingId))).thenReturn(lodging);
//
//        // Perform the GET request
//        mockMvc.perform(get("/lodging/reservation/form")
//                        .param("lodgingId", String.valueOf(lodgingId))
//                        .param("roomId", String.valueOf(roomId))
//                        .principal(authentication))
//                .andExpect(status().isOk())
//                .andExpect(view().name("lodging/LodgingBooking"))
//                .andExpect(model().attributeExists("user", "lodging", "selectedRoom", "reservation"));
//
//        // Verify that userService.findByUsername() is called once with "username" argument
//        verify(userService, times(1)).findByUsername(eq("username"));
//
//        // Verify that roomService.getRoomById() is called once with roomId argument
//        verify(roomService, times(1)).getRoomById(eq(roomId));
//
//        // Verify that lodgingService.getLodgingById() is called once with lodgingId argument
//        verify(lodgingService, times(1)).getLodgingById(eq(lodgingId));
//    }
//}
