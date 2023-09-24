package com.sale.sale.domain.enums;

import lombok.Getter;

@Getter
public enum PaymentMethods {
    CARD("card"),
    PIX("pix"),
    BILLET("billit");

    private final String method;
    PaymentMethods(String method){
            this.method = method;
    }

}
