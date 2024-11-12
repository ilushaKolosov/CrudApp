package moysklad.adapter.persistence.sales;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moysklad.app.api.sales.ProductSaleRepository;
import moysklad.domain.ProductSale;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductSaleRepositoryAdapter implements ProductSaleRepository {
    private final ProductSaleRepositoryJpa productSaleRepositoryJpa;

    @Override
    public List<ProductSale> findAll() {
        return productSaleRepositoryJpa.findAll();
    }

    @Override
    public Optional<ProductSale> findById(Long id) {
        return productSaleRepositoryJpa.findById(id);
    }

    @Override
    public ProductSale save(ProductSale product) {
        return productSaleRepositoryJpa.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productSaleRepositoryJpa.deleteById(id);
    }

    @Override
    public ProductSale update(ProductSale product) {
        return productSaleRepositoryJpa.save(product);
    }
}
