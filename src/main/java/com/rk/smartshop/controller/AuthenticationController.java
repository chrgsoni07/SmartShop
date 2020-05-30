package com.rk.smartshop.controller;

import com.rk.smartshop.model.JwtDto;
import com.rk.smartshop.model.UserLoginDTO;
import com.rk.smartshop.model.UserLoginResponse;
import com.rk.smartshop.model.UserRegistrationDTO;
import com.rk.smartshop.service.AuthenticationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  public AuthenticationController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @ApiOperation(value = "User authentication", response = UserLoginResponse.class)
  @PostMapping("/login")
  public UserLoginResponse loginUser(@RequestBody UserLoginDTO userLoginDTO) {

    return authenticationService.login(userLoginDTO);
  }


  @ApiOperation(value = "User logout (make token outdated)", response = ResponseEntity.class)
  @PostMapping("/logout")
  public ResponseEntity logoutUser(@RequestBody JwtDto jwtDto) {
    authenticationService.logout(jwtDto);

    return ResponseEntity.ok().build();
  }


  @ApiOperation(value = "User registration", response = ResponseEntity.class)
  @PostMapping("/signup")
  public ResponseEntity registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
    authenticationService.signUp(userRegistrationDTO);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @ApiOperation(value = "User confirmation", response = ResponseEntity.class)
  @PostMapping("/confirm")
  public ResponseEntity confirmUserSignUp(@RequestParam("token") String confirmToken) {
    authenticationService.confirmSignUp(confirmToken);

    return ResponseEntity.status(HttpStatus.OK).build();
  }


  @ApiOperation(value = "User re-login to generate new token and refresh token", response = JwtDto.class)
  @PostMapping("/refresh")
  public JwtDto refreshToken(@RequestBody JwtDto jwtDto) {

    return authenticationService.refresh(jwtDto);
  }

  @ApiOperation(value = "User email confirm after sign-in", response = ResponseEntity.class)
  @GetMapping("/confirm-email")
  public ResponseEntity sendConfirmation(@RequestParam("email") String email) {

    authenticationService.sendConfirmationEmailToUser(email);

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}