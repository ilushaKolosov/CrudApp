package moysklad.app.api.sales;

import moysklad.domain.ProductSale;

public interface CreateProductSaleInbound {
    ProductSale execute(ProductSale productSale);
}
