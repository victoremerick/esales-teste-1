package br.victoremerick.esales.order.model.dto;

import br.victoremerick.esales.order.model.entity.OrderProduct;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@JsonIgnoreProperties
@Data
public class ProductOrderDTO {
    private Long idProduct;
    private Long quantity;
    private BigDecimal valueUnit;

    public static ProductOrderDTO from(OrderProduct dto){
        return ProductOrderDTO.builder()
                .idProduct(dto.getIdProduct())
                .quantity(dto.getQuantity())
                .valueUnit(dto.getValueUnit())
                .build();
    }
}
