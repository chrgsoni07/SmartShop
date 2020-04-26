package com.rk.smartshop.service;

import com.rk.smartshop.model.User;
import java.util.List;

public interface UserService {

  User create(User user);

  User update(Long userId, User user);

  User getById(Long userId);

  List<User> getAllUsers();

  void delete(Long userId);
}
