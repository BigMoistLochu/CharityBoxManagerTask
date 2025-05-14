package application.charityboxmanager.api;

import application.charityboxmanager.api.dto.FundraisingEventInputDto;
import application.charityboxmanager.api.dto.StoredMoneyDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CollectionBoxControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldReturnAllBoxes() throws Exception {
        mockMvc.perform(get("/api/v1/box"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldAddMoneyToBoxSuccessfully() throws Exception {
        FundraisingEventInputDto eventInput = new FundraisingEventInputDto("Test Event", "PLN");

        String eventResponse = mockMvc.perform(post("/api/v1/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventInput)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long eventId = ((Number) JsonPath.read(eventResponse, "$.id")).longValue();

        // tworzymy collectionBox
        String boxResponse = mockMvc.perform(post("/api/v1/box"))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long boxId = ((Number) JsonPath.read(boxResponse, "$.id")).longValue();

        // przypisujemy box do eventu
        mockMvc.perform(put("/api/v1/event/" + eventId + "/box/" + boxId))
                .andExpect(status().isNoContent());

        // dodajemy pieniadze
        StoredMoneyDto dto = new StoredMoneyDto(new BigDecimal("100.00"), "PLN");

        mockMvc.perform(post("/api/v1/box/" + boxId + "/money")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }




}
