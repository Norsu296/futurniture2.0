package pl.futurniture.futurniture.service.serviceImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.jdbc.JdbcTestUtils;
import pl.futurniture.futurniture.exception.notFound.NotFoundException;
import pl.futurniture.futurniture.model.Address;
import pl.futurniture.futurniture.model.AddressType;
import pl.futurniture.futurniture.repository.AddressRepository;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressServiceImplTest {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AddressServiceImpl addressService;


    @BeforeEach
    void prepareDb() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(false, false,
                "UTF-8", new ClassPathResource("addresses.sql"));
        resourceDatabasePopulator.execute(dataSource);
    }

    @AfterEach
    void tearDown() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "addresses");
    }

    @Test
    void shouldReturnAddressById() {
        //when
        Address address = addressRepository.getById(1L);
        //then
        assertNotNull(address);
        assertEquals("Kraków", address.getCity());
        assertInstanceOf(Address.class, address);
    }

    @Test
    void shouldThrowNotFoundException(){
        assertThrows(NotFoundException.class, () -> addressService.findById(123L));
    }

    @Test
    void shouldCreateNewAddress(){
        //given
        Address addressToSave = Address.builder()
                .city("Kraków")
                .street("Krakowska")
                .houseNumber("12")
                .postCode("30-100")
                .addressType(AddressType.MAILING)
                .build();
        //when
        Address address = addressRepository.save(addressToSave);
        //then
        assertNotNull(address);
        assertInstanceOf(Address.class, address);
        assertEquals("12", addressRepository.findById(address.getId()).get().getHouseNumber());
    }

    @Test
    void shouldReturnUpdatedAddress(){
        //given
        Address addressToUpdate = Address.builder()
                .city("Wadowice")
                .street("Wadowicka")
                .houseNumber("123")
                .postCode("32-123")
                .addressType(AddressType.MAILING)
                .build();
        //when
        Address addressFromDb = addressService.edit(1L,addressToUpdate);
        //then
        assertNotNull(addressFromDb);
        assertEquals("Wadowice", addressFromDb.getCity());
        assertEquals("Wadowicka", addressFromDb.getStreet());
        assertEquals(1L, addressFromDb.getId());
    }

    @Test
    void shouldThrowNotFoundExceptionWhileUpdateAddress(){
        //given
        Address addressToUpdate = Address.builder()
                .city("Wadowice")
                .street("Wadowicka")
                .houseNumber("123")
                .postCode("32-123")
                .addressType(AddressType.MAILING)
                .build();
        //then
        assertThrows(NotFoundException.class, () -> addressService.edit(123L, addressToUpdate));
    }


}