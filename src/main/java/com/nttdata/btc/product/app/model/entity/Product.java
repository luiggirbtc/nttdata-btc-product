package com.nttdata.btc.product.app.model.entity;

import java.util.Date;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity Product.
 *
 * @author lrs
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "product")
public class Product {
    @Id
    private String id_product;

    private String name;

    /**
     * 1 = ahorro, 2 = corriente, 3 = plazo fijo
     * 4 = credito personal, 5 = credito empresarial, 6 = tarjeta credito personal o empresarial
     */
    private String type; // pasivo  - activo

    /**
     * 1 = pasivo, 2 = activo
     */
    private String category;

    private String description;

    /**
     * comision_mantenimiento
     */
    private Double statement_fee;

    /**
     * comision_transaccion
     */
    private Double statement_transaction;

    private Integer max_operations;

    private Date registerDate = new Date();

    private boolean status = true;

    /**
     * Constructor create a new product.
     *
     * @param name                  {@link String}
     * @param type                  {@link String}
     * @param category              {@link String}
     * @param description           {@link String}
     * @param statement_fee         {@link Double}
     * @param statement_transaction {@link Double}
     * @param max_operations        {@link Integer}
     */
    public Product(String name, String type, String category, String description,
                   Double statement_fee, Double statement_transaction,
                   Integer max_operations) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.description = description;
        this.statement_fee = statement_fee;
        this.statement_transaction = statement_transaction;
        this.max_operations = max_operations;
    }
}