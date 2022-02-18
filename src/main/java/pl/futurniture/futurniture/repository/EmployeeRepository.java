package pl.futurniture.futurniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.futurniture.futurniture.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
