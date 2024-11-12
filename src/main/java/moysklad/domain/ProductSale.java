package moysklad.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Table(name = "product_sales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSale {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sales_id_seq")
    @SequenceGenerator(name = "product_sales_id_seq", sequenceName = "product_sales_id_seq", allocationSize = 1)
    private Long id;

    private String documentName;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer quantity;
    private Double purchasePrice;

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
        if (this.quantity != null && !this.quantity.equals(quantity)) {
            log.info("Quantity changed for product {}. Old: {}, New: {}", product.getName(), this.quantity, quantity);
        }
        this.quantity = quantity;
    }

    public void setPurchasePrice(Double purchasePrice) {
        if (purchasePrice == null || purchasePrice < 0) {
            throw new IllegalArgumentException("Purchase price cannot be null or negative.");
        }
        this.purchasePrice = purchasePrice;
    }
}
