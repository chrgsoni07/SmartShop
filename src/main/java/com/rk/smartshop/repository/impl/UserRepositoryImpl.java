package com.rk.smartshop.repository.impl;

import com.rk.smartshop.mapper.UserMapper;
import com.rk.smartshop.model.User;
import com.rk.smartshop.repository.UserRepository;
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
public class UserRepositoryImpl extends BaseJdbcRepository implements UserRepository {
  private static final String UPDATE_QUERY = "update user set first_name=?, last_name=?,"
      + "email=?, password=? , address=?, phone =?, type = ? where id=?";
  private static final String DELETE_BY_ID = "delete from user where id=?";
  private static final String SELECT_BY_ID = "select * from user where id = ?";
  private static final String SELECT_ALL = "select * from user";
  private static final String SELECT_BY_EMAIL = "select * from user where email = ?";

  private static final String TABLE_NAME = "user";
  private final JdbcTemplate jdbcTemplate;
  private final UserMapper userMapper;

  @PostConstruct
  public void setUp() {
    super.configuration(jdbcTemplate, TABLE_NAME);
  }

  @Override
  public Optional<User> getById(Long modelId) {

    return retrieveEntity(userMapper, SELECT_BY_ID, modelId);
  }

  @Override
  public Optional<User> getByEmail(String email) {

    return retrieveEntity(userMapper, SELECT_BY_EMAIL, email);
  }

  @Override
  public List<User> getAll() {

    return retrieveEntityList(userMapper, SELECT_ALL);
  }

  @Override
  public Integer update(User model, Long modelId) {

    return updateOrInsertEntity(UPDATE_QUERY, model.getFirstName(), model.getLastName(),
        model.getEmail(), model.getPassword(), model.getAddress(), model.getPhone(), model.getType().name(),
        modelId);
  }

  @Override
  public Integer delete(Long modelId) {

    return updateOrInsertEntity(DELETE_BY_ID, modelId);
  }

  @Override
  public Long create(User model) {

    Map<String, Object> params = new HashMap<>();
    params.put("first_name", model.getFirstName());
    params.put("last_name", model.getLastName());
    params.put("email", model.getEmail());
    params.put("password", model.getPassword());
    params.put("address", model.getAddress());
    params.put("phone", model.getPhone());
    params.put("type", model.getType().name());
    params.put("created_at", Timestamp.valueOf(model.getCreatedAt().withNano(0)));

    return insertEntityAndReturnKey(params);
  }
}
