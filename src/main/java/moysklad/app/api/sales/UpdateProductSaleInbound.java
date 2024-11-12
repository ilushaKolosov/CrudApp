package moysklad.app.api.sales;

import moysklad.domain.ProductSale;

public interface UpdateProductSaleInbound {
    ProductSale execute(ProductSale productSale);
}
