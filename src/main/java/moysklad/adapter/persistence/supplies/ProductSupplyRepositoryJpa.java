package moysklad.adapter.persistence.supplies;

import moysklad.domain.ProductSupply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSupplyRepositoryJpa extends JpaRepository<ProductSupply, Long> {
}
