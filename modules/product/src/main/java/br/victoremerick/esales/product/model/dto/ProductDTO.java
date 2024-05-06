package br.victoremerick.esales.product.model.dto;

import br.victoremerick.esales.product.model.entity.Product;
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

    public static ProductDTO from(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .build();
    }
}
