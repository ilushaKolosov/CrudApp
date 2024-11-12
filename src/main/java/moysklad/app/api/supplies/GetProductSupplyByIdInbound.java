package moysklad.app.api.supplies;

import moysklad.domain.ProductSupply;

import java.util.Optional;

public interface GetProductSupplyByIdInbound {
    Optional<ProductSupply> execute(Long id);
}
