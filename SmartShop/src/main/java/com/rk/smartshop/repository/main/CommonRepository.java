package com.rk.smartshop.repository.main;

import java.util.List;
import java.util.Optional;

public interface CommonRepository<T> {

  Optional<T> getById(Long modelId);

  List<T> getAll();

  Integer update(T model, Long modelId);

  Integer delete(Long modelId);

  Long create(T model);
}
