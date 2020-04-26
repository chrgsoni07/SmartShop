package com.rk.smartshop.repository.impl;

import com.rk.smartshop.mapper.OrderDetailMapper;
import com.rk.smartshop.model.OrderDetail;
import com.rk.smartshop.repository.OrderDetailRepository;
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
public class OrderDetailRepositoryImpl extends BaseJdbcRepository implements OrderDetailRepository {
  private static final String UPDATE_QUERY = "update order_detail set order_id=?, product_id= ?, quantity = ? where id=?";
  private static final String DELETE_BY_ID = "delete from order_detail where id=?";
  private static final String SELECT_BY_ID = "select * from order_detail where id = ?";
  private static final String SELECT_ALL = "select * from order_detail";

  private static final String TABLE_NAME = "order_detail";
  private final JdbcTemplate jdbcTemplate;
  private final OrderDetailMapper orderDetailMapper;

  @PostConstruct
  public void setUp() {
    super.configuration(jdbcTemplate, TABLE_NAME);
  }

  @Override
  public Optional<OrderDetail> getById(Long modelId) {

    return retrieveEntity(orderDetailMapper, SELECT_BY_ID, modelId);
  }

  @Override
  public List<OrderDetail> getAll() {

    return retrieveEntityList(orderDetailMapper, SELECT_ALL);
  }

  @Override
  public Integer update(OrderDetail model, Long modelId) {

    return updateOrInsertEntity(UPDATE_QUERY, model.getOrderId(), model.getProductId(), model.getQuantity(), modelId);
  }

  @Override
  public Integer delete(Long modelId) {

    return updateOrInsertEntity(DELETE_BY_ID, modelId);
  }

  @Override
  public Long create(OrderDetail model) {

    Map<String, Object> params = new HashMap<>();
    params.put("order_id", model.getOrderId());
    params.put("quantity", model.getQuantity());
    params.put("product_id", model.getProductId());

    return insertEntityAndReturnKey(params);
  }
}
