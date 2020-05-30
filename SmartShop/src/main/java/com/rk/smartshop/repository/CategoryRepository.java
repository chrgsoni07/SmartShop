package com.rk.smartshop.repository;

import com.rk.smartshop.model.Category;
import com.rk.smartshop.model.CategoryLevel;
import com.rk.smartshop.repository.main.CommonRepository;
import java.util.List;

public interface CategoryRepository extends CommonRepository<Category> {

  List<Category> getAllRootCategories();

  List<Category> getAllSubCategories(Long id);

  List<CategoryLevel> getCategoryMenu();
}
