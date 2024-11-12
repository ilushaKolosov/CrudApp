package moysklad.app.impl.sales;

import lombok.RequiredArgsConstructor;
import moysklad.app.api.product.ProductRepository;
import moysklad.app.api.sales.DeleteProductSaleInbound;
import moysklad.app.api.sales.ProductSaleRepository;
import moysklad.domain.ProductSale;
import moysklad.domain.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteProductSaleUseCase implements DeleteProductSaleInbound {
    private final ProductSaleRepository productSaleRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void execute(Long id) {
        ProductSale productSale = productSaleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ProductSale with ID " + id + " not found."));

        Product product = productSale.getProduct();
        product.setQuantity(product.getQuantity() + productSale.getQuantity());
        productRepository.save(product);

        productSaleRepository.deleteById(id);
    }
}