package pl.futurniture.futurniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.futurniture.futurniture.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
