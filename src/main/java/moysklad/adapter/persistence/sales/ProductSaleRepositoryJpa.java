package moysklad.adapter.persistence.sales;

import moysklad.domain.ProductSale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSaleRepositoryJpa extends JpaRepository<ProductSale, Long> {
}
