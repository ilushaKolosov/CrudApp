package moysklad.app.impl.product;

import lombok.RequiredArgsConstructor;
import moysklad.app.api.product.GetAllProductsInbound;
import moysklad.app.api.product.ProductRepository;
import moysklad.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllProductsUseCase implements GetAllProductsInbound {
    private final ProductRepository productRepository;

    public List<Product> execute() {
        return productRepository.findAll();
    }
}
