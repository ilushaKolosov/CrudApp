package moysklad.app.impl.supplies;

import lombok.RequiredArgsConstructor;
import moysklad.app.api.supplies.GetProductSupplyByIdInbound;
import moysklad.app.api.supplies.ProductSupplyRepository;
import moysklad.domain.ProductSupply;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetProductSupplyByIdUseCase implements GetProductSupplyByIdInbound {
    private final ProductSupplyRepository productSupplyRepository;

    @Override
    public Optional<ProductSupply> execute(Long id) {
        return productSupplyRepository.findById(id);
    }
}
