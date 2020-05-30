package com.rk.smartshop.service.impl;

import com.rk.smartshop.model.JwtDto;
import com.rk.smartshop.model.UserLoginDTO;
import com.rk.smartshop.model.UserLoginResponse;
import com.rk.smartshop.model.UserRegistrationDTO;
import com.rk.smartshop.service.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  @Override
  public UserLoginResponse login(UserLoginDTO userLoginDTO) {
    return null;
  }

  @Override
  public void logout(JwtDto jwtDto) {

  }

  @Override
  public void signUp(UserRegistrationDTO userRegistrationDTO) {

  }

  @Override
  public void confirmSignUp(String confirmToken) {

  }

  @Override
  public JwtDto refresh(JwtDto jwtDto) {
    return null;
  }

  @Override
  public void sendConfirmationEmailToUser(String email) {

  }
}
