package moysklad.app.api.product;


import moysklad.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    void deleteById(Long id);
    Product update(Product product);
    String getProductDatabaseState();
}
