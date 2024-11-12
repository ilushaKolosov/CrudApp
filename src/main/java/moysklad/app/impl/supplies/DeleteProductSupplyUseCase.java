package moysklad.app.impl.supplies;

import lombok.RequiredArgsConstructor;
import moysklad.app.api.product.ProductRepository;
import moysklad.app.api.supplies.DeleteProductSupplyInbound;
import moysklad.app.api.supplies.ProductSupplyRepository;
import moysklad.domain.ProductSupply;
import moysklad.domain.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteProductSupplyUseCase implements DeleteProductSupplyInbound {
    private final ProductSupplyRepository productSupplyRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void execute(Long id) {
        ProductSupply productSupply = productSupplyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ProductSupply with ID " + id + " not found."));

        Product product = productSupply.getProduct();
        product.setQuantity(product.getQuantity() - productSupply.getQuantity());
        productRepository.save(product);

        productSupplyRepository.deleteById(id);
    }
}
