package com.rk.smartshop.service.impl;

import com.rk.smartshop.exception.ResourceNotFoundException;
import com.rk.smartshop.model.User;
import com.rk.smartshop.repository.UserRepository;
import com.rk.smartshop.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
  private final UserRepository userRepository;

  @Override
  public User create(User user) {
    log.info("create user with info {}", user);

    Long id  = userRepository.create(user);
    user.setId(id);

    return user;
  }

  @Override
  public User update(Long userId, User user) {
    log.info("update user with id {} and data {}", userId, user);

    userRepository.update(user, userId);

    return getById(userId);
  }

  @Override
  public User getById(Long userId) {
    log.info("get user by id {}", userId);

    Optional<User> optionalUsers = userRepository.getById(userId);

    return optionalUsers.orElseThrow(() -> new ResourceNotFoundException("user not found with id "+ userId));
  }

  @Override
  public List<User> getAllUsers() {
    log.info("get all users ");

    return userRepository.getAll();
  }

  @Override
  public void delete(Long userId) {
    log.info("delete user by id {}", userId);

    getById(userId);
    userRepository.delete(userId);
  }

  @Override
  public User getUserByEmail(String email) {
    log.info("get user by email {}", email);

    Optional<User> optionalUser = userRepository.getByEmail(email);

    return optionalUser.orElseThrow(() -> new ResourceNotFoundException("user not found with email "+ email));
  }
}
