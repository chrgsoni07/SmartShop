package com.rk.smartshop.repository.impl;

import com.rk.smartshop.mapper.CategoryMapper;
import com.rk.smartshop.mapper.ContactMapper;
import com.rk.smartshop.model.Category;
import com.rk.smartshop.model.Contact;
import com.rk.smartshop.repository.ContactRepository;
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
public class ContactRepositoryImpl extends BaseJdbcRepository implements ContactRepository {
  private static final String UPDATE_QUERY = "update contact set first_name=?, last_name=?, email=?, message=? where id=?";
  private static final String DELETE_BY_ID = "delete from contact where id=?";
  private static final String SELECT_BY_ID = "select * from contact where id = ?";
  private static final String SELECT_ALL = "select * from contact";

  private static final String CONTACT_TABLE_NAME = "contact";
  private final JdbcTemplate jdbcTemplate;
  private final ContactMapper contactMapper;

  @PostConstruct
  public void setUp() {
    super.configuration(jdbcTemplate, CONTACT_TABLE_NAME);
  }

  @Override
  public Optional<Contact> getById(Long modelId) {
    return retrieveEntity(contactMapper, SELECT_BY_ID, modelId);
  }

  @Override
  public List<Contact> getAll() {

    return retrieveEntityList(contactMapper, SELECT_ALL);
  }

  @Override
  public Integer update(Contact model, Long modelId) {

    return updateOrInsertEntity(UPDATE_QUERY, model.getFirstName(),
        model.getLastName(), model.getEmail(), model.getMessage() ,modelId);
  }

  @Override
  public Integer delete(Long modelId) {

    return updateOrInsertEntity(DELETE_BY_ID, modelId);
  }

  @Override
  public Long create(Contact model) {

    Map<String, Object> params = new HashMap<>();
    params.put("first_name", model.getFirstName());
    params.put("last_name", model.getLastName());
    params.put("email", model.getEmail());
    params.put("message", model.getMessage());
    params.put("created_at", Timestamp.valueOf(model.getCreatedAt().withNano(0)));

    return insertEntityAndReturnKey(params);
  }
}
