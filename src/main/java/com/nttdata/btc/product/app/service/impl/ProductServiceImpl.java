package com.nttdata.btc.product.app.service.impl;

import com.nttdata.btc.product.app.model.entity.Product;
import com.nttdata.btc.product.app.model.request.ProductRequest;
import com.nttdata.btc.product.app.model.request.UpdateProductRequest;
import com.nttdata.btc.product.app.model.response.ProductResponse;
import com.nttdata.btc.product.app.repository.ProductRepository;
import com.nttdata.btc.product.app.service.ProductService;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.nttdata.btc.product.app.util.constant.Constants.DEFAULT_FALSE;
import static com.nttdata.btc.product.app.util.enums.FamilyProductEnum.findFamily;
import static com.nttdata.btc.product.app.util.enums.TypeProductEnum.findType;

/**
 * Class implement methods from ProductService.
 *
 * @author lrs
 */
@Service
public class ProductServiceImpl implements ProductService {

    /**
     * Inject dependency {@link ProductRepository}
     */
    @Autowired
    ProductRepository repository;

    /**
     * This method return all products.
     *
     * @return {@link List <ProductResponse>}
     */
    @Override
    public Flux<ProductResponse> findAll() {
        return repository.findAll().filter(Product::isStatus)
                .map(c -> buildProductR.apply(c))
                .onErrorReturn(new ProductResponse());
    }

    /**
     * This method find a customer by id.
     *
     * @param id {@link String}
     * @return {@link ProductResponse}
     */
    @Override
    public Mono<ProductResponse> findById(String id) {
        return repository.findById(id)
                .filter(Product::isStatus)
                .map(e -> buildProductR.apply(e))
                .onErrorReturn(new ProductResponse());
    }

    /**
     * This method save a product.
     *
     * @param request {@link ProductRequest}
     * @return {@link ProductResponse}
     */
    @Override
    public Mono<ProductResponse> save(ProductRequest request) {
        return repository.save(buildProduct.apply(request))
                .flatMap(entity -> Mono.just(buildProductR.apply(entity)))
                .onErrorReturn(new ProductResponse());
    }

    /**
     * This method update status from product.
     *
     * @param id {@link String}
     * @return {@link Void}
     */
    @Override
    public Mono<Void> delete(String id) {
        return repository.findById(id).filter(Product::isStatus)
                .map(e -> updateStatus.apply(e, DEFAULT_FALSE))
                .flatMap(e -> repository.delete(e));
    }

    /**
     * This method update a product.
     *
     * @param request {@link UpdateProductRequest}
     * @return {@link ProductResponse}
     */
    @Override
    public Mono<ProductResponse> update(UpdateProductRequest request) {
        return repository.findById(request.getId_product())
                .map(entity -> updateProduct.apply(request, entity))
                .flatMap(product -> repository.save(product))
                .flatMap(pupdated -> Mono.just(buildProductR.apply(pupdated)))
                .onErrorReturn(new ProductResponse());
    }

    /**
     * BiFunction update Product.
     */
    BiFunction<UpdateProductRequest, Product, Product> updateProduct = (request, bean) -> {
        bean.setName(request.getName());
        bean.setType(request.getType());
        bean.setCategory(request.getCategory());
        bean.setDescription(request.getDescription());
        bean.setStatement_fee(request.getStatement_fee());
        bean.setStatement_transaction(request.getStatement_transaction());
        bean.setMax_operations(request.getMax_operations());
        return bean;
    };

    /**
     * BiFunction updateStatus from Product.
     */
    BiFunction<Product, Boolean, Product> updateStatus = (product, status) -> {
        product.setStatus(status);
        return product;
    };

    /**
     * Function build new Product.
     */
    Function<ProductRequest, Product> buildProduct = p -> new Product(p.getName(),
            p.getType(), p.getCategory(), p.getDescription(), p.getStatement_fee(),
            p.getStatement_transaction(), p.getMax_operations());

    /**
     * Function build new ProductResponse.
     */
    Function<Product, ProductResponse> buildProductR = entity -> {
        ProductResponse response = new ProductResponse();
        response.setId_product(entity.getId_product());
        response.setName(entity.getName());
        response.setType(entity.getType());
        response.setType_description(findType(Integer.valueOf(entity.getType())).getDescription());
        response.setCategory(entity.getCategory());
        response.setCategory_description(findFamily(Integer.valueOf(entity.getCategory())).getDescription());
        response.setDescription(entity.getDescription());
        response.setStatement_fee(entity.getStatement_fee());
        response.setStatement_transaction(entity.getStatement_transaction());
        response.setMax_operations(entity.getMax_operations());
        response.setRegisterDate(entity.getRegisterDate());
        response.setStatus(entity.isStatus());
        return response;
    };
}