package moysklad.app.api.supplies;

import moysklad.domain.ProductSupply;

public interface UpdateProductSupplyInbound {
    ProductSupply execute(ProductSupply productSupply);
}
