package com.rk.smartshop.repository;

import com.rk.smartshop.model.User;
import com.rk.smartshop.repository.main.CommonRepository;
import java.util.Optional;

public interface UserRepository extends CommonRepository<User> {

  Optional<User> getByEmail(String email);
}
