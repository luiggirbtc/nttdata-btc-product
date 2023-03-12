package com.nttdata.btc.product.app.service;

import com.nttdata.btc.product.app.model.request.ProductRequest;
import com.nttdata.btc.product.app.model.request.UpdateProductRequest;
import com.nttdata.btc.product.app.model.response.ProductResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service ProductService.
 *
 * @author lrs.
 */
public interface ProductService {
    /**
     * Method findAll.
     */
    Flux<ProductResponse> findAll();

    /**
     * Method findById.
     */
    Mono<ProductResponse> findById(String id);

    /**
     * Method save.
     */
    Mono<ProductResponse> save(ProductRequest request);

    /**
     * Method Delete.
     */
    Mono<Void> delete(String id);

    /**
     * Method update product.
     */
    Mono<ProductResponse> update(UpdateProductRequest request);
}