package com.rk.smartshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Order {

  private Long id;
  private Long userId;
  private String shippingAddress;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime shippingDate;
  private String shippingStatus;
  private LocalDateTime createdAt = LocalDateTime.now().withNano(0);
}
