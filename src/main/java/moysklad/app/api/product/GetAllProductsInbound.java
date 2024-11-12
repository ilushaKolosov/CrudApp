package moysklad.app.api.product;

import moysklad.adapter.dto.GetProductsDto;
import moysklad.domain.Product;

import java.util.List;

public interface GetAllProductsInbound {
    List<Product> execute(GetProductsDto request);
}
