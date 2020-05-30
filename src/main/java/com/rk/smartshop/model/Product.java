package com.rk.smartshop.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Product {

  private Long id;
  private String name;
  private String description;
  private String image;
  private Long categoryId;
  private Integer quantity;
  private String brand;
  private String model;
  private String configuration;
  private Double price;
  private String feature;
  private LocalDateTime createdAt = LocalDateTime.now().withNano(0);
}
