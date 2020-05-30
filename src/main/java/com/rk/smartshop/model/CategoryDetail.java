package com.rk.smartshop.model;

import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDetail {
  private Long id;
  private String label;
  private Map items;
}

