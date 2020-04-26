package com.rk.smartshop.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class User {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String address;
  private String phone;
  private UserRoleType type;
  private LocalDateTime createdAt = LocalDateTime.now().withNano(0);
}
