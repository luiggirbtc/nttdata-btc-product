package com.nttdata.btc.product.app.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum TypeProductEnum {
    AHORRO(1, "Ahorro", 1),
    CUENTA_CORRIENTE(2, "Cuenta corriente", 1),
    PLAZO_FIJO(3, "Cuenta corriente", 1),
    PERSONAL(4, "Cuenta corriente", 2),
    EMPRESARIAL(5, "Cuenta corriente", 2),
    TARJETA_CREDITO(6, "Cuenta corriente", 2);

    private int code;
    private String description;
    private int family;

    /**
     * Method search TypeProductEnum by code.
     *
     * @param code {@link Integer}
     * @return {@link TypeProductEnum}
     */
    public static TypeProductEnum findType(Integer code) {
        return Arrays.stream(TypeProductEnum.values())
                .filter(e -> e.code == code).findFirst()
                .orElse(null);
    }
}