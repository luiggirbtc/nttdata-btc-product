package com.nttdata.btc.product.app.model.response;

import com.nttdata.btc.product.app.model.request.BaseRequest;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Response bean ProductResponse.
 *
 * @author lrs
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ProductResponse extends BaseRequest {
    @Schema(description = "Id product", example = "120cf999662f294fc1234567")
    private String id_product;
    @Schema(required = true, description = "Register date", example = "2023-03-11T21:58:49.101+00:00")
    private Date registerDate;
    @Schema(required = true, description = "Status product", example = "true")
    private Boolean status = false;
}