package com.nttdata.btc.product.app.controller;

import com.nttdata.btc.product.app.model.request.ProductRequest;
import com.nttdata.btc.product.app.model.request.UpdateProductRequest;
import com.nttdata.btc.product.app.model.response.ProductResponse;
import com.nttdata.btc.product.app.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class ProductController.
 *
 * @author lrs
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    /**
     * Inject dependency ProductService.
     */
    @Autowired
    private ProductService service;

    /**
     * Service find by id.
     *
     * @param id {@link String}
     * @return {@link ProductResponse}
     */
    @GetMapping("id/{id}")
    public Mono<ResponseEntity<ProductResponse>> findById(@PathVariable final String id) {
        return service.findById(id)
                .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Service create customer.
     *
     * @param request {@link ProductRequest}
     * @return {@link ProductResponse}
     */
    @PostMapping("/")
    public Mono<ResponseEntity<ProductResponse>> createProduct(@RequestBody final ProductRequest request) {
        log.info("Start CreateProduct.");
        return service.save(request)
                .map(p -> new ResponseEntity<>(p, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    /**
     * Service update product.
     *
     * @param request {@link UpdateProductRequest}
     * @return {@link ProductResponse}
     */
    @PutMapping("/")
    public Mono<ResponseEntity<ProductResponse>> updateProduct(@RequestBody final UpdateProductRequest request) {
        log.info("Start UpdateProduct.");
        return service.update(request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    /**
     * Service list all products.
     *
     * @return {@link ProductResponse}
     */
    @GetMapping("/")
    public Flux<ProductResponse> findAll() {
        log.info("Start findAll Products.");
        return service.findAll()
                .doOnNext(product -> log.info(product.toString()));
    }

    /**
     * Service delete product.
     *
     * @param id {@link String}
     * @return {@link Void}
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable(value = "id") final String id) {
        return service.delete(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}