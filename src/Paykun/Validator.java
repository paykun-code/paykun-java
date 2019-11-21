package Paykun;
import Paykun.ErrorCodes;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Validator {
	
	public static final int ORDER_ID_MIN_LENGTH       = 10;
	public static final int MERCHANT_ID_MIN_LENGTH    = 15;
	public static final int ACCESS_TOKEN_MIN_LENGTH   = 32;
	public static final int ENC_KEY_MIN_LENGTH        = 32;
	public static final int MIN_AMOUNT_REQUIRE        = 10;
	public static final int MOBILE_NO_MIN_LENGTH      = 10;
	public static final int MOBILE_NO_MAX_LENGTH      = 10;
	public static final int ADDRESS_MIN_LENGTH        = 10;
	
	public static boolean VALIDATE_MERCHANT_ID(String mid) {

        return mid.length() != Validator.MERCHANT_ID_MIN_LENGTH;

    }
	
	public static boolean VALIDATE_ACCESS_TOKEN(String accessToken) {

        return (accessToken.length() != Validator.ACCESS_TOKEN_MIN_LENGTH);

    }

    public static boolean VALIDATE_ENCRYPTION_KEY(String encKey) {

        return (encKey.length() != Validator.ENC_KEY_MIN_LENGTH);

    }

    public static boolean VALIDATE_ORDER_NUMBER(String orderId) {

        return (orderId.length() < Validator.ORDER_ID_MIN_LENGTH);

    }

    public static boolean VALIDATE_PURPOSE(String purpose) {

        return purpose.length() == 0;

    }

    public static boolean VALIDATE_AMOUNT(double amount) {

        return amount < Validator.MIN_AMOUNT_REQUIRE;

    }

    public static boolean VALIDATE_URL(String url) {
    	
    	try { 
            new URL(url).toURI(); 
            return true; 
        } 
          
        // If there was an Exception 
        // while creating URL object 
        catch (Exception e) { 
            return false; 
        } 

    }

    public static Map<String, String> VALIDATE_CUSTOMER_NAME(String name) {
    	
    	Map<String, String> errorDetail = new HashMap<String, String>();
    	
        if (name.length() == 0) {
        	errorDetail.put("message", ErrorCodes.MISSING_CUSTOMER_NAME_STRING);
        	errorDetail.put("code", ErrorCodes.MISSING_CUSTOMER_NAME_CODE);
        }

        if (name.matches("/^[a-zA-Z ]*$/")) {
        	
        	errorDetail.put("message", ErrorCodes.INVALID_CUSTOMER_NAME_STRING);
        	errorDetail.put("code", ErrorCodes.INVALID_CUSTOMER_NAME_CODE);

        }
        
        return errorDetail;

    }

    public static Map<String, String> VALIDATE_CUSTOMER_EMAIL(String email) {

        
        Map<String, String> errorDetail = new HashMap<String, String>();
        
        if (email.length() == 0) {
        	errorDetail.put("message", ErrorCodes.MISSING_CUSTOMER_EMAIL_STRING);
        	errorDetail.put("code", ErrorCodes.MISSING_CUSTOMER_EMAIL_CODE);
        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                "[a-zA-Z0-9_+&*-]+)*@" + 
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                "A-Z]{2,7}$"; 
        
        Pattern pat = Pattern.compile(emailRegex);
        
        if(!pat.matcher(email).matches()) {
        	errorDetail.put("message", ErrorCodes.INVALID_CUSTOMER_EMAIL_STRING);
        	errorDetail.put("code", ErrorCodes.INVALID_CUSTOMER_EMAIL_CODE);
        }
        
        return errorDetail;

    }

    public static boolean VALIDATE_MOBILE_NO(String mobileNo) {

    	return (mobileNo == null || mobileNo.length() == 0 || mobileNo.length() < Validator.MOBILE_NO_MIN_LENGTH || mobileNo.length() > Validator.MOBILE_NO_MAX_LENGTH);
        
    }

    public static boolean VALIDATE_COMMON_FIELD(String name) {

        
        return !(name.matches("/^[a-zA-Z ]*$/"));
       

    }

    public static boolean VALIDATE_ADDRESS_FIELD(String address) {

        return !(address.length() < Validator.ADDRESS_MIN_LENGTH);
        
    }

    public static boolean VALIDATE_PINCODE(String pin) {

    	return !(pin.matches("\\\\d{5}(-\\\\d{4})?"));
        

    }
}
