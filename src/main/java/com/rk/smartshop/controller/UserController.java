package com.rk.smartshop.controller;

import static com.rk.smartshop.util.Constant.API_VERSION;

import com.rk.smartshop.model.User;
import com.rk.smartshop.service.UserService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API_VERSION + "/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

  private final UserService userService;

  @ApiOperation(value = "Save new user.", response = User.class)
  @PostMapping
  public User createUser(@RequestBody User user) {

    log.debug("Create user {}.", user);

    return userService.create(user);
  }

  @ApiOperation(value = "Update user.", response = User.class)
  @PutMapping("/{userId}")
  public User updateUser(@PathVariable("userId") Long userId, @RequestBody User user) {
    log.debug("update user with id {}.", userId);

    return userService.update(userId, user);
  }

  @ApiOperation(value = "Delete user by id.", response = ResponseEntity.class)
  @DeleteMapping("/{userId}")
  public ResponseEntity<?> deleteUser(@PathVariable(value = "userId") Long userId) {
    log.debug("Delete user by id {}.", userId);

    userService.delete(userId);

    return ResponseEntity.ok().build();
  }

  @ApiOperation(value = "Get user by user id", response = User.class)
  @GetMapping("/{userId}")
  public User getUser(@PathVariable("userId") Long userId) {

    return userService.getById(userId);
  }

  @ApiOperation(value = "Get all users", response = ResponseEntity.class)
  @GetMapping("/all")
  public List<User> getAllUsers() {

    return userService.getAllUsers();
  }

  @ApiOperation(value = "Get user by user id", response = User.class)
  @GetMapping("/email/{email}")
  public User getUser(@PathVariable("email") String email) {

    return userService.getUserByEmail(email);
  }


}
