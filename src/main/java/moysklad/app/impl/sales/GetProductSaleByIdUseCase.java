package moysklad.app.impl.sales;

import lombok.RequiredArgsConstructor;
import moysklad.app.api.sales.GetProductSaleByIdInbound;
import moysklad.app.api.sales.ProductSaleRepository;
import moysklad.domain.ProductSale;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetProductSaleByIdUseCase implements GetProductSaleByIdInbound {
    private final ProductSaleRepository productSaleRepository;

    @Override
    public Optional<ProductSale> execute(Long id) {
        return productSaleRepository.findById(id);
    }
}
