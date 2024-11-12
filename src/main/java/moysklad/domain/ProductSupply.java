package moysklad.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_supplies")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSupply {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_supplies_id_seq")
    @SequenceGenerator(name = "product_supplies_id_seq", sequenceName = "product_supplies_id_seq", allocationSize = 1)
    private Long id;

    private String documentName;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer quantity;


    public void setDocumentName(String documentName) {
        if (documentName == null || documentName.length() > 255) {
            throw new IllegalArgumentException("Document name cannot be null and cannot exceed 255 characters.");
        }
        this.documentName = documentName;
    }

    public void setProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        this.product = product;
    }

    public void setQuantity(Integer quantity) {
        if (quantity == null || quantity < 1) {
            throw new IllegalArgumentException("Quantity must be greater than 0.");
        }
        this.quantity = quantity;
    }
}
