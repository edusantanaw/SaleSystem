package com.sale.sale.domain.usecases;

import com.sale.sale.utils.EmailAlreadyUsedException;

public interface CreateUsecase<In, Out> {
    public Out execute(In data) throws EmailAlreadyUsedException;
}
