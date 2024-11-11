package moysklad.adapter.persistence.product;

import moysklad.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositoryJpa extends JpaRepository<Product, Long> {
}
