package com.nttdata.btc.product.app.controller;

import com.nttdata.btc.product.app.model.request.ProductRequest;
import com.nttdata.btc.product.app.model.request.UpdateProductRequest;
import com.nttdata.btc.product.app.model.response.ProductResponse;
import com.nttdata.btc.product.app.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @InjectMocks
    ProductController controller;

    @Mock
    ProductService service;

    private List<ProductResponse> listProducts = new ArrayList<>();

    static final String ID_PRODUCT = "640c24cd3b905b25cfa2f25a";

    @BeforeEach
    private void setUp() {
        ProductResponse product = new ProductResponse();
        product.setId_product(ID_PRODUCT);
        product.setName("Cuenta Corriente");
        product.setType("2");
        product.setCategory("1");
        product.setDescription("posee comisión de mantenimiento y sin límite de movimientos mensuales.");
        product.setStatement_fee(0.0);
        product.setStatement_transaction(0.0);
        product.setMax_operations(100);
        product.setRegisterDate(new Date());
        product.setStatus(true);

        listProducts.add(product);
    }

    @Test
    @DisplayName("Return product by id")
    void testFindById() {
        when(service.findById(anyString())).thenReturn(Mono.just(listProducts.get(0)));

        Mono<ProductResponse> result = controller.findById(ID_PRODUCT);

        assertEquals(result.block().getId_product(), listProducts.get(0).getId_product());
    }

    @Test
    @DisplayName("Create new product")
    void testCreateProduct() {
        ProductResponse response = listProducts.get(0);

        ProductRequest request = new ProductRequest();
        request.setName(response.getName());
        request.setType(response.getType());
        request.setCategory(response.getCategory());
        request.setDescription(response.getDescription());
        request.setStatement_fee(response.getStatement_fee());
        request.setStatement_transaction(response.getStatement_transaction());
        request.setMax_operations(response.getMax_operations());

        when(service.save(request)).thenReturn(Mono.just(response));

        Mono<ProductResponse> result = controller.createProduct(request);

        assertEquals(result.block().getId_product(), response.getId_product());
    }

    @Test
    @DisplayName("Update product")
    void testUpdateProduct() {
        ProductResponse response = listProducts.get(0);

        UpdateProductRequest request = new UpdateProductRequest();
        request.setId_product(ID_PRODUCT);
        request.setName(response.getName());
        request.setType(response.getType());
        request.setCategory(response.getCategory());
        request.setDescription(response.getDescription());
        request.setStatement_fee(response.getStatement_fee());
        request.setStatement_transaction(response.getStatement_transaction());
        request.setMax_operations(response.getMax_operations());

        when(service.update(any())).thenReturn(Mono.just(response));

        Mono<ProductResponse> result = controller.updateProduct(request);

        assertEquals(result.block().getMax_operations(), response.getMax_operations());
    }

    @Test
    @DisplayName("Return all products")
    void testFindAll() {
        when(service.findAll()).thenReturn(Flux.fromIterable(listProducts));

        Flux<ProductResponse> result = controller.findAll();

        assertEquals(result.blockFirst().getName(), listProducts.get(0).getName());
    }

    @Test
    @DisplayName("Delete product")
    void testDeleteProduct() {
        when(service.delete(anyString())).thenReturn(Mono.empty());

        Mono<Boolean> result = controller.deleteProduct(ID_PRODUCT).thenReturn(Boolean.TRUE);

        assertTrue(result.block());
    }
}