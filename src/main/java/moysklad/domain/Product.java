package moysklad.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Setter
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Boolean inStock;

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
        this.inStock = inStock != null ? inStock : false;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", inStock=" + inStock +
                '}';
    }
}
