package moysklad.app.impl.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moysklad.app.api.product.CreateProductInbound;
import moysklad.app.api.product.ProductRepository;
import moysklad.domain.Product;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateProductUseCase implements CreateProductInbound {
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Product execute(Product product) {
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setInStock(product.getInStock());
        newProduct.setQuantity(product.getQuantity());
        return productRepository.save(newProduct);
    }
}
