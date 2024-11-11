package moysklad.app.api.product;

import moysklad.domain.Product;

public interface UpdateProductInbound {
    Product execute(Product product);
}
