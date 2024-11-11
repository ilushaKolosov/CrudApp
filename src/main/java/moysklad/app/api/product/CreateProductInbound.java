package moysklad.app.api.product;

import moysklad.domain.Product;

public interface CreateProductInbound {
    Product execute(Product product);
}
