package com.rk.smartshop.controller;

import static com.rk.smartshop.util.Constant.API_VERSION;

import com.rk.smartshop.service.PaytmService;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API_VERSION + "/paytm-payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentGatewayController {

  private final PaytmService paytmService;

  @PostMapping("/response")
  public String  callBackResponse(HttpServletRequest request) {
    log.info("Call back response is calling");

   return paytmService.checkResponse(request);
  }

  @PostMapping(value = "/charge")
  @ResponseBody
  public String getRedirect(@RequestParam(name = "CUST_ID") String customerId,
      @RequestParam(name = "TXN_AMOUNT") String transactionAmount,
      @RequestParam(name = "ORDER_ID") String orderId) throws Exception {
    log.info("charge customer is calling");

    return paytmService.chargeCustomer(orderId, customerId, transactionAmount);
  }


  @GetMapping(value = "/refund")
  public String refund( @RequestParam(name = "TXN_AMOUNT") String transactionAmount,
      @RequestParam(name = "ORDER_ID") String orderId,
      @RequestParam(name = "REF_ID") String refId,
      @RequestParam(name = "TXN_ID") String txnId
      ) throws Exception {

    return paytmService.refund(orderId, refId, transactionAmount, txnId);
  }

  @GetMapping(value = "/refund-status")
  public String refundStatus(@RequestParam(name = "ORDER_ID") String orderId,
      @RequestParam(name = "REF_ID") String refId) throws Exception {

    return paytmService.getRefundStatus(orderId, refId);

    // https://developer.paytm.com/docs/refund-status-api/?ref=refunds
  }
}
