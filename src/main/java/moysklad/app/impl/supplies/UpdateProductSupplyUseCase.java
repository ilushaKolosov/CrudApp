package moysklad.app.impl.supplies;

import lombok.RequiredArgsConstructor;
import moysklad.app.api.product.ProductRepository;
import moysklad.app.api.supplies.ProductSupplyRepository;
import moysklad.app.api.supplies.UpdateProductSupplyInbound;
import moysklad.domain.Product;
import moysklad.domain.ProductSupply;
import moysklad.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateProductSupplyUseCase implements UpdateProductSupplyInbound {
    private final ProductSupplyRepository productSupplyRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ProductSupply execute(ProductSupply productSupply) {
        return productSupplyRepository.findById(productSupply.getId())
                .map(existingProductSupply -> {
                    Optional.ofNullable(productSupply.getDocumentName())
                            .ifPresent(existingProductSupply::setDocumentName);

                    Optional.ofNullable(productSupply.getProduct())
                            .ifPresent(existingProductSupply::setProduct);

                    Optional.ofNullable(productSupply.getQuantity()).ifPresent(newQuantity -> {
                        Product product = productRepository.findById(existingProductSupply.getProduct().getId())
                                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

                        int temp = product.getQuantity() - existingProductSupply.getQuantity() + newQuantity;
                        if (temp < 0) {
                            throw new IllegalArgumentException("Quantity cannot be negative!");
                        }

                        product.setQuantity(temp);
                        productRepository.save(product);
                        existingProductSupply.setQuantity(newQuantity);
                    });

                    return productSupplyRepository.update(existingProductSupply);
                })
                .orElseThrow(() -> new IllegalArgumentException("ProductSupply with ID " + productSupply.getId() + " not found."));
    }

}
