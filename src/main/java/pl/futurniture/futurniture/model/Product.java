package pl.futurniture.futurniture.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {

    }
}
