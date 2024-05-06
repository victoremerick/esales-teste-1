package br.victoremerick.esales.services.product;

import br.victoremerick.esales.order.model.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Map;

@FeignClient(contextId = "ProductClient", name = "${services.product.name:ProductClient}", url = "${services.product.url:http://localhost:8081/api/v1}")
public interface ProductClient {
    @GetMapping("/find-all")
    ResponseEntity<Map<Long, ProductDTO>> findAll(
            @RequestHeader("Authorization") String authorization,
            @RequestBody List<ProductDTO> products);
}
