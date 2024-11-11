package moysklad.adapter.persistence.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moysklad.app.api.product.ProductRepository;
import moysklad.domain.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {
    public static ConcurrentHashMap<Long, Product> STORAGE = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    @Override
    public List<Product> findAll() {
        log.info("Fetching all products. Current storage size: {}", STORAGE.size());
        return new ArrayList<>(STORAGE.values());
    }

    @Override
    public Optional<Product> findById(Long id) {
        log.info("Fetching product with id: {}", id);
        return Optional.ofNullable(STORAGE.get(id));
    }

    @Override
    public Product save(Product product) {
        product.setId(nextId.getAndIncrement());
        STORAGE.put(product.getId(), product);
        log.info("Product saved with id: {}", product.getId());
        return product;
    }

    @Override
    public Product update(Product product) {
        STORAGE.put(product.getId(), product);
        log.info("Product updated with id: {}", product.getId());
        return product;
    }

    @Override
    public void deleteById(Long id) {
        STORAGE.remove(id);
        log.info("Product with id: {} deleted", id);
    }

    @Override
    public String getProductDatabaseState() {
        StringBuilder state = new StringBuilder("Current Database State:\n");
        if (STORAGE.isEmpty()) {
            state.append("No products stored.\n");
        } else {
            for (Map.Entry<Long, Product> entry : STORAGE.entrySet()) {
                Product product = entry.getValue();
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
