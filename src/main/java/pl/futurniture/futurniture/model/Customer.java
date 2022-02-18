package pl.futurniture.futurniture.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "categories")
public class Customer extends PersonalInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "customer")
    private List<Address> addressList;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
