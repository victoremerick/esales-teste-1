package br.victoremerick.esales.order.model.dto;

import br.victoremerick.esales.order.model.entity.Order;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Builder
@JsonIgnoreProperties
@Data
public class OrderDTO {
    private Long id;
    private LocalDateTime createdAt;
    private BigDecimal totalValue;
    private List<ProductOrderDTO> products;

    public static OrderDTO from(Order order){

        AtomicReference<BigDecimal> valueAccumulator = new AtomicReference<>();
        List<ProductOrderDTO> productOrderDTOS = order.getProducts().stream().map(orderProduct -> {
            valueAccumulator.set(valueAccumulator.get().add(orderProduct.getValueUnit().multiply(new BigDecimal(orderProduct.getQuantity()))));
            return ProductOrderDTO.from(orderProduct);
        }).collect(Collectors.toList());

        return OrderDTO.builder()
                .id(order.getId())
                .createdAt(order.getCreatedAt())
                .products(productOrderDTOS)
                .totalValue(valueAccumulator.get())
                .build();
    }
}
