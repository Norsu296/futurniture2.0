package pl.futurniture.futurniture.dto.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.futurniture.futurniture.dto.model.AddressDTO;
import pl.futurniture.futurniture.model.Address;
import pl.futurniture.futurniture.model.AddressType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressMapperTest {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    void shouldReturnInstanceOfAddress() {
        //given
        AddressDTO addressDTO = AddressDTO.builder()
                .id(1L)
                .city("Kraków")
                .street("Krakowska")
                .houseNumber("12")
                .postCode("30-100")
                .addressType(AddressType.MAILING)
                .build();
        //when
        Address address = addressMapper.toAddress(addressDTO);
        //then
        assertNotNull(address);
        assertInstanceOf(Address.class, address);
    }

    @Test
    void shouldReturnInstanceOfAddressDTO() {
        //given
        Address address = Address.builder()
                .id(1L)
                .city("Kraków")
                .street("Krakowska")
                .houseNumber("12")
                .postCode("30-100")
                .addressType(AddressType.MAILING)
                .build();
        //when
        AddressDTO addressDTO = addressMapper.toAddressDTO(address);
        //then
        assertNotNull(addressDTO);
        assertInstanceOf(AddressDTO.class, addressDTO);
    }

    @Test
    void shouldReturnListInstanceOfAddress() {
        //given
        List<AddressDTO> addressDTOS = new ArrayList<>(
                Arrays.asList(
                        AddressDTO.builder()
                                .id(1L)
                                .city("Kraków")
                                .street("Krakowska")
                                .houseNumber("12")
                                .postCode("30-100")
                                .addressType(AddressType.MAILING)
                                .build(),
                        AddressDTO.builder()
                                .id(2L)
                                .city("Warszawa")
                                .street("Warszawska")
                                .houseNumber("15")
                                .postCode("34-121")
                                .addressType(AddressType.MAILING)
                                .build()
                ));
        //when
        List<Address> addresses = addressMapper.toAddresses(addressDTOS);
        //then
        assertNotNull(addresses);
        assertEquals(2, addresses.size());
        assertEquals(2L, addresses.get(1).getId());
        assertInstanceOf(Address.class, addresses.get(0));
    }

    @Test
    void shouldReturnListInstanceOfAddressDTO() {
        //given
        List<Address> addresses = new ArrayList<>(
                Arrays.asList(
                        Address.builder()
                                .id(1L)
                                .city("Kraków")
                                .street("Krakowska")
                                .houseNumber("12")
                                .postCode("30-100")
                                .addressType(AddressType.MAILING)
                                .build(),
                        Address.builder()
                                .id(2L)
                                .city("Warszawa")
                                .street("Warszawska")
                                .houseNumber("15")
                                .postCode("34-121")
                                .addressType(AddressType.MAILING)
                                .build()
                )
        );
        //when
        List<AddressDTO> addressDTOs = addressMapper.toAddressDTOs(addresses);
        //then
        assertNotNull(addressDTOs);
        assertEquals(2, addressDTOs.size());
        assertEquals(2L, addressDTOs.get(1).getId());
        assertInstanceOf(AddressDTO.class, addressDTOs.get(0));
    }

}