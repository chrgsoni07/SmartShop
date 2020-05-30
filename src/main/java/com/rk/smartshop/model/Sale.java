package com.rk.smartshop.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Sale {

  private Long id;
  private Long orderId;
  private Double salesAmount;
  private LocalDateTime createdAt = LocalDateTime.now().withNano(0);
}
