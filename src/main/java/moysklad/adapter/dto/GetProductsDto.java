package moysklad.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetProductsDto {
    private String name;
    private Double priceMin;
    private Double priceMax;
    private Boolean inStock;
    private String sortBy;
    private Integer limit;

    public void validate() {
        if (name != null && name.length() > 255) {
            throw new IllegalArgumentException("Product name cannot exceed 255 characters.");
        }
        if (priceMin != null && priceMin < 0) {
            throw new IllegalArgumentException("Minimum price cannot be less than 0.");
        }
        if (priceMax != null && priceMax < 0) {
            throw new IllegalArgumentException("Maximum price cannot be less than 0.");
        }
        if (priceMin != null && priceMax != null && priceMin > priceMax) {
            throw new IllegalArgumentException("Minimum price cannot be greater than maximum price.");
        }
        if (limit != null && limit <= 0) {
            throw new IllegalArgumentException("Limit must be greater than 0.");
        }
        if (sortBy != null && !sortBy.matches("name|price")) {
            throw new IllegalArgumentException("Invalid sort field. Allowed fields: 'name', 'price'.");
        }
    }
}
