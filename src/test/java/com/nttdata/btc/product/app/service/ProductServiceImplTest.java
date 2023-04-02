package com.nttdata.btc.product.app.service;

import com.nttdata.btc.product.app.model.entity.Product;
import com.nttdata.btc.product.app.model.request.ProductRequest;
import com.nttdata.btc.product.app.model.request.UpdateProductRequest;
import com.nttdata.btc.product.app.model.response.ProductResponse;
import com.nttdata.btc.product.app.repository.ProductRepository;
import com.nttdata.btc.product.app.service.impl.ProductServiceImpl;
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
class ProductServiceImplTest {
    @InjectMocks
    ProductServiceImpl service;
    @Mock
    ProductRepository repository;


    private List<Product> listProducts = new ArrayList<>();

    static final String ID_PRODUCT = "640c24cd3b905b25cfa2f25a";

    @BeforeEach
    private void setUp() {
        Product product = new Product();
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
    @DisplayName("Return all products")
    void testFindAll() {
        when(repository.findAll()).thenReturn(Flux.fromIterable(listProducts));

        Flux<ProductResponse> result = service.findAll();

        assertEquals(result.blockFirst().getName(), listProducts.get(0).getName());
    }

    @Test
    @DisplayName("Return product by id")
    void testFindById() {
        when(repository.findById(anyString())).thenReturn(Mono.just(listProducts.get(0)));

        Mono<ProductResponse> result = service.findById(ID_PRODUCT);

        assertEquals(result.block().getId_product(), listProducts.get(0).getId_product());
    }

    @Test
    @DisplayName("Create new product")
    void testSave() {
        Product entity = listProducts.get(0);

        ProductRequest request = new ProductRequest();
        request.setName(entity.getName());
        request.setType(entity.getType());
        request.setCategory(entity.getCategory());
        request.setDescription(entity.getDescription());
        request.setStatement_fee(entity.getStatement_fee());
        request.setStatement_transaction(entity.getStatement_transaction());
        request.setMax_operations(entity.getMax_operations());

        when(repository.save(any())).thenReturn(Mono.just(entity));

        Mono<ProductResponse> result = service.save(request);

        assertEquals(result.block().getStatement_fee(), entity.getStatement_fee());
    }

    @Test
    @DisplayName("Delete product")
    void testDelete() {
        Product entity = listProducts.get(0);
        when(repository.findById(anyString())).thenReturn(Mono.just(entity));
        when(repository.save(any())).thenReturn(Mono.just(entity));

        Mono<Boolean> result = service.delete(ID_PRODUCT).thenReturn(Boolean.TRUE);

        assertTrue(result.block());
    }

    @Test
    @DisplayName("Update product")
    void testUpdate() {
        Product entity = listProducts.get(0);

        UpdateProductRequest request = new UpdateProductRequest();
        request.setId_product(ID_PRODUCT);
        request.setName(entity.getName());
        request.setType(entity.getType());
        request.setCategory(entity.getCategory());
        request.setDescription(entity.getDescription());
        request.setStatement_fee(entity.getStatement_fee());
        request.setStatement_transaction(entity.getStatement_transaction());
        request.setMax_operations(entity.getMax_operations());

        when(repository.findById(anyString())).thenReturn(Mono.just(entity));
        when(repository.save(any())).thenReturn(Mono.just(entity));

        Mono<ProductResponse> result = service.update(request);

        assertEquals(result.block().getMax_operations(), entity.getMax_operations());
    }
}