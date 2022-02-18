package pl.futurniture.futurniture.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.futurniture.futurniture.dto.mapper.AddressMapper;
import pl.futurniture.futurniture.dto.model.AddressDTO;
import pl.futurniture.futurniture.model.AddressType;
import pl.futurniture.futurniture.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final AddressMapper addressMapper;

    @GetMapping("/{id}")
    public AddressDTO findById(@PathVariable Long id) {
        return addressMapper.toAddressDTO(addressService.findById(id));
    }

    @GetMapping("/find-by-customer/{customerId}/{type}")
    public List<AddressDTO> findByTypeAndCustomerId(
            @PathVariable Long customerId,
            @PathVariable AddressType type) {
        return addressMapper.toAddressDTOs(addressService.findByTypeAndCustomerId(type, customerId));
    }

    @GetMapping("/find-by-customer/{customerId}")
    public List<AddressDTO> findByCustomerId(@RequestParam Long customerId) {
        return addressMapper.toAddressDTOs(addressService.findByCustomerId(customerId));
    }

}
