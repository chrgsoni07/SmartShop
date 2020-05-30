package com.rk.smartshop.service;

import com.rk.smartshop.model.Category;
import java.util.List;
import java.util.Map;

public interface CategoryService {

  Category create(Category category);

  Category update(Long categoryId, Category category);

  void delete(Long categoryId);

  List<Category> getAllCategories();

  Category getById(Long categoryId);

  List<Category> getAllSubCategories(Long id);

  List<Category> getAllRootCategories();

  List getCategoryMenu();

}
