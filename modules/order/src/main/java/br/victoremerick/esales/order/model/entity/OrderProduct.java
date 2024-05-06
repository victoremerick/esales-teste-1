package br.victoremerick.esales.order.model.entity;

import br.victoremerick.esales.order.model.dto.ProductDTO;
import br.victoremerick.esales.order.model.dto.ProductOrderDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_product_tb")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idProduct;
    private Long quantity;
    private BigDecimal valueUnit;

    public static OrderProduct from(ProductOrderDTO dto, ProductDTO productDTO){
        return OrderProduct.builder()
                .idProduct(dto.getIdProduct())
                .quantity(dto.getQuantity())
                .valueUnit(productDTO.getValue())
                .build();
    }
}
