package com.rk.smartshop.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryItem {
  private String label;
  private Long ctgId;
  private List<CategoryItem> items;
}
