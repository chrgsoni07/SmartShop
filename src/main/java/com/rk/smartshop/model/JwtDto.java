package com.rk.smartshop.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "JwtDto", description = "Object to transfer jwt and refresh token pair")
public class JwtDto {

  @ApiModelProperty(value = "Jwt token value")
  private String token;
  @ApiModelProperty(value = "Refresh token value")
  private String refreshToken;

}
