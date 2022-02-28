package pl.futurniture.futurniture.service.serviceImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.jdbc.JdbcTestUtils;
import pl.futurniture.futurniture.exception.notFound.NotFoundException;
import pl.futurniture.futurniture.model.Category;
import pl.futurniture.futurniture.service.CategoryService;

import javax.sql.DataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;
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
    void shouldReturnCategoryById() {
        //when
        Category category = categoryService.findById(1L);
        //then
        assertNotNull(category);
        assertEquals(1, category.getId());
        assertInstanceOf(Category.class, category);
    }

    @Test
    void shouldThrowNotFoundExceptionWhileFindById() {
        assertThrows(NotFoundException.class, () -> categoryService.findById(123L));
    }

    @Test
    void shouldReturnListOfCategories() {
        //when
        List<Category> categories = categoryService.findAll();
        //then
        assertNotNull(categories);
        assertEquals(3, categories.size());
        assertInstanceOf(Category.class, categories.get(0));
    }

    @Test
    void shouldReturnCategoryByName() {
        //when
        Category category = categoryService.findByName("Fotele");
        //then
        assertNotNull(category);
        assertEquals("Fotele", category.getName());
        assertInstanceOf(Category.class, category);
    }

    @Test
    void shouldThrowNotFoundExceptionWhileFindByName() {
        assertThrows(NotFoundException.class, () -> categoryService.findByName("notExists"));
    }

    @Test
    void shouldEditCategory(){
        //given
        String nameToUpdate = "Krzesła";
        //when
        Category categoryUpdated = categoryService.edit(1L, nameToUpdate);
        //then
        assertNotNull(categoryUpdated);
        assertEquals("Krzesła", categoryUpdated.getName());
        assertInstanceOf(Category.class, categoryUpdated);
    }

    @Test
    void shouldCreateCategory(){
        //given
        Category category = Category.builder()
                .name("Stoły")
                .build();
        //when
        Category categoryCreated = categoryService.create(category);
        //then
        assertNotNull(categoryCreated);
        assertEquals("Stoły", categoryCreated.getName());
        assertInstanceOf(Category.class, categoryCreated);

    }


}