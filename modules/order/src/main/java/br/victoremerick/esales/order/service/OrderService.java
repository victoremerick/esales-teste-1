package br.victoremerick.esales.order.service;

import br.victoremerick.esales.order.model.dto.ProductDTO;
import br.victoremerick.esales.order.model.dto.ProductOrderDTO;
import br.victoremerick.esales.order.model.entity.OrderProduct;
import br.victoremerick.esales.order.repository.OrderRepository;
import br.victoremerick.esales.order.model.dto.OrderDTO;
import br.victoremerick.esales.order.model.entity.Order;
import br.victoremerick.esales.services.product.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public OrderDTO create(OrderDTO orderDTO, String authorization){

        Map<Long, ProductDTO> productDTOS = productClient.findAll(authorization, orderDTO.getProducts().stream().map(ProductDTO::from).toList())
                .getBody();

        List<OrderProduct> productOrders = new ArrayList<>();
        orderDTO.getProducts().forEach(productOrderDTO -> {
            ProductDTO dto = productDTOS.get(productOrderDTO.getIdProduct());
            if(dto != null){
                productOrders.add(OrderProduct.from(productOrderDTO, dto));
            }
        });

        Order order = new Order();
        order.setCreatedAt(LocalDateTime.now());
        order.setProducts(productOrders);

        order = orderRepository.save(order);
        return OrderDTO.from(order);
    }

    public OrderDTO edit(Long id, OrderDTO orderDTO, String authorization){
        Optional<Order> orderOpt = orderRepository.findById(id);
        if(orderOpt.isPresent()) {
            Order order = orderOpt.get();

            Map<Long, ProductDTO> productDTOS = productClient.findAll(authorization, orderDTO.getProducts().stream().map(ProductDTO::from).toList())
                    .getBody();

            List<OrderProduct> productOrders = new ArrayList<>();
            orderDTO.getProducts().forEach(productOrderDTO -> {
                ProductDTO dto = productDTOS.get(productOrderDTO.getIdProduct());
                if(dto != null){
                    productOrders.add(OrderProduct.from(productOrderDTO, dto));
                }
            });

            order.setProducts(productOrders);
            order = orderRepository.save(order);
            return OrderDTO.from(order);
        }
        throw new ResourceNotFoundException("Order not found.");
    }

    public OrderDTO find(Long id){
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()) {
            return OrderDTO.from(order.get());
        }
        throw new ResourceNotFoundException("Order not found.");
    }

    public List<OrderDTO> find(Integer page, Integer quantity){
        Pageable pageable = PageRequest.of(page-1, quantity);
        return orderRepository.findAll(pageable)

                .map(OrderDTO::from)
                .stream()
                .collect(Collectors.toList());
    }

}
