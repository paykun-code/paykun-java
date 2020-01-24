package Paykun;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.xml.ws.spi.http.HttpContext;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

import Paykun.ErrorCodes;
import Paykun.ValidationException;
public class Payment {
	
	private static final String GATEWAY_URL_PROD = "https://checkout.paykun.com/payment";
	private static final String GATEWAY_URL_DEV = "https://sandbox.paykun.com/payment";
	private static final String PAGE_TITLE = "Processing Payment...";
	
	private String merchantId;
    private String accessToken;
    private String encryptionKey;
    private String orderId;
    private String purpose;
    private Double amount;
    private String successUrl;
    private String failureUrl;
    private String country;
    private String state;
    private String city;
    private String pinCode;
    private String addressString;
    private String billingCountry;
    private String billingState;
    private String billingCity;
    private String billingPinCode;
    private String billingAddressString;
    private Boolean isLive;
    
    private String customerName;
    private String customerEmail;
    private String customerMoNo;
    
    private Boolean isPassedValidationForConstructor = false;
    private Boolean isPassedValidationForInitOrder = false;
    private Boolean isPassedValidationForCustomer = false;
    private Boolean isPassedValidationForShipping = false;
    private Boolean isPassedValidationForBilling = false;
    private Boolean isCustomRenderer = false;

    private String udf_1;
    private String udf_2;
    private String udf_3;
    private String udf_4;
    private String udf_5;
    
    public Payment(String mid, String accessToken, String encKey, boolean isLive) throws ValidationException {
    	
    	/*if (Validator.VALIDATE_MERCHANT_ID(mid)) {
            throw new ValidationException(ErrorCodes.INVALID_MERCHANT_ID_STRING,
                ErrorCodes.INVALID_MERCHANT_ID_CODE, null);
        }*/

        if (Validator.VALIDATE_ACCESS_TOKEN(accessToken)) {
            throw new ValidationException(ErrorCodes.INVALID_ACCESS_TOKEN_STRING,
                ErrorCodes.INVALID_ACCESS_TOKEN_CODE, null);
        }

        if (Validator.VALIDATE_ENCRYPTION_KEY(encKey)) {
            throw new ValidationException(ErrorCodes.INVALID_API_SECRETE_STRING,
                ErrorCodes.INVALID_API_SECRETE_CODE, null);
        }
        this.merchantId       = mid;
        this.accessToken      = accessToken;
        this.encryptionKey    = encKey;
        this.isLive           = isLive;
        this.isPassedValidationForConstructor = true;
    } 
    
    public void initOrder (String orderId, String purpose, Double amount, String successUrl, String failureUrl) throws ValidationException {

    	
        if (Validator.VALIDATE_ORDER_NUMBER(orderId)) {
            throw new ValidationException(ErrorCodes.INVALID_ORDER_ID_STRING,
                ErrorCodes.INVALID_ORDER_ID_CODE, null);
        }
       
        if (Validator.VALIDATE_PURPOSE(purpose)) {
            throw new ValidationException(ErrorCodes.INVALID_PURPOSE_STRING,
                ErrorCodes.INVALID_PURPOSE_CODE, null);
        }
        
        if (Validator.VALIDATE_AMOUNT(amount)) {
            throw new ValidationException(ErrorCodes.INVALID_AMOUNT_STRING,
                ErrorCodes.INVALID_AMOUNT_CODE, null);
        }
        
        
        this.orderId      = orderId;
        this.purpose      = purpose;
        this.amount       = amount;
        this.successUrl   = successUrl;
        this.failureUrl   = failureUrl;
        this.isPassedValidationForInitOrder = true;

    }
        
