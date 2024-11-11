package moysklad.adapter.persistence.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moysklad.app.api.product.ProductRepository;
import moysklad.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {
    private final ProductRepositoryJpa productRepositoryJpa;

    @Override
    public List<Product> findAll() {
        log.info("Fetching all products.");
        return productRepositoryJpa.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        log.info("Fetching product with id: {}", id);
        return productRepositoryJpa.findById(id);
    }

    @Override
    public Product save(Product product) {
        log.info("Product saved with id: {}", product.getId());
        return productRepositoryJpa.save(product);
    }

    @Override
    public Product update(Product product) {
        log.info("Product updated with id: {}", product.getId());
        return productRepositoryJpa.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepositoryJpa.deleteById(id);
        log.info("Product with id: {} deleted", id);
    }

    @Override
    public String getProductDatabaseState() {
        StringBuilder state = new StringBuilder("Current Database State:\n");
        List<Product> products = productRepositoryJpa.findAll();
        if (products.isEmpty()) {
            state.append("No products stored.\n");
        } else {
            for (Product product : products) {
                state.append(String.format(
                        "ID: %d, Name: %s, Description: %s, Price: %.2f, InStock: %s%n",
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getInStock()
                ));
            }
        }
        log.info(state.toString());
        return state.toString();
    }
}
