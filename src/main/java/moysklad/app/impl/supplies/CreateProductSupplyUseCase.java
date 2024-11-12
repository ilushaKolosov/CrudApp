package moysklad.app.impl.supplies;

import lombok.RequiredArgsConstructor;
import moysklad.app.api.product.ProductRepository;
import moysklad.app.api.supplies.CreateProductSupplyInbound;
import moysklad.app.api.supplies.ProductSupplyRepository;
import moysklad.domain.ProductSupply;
import moysklad.domain.Product;
import moysklad.exception.ProductNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateProductSupplyUseCase implements CreateProductSupplyInbound {
    private final ProductSupplyRepository productSupplyRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ProductSupply execute(ProductSupply productSupply) {
        if (productSupply.getQuantity() == null) {
            throw new IllegalArgumentException("Quantity cannot be null.");
        }

        Product product = productRepository.findById(productSupply.getProduct().getId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        product.setQuantity(product.getQuantity() + productSupply.getQuantity());
        productRepository.save(product);

        return productSupplyRepository.save(productSupply);
    }
}
