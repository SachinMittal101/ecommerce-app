package com.example.om.client;

import com.example.om.dto.ProductDto;
import com.example.om.dto.ProductResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;


@Component
@Slf4j
public class ProductServiceClient {

    @Autowired
    private RestTemplate template;

    @Autowired
    private ObjectMapper objectMapper;

    public ProductDto fetchProduct(final String username,
                                   final String productName) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-username", username);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        System.out.println("Trying to call product service for product detail");
        ResponseEntity<String> response = template.exchange("http://PRODUCT-SERVICE/product/{name}",
                HttpMethod.GET, requestEntity, String.class, productName);
        String responseBody = response.getBody();
        System.out.println("Received Product details: " + responseBody);
        List<ProductDto> productDtoList = objectMapper.readValue(responseBody, new TypeReference<List<ProductDto>>() {});
        if (CollectionUtils.isEmpty(productDtoList)) {
            log.info("no product available");
            throw new RuntimeException("product not found");
        } else {
            return productDtoList.get(0);
        }
    }

}
