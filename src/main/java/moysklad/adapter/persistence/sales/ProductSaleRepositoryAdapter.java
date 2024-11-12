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
        log.info("Fetching all product sales.");
        return productSaleRepositoryJpa.findAll();
    }

    @Override
    public Optional<ProductSale> findById(Long id) {
        log.info("Fetching product sale with id: {}", id);
        return productSaleRepositoryJpa.findById(id);
    }

    @Override
    public ProductSale save(ProductSale productSale) {
        ProductSale savedSale = productSaleRepositoryJpa.save(productSale);
        log.info("Product sale saved with id: {}", savedSale.getId());
        return savedSale;
    }

    @Override
    public void deleteById(Long id) {
        productSaleRepositoryJpa.deleteById(id);
        log.info("Product sale with id: {} deleted", id);
    }

    @Override
    public ProductSale update(ProductSale productSale) {
        ProductSale updatedSale = productSaleRepositoryJpa.save(productSale);
        log.info("Product sale updated with id: {}", updatedSale.getId());
        return updatedSale;
    }
}
