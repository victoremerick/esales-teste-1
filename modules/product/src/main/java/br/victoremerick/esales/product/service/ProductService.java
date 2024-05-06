package br.victoremerick.esales.product.service;

import br.victoremerick.esales.product.model.dto.ProductDTO;
import br.victoremerick.esales.product.model.entity.Product;
import br.victoremerick.esales.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDTO create(ProductDTO productDTO){
        Product product = Product.from(productDTO);
        product = productRepository.save(product);
        return ProductDTO.from(product);
    }

    public ProductDTO edit(Long id, ProductDTO productDTO){
        if(productRepository.existsById(id)) {
            productDTO.setId(id);
            Product product = Product.from(productDTO);
            product = productRepository.save(product);
            return ProductDTO.from(product);
        }
        throw new ResourceNotFoundException("Product not found.");
    }

    public ProductDTO find(Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            return ProductDTO.from(product.get());
        }
        throw new ResourceNotFoundException("Product not found.");
    }

    public Map<Long, ProductDTO> findAllIds(List<ProductDTO> products){
        List<Product> productsResult = productRepository.findAllByIdIn(products.stream().map(ProductDTO::getId).toList());
        Map<Long, ProductDTO> map = new HashMap<>();
        productsResult.forEach(product -> {
            map.put(product.getId(), ProductDTO.from(product));
        });
        return map;
    }

    public List<ProductDTO> find(Integer page, Integer quantity){
        Pageable pageable = PageRequest.of(page-1, quantity);
        return productRepository.findAll(pageable)
                .map(ProductDTO::from)
                .stream()
                .collect(Collectors.toList());
    }

}
