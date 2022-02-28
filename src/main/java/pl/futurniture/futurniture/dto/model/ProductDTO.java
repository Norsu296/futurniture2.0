package pl.futurniture.futurniture.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private CategoryDTO category;

}
