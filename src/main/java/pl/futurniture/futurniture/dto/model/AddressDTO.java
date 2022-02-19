package pl.futurniture.futurniture.dto.model;

import lombok.*;
import pl.futurniture.futurniture.model.AddressType;


@Builder
@Getter
@Setter
@AllArgsConstructor
public class AddressDTO {

    private Long id;
    private String city;
    private String street;
    private String houseNumber;
    private String postCode;
    private AddressType addressType;

}
