package moysklad.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_id_seq")
    @SequenceGenerator(name = "products_id_seq", sequenceName = "products_id_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Boolean inStock;
    private Integer quantity;

    public void setName(String name) {
        if (name == null || name.length() > 255) {
            throw new IllegalArgumentException("Product name must not be null and cannot exceed 255 characters.");
        }
        this.name = name;
    }

    public void setDescription(String description) {
        if (description != null && description.length() > 4096) {
            throw new IllegalArgumentException("Product description cannot exceed 4096 characters.");
        }
        this.description = description;
    }

    public void setPrice(Double price) {
        if (price != null && price < 0) {
            throw new IllegalArgumentException("Product price cannot be less than 0.");
        }
        this.price = price != null ? price : 0.0;
    }

    public void setInStock(Boolean inStock) {
        if (this.inStock != null && !this.inStock.equals(inStock)) {
            log.info("Product stock status changed for {}. Old: {}, New: {}", this.name, this.inStock, inStock);
        }
        this.inStock = inStock != null ? inStock : false;
    }

    public void setQuantity(Integer quantity) {
        if (quantity == null) {
            this.quantity = 0;
            return;
        } else if (quantity < 0) {
            throw new IllegalArgumentException("Product quantity cannot be negative.");
        }
        this.quantity = quantity;
    }
}