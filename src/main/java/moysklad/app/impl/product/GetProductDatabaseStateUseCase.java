package moysklad.app.impl.product;

import lombok.RequiredArgsConstructor;
import moysklad.app.api.product.GetProductDatabaseStateInbound;
import moysklad.app.api.product.ProductRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetProductDatabaseStateUseCase implements GetProductDatabaseStateInbound {
    private final ProductRepository productRepository;

    @Override
    public String execute() {
        return productRepository.getProductDatabaseState();
    }
}
