package pl.futurniture.futurniture.model;

import lombok.Data;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
