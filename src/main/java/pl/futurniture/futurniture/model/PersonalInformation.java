package pl.futurniture.futurniture.model;

import lombok.Data;

@Data
public abstract class PersonalInformation {

    private String name;
    private String surname;
    private String phone;
    private String email;

}
