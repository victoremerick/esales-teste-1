package br.victoremerick.esales.order.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@JsonIgnoreProperties
@Data
public class ProductDTO {
    private Long id;
    private String name;
    private Long quantity;
    private BigDecimal value;

    public static ProductDTO from(ProductOrderDTO productOrderDTO){
        return ProductDTO.builder().id(productOrderDTO.getIdProduct()).build();
    }
}
