package com.rk.smartshop.service;

import com.rk.smartshop.model.JwtDto;
import com.rk.smartshop.model.UserLoginDTO;
import com.rk.smartshop.model.UserLoginResponse;
import com.rk.smartshop.model.UserRegistrationDTO;

public interface AuthenticationService {

  /**
   * Authenticate user's credentials for generation new token
   *
   * @param userLoginDTO - contains login information {@link UserLoginDTO}
   * @return UserLoginResponse - user first name, user last name, user email and generated tokens pair {@link UserLoginResponse}
   */
  UserLoginResponse login(UserLoginDTO userLoginDTO);

  /**
   * Make current token outdated
   *
   * @param jwtDto - tokens pair {@link JwtDto}
   */
  void logout(JwtDto jwtDto);

  /**
   * Register new user in system
   *
   * @param userRegistrationDTO - registration information to create new user {@link UserRegistrationDTO}
   */
  void signUp(UserRegistrationDTO userRegistrationDTO);

  /**
   * Confirm new user in system
   *
   * @param confirmToken - confirm token for sign up confirmation
   */
  void confirmSignUp(String confirmToken);

  /**
   * Generate new tokens pair by using refresh token
   *
   * @param jwtDto - tokens pair that need to update {@link JwtDto}
   * @return JwtDto - new generated tokens pair {@link JwtDto}
   */
  JwtDto refresh(JwtDto jwtDto);

  void sendConfirmationEmailToUser(String email);
}