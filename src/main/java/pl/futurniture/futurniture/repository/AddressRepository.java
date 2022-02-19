package pl.futurniture.futurniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.futurniture.futurniture.model.Address;
import pl.futurniture.futurniture.model.AddressType;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByAddressTypeAndCustomerId(AddressType addressType, Long id);

    List<Address> findAllByCustomerId(Long id);
}
