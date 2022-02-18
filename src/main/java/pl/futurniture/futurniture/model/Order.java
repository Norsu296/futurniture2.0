package pl.futurniture.futurniture.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "is_paid")
    private boolean isPaid;
    @Column(name = "start_date")
    private LocalDate startDate = LocalDate.now();
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "ship_date")
    private LocalDate shipDate;
    @Column(name = "is_important")
    private boolean isImportant;
    private Double amount;
    private String description;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToMany
    private List<Product> products;

}
