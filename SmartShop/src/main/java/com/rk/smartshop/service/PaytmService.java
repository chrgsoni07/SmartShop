package com.rk.smartshop.service;

import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;

public interface PaytmService {

  String chargeCustomer(String orderId, String customerId, String transactionAmount) throws Exception;

  String checkResponse(HttpServletRequest request);

  String refund(String orderId, String refId, String transactionAmount, String txnId);

  String getRefundStatus(String orderId, String refId);
}
