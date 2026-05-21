package techgearhub.com.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // Relazione: Una categoria ha molti prodotti
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @ToString.Exclude // Evita cicli infiniti nei log
    private List<Product> products;
}