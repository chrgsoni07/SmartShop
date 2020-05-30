package com.rk.smartshop.service.impl;

import com.rk.smartshop.exception.ResourceNotFoundException;
import com.rk.smartshop.model.Category;
import com.rk.smartshop.model.CategoryDetail;
import com.rk.smartshop.model.CategoryItem;
import com.rk.smartshop.model.CategoryLevel;
import com.rk.smartshop.repository.CategoryRepository;
import com.rk.smartshop.service.CategoryService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
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
    log.info("get categories by id {} ", categoryId);

    Optional<Category> categoryOptional = categoryRepository.getById(categoryId);

    return categoryOptional.orElseThrow(() -> new ResourceNotFoundException("category not found with id " + categoryId));

  }

  @Override
  public List<Category> getAllSubCategories(Long id) {
    log.info("get all sub categories of id {}", id);

    return categoryRepository.getAllSubCategories(id);
  }

  @Override
  public List<Category> getAllRootCategories() {
    log.info("get all root categories");

    return categoryRepository.getAllRootCategories();
  }

  @Override
  public List getCategoryMenu() {
    List<CategoryLevel> dtos = categoryRepository.getCategoryMenu();
    List<Category> allCategories = getAllCategories();

    Map<String, CategoryDetail> finalMap = new HashMap<>();

    dtos.forEach(dto -> {
      String lvl1 = dto.getLvl1();
      String lvl2 = dto.getLvl2();
      String lvl3 = dto.getLvl3();
      String lvl4 = dto.getLvl4();

      //first level
      finalMap.putIfAbsent(lvl1, getCatDTO(lvl1, allCategories));

      if (Objects.nonNull(lvl2) && !lvl2.isEmpty()) {
        //second level
        finalMap.get(lvl1).getItems().putIfAbsent(lvl2, getCatDTO(lvl2, allCategories));
      }

      if (Objects.nonNull(lvl3) && !lvl3.isEmpty()) {
        // third level
        CategoryDetail level2DTO = (CategoryDetail) finalMap.get(lvl1).getItems().get(lvl2);
        level2DTO.getItems().putIfAbsent(dto.getLvl3(), getCatDTO(lvl3, allCategories));
      }

      if (Objects.nonNull(lvl4) && !lvl4.isEmpty()) {
        //fourth level
        CategoryDetail level2DTO = (CategoryDetail) finalMap.get(lvl1).getItems().get(lvl2);
        CategoryDetail level3DTO = (CategoryDetail) level2DTO.getItems().get(lvl3);
        level3DTO.getItems().putIfAbsent(lvl4, getCatDTO(lvl4, allCategories));
      }
    });

    List listOfValues = createItems(finalMap);

 /*List listOfValues =  finalMap.values().stream().map( v ->
      CategoryItem.builder()
          .label(v.getLabel())
          .id(v.getId())
          .item(createItems(v.getItems()))
          .build()
      ).collect(Collectors.toList());
*/
    System.out.println(listOfValues);

    return listOfValues;
  }

  private List<CategoryItem> createItems(Map<String, CategoryDetail> items) {
    return items.values()
        .stream()
        .map(v -> CategoryItem.builder()
            .label(v.getLabel())
            .ctgId(v.getId())
            .items(createItems(v.getItems()))
            .build())
        .collect(Collectors.toList());
  }

  private CategoryDetail getCatDTO(String categoryName, List<Category> categories) {

    Optional<Category> opCate = categories.stream().filter(category -> category.getName().equals(categoryName)).findAny();
    if (opCate.isPresent()) {
      return CategoryDetail.builder().id(opCate.get().getId()).label(categoryName).items(new HashMap()).build();
    } else {
      return CategoryDetail.builder().items(new HashMap()).build();
    }
  }
}