    public void addCustomer(String customerName, String customerEmail, String customerMoNo) throws ValidationException{


        if(Validator.VALIDATE_CUSTOMER_NAME(customerName).containsKey("message")) {

        	Map<String, String> errorDetail = new HashMap<String, String>();
        	errorDetail = Validator.VALIDATE_CUSTOMER_NAME(customerName);
//        	System.out.println(errorDetail.toString());
            throw new ValidationException(errorDetail.get("message"), errorDetail.get("code"), null);
        }

        if(Validator.VALIDATE_CUSTOMER_EMAIL(customerEmail).containsKey("message")) {
        	Map<String, String> errorDetail = new HashMap<String, String>();
            errorDetail = Validator.VALIDATE_CUSTOMER_EMAIL(customerEmail);
            throw new ValidationException(errorDetail.get("message"), errorDetail.get("code"), null);
        }

        this.customerName     = customerName;
        this.customerEmail    = customerEmail;
        this.customerMoNo     = customerMoNo;
        
        this.isPassedValidationForCustomer = true;
        

    }
    
    
    public void addShippingAddress(String country, String state, String city, String pinCode, String addressString) {
        
        this.country          = country;
        this.state            = state;
        this.city             = city;
        this.pinCode          = pinCode;
        this.addressString    = addressString;
        this.isPassedValidationForShipping = true;
    }
    
    public void addBillingAddress(String country, String state, String city, String pinCode, String addressString) {
        
        this.billingCountry          = country;
        this.billingState            = state;
        this.billingCity             = city;
        this.billingPinCode          = pinCode;
        this.billingAddressString    = addressString;
        this.isPassedValidationForBilling = true;
    }
    
    public void setCustomFields1(String cf1) {
    	this.udf_1 = cf1;
    }
    public void setCustomFields2(String cf2) {
    	this.udf_2 = cf2;
    }
    public void setCustomFields3(String cf3) {
    	this.udf_3 = cf3;
    }
    public void setCustomFields4(String cf4) {
    	this.udf_4 = cf4;
    }
    public void setCustomFields5(String cf5) {
    	this.udf_5 = cf5;
    }
    
    public String submit() throws  ValidationException{
        if (
            this.isPassedValidationForConstructor &&
            this.isPassedValidationForInitOrder &&
            this.isPassedValidationForCustomer
            
        ) {

        	Map<String, String> dataArray = new TreeMap<String, String>();
        	dataArray.put("order_no", this.orderId);
        	
        	dataArray.put("product_name", this.purpose);
        	dataArray.put("amount", this.amount.toString());
        	dataArray.put("success_url", this.successUrl);
        	dataArray.put("failure_url", this.failureUrl);
        	dataArray.put("customer_name", this.customerName);
        	dataArray.put("customer_email", this.customerEmail);
        	dataArray.put("customer_phone", this.customerMoNo);
        	
        	dataArray.put("shipping_address", (this.addressString != null) ? this.addressString  : "");
        	dataArray.put("shipping_city", (this.city != null) ? this.city  : "");
        	dataArray.put("shipping_state", (this.state != null) ? this.state  : "");
        	dataArray.put("shipping_country", (this.country != null) ? this.country  : "");
        	dataArray.put("shipping_zip", (this.pinCode != null) ? this.pinCode  : "");
        	
        	dataArray.put("billing_address", (this.billingAddressString != null) ? this.billingAddressString  : "");
        	dataArray.put("billing_city", (this.billingCity != null) ? this.billingCity  : "");
        	dataArray.put("billing_state", (this.billingState != null) ? this.billingState  : "");
        	dataArray.put("billing_country", (this.billingCountry != null) ? this.billingCountry  : "");
        	dataArray.put("billing_zip", (this.billingPinCode != null) ? this.billingPinCode  : "");
        	
        	dataArray.put("udf_1", (this.udf_1 != null) ? this.udf_1 : "");
        	dataArray.put("udf_2", (this.udf_2 != null) ? this.udf_2 : "");
        	dataArray.put("udf_3", (this.udf_3 != null) ? this.udf_3 : "");
        	dataArray.put("udf_4", (this.udf_4 != null) ? this.udf_4 : "");
        	dataArray.put("udf_5", (this.udf_5 != null) ? this.udf_5 : "");
            
        	String encryptedData = this.encryptData(dataArray);
        	//System.out.println(encryptedData);
            return this.createForm(encryptedData);

        }
        
        /*Validation is not passed for all the steps*/

        throw new ValidationException(ErrorCodes.INVALID_DATA_PROVIDED_STRING,
            ErrorCodes.INVALID_DATA_PROVIDED_CODE, null);

    }
    
