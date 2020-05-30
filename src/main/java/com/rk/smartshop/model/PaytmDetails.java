package com.rk.smartshop.model;


import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@ConfigurationProperties("paytm.payment.sandbox")

public class PaytmDetails {

  private String merchantId;

  private String merchantKey;

  private String channelId;

  private String website;

  private String industryTypeId;

  private String paytmUrl;

  private Map<String, String> details;

}
