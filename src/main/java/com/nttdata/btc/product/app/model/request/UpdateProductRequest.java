package com.nttdata.btc.product.app.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class UpdateProductRequest.
 *
 * @author lrs
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateProductRequest extends BaseRequest {
    private String id_product;
}