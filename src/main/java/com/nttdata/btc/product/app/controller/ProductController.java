package com.nttdata.btc.product.app.controller;

import com.nttdata.btc.product.app.model.request.ProductRequest;
import com.nttdata.btc.product.app.model.request.UpdateProductRequest;
import com.nttdata.btc.product.app.model.response.ProductResponse;
import com.nttdata.btc.product.app.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
@Tag(name = "Product", description = "Service product")
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
    @Operation(summary = "Get a product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found product",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProductResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)})
    @GetMapping(value = "id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ProductResponse>> findById(@PathVariable final String id) {
        return service.findById(id)
                .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Service create product.
     *
     * @param request {@link ProductRequest}
     * @return {@link ProductResponse}
     */
    @Operation(summary = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProductResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
    @Operation(summary = "Update product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProductResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
    @Operation(summary = "Get all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProductResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
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
    @Operation(summary = "Delete product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable(value = "id") final String id) {
        return service.delete(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}