package pl.futurniture.futurniture.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import pl.futurniture.futurniture.dto.model.AddressDTO;
import pl.futurniture.futurniture.model.AddressType;

import javax.sql.DataSource;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @BeforeEach
    void prepareDb() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(false, false,
                "UTF-8", new ClassPathResource("data.sql"));
        resourceDatabasePopulator.execute(dataSource);
    }

    @AfterEach
    void tearDown() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "addresses");
    }

    @Test
    public void findByIdTest() throws Exception {
        mockMvc.perform(get("/addresses/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.id", Matchers.is("1")))
                .andExpect(jsonPath("$.city", Matchers.is("Kraków")));
    }

    @Test
    public void editByIdTest() throws Exception {
        //given
        AddressDTO addressDTO = AddressDTO.builder()
                .city("Wadowice")
                .street("Wadowicka")
                .houseNumber("21")
                .postCode("32-123")
                .addressType(AddressType.RESIDENCE)
                .build();
        //when
        mockMvc.perform(put("/addresses/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressDTO))
                .accept(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.city", Matchers.is("Wadowice")))
                .andExpect(jsonPath("$.street", Matchers.is("Wadowicka")))
                .andExpect(jsonPath("$.houseNumber", Matchers.is("21")))
                .andExpect(jsonPath("$.postCode", Matchers.is("32-123")))
                .andExpect(jsonPath("$.addressType", Matchers.is("RESIDENCE")));
    }

    @Test
    public void createTest() throws Exception{
        //given
        AddressDTO addressDTO = AddressDTO.builder()
                .city("Wadowice")
                .street("Wadowicka")
                .houseNumber("21")
                .postCode("32-123")
                .addressType(AddressType.RESIDENCE)
                .build();
        //when
        mockMvc.perform(post("/addresses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressDTO))
                .accept(MediaType.APPLICATION_JSON))
        //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.city", Matchers.is("Wadowice")))
                .andExpect(jsonPath("$.street", Matchers.is("Wadowicka")))
                .andExpect(jsonPath("$.houseNumber", Matchers.is("21")))
                .andExpect(jsonPath("$.postCode", Matchers.is("32-123")))
                .andExpect(jsonPath("$.addressType", Matchers.is("RESIDENCE")));

    }

}