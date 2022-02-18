package pl.futurniture.futurniture.service;

import pl.futurniture.futurniture.model.Address;
import pl.futurniture.futurniture.model.AddressType;

import java.util.List;


public interface AddressService {

    Address findById(Long id);

    List<Address> findByTypeAndCustomerId(AddressType addressType, Long id);

    List<Address> findByCustomerId(Long id);

    Address edit(Long id, Address address);


}
