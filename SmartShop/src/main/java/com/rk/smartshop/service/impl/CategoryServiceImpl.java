package com.rk.smartshop.service.impl;

import com.rk.smartshop.exception.ResourceNotFoundException;
import com.rk.smartshop.model.Category;
import com.rk.smartshop.repository.CategoryRepository;
import com.rk.smartshop.service.CategoryService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
  private final CategoryRepository categoryRepository;

  @Override
  public Category create(Category category) {
    log.info("create category with date {}", category);

    Long id = categoryRepository.create(category);
    category.setId(id);

    return category;
  }

  @Override
  public Category update(Long categoryId, Category category) {
    log.info("update category with id {}, and value {}", categoryId, category);

    categoryRepository.update(category, categoryId);

    return getById(categoryId);
  }

  @Override
  public void delete(Long categoryId) {
    log.info("delete category with id {}", categoryId);

    getById(categoryId);

    categoryRepository.delete(categoryId);
  }

  @Override
  public List<Category> getAllCategories() {
    log.info("get all categories");

    return categoryRepository.getAll();
  }

  @Override
  public Category getById(Long categoryId) {
    log.info ("get categories by id {} ", categoryId);

    Optional<Category> categoryOptional = categoryRepository.getById(categoryId);

    return categoryOptional.orElseThrow(() -> new ResourceNotFoundException("category not found with id "+categoryId));

  }
}
