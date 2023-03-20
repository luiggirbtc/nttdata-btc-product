package com.nttdata.btc.product.app.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nttdata.btc.product.app.util.enums.TypeProductEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Entity BaseRequest.
 *
 * @author lrs
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseRequest {

    @NotNull(message = "Is mandatory")
    @NotEmpty(message = "Not be empty")
    @Schema(required = true, description = "Name product", example = "Ahorro")
    private String name;

    /**
     * 1 = ahorro, 2 = corriente, 3 = plazo fijo
     * 4 = credito personal, 5 = credito empresarial, 6 = tarjeta credito personal o empresarial
     */
    @NotNull(message = "Is mandatory")
    @NotEmpty(message = "Not be empty")
    @Schema(required = true, description = "Type product", example = "1 = ahorro | 2 = corriente | 3 = plazo fijo |" +
            " 4 = credito personal | 5 = credito empresarial | 6 = tarjeta credito personal o empresarial")
    private String type;

    @Schema(required = false, description = "Description product", example = "Ahorro")
    private String type_description;

    /**
     * 1 = pasivo, 2 = activo
     */
    @NotNull(message = "Is mandatory")
    @NotEmpty(message = "Not be empty")
    @Schema(required = true, description = "Category", example = "1 = pasivo | 2 = activo")
    private String category;

    @Schema(required = false, description = "Category product", example = "Pasivo")
    private String category_description;

    @Schema(required = false, description = "Description product", example = "Libre de comisión por mantenimiento")
    private String description;

    /**
     * comision_mantenimiento
     */
    @Schema(required = false, description = "product statement fee", example = "0.0")
    private Double statement_fee;

    /**
     * comision_transaccion
     */
    @Schema(required = false, description = "product statement transaction", example = "0.0")
    private Double statement_transaction;

    @Schema(required = false, description = "max operations", example = "250")
    private Integer max_operations;
}