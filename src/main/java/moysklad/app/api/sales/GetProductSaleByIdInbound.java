package moysklad.app.api.sales;

import moysklad.domain.ProductSale;

import java.util.Optional;

public interface GetProductSaleByIdInbound {
    Optional<ProductSale> execute(Long id);
}
