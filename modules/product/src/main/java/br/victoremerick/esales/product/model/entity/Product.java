package br.victoremerick.esales.product.model.entity;


import br.victoremerick.esales.product.model.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_tb")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long quantity;
    private BigDecimal value;

    public static Product from(ProductDTO product){
        return Product.builder()
                .id(product.getId())
                .name(product.getName())
                .quantity(product.getQuantity())
                .value(product.getValue())
                .build();
    }
}
