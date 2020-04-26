package com.rk.smartshop.repository.impl;

import com.rk.smartshop.mapper.CategoryMapper;
import com.rk.smartshop.model.Category;
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
  private static final String UPDATE_QUERY = "update category set name=?, details= ? where id=?";
  private static final String DELETE_BY_ID = "delete from category where id=?";
  private static final String SELECT_BY_ID = "select * from category where id = ?";
  private static final String SELECT_ALL = "select * from category";

  private static final String CATEGORY_TABLE_NAME = "category";
  private final JdbcTemplate jdbcTemplate;
  private final CategoryMapper categoryMapper;

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

    return updateOrInsertEntity(UPDATE_QUERY, model.getName(), model.getDetails(), modelId);
  }

  @Override
  public Integer delete(Long modelId) {

    return updateOrInsertEntity(DELETE_BY_ID, modelId);
  }

  @Override
  public Long create(Category model) {

    Map<String, Object> params = new HashMap<>();
    params.put("name", model.getName());
    params.put("details", model.getDetails());

    return insertEntityAndReturnKey(params);
  }
}
