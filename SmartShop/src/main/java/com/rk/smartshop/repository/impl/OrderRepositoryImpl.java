package com.rk.smartshop.repository.impl;

import com.rk.smartshop.mapper.OrderMapper;
import com.rk.smartshop.model.Order;
import com.rk.smartshop.repository.OrderRepository;
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
public class OrderRepositoryImpl extends BaseJdbcRepository implements OrderRepository {
  private static final String UPDATE_QUERY = "update order set user_id=?, shipping_address=?, shipping_date=?, shipping_status=? where id=?";
  private static final String DELETE_BY_ID = "delete from order where id=?";
  private static final String SELECT_BY_ID = "select * from order where id = ?";
  private static final String SELECT_ALL = "select * from order";

  private static final String TABLE_NAME = "order";
  private final JdbcTemplate jdbcTemplate;
  private final OrderMapper orderMapper;

  @PostConstruct
  public void setUp() {
    super.configuration(jdbcTemplate, TABLE_NAME);
  }

  @Override
  public Optional<Order> getById(Long modelId) {

    return retrieveEntity(orderMapper, SELECT_BY_ID, modelId);
  }

  @Override
  public List<Order> getAll() {

    return retrieveEntityList(orderMapper, SELECT_ALL);
  }

  @Override
  public Integer update(Order model, Long modelId) {

    return updateOrInsertEntity(UPDATE_QUERY, model.getUserId(), model.getShippingAddress(), model.getShippingDate(), model.getShippingStatus(), modelId);
  }

  @Override
  public Integer delete(Long modelId) {

    return updateOrInsertEntity(DELETE_BY_ID, modelId);
  }

  @Override
  public Long create(Order model) {

    Map<String, Object> params = new HashMap<>();
    params.put("user_id", model.getUserId());
    params.put("shipping_address", model.getShippingAddress());
    params.put("shipping_date", model.getShippingDate());
    params.put("shipping_status", model.getShippingStatus());
    params.put("created_at", Timestamp.valueOf(model.getCreatedAt().withNano(0)));

    return insertEntityAndReturnKey(params);
  }
}
