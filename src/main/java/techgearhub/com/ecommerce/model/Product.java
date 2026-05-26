package techgearhub.com.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;
    
    @Column(nullable = false)
    private Double price;
    
    private String imageUrl;
    
    @Column(nullable = false)
    private Integer stock;
    
    @Column(nullable = false)
    private boolean active = true; 

    // Relazione: Molti prodotti appartengono a una categoria
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; 
}