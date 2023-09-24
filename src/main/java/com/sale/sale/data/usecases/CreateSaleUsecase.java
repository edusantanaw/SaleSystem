package com.sale.sale.data.usecases;

import com.sale.sale.domain.entities.Sale;
import com.sale.sale.domain.usecases.CreateUsecase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service()
@AllArgsConstructor()
public class CreateSaleUsecase implements CreateUsecase<Sale, Sale> {
    @Override
    public Sale execute(Sale data) {
        return null;
    }
}