    private String encryptData(Map<String, String> data) {

        
        String[] strValues = { "" };
        data.forEach((key, value) -> {
        	strValues[0] += key + "::" + (value) + ";";
        });
        
        String dataToPostToPG = strValues[0];
        // Removing last 2 characters (::) 
        dataToPostToPG = dataToPostToPG.substring(0, dataToPostToPG.length() - 1);
        // Encrypting String
        try {
        	
        	return AesEncryptDecrypt.encrypt(this.encryptionKey, dataToPostToPG);
        	
        } catch(NoSuchPaddingException | NoSuchAlgorithmException |
        		InvalidAlgorithmParameterException | InvalidKeyException |
        		BadPaddingException | IllegalBlockSizeException | ShortBufferException e) {
        	
        	System.out.println(e.toString());
        	return null;
        	
        } catch(Exception e) {
        	//System.out.println(e + "");
        	e.printStackTrace();
        	return null;
        }
        
    }
    
    private String createForm(String encData) {
        
        Map<String, String> formData = new HashMap<String, String>();
        formData.put("encrypted_request", encData);
        formData.put("merchant_id", this.merchantId);
        formData.put("access_token", this.accessToken);
        
        if (this.isLive) {
        	formData.put("gateway_url", Payment.GATEWAY_URL_PROD);
            
        } else {
        	formData.put("gateway_url", Payment.GATEWAY_URL_DEV);
            
        }
        formData.put("pageTitle", Payment.PAGE_TITLE);
        
       
       return this.prepareCustomFormTemplate(formData);
       
    }
    
    private String prepareCustomFormTemplate (Map<String, String> formData) {
    	
    	return new StringBuilder()
    			.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">")
    				.append("<html lang=\"en\">")
    						
    						.append("<head>")
    							.append("<title>"+ formData.get("pageTitle") +"</title>")
    							.append("<meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\">")
    						.append("</head>")
    						
			    			.append("<body>")
			    				.append("<div>")
			    					.append("Processing your payment, please wait...")
			    				.append("</div>")
			    				
			    				.append("<form  action=\""+ formData.get("gateway_url")+"\" method=\"post\" name=\"server_request\" target=\"_top\" >")
			    					.append("<table width=\"80%\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">")
			    						.append("<tr><td><input type=\"hidden\" name=\"encrypted_request\" id=\"encrypted_request\" value=\""+formData.get("encrypted_request")+"\" /></td></tr>")
			    						.append("<tr><td><input type=\"hidden\" name=\"merchant_id\" id=\"merchant_id\" value=\""+formData.get("merchant_id")+"\" /></td></tr>")
			    						.append("<tr><td><input type=\"hidden\" name=\"access_token\" id=\"access_token\" value=\""+formData.get("access_token")+"\" /></td></tr>")
			    					.append("</table>")
			    				.append("</form>")
			    			.append("</body>")
			    			.append("<script type=\"text/javascript\">document.server_request.submit();</script>")
			    	.append("</html>")		
    			.toString();
    	
        
    }
    
    public TransactionStatusResponse getTransactionInfo(String transactionId) {
    	
    	URL url = null;
    	
    	try {
    		if(this.isLive)
        		url = new URL("https://api.paykun.com/v1/merchant/transaction/"+transactionId);
        	else
        		url = new URL("https://sandbox.paykun.com/api/v1/merchant/transaction/"+transactionId);	
    		
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
    	
    	
    	HttpURLConnection con;
		try {
//			System.out.println(this.merchantId);
//	    	System.out.println(this.accessToken);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
	    	con.setRequestProperty("MerchantId", this.merchantId);
	    	con.setRequestProperty("AccessToken", this.accessToken);
	    	int responseCode = con.getResponseCode();
	    	//System.out.println(responseCode);
	    	if (responseCode == 200) {
	    		
                InputStream inputStr = con.getInputStream();
                String encoding = con.getContentEncoding() == null ? "UTF-8"
                        : con.getContentEncoding();
                
                String jsonResponse = IOUtils.toString(inputStr, encoding);
                Gson gson = new Gson();
                TransactionStatusResponse response = gson.fromJson(jsonResponse, TransactionStatusResponse.class);
                return response;
                

            } else {
            	return null;
            }
	    	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    	
    	
    }
}
