package com.api.demo.controller;

import com.api.demo.models.Product;
import com.api.demo.models.ProductJson;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

  Resource resource = new ClassPathResource("data/products.json");

  @GetMapping(value = "", produces = "application/json")
  public ResponseEntity<List<ProductJson>> getProducts() {
    List<ProductJson> products = Product.getProducts();

    return ResponseEntity.ok(products);
  }
}
