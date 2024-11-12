package moysklad.app.impl.sales;

import lombok.RequiredArgsConstructor;
import moysklad.app.api.sales.GetAllProductSalesInbound;
import moysklad.app.api.sales.ProductSaleRepository;
import moysklad.domain.ProductSale;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllProductSalesUseCase implements GetAllProductSalesInbound {
    private final ProductSaleRepository productSaleRepository;

    @Override
    public List<ProductSale> execute() {
        return productSaleRepository.findAll();
    }
}
