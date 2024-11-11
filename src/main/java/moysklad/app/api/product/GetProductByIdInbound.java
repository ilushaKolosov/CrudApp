package moysklad.app.api.product;

import moysklad.domain.Product;

import java.util.Optional;

public interface GetProductByIdInbound {
    Optional<Product> execute(Long id);
}
