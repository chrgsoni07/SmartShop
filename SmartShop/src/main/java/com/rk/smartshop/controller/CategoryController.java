package com.rk.smartshop.controller;

import static com.rk.smartshop.util.Constant.API_VERSION;

import com.rk.smartshop.model.Category;
import com.rk.smartshop.model.CategoryDetail;
import com.rk.smartshop.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API_VERSION + "/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

  private final CategoryService categoryService;

  @ApiOperation(value = "Save new category.", response = Category.class)
  @PostMapping
  public Category createCategory(@RequestBody Category category) {

    log.debug("Create category {}.", category);

    return categoryService.create(category);
  }

  @ApiOperation(value = "Update category.", response = Category.class)
  @PutMapping("/{categoryId}")
  public Category updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody Category category) {
    log.debug("Create category with id {}.", categoryId);

    return categoryService.update(categoryId, category);
  }

  @ApiOperation(value = "Delete category by id.", response = ResponseEntity.class)
  @DeleteMapping("/{categoryId}")
  public ResponseEntity<?> deleteCategory(@PathVariable(value = "categoryId") Long categoryId) {
    log.debug("Delete category by id {}.", categoryId);

    categoryService.delete(categoryId);

    return ResponseEntity.ok().build();
  }

  @ApiOperation(value = "Get category by category id", response = Category.class)
  @GetMapping("/{categoryId}")
  public Category getCategory(@PathVariable("categoryId") Long categoryId) {

    return categoryService.getById(categoryId);
  }

  @ApiOperation(value = "Get all categories categories for of product.", response = ResponseEntity.class)
  @GetMapping("/all")
  public List<Category> getCategories() {

    return categoryService.getAllCategories();
  }

  @ApiOperation(value = "Get all sub-categories by parent category id", response = Category.class, responseContainer = "List")
  @GetMapping("/sub-categories/{id}")
  public List<Category> getSubCategories(@PathVariable("id") Long id) {

    return categoryService.getAllSubCategories(id);
  }


  @ApiOperation(value = "Get all sub-categories by parent category id", response = Category.class, responseContainer = "List")
  @GetMapping("/root-categories")
  public List<Category> getAllRootCategories() {

    return categoryService.getAllRootCategories();
  }


  @ApiOperation(value = "Get hierarchical  categories id", response = Category.class, responseContainer = "List")
  @GetMapping("/category-menu")
  public List getCategoryMenu() {

    return categoryService.getCategoryMenu();
  }
}
