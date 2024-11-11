package moysklad.app.api.product;

import moysklad.domain.Product;

import java.util.List;

public interface GetAllProductsInbound {
    List<Product> execute();
}
