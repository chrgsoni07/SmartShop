package com.rk.smartshop.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;
import static com.rk.smartshop.util.Constant.EMAIL_REGEX;

@Data
@ApiModel(value = "UserLoginDTO", description = "Object to transfer user login details")
public class UserLoginDTO {

  @ApiModelProperty(value = "User's unique email address")
  @NotBlank(message = "Email Cannot be blank")
  @Pattern(regexp = EMAIL_REGEX,message = "Should be valid email")
  private String email;

  @ApiModelProperty(value = "User's password")
  @NotBlank(message = "Password cannot be blank")
  private String password;
}
