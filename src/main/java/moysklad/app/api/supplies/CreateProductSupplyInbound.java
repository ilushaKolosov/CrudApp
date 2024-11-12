package moysklad.app.api.supplies;

import moysklad.domain.ProductSupply;

public interface CreateProductSupplyInbound {
    ProductSupply execute(ProductSupply productSupply);
}
