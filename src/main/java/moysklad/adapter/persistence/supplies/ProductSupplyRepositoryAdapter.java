package moysklad.adapter.persistence.supplies;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moysklad.app.api.supplies.ProductSupplyRepository;
import moysklad.domain.ProductSupply;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductSupplyRepositoryAdapter implements ProductSupplyRepository {
    private final ProductSupplyRepositoryJpa productSupplyRepositoryJpa;

    @Override
    public List<ProductSupply> findAll() {
        log.info("Fetching all product supplies.");
        return productSupplyRepositoryJpa.findAll();
    }

    @Override
    public Optional<ProductSupply> findById(Long id) {
        log.info("Fetching product supply with id: {}", id);
        return productSupplyRepositoryJpa.findById(id);
    }

    @Override
    public ProductSupply save(ProductSupply productSupply) {
        ProductSupply savedSupply = productSupplyRepositoryJpa.save(productSupply);
        log.info("Product supply saved with id: {}", savedSupply.getId());
        return savedSupply;
    }

    @Override
    public void deleteById(Long id) {
        productSupplyRepositoryJpa.deleteById(id);
        log.info("Product supply with id: {} deleted", id);
    }

    @Override
    public ProductSupply update(ProductSupply productSupply) {
        ProductSupply updatedSupply = productSupplyRepositoryJpa.save(productSupply);
        log.info("Product supply updated with id: {}", updatedSupply.getId());
        return updatedSupply;
    }
}