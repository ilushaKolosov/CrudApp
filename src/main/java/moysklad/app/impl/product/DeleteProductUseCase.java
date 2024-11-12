package moysklad.app.impl.product;

import lombok.RequiredArgsConstructor;
import moysklad.app.api.product.DeleteProductInbound;
import moysklad.app.api.product.ProductRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DeleteProductUseCase implements DeleteProductInbound {
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void execute(Long id) {
        productRepository.deleteById(id);
    }
}
