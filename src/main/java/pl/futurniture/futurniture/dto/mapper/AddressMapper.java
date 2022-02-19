package pl.futurniture.futurniture.dto.mapper;

import org.springframework.stereotype.Component;
import pl.futurniture.futurniture.dto.model.AddressDTO;
import pl.futurniture.futurniture.model.Address;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressMapper {

    public AddressDTO toAddressDTO(Address address) {
        return AddressDTO.builder()
                .id(address.getId())
                .city(address.getCity())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .postCode(address.getPostCode())
                .addressType(address.getAddressType())
                .build();
    }

    public Address toAddress(AddressDTO addressDTO) {
        return Address.builder()
                .id(addressDTO.getId())
                .city(addressDTO.getCity())
                .street(addressDTO.getStreet())
                .houseNumber(addressDTO.getHouseNumber())
                .postCode(addressDTO.getPostCode())
                .addressType(addressDTO.getAddressType())
                .build();
    }

    public List<AddressDTO> toAddressDTOs(List<Address> addresses) {
        List<AddressDTO> addressDTOS = new ArrayList<>();
        for (Address address : addresses) {
            addressDTOS.add(
                    AddressDTO.builder()
                            .id(address.getId())
                            .city(address.getCity())
                            .street(address.getStreet())
                            .houseNumber(address.getHouseNumber())
                            .postCode(address.getPostCode())
                            .addressType(address.getAddressType())
                            .build());
        }
        return addressDTOS;
    }

    public List<Address> toAddresses(List<AddressDTO> addressDTOS) {
        List<Address> addresses = new ArrayList<>();
        for (AddressDTO addressDTO : addressDTOS) {
            addresses.add(
                    Address.builder()
                            .id(addressDTO.getId())
                            .city(addressDTO.getCity())
                            .street(addressDTO.getStreet())
                            .houseNumber(addressDTO.getHouseNumber())
                            .postCode(addressDTO.getPostCode())
                            .addressType(addressDTO.getAddressType())
                            .build());
        }
        return addresses;
    }

}
