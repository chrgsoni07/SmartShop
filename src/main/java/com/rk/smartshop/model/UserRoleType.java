package com.rk.smartshop.model;

public enum UserRoleType {
  BUYER,
  SELLER,
  SYSTEM_ADMINISTRATOR;

  public String getRoleName() {
    return "ROLE_" + this.name();
  }
}
