package moysklad.app.api.supplies;

import moysklad.domain.ProductSupply;

import java.util.List;
import java.util.Optional;

public interface ProductSupplyRepository {
    List<ProductSupply> findAll();
    Optional<ProductSupply> findById(Long id);
    ProductSupply save(ProductSupply product);
    void deleteById(Long id);
    ProductSupply update(ProductSupply product);
}
