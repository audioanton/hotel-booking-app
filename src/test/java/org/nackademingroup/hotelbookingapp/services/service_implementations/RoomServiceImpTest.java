package org.nackademingroup.hotelbookingapp.services.service_implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nackademingroup.hotelbookingapp.models.Room;
import org.nackademingroup.hotelbookingapp.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class RoomServiceImpTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RoomServiceImp roomServiceImp;
    @Autowired
    private RoomRepository roomRepository;

    @BeforeEach
    public void setup() {
        roomRepository.deleteAll();
        roomRepository.saveAll(
                List.of(
                        Room.builder().id(1L).name("suit").build(),
                        Room.builder().id(2L).name("shed").build(),
                        Room.builder().id(3L).name("lobby").build()
                )
        );
    }

    @Test
    void toRoomDto() {
        fail();
    }
}