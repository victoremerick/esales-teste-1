package br.victoremerick.esales.order.api;

import br.victoremerick.esales.order.model.dto.OrderDTO;
import br.victoremerick.esales.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<OrderDTO> create(
            @RequestBody OrderDTO orderDTO,
            @RequestHeader("Authorization") String authorization){
        return ResponseEntity.ok(service.create(orderDTO, authorization));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> edit(
            @PathVariable Long id,
            @RequestBody OrderDTO orderDTO,
            @RequestHeader("Authorization") String authorization){
        return ResponseEntity.ok(service.edit(id, orderDTO, authorization));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> find(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authorization){
        return ResponseEntity.ok().body(service.find(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> list(
            @RequestParam(required = false, defaultValue = "20") int quantityByPage,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestHeader("Authorization") String authorization){
        return ResponseEntity.ok().body(service.find(page, quantityByPage));
    }

}
