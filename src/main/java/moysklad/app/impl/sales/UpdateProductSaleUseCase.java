package moysklad.app.impl.sales;

import lombok.RequiredArgsConstructor;
import moysklad.app.api.product.ProductRepository;
import moysklad.app.api.sales.ProductSaleRepository;
import moysklad.app.api.sales.UpdateProductSaleInbound;
import moysklad.domain.Product;
import moysklad.domain.ProductSale;
import moysklad.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateProductSaleUseCase implements UpdateProductSaleInbound {
    private final ProductSaleRepository productSaleRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ProductSale execute(ProductSale productSale) {
        return productSaleRepository.findById(productSale.getId())
                .map(existingProductSale -> {
                    Optional.ofNullable(productSale.getDocumentName())
                            .ifPresent(existingProductSale::setDocumentName);

                    Optional.ofNullable(productSale.getProduct())
                            .ifPresent(existingProductSale::setProduct);

                    Optional.ofNullable(productSale.getQuantity()).ifPresent(newQuantity -> {
                        Product product = productRepository.findById(existingProductSale.getProduct().getId())
                                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

                        int temp = product.getQuantity() + existingProductSale.getQuantity() - newQuantity;
                        if (temp < 0) {
                            throw new IllegalArgumentException("Quantity cannot be negative!");
                        }

                        product.setQuantity(temp);
                        productRepository.save(product);
                        existingProductSale.setQuantity(newQuantity);
                    });

                    Optional.ofNullable(productSale.getPurchasePrice())
                            .ifPresent(existingProductSale::setPurchasePrice);

                    return productSaleRepository.update(existingProductSale);
                })
                .orElseThrow(() -> new IllegalArgumentException("ProductSale with ID " + productSale.getId() + " not found."));
    }
}
