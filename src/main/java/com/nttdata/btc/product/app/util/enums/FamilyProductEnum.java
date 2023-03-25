package com.nttdata.btc.product.app.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * EnumClass FamilyProductEnum.
 *
 * @author lrs
 */
@AllArgsConstructor
@Getter
public enum FamilyProductEnum {
    PASIVO(1, "Pasivo"),
    ACTIVO(2, "Activo");

    private Integer code;
    private String description;

    /**
     * Method search FamilyProductEnum by code.
     *
     * @param code {@link Integer}
     * @return {@link FamilyProductEnum}
     */
    public static FamilyProductEnum findFamily(Integer code) {
        return Arrays.stream(FamilyProductEnum.values())
                .filter(e -> e.code.equals(code)).findFirst()
                .orElse(null);
    }
}