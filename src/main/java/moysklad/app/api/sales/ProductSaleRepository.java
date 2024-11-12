package moysklad.app.api.sales;

import moysklad.domain.ProductSale;

import java.util.List;
import java.util.Optional;

public interface ProductSaleRepository {
    List<ProductSale> findAll();
    Optional<ProductSale> findById(Long id);
    ProductSale save(ProductSale product);
    void deleteById(Long id);
    ProductSale update(ProductSale product);
}
