package com.nttdata.btc.product.app.model.request;

import lombok.*;

/**
 * Entity BaseRequest.
 *
 * @author lrs
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BaseRequest {

    private String name;

    /**
     * 1 = ahorro, 2 = corriente, 3 = plazo fijo
     * 4 = credito personal, 5 = credito empresarial, 6 = tarjeta credito personal o empresarial
     */
    private String type;

    private String type_description;

    /**
     * 1 = pasivo, 2 = activo
     */
    private String category;

    private String category_description;

    private String description;

    /**
     * comision_mantenimiento
     */
    private Double statement_fee;

    /**
     * comision_transaccion
     */
    private Double statement_transaction;

    private Integer max_operations;
}
