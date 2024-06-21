package com.lec.spring.repository;

import com.lec.spring.domain.Room;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoomRepositoryTest {
    @Autowired
    private SqlSession sqlSession;

    @Test
    void roomRepoTest() {
        RoomRepository roomRepository = sqlSession.getMapper(RoomRepository.class);

        roomRepository.findAll().forEach(System.out::println);

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
        int result = roomRepository.create(room);
        System.out.println("[생성 후]" + room);

        roomRepository.findAll().forEach(System.out::println);

        Long roomId = room.getRoomId();
        room = roomRepository.findByRoomId(roomId);
        System.out.println(room);

        Long lodgingId = room.getLodgingId();
        room = roomRepository.findByLodgingId(lodgingId);
        System.out.println(room);

        room.setRoomName("스위트룸");
        roomRepository.update(room);
        room = roomRepository.findByRoomId(roomId);

        roomRepository.delete(room);
        roomRepository.findAll().forEach(System.out::println);
    }
}
