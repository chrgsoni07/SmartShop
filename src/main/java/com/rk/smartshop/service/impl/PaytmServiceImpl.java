package com.rk.smartshop.service.impl;

import com.paytm.pg.merchant.CheckSumServiceHelper;
import com.rk.smartshop.model.PaytmDetails;
import com.rk.smartshop.service.PaytmService;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

@Service
@RequiredArgsConstructor
public class PaytmServiceImpl implements PaytmService {

  @Autowired
  private PaytmDetails paytmDetails;

  @Override
  public String chargeCustomer(String orderId, String customerId, String transactionAmount) throws Exception {

    TreeMap<String, String> parameters = new TreeMap<>();
    paytmDetails.getDetails().forEach((k, v) -> parameters.put(k, v));
    parameters.put("MOBILE_NO", "8319217166");
    parameters.put("EMAIL", "chirag.digatechnoarts@gmail.com");
    parameters.put("ORDER_ID", orderId);
    parameters.put("TXN_AMOUNT", transactionAmount);
    parameters.put("CUST_ID", customerId);
    String checkSum = getCheckSum(parameters);
    parameters.put("CHECKSUMHASH", checkSum);

    StringBuilder outputHtml = new StringBuilder();
    outputHtml.append("<html>");
    outputHtml.append("<head>");
    outputHtml.append("<title>Merchant Checkout Page</title>");
    outputHtml.append("</head>");
    outputHtml.append("<body>");
    outputHtml.append("<center><h1>Please do not refresh this page...</h1></center>");
    outputHtml.append("<form method='post' action='" + paytmDetails.getPaytmUrl() + "' name='paytm_form'>");


    for(Map.Entry<String,String> entry : parameters.entrySet()) {
      outputHtml.append("<input type='hidden' name='" + entry.getKey() + "' value='" + entry.getValue() + "'>");
    }

    outputHtml.append("<input type='hidden' name='CHECKSUMHASH' value='" + checkSum + "'>");
    outputHtml.append("</form>");
    outputHtml.append("<script type='text/javascript'>");
    outputHtml.append("document.paytm_form.submit();");
    outputHtml.append("</script>");
    outputHtml.append("</body>");
    outputHtml.append("</html>");

    return outputHtml.toString();
  }

  @Override
  public String checkResponse(HttpServletRequest request) {
    System.out.println("paytm callbak url is working");
    Map<String, String[]> mapData = request.getParameterMap();
    TreeMap<String,String> parameters = new TreeMap<String,String>();
    mapData.forEach((key,val)-> parameters.put(key, val[0]));
    String paytmChecksum =  "";
    if(mapData.containsKey("CHECKSUMHASH"))
    {
      paytmChecksum = mapData.get("CHECKSUMHASH")[0];
    }
    String result;

    boolean isValideChecksum = false;
    try{
      isValideChecksum = validateCheckSum(parameters, paytmChecksum);
      if(isValideChecksum && parameters.containsKey("RESPCODE")){
        System.out.println(parameters.get("RESPCODE"));
        System.out.println(parameters.get("RESPMSG"));
        if(parameters.get("RESPCODE").equals("01")){
          result = parameters.toString();
        }else{
          result="<b>Payment Failed.</b>";
        }
      }else{
        result="<b>Checksum mismatched.</b>";
      }
    }catch(Exception e){
      result=e.toString();
    }

    return result;
  }

  @Override
  public String refund(String orderId, String refId, String txnAmt, String txnId) {
    JSONObject body = new JSONObject();
    body.put("mid", paytmDetails.getMerchantId());
    body.put("orderId", orderId);
    body.put("refId", refId);
    body.put("txnId", txnId);
    body.put("refundAmount", txnAmt);
    body.put("txnType", "REFUND");

    String checkSum = null;
    try {
      checkSum = getCheckSum(body);
    } catch (Exception e) {
     System.out.println("refund initiated");
    }
    JSONObject head = new JSONObject();
    head.put("clientId", "C11");
    head.put("signature", checkSum);

    JSONObject request = new JSONObject();
    request.put("body", body);
    request.put("head", head);

    try {
      URL url = new URL("https://securegw-stage.paytm.in/refund/apply");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setDoOutput(true);

      DataOutputStream requestWriter = new DataOutputStream(connection.getOutputStream());
      requestWriter.writeBytes(request.toString());
      requestWriter.close();

      String responseData = "";
      InputStream is = connection.getInputStream();
      BufferedReader responseReader = new BufferedReader(new InputStreamReader(is));
      if ((responseData = responseReader.readLine()) != null) {
        System.out.append("Response Json = " + responseData);
      }
      System.out.append("Requested Json = " + request.toString() + " ");
      responseReader.close();
      return responseData;
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return checkSum;
  }

  @Override
  public String getRefundStatus(String orderId, String refId){
    JSONObject paytmParams = new JSONObject();

    JSONObject body = new JSONObject();
    body.put("mid", paytmDetails.getMerchantId());
    body.put("orderId", orderId);
    body.put("refId", refId);

    String checkSum = null;
    try {
      checkSum = getCheckSum(body);
    } catch (Exception e) {
      System.out.println("refund initiated");
    }

    JSONObject head = new JSONObject();
    head.put("clientId", "C11");
    head.put("signature", checkSum);

    /* prepare JSON string for request */
    paytmParams.put("body", body);
    paytmParams.put("head", head);
    String post_data = paytmParams.toString();

    /* for Production */
// URL url = new URL("https://securegw.paytm.in/v2/refund/status");

    try {
      URL url = new URL("https://securegw-stage.paytm.in/v2/refund/status");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setDoOutput(true);

      DataOutputStream requestWriter = new DataOutputStream(connection.getOutputStream());
      requestWriter.writeBytes(post_data);
      requestWriter.close();
      String responseData = "";
      InputStream is = connection.getInputStream();
      BufferedReader responseReader = new BufferedReader(new InputStreamReader(is));
      if ((responseData = responseReader.readLine()) != null) {
        System.out.append("Response Json = " + responseData);
      }
      responseReader.close();
      return responseData;
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return checkSum;
  }

  private boolean validateCheckSum(TreeMap<String, String> parameters, String paytmChecksum) throws Exception {
    return CheckSumServiceHelper.getCheckSumServiceHelper().verifycheckSum(paytmDetails.getMerchantKey(),
        parameters, paytmChecksum);
  }


  private String getCheckSum(TreeMap<String, String> parameters) throws Exception {
    return CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(paytmDetails.getMerchantKey(), parameters);
  }

  private String getCheckSum(JSONObject body) throws Exception {
    return CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(paytmDetails.getMerchantKey(), body.toString());
  }
}

