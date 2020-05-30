package com.rk.smartshop.model;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

@Data
public class Contact {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String message;
  private LocalDateTime createdAt = LocalDateTime.now().withNano(0);
}
