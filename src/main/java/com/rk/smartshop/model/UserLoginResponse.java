package com.rk.smartshop.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserLoginResponse", description = "Object for login response")
public class UserLoginResponse {

  @ApiModelProperty(value = "Tokens pair")
  private JwtDto tokensPair;
  @ApiModelProperty(value = "User's first name")
  private String firstName;
  @ApiModelProperty(value = "User's last name")
  private String lastName;
  @ApiModelProperty(value = "User's email")
  private String email;
  @ApiModelProperty(value = "User's userRoleType")
  private UserRoleType userRoleType;
}