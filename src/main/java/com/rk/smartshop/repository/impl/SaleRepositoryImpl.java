package com.rk.smartshop.repository.impl;

import com.rk.smartshop.mapper.SalesMapper;
import com.rk.smartshop.model.Sale;
import com.rk.smartshop.repository.SaleRepository;
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
public class SaleRepositoryImpl extends BaseJdbcRepository implements SaleRepository {
  private static final String UPDATE_QUERY = "update sale set order_id=?, sale_amount= ? where id=?";
  private static final String DELETE_BY_ID = "delete from sale where id=?";
  private static final String SELECT_BY_ID = "select * from sale where id = ?";
  private static final String SELECT_ALL = "select * from sale";

  private static final String TABLE_NAME = "sale";
  private final JdbcTemplate jdbcTemplate;
  private final SalesMapper saleMapper;

  @PostConstruct
  public void setUp() {
    super.configuration(jdbcTemplate, TABLE_NAME);
  }

  @Override
  public Optional<Sale> getById(Long modelId) {
    return retrieveEntity(saleMapper, SELECT_BY_ID, modelId);
  }

  @Override
  public List<Sale> getAll() {

    return retrieveEntityList(saleMapper, SELECT_ALL);
  }

  @Override
  public Integer update(Sale model, Long modelId) {

    return updateOrInsertEntity(UPDATE_QUERY, model.getOrderId(), model.getSalesAmount(), modelId);
  }

  @Override
  public Integer delete(Long modelId) {

    return updateOrInsertEntity(DELETE_BY_ID, modelId);
  }

  @Override
  public Long create(Sale model) {

    Map<String, Object> params = new HashMap<>();
    params.put("order_id", model.getOrderId());
    params.put("sale_amount", model.getSalesAmount());
    params.put("created_at", Timestamp.valueOf(model.getCreatedAt().withNano(0)));

    return insertEntityAndReturnKey(params);
  }
}
