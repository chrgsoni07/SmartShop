package com.rk.smartshop.repository.impl;

import com.rk.smartshop.mapper.LocationMapper;
import com.rk.smartshop.model.Location;
import com.rk.smartshop.repository.LocationRepository;
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
public class LocationRepositoryImpl extends BaseJdbcRepository implements LocationRepository {

  private static final String UPDATE_QUERY = "update location set name=? where id=?";
  private static final String DELETE_BY_ID = "delete from location where id=?";
  private static final String SELECT_BY_ID = "select * from location where id = ?";
  private static final String SELECT_ALL = "select * from location";

  private static final String TABLE_NAME = "location";
  private final JdbcTemplate jdbcTemplate;
  private final LocationMapper locationMapper;

  @PostConstruct
  public void setUp() {
    super.configuration(jdbcTemplate, TABLE_NAME);
  }


  @Override
  public Optional<Location> getById(Long modelId) {
    return retrieveEntity(locationMapper, SELECT_BY_ID, modelId);
  }

  @Override
  public List<Location> getAll() {

    return retrieveEntityList(locationMapper, SELECT_ALL);
  }

  @Override
  public Integer update(Location model, Long modelId) {

    return updateOrInsertEntity(UPDATE_QUERY, model.getName(), modelId);
  }

  @Override
  public Integer delete(Long modelId) {

    return updateOrInsertEntity(DELETE_BY_ID, modelId);
  }

  @Override
  public Long create(Location model) {

    Map<String, Object> params = new HashMap<>();
    params.put("id", model.getId());
    params.put("name", model.getName());

    return insertEntityAndReturnKey(params);
  }
}