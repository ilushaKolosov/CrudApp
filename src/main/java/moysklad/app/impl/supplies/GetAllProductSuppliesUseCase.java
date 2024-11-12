package moysklad.app.impl.supplies;

import lombok.RequiredArgsConstructor;
import moysklad.app.api.supplies.GetAllProductSuppliesInbound;
import moysklad.app.api.supplies.ProductSupplyRepository;
import moysklad.domain.ProductSupply;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllProductSuppliesUseCase implements GetAllProductSuppliesInbound {
    private final ProductSupplyRepository productSupplyRepository;

    @Override
    public List<ProductSupply> execute() {
        return productSupplyRepository.findAll();
    }
}
