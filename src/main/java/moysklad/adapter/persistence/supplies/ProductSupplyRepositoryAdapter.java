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
        return productSupplyRepositoryJpa.findAll();
    }

    @Override
    public Optional<ProductSupply> findById(Long id) {
        return productSupplyRepositoryJpa.findById(id);
    }

    @Override
    public ProductSupply save(ProductSupply product) {
        return productSupplyRepositoryJpa.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productSupplyRepositoryJpa.deleteById(id);
    }

    @Override
    public ProductSupply update(ProductSupply product) {
        return productSupplyRepositoryJpa.save(product);
    }
}
