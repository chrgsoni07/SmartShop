package com.rk.smartshop.repository.impl;

import com.rk.smartshop.mapper.CategoryMapper;
import com.rk.smartshop.mapper.CategoryMenuMapper;
import com.rk.smartshop.model.Category;
import com.rk.smartshop.model.CategoryLevel;
import com.rk.smartshop.repository.CategoryRepository;
import com.rk.smartshop.repository.main.BaseJdbcRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl extends BaseJdbcRepository implements CategoryRepository {

  private static final String UPDATE_QUERY = "update category set name=?, parent= ? where id=?";
  private static final String DELETE_BY_ID = "delete from category where id=?";
  private static final String SELECT_BY_ID = "select * from category where id = ?";
  private static final String SELECT_ALL = "select * from category";
  private static final String SELECT_ALL_ROOT_CATEGORY = "select * from category where parent = 0";
  private static final String SELECT_ALL_SUB_CATEGORY = "select * from category where parent = ?";
  private static final String SELECT_CATEGORY_MENU = "SELECT t1.name AS lvl1, t2.name as lvl2, t3.name as lvl3, t4.name as lvl4 " + "FROM category AS t1 "
      + "LEFT JOIN category AS t2 ON t2.parent = t1.id " + "LEFT JOIN category AS t3 ON t3.parent = t2.id " + "LEFT JOIN category AS t4 ON t4.parent = t3.id where t1.parent = 0";

  private static final String SELECT_CATEGORY_PATH = "WITH RECURSIVE category_path (id, name, path) AS\n" + "(\n" + "  SELECT id, name, name as path\n" + "    FROM category\n"
      + "    WHERE parent = 0\n" + "  UNION ALL\n" + "  SELECT c.id, c.name, CONCAT(cp.path, ' > ', c.name)\n" + "    FROM category_path AS cp JOIN category AS c\n"
      + "      ON cp.id = c.parent\n" + ")\n" + "SELECT * FROM category_path\n" + "ORDER BY path";

  private static final String GET_ALL_PRODUCT = "SELECT * FROM products WHERE category_id = ? OR category_id IN " +
    "( SELECT parent_id FROM categories WHERE id = ? )";

  private static final String CATEGORY_TABLE_NAME = "category";
  private final JdbcTemplate jdbcTemplate;
  private final CategoryMapper categoryMapper;
  private final CategoryMenuMapper categoryMenuMapper;

  @PostConstruct
  public void setUp() {
    super.configuration(jdbcTemplate, CATEGORY_TABLE_NAME);
  }

  @Override
  public Optional<Category> getById(Long modelId) {
    return retrieveEntity(categoryMapper, SELECT_BY_ID, modelId);
  }

  @Override
  public List<Category> getAll() {

    return retrieveEntityList(categoryMapper, SELECT_ALL);
  }

  @Override
  public Integer update(Category model, Long modelId) {

    return updateOrInsertEntity(UPDATE_QUERY, model.getName(), model.getParent(), modelId);
  }

  @Override
  public Integer delete(Long modelId) {

    return updateOrInsertEntity(DELETE_BY_ID, modelId);
  }

  @Override
  public Long create(Category model) {
    Map<String, Object> params = new HashMap<>();
    params.put("name", model.getName());
    params.put("parent", model.getParent());

    return insertEntityAndReturnKey(params);
  }

  @Override
  public List<Category> getAllRootCategories() {

    return retrieveEntityList(categoryMapper, SELECT_ALL_ROOT_CATEGORY);
  }

  @Override
  public List<Category> getAllSubCategories(Long id) {

    return retrieveEntityList(categoryMapper, SELECT_ALL_SUB_CATEGORY, id);
  }

  @Override
  public List<CategoryLevel> getCategoryMenu() {

    return retrieveEntityList(categoryMenuMapper, SELECT_CATEGORY_MENU);

  }
  // BestHelp ->   https://www.mysqltutorial.org/mysql-adjacency-list-tree/
  // HELP -> http://mikehillyer.com/articles/managing-hierarchical-data-in-mysql/
}
