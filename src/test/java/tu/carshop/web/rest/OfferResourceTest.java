package tu.carshop.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import tu.carshop.dtos.CreateOfferDTO;
import tu.carshop.enums.Engine;
import tu.carshop.enums.Transmission;
import tu.carshop.models.Model;
import tu.carshop.models.Offer;
import tu.carshop.models.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@WithMockUser
@AutoConfigureMockMvc
class OfferResourceTest {

  private static final String DESCRIPTION = "description";
  private static final int MILEAGE = 123;
  private static final BigDecimal PRICE = BigDecimal.valueOf(15000);

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void createOfferSuccessfully() throws Exception {
    // Arrange
    CreateOfferDTO requestDTO = new CreateOfferDTO(
        1L,
        PRICE,
        Engine.DIESEL,
        Transmission.AUTOMATIC,
        LocalDate.of(2021, 1, 1),
        MILEAGE,
        DESCRIPTION,
        "no image"
    );

    // Act
    ResultActions resultActions = mvc.perform(
        post("/offers/create")
            .content(objectMapper.writeValueAsString(requestDTO))
            .contentType(MediaType.APPLICATION_JSON)
    );

    // Assert
    resultActions.andExpect(status().isOk());
  }
}