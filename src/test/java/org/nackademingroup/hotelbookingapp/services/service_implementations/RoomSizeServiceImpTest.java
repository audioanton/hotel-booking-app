package org.nackademingroup.hotelbookingapp.services.service_implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nackademingroup.hotelbookingapp.models.RoomSize;
import org.nackademingroup.hotelbookingapp.repositories.RoomSizeRepository;
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
class RoomSizeServiceImpTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    RoomSizeServiceImp roomSizeServiceImp;
    @Autowired
    RoomSizeRepository roomSizeRepository;

    @BeforeEach
    public void setup() {
        roomSizeRepository.deleteAll();
        roomSizeRepository.saveAll(
                List.of(
                        RoomSize.builder().id(1L).beds(1).size("small").maxExtraBeds(0).build(),
                        RoomSize.builder().id(2L).beds(2).size("medium").maxExtraBeds(1).build(),
                        RoomSize.builder().id(3L).beds(2).size("large").maxExtraBeds(2).build()
                )
        );
    }

    @Test
    void getRoomSizeById() {
        fail();
    }

    @Test
    void toRoomSizeDto() {
        fail();
    }
}