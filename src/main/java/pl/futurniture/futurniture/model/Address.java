package pl.futurniture.futurniture.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String street;
    private String houseNumber;
    private String postCode;
    @Enumerated(EnumType.STRING)
    private AddressType addressType;
    @ManyToOne
    private Customer customer;

}