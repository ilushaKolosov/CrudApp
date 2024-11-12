package moysklad.app.api.supplies;

import moysklad.domain.ProductSupply;

import java.util.List;

public interface GetAllProductSuppliesInbound {
    List<ProductSupply> execute();
}
