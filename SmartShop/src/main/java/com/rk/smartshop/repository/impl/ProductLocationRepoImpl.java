package com.rk.smartshop.repository.impl;

import com.rk.smartshop.mapper.ProductLocationMapper;
import com.rk.smartshop.model.ProductLocation;
import com.rk.smartshop.repository.ProductLocationRepo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.rk.smartshop.repository.main.BaseJdbcRepository;

@Repository
@RequiredArgsConstructor
public class ProductLocationRepoImpl extends BaseJdbcRepository implements ProductLocationRepo {

  private static final String UPDATE_QUERY = "update order set product_id=?, location_id=? where id=?";
  private static final String DELETE_BY_ID = "delete from product_location where id=?";
  private static final String SELECT_BY_ID = "select * from product_location where id = ?";
  private static final String SELECT_ALL = "select * from product_location";
  private static final String SELECT_ALL_BY_LOCATION_ID = "select * from product_location by location_id";

  private static final String TABLE_NAME = "product_location";
  private final JdbcTemplate jdbcTemplate;
  private final ProductLocationMapper prodLocalMapper;

  @PostConstruct
  public void setUp() {
    super.configuration(jdbcTemplate, TABLE_NAME);
  }

  @Override
  public Optional<ProductLocation> getById(Long modelId) {

    return retrieveEntity(prodLocalMapper, SELECT_BY_ID, modelId);
  }

  @Override
  public List<ProductLocation> getAll() {

    return retrieveEntityList(prodLocalMapper, SELECT_ALL);
  }

  @Override
  public List<ProductLocation> getAllByLocationId(Long locationId) {

    return retrieveEntityList(prodLocalMapper, SELECT_ALL_BY_LOCATION_ID ,locationId);
  }

  @Override
  public Integer update(ProductLocation model, Long modelId) {

    return updateOrInsertEntity(UPDATE_QUERY, model.getProductId(), model.getLocationId(), modelId);
  }

  @Override
  public Integer delete(Long modelId) {

    return updateOrInsertEntity(DELETE_BY_ID, modelId);
  }

  @Override
  public Long create(ProductLocation model) {
    Map<String, Object> params = new HashMap<>();
    params.put("product_id",model.getProductId());
    params.put("location_id", model.getLocationId());

    return insertEntityAndReturnKey(params);
  }
}
