package moysklad.app.impl.sales;

import lombok.RequiredArgsConstructor;
import moysklad.app.api.product.ProductRepository;
import moysklad.app.api.sales.CreateProductSaleInbound;
import moysklad.app.api.sales.ProductSaleRepository;
import moysklad.domain.ProductSale;
import moysklad.domain.Product;
import moysklad.exception.ProductNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateProductSaleUseCase implements CreateProductSaleInbound {
    private final ProductSaleRepository productSaleRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ProductSale execute(ProductSale productSale) {
        if (productSale.getQuantity() == null) {
            throw new IllegalArgumentException("Quantity cannot be null.");
        }

        Product product = productRepository.findById(productSale.getProduct().getId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        if (product.getQuantity() < productSale.getQuantity()) {
            throw new IllegalArgumentException("Insufficient product quantity for sale.");
        }

        product.setQuantity(product.getQuantity() - productSale.getQuantity());
        productRepository.save(product);

        return productSaleRepository.save(productSale);
    }
}
