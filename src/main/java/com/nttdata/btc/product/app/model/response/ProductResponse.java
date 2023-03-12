package com.nttdata.btc.product.app.model.response;

import com.nttdata.btc.product.app.model.request.BaseRequest;

import java.util.Date;

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
    private String id_product;
    private Date registerDate;
    private Boolean status = false;
}