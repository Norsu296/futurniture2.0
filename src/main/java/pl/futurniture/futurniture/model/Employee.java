package pl.futurniture.futurniture.model;

import lombok.Data;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "employees")
public class Employee extends PersonalInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "employee")
    private List<Order> orders;

}
