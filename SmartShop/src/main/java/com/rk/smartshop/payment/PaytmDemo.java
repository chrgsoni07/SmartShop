package com.rk.smartshop.payment;

import com.paytm.pg.merchant.CheckSumServiceHelper;
import java.util.TreeMap;
import org.springframework.stereotype.Service;

@Service
public class PaytmDemo {

  public void init() {
    TreeMap<String, String> paytmParams = new TreeMap<String, String>();

    /* Find your MID in your Paytm Dashboard at https://dashboard.paytm.com/next/apikeys */
    paytmParams.put("MID", "");

    /* Find your WEBSITE in your Paytm Dashboard at https://dashboard.paytm.com/next/apikeys */
    paytmParams.put("WEBSITE", "YOUR_WEBSITE_HERE");

    /* Find your INDUSTRY_TYPE_ID in your Paytm Dashboard at https://dashboard.paytm.com/next/apikeys */
    paytmParams.put("INDUSTRY_TYPE_ID", "YOUR_INDUSTRY_TYPE_ID_HERE");

    /* WEB for website and WAP for Mobile-websites or App */
    paytmParams.put("CHANNEL_ID", "YOUR_CHANNEL_ID");

    /* Enter your unique order id */
    paytmParams.put("ORDER_ID", "YOUR_ORDER_ID");

    /* unique id that belongs to your customer */
    paytmParams.put("CUST_ID", "CUSTOMER_ID");

    /* customer's mobile number */
    paytmParams.put("MOBILE_NO", "CUSTOMER_MOBILE_NUMBER");

    /* customer's email */
    paytmParams.put("EMAIL", "CUSTOMER_EMAIL");

/**
 * Amount in INR that is payble by customer
 * this should be numeric with optionally having two decimal points
 */
    paytmParams.put("TXN_AMOUNT", "ORDER_TRANSACTION_AMOUNT");

    /* on completion of transaction, we will send you the response on this URL */
    paytmParams.put("CALLBACK_URL", "YOUR_CALLBACK_URL");

/**
 * Generate checksum for parameters we have
 * You can get Checksum JAR from https://developer.paytm.com/docs/checksum/
 * Find your Merchant Key in your Paytm Dashboard at https://dashboard.paytm.com/next/apikeys
 */
    try {
      String checksum = CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum("YOUR_KEY_HERE", paytmParams);
    } catch (Exception e) {
      e.printStackTrace();
    }

    /* for Staging */
    String url = "https://securegw-stage.paytm.in/order/process";
  }

}
