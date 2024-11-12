package moysklad.app.impl.product;

import lombok.RequiredArgsConstructor;
import moysklad.app.api.product.ProductRepository;
import moysklad.app.api.product.UpdateProductInbound;
import moysklad.domain.Product;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UpdateProductUseCase implements UpdateProductInbound {
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Product execute(Product product) {
        Product existingProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new IllegalArgumentException("Product with ID " + product.getId() + " does not exist."));

        Optional.ofNullable(product.getName()).ifPresent(existingProduct::setName);
        Optional.ofNullable(product.getDescription()).ifPresent(existingProduct::setDescription);
        Optional.ofNullable(product.getPrice()).ifPresent(existingProduct::setPrice);
        Optional.ofNullable(product.getInStock()).ifPresent(existingProduct::setInStock);

        return productRepository.update(existingProduct);
    }
}
