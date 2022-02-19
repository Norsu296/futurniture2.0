package pl.futurniture.futurniture.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.futurniture.futurniture.exception.ErrorMessage;
import pl.futurniture.futurniture.exception.notFound.NotFoundException;
import pl.futurniture.futurniture.model.Address;
import pl.futurniture.futurniture.model.AddressType;
import pl.futurniture.futurniture.repository.AddressRepository;
import pl.futurniture.futurniture.service.AddressService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
    }

    @Override
    public List<Address> findByTypeAndCustomerId(AddressType addressType, Long id) {
        return addressRepository.findAllByAddressTypeAndCustomerId(addressType, id);
    }

    @Override
    public List<Address> findByCustomerId(Long id) {
        return addressRepository.findAllByCustomerId(id);
    }

    @Override
    public Address edit(Long id, Address address) {
        return addressRepository.findById(id)
                .map(addressFromDb -> {
                    addressFromDb.setAddressType(address.getAddressType());
                    addressFromDb.setCity(address.getCity());
                    addressFromDb.setStreet(address.getStreet());
                    addressFromDb.setHouseNumber(address.getHouseNumber());
                    addressFromDb.setPostCode(address.getPostCode());
                    return addressRepository.save(addressFromDb);
                }).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
    }

    @Override
    public Address create(Address address) {
        return addressRepository.save(address);
    }
}
