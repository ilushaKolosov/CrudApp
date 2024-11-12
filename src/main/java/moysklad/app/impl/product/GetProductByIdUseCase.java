package moysklad.app.impl.product;


import lombok.RequiredArgsConstructor;
import moysklad.app.api.product.GetProductByIdInbound;
import moysklad.app.api.product.ProductRepository;
import moysklad.domain.Product;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetProductByIdUseCase implements GetProductByIdInbound {
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Optional<Product> execute(Long id) {
        return productRepository.findById(id);
    }
}
