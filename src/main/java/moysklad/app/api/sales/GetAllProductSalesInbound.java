package moysklad.app.api.sales;

import moysklad.domain.ProductSale;

import java.util.List;

public interface GetAllProductSalesInbound {
    List<ProductSale> execute();
}
