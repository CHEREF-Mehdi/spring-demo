package com.api.demo.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.springframework.core.io.ClassPathResource;

public class Product extends ProductJson {

  public Product(
    long id,
    String productName,
    String productCode,
    String description,
    float starRating
  ) {
    this.id = id;
    this.productName = productName;
    this.productCode = productCode;
    this.description = description;
    this.starRating = starRating;
  }

  public static List<ProductJson> getProducts() {
    List<ProductJson> products = null;
    try {
      File resource = new ClassPathResource("data/products.json").getFile();

      final ObjectMapper objectMapper = new ObjectMapper();
      products =
        objectMapper.readValue(
          resource,
          new TypeReference<List<ProductJson>>() {}
        );
        
      return products;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return products;
  }

}
