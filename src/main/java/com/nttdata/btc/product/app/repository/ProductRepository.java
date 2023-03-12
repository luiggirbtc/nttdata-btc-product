package com.nttdata.btc.product.app.repository;

import com.nttdata.btc.product.app.model.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Response bean ProductRepository.
 *
 * @author lrs
 */
@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}