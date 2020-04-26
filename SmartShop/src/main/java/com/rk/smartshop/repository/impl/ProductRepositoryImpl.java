package com.rk.smartshop.repository.impl;

import com.rk.smartshop.mapper.ProductMapper;
import com.rk.smartshop.model.Product;
import com.rk.smartshop.repository.ProductRepository;
import com.rk.smartshop.repository.main.BaseJdbcRepository;
import java.sql.Timestamp;
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
public class ProductRepositoryImpl extends BaseJdbcRepository implements ProductRepository {
  private static final String UPDATE_QUERY = "update product set name=?, description= ?, "  +
  "category_id = ?, quantity =? , brand= ? , model = ?, configuration = ? ,price = ?," +
      "featured= ? where id=?";

  private static final String DELETE_BY_ID = "delete from product where id=?";
  private static final String SELECT_BY_ID = "select * from product where id = ?";
  private static final String SELECT_ALL = "select * from product";

  private static final String TABLE_NAME = "product";
  private final JdbcTemplate jdbcTemplate;
  private final ProductMapper productMapper;

  @PostConstruct
  public void setUp() {
    super.configuration(jdbcTemplate, TABLE_NAME);
  }

  @Override
  public Optional<Product> getById(Long modelId) {
    return retrieveEntity(productMapper, SELECT_BY_ID, modelId);
  }

  @Override
  public List<Product> getAll() {

    return retrieveEntityList(productMapper, SELECT_ALL);
  }

  @Override
  public Integer update(Product model, Long modelId) {

    return updateOrInsertEntity(UPDATE_QUERY, model.getName(), model.getDescription(),model.getCategoryId(),
        model.getQuantity(), model.getBrand(), model.getModel(), model.getConfiguration(), model.getPrice(),
        model.getFeature(), modelId);
  }

  @Override
  public Integer delete(Long modelId) {

    return updateOrInsertEntity(DELETE_BY_ID, modelId);
  }

  @Override
  public Long create(Product model) {

    Map<String, Object> params = new HashMap<>();
    params.put("name", model.getName());
    params.put("description", model.getDescription());
    params.put("image", model.getImage());
    params.put("category_id", model.getCategoryId());
    params.put("quantity", model.getQuantity());
    params.put("brand", model.getBrand());
    params.put("model", model.getModel());
    params.put("configuration", model.getConfiguration());
    params.put("price", model.getPrice());
    params.put("featured", model.getFeature());
    params.put("created_at", Timestamp.valueOf(model.getCreatedAt().withNano(0)));

    return insertEntityAndReturnKey(params);
  }
}
