package com.lec.spring.repository;

import com.lec.spring.domain.Room;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RoomRepositoryTest {
    @Autowired
    private SqlSession sqlSession;

    @Test
    void roomRepoTest() {
        RoomRepository roomRepository = sqlSession.getMapper(RoomRepository.class);

        System.out.println("[현재 room Table 목록]");
        roomRepository.findAll().forEach(System.out::println);
        System.out.println();

        Room room = Room.builder()
                .roomPicture1("1")
                .roomName("진실의 방")
                .roomNormalPeople(1)
                .roomMaxPeople(1)
                .roomPrice(5555555)
                .roomArea("1")
                .roomBed(0)
                .roomSmoke(Room.RoomSmoke.valueOf("YES"))
                .lodgingId(1L)
                .build();

        System.out.println("[생성 전]" + room);
        roomRepository.createRoom(room);
        System.out.println("[생성 후]" + room);
        System.out.println();

        System.out.println("[Create 후 room Table 목록]");
        roomRepository.findAll().forEach(System.out::println);
        System.out.println();

        Long roomId = room.getRoomId();
        room = roomRepository.findByRoomId(roomId);

        System.out.println("[roomId를 이용한 추출]" + room);

        Long lodgingId = room.getLodgingId();
        List<Room> roomArr = roomRepository.findByLodgingId(lodgingId);
        System.out.println("[lodgingId를 이용한 추출]" + roomArr);
        System.out.println();

        room.setRoomName("스위트룸");
        roomRepository.updateRoom(room);
        room = roomRepository.findByRoomId(roomId);
        System.out.println("[객실명 변경 후]");
        roomRepository.findAll().forEach(System.out::println);
        System.out.println();

        roomRepository.deleteRoom(Math.toIntExact(roomId));
        System.out.println("[객실 삭제 후]");
        roomRepository.findAll().forEach(System.out::println);
    }
}