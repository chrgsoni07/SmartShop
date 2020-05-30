package com.rk.smartshop.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryLevel {
  private String lvl1;
  private String lvl2;
  private String lvl3;
  private String lvl4;
}
