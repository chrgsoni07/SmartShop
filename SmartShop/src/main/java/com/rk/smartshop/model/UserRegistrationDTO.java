package com.rk.smartshop.model;

import static com.rk.smartshop.util.Constant.EMAIL_REGEX;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
@ApiModel(value = "UserRegistrationDTO", description = "Object to user's registration details")
public class UserRegistrationDTO {

  @ApiModelProperty(value = "User's first name")
  @NotBlank(message = "FirstName cannot be empty")
  private String firstName;

  @ApiModelProperty(value = "User's last name")
  @NotBlank(message = "LastName cannot be empty")
  private String lastName;

  @ApiModelProperty(value = "User's registration email")
  @NotBlank
  @Pattern(regexp = EMAIL_REGEX,message = "Should be valid email")
  private String email;

  @ApiModelProperty(value = "User's password")
  @NotBlank(message = "Password cannot be blank")
  private String password;

  @ApiModelProperty(value = "User's repeat password")
  @NotBlank(message = "Repeat password cannot be blank")
  private String repeatPassword;
}
