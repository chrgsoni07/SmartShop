package com.rk.smartshop.repository.main;


import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class BaseJdbcRepository {

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;

  protected void configuration(JdbcTemplate jdbcTemplate, String entityTableName) {
    this.jdbcTemplate = jdbcTemplate;
    this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).usingGeneratedKeyColumns("id");
    simpleJdbcInsert.setTableName(entityTableName);
  }

  protected <T> Optional<T> retrieveEntity(RowMapper<T> entityMapper, String query, Object... queryParams) {
    try {

      return ofNullable(jdbcTemplate.queryForObject(query, queryParams, entityMapper));
    } catch (Exception ex) {

      return empty();
    }
  }

  protected <T> Optional<T> retrieveObject(Class<T> objectType, String query, Object... queryParams) {
    try {

      return ofNullable(jdbcTemplate.queryForObject(query, queryParams, objectType));
    } catch (Exception ex) {

      return empty();
    }
  }

  protected Integer retrieveCount(String query, Object... queryParams) {
    try {

      return jdbcTemplate.queryForObject(query, queryParams, Integer.class);
    } catch (Exception ex) {

      return 0;
    }
  }

  protected <T> List<T> retrieveEntityList(RowMapper<T> entityMapper, String query, Object... queryParams) {
    try {

      return jdbcTemplate.query(query, queryParams, entityMapper);
    } catch (Exception ex) {

      return Collections.emptyList();
    }
  }

  protected Integer updateOrInsertEntity(String query, Object... queryParams) {

    return jdbcTemplate.update(query, queryParams);
  }

  protected Long insertEntityAndReturnKey(Map<String, Object> parameters) {

    return simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
  }
}
