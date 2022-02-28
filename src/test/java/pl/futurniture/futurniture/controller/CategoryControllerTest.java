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
import pl.futurniture.futurniture.dto.model.CategoryDTO;

import javax.sql.DataSource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;
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
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "addresses", "categories");
    }

    @Test
    void findByIdTest() throws Exception{
        mockMvc.perform(get("/categories/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("Fotele")));
    }
    @Test
    void shouldThrowNotFoundExceptionWhileFindById() throws Exception{
        mockMvc.perform(get("/categories/{id}", 123))
                .andExpect(status().isNotFound());
    }

    @Test
    void findByNameTest() throws Exception {
        mockMvc.perform(get("/categories/search?name={name}", "Fotele"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("Fotele")));
    }

    @Test
    void shouldThrowNotFoundExceptionWhileFindByName() throws  Exception {
        mockMvc.perform(get("/categories/search?name={name}", "not_exsists"))
                .andExpect(status().isNotFound());
    }

    @Test
    void editNameByIdTest() throws Exception {
        //given
        String name = "edit";
        //when
        mockMvc.perform(put("/categories/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(name)
                .accept(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("edit")));
    }

    @Test
    void shouldThrowNotFoundExceptionWhileEditNameById() throws Exception {
        //given
        String name = "edit";
        //when
        mockMvc.perform(put("/categories/{id}", 123)
                .contentType(MediaType.APPLICATION_JSON)
                .content(name)
                .accept(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isNotFound());
    }

    @Test
    void createTest() throws Exception {
        //given
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .name("Created")
                .build();
        //when
        mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryDTO))
                .accept(MediaType.APPLICATION_JSON))
                //then
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.name", Matchers.is("Created")));
    }

}