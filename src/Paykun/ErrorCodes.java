package Paykun;

public class ErrorCodes {
	
	public static final String INVALID_MERCHANT_ID_CODE      = "100";
	public static final String INVALID_MERCHANT_ID_STRING    = "Merchant ID is not valid";

	public static final String INVALID_ACCESS_TOKEN_CODE     = "101";
	public static final String INVALID_ACCESS_TOKEN_STRING   = "Access Token is not valid";

	public static final String INVALID_API_SECRETE_CODE      = "102";
	public static final String INVALID_API_SECRETE_STRING    = "Api Secret is not valid";

	public static final String INVALID_ORDER_ID_CODE         = "103";
	public static final String INVALID_ORDER_ID_STRING       = "Order ID must be longer than 10 characters";

	public static final String INVALID_PURPOSE_CODE          = "104";
	public static final String INVALID_PURPOSE_STRING        = "Please provide Payment Purpose";

	public static final String INVALID_AMOUNT_CODE           = "105";
	public static final String INVALID_AMOUNT_STRING         = "Amount should not be less than 10 rupees";

	public static final String INVALID_SUCCESS_URL_CODE      = "106";
	public static final String INVALID_SUCCESS_URL_STRING    = "Success Url is not valid";

	public static final String INVALID_FAIL_URL_CODE         = "107";
	public static final String INVALID_FAIL_URL_STRING       = "Failure Url is not valid";

	public static final String MISSING_CUSTOMER_NAME_CODE    = "109";
	public static final String MISSING_CUSTOMER_NAME_STRING  = "Please provide Customer Name";

	public static final String INVALID_CUSTOMER_NAME_CODE    = "110";
	public static final String INVALID_CUSTOMER_NAME_STRING  = "Only letters and white space allowed as Customer Name";

	public static final String MISSING_CUSTOMER_EMAIL_CODE   = "111";
	public static final String MISSING_CUSTOMER_EMAIL_STRING = "Please provide Customer Email";

	public static final String INVALID_CUSTOMER_EMAIL_CODE   = "112";
	public static final String INVALID_CUSTOMER_EMAIL_STRING = "Customer Email is not valid";

	public static final String INVALID_MOBILE_NO_CODE        = "113";
	public static final String INVALID_MOBILE_NO_STRING      = "Customer Mobile Number is not valid";

	public static final String INVALID_DATA_PROVIDED_CODE    = "114";
	public static final String INVALID_DATA_PROVIDED_STRING  = "Provided data is not proper to make this transaction";

	public static final String INVALID_COUNTRY_NAME_CODE     = "115";
	public static final String INVALID_COUNTRY_NAME_STRING   = "country must be longer than 4 characters";

	public static final String INVALID_STATE_NAME_CODE       = "116";
	public static final String INVALID_STATE_NAME_STRING     = "state must be longer than 2 characters";

	public static final String INVALID_CITY_NAME_CODE        = "117";
	public static final String INVALID_CITY_NAME_STRING      = "city must be longer than 2 characters";

	public static final String INVALID_POSTAL_CODE_CODE      = "118";
	public static final String INVALID_POSTAL_CODE_STRING    = "Postal code is not valid";

	public static final String INVALID_ADDRESS_CODE          = "119";
	public static final String INVALID_ADDRESS_STRING        = "address can not be left blank, should be longer than 4 characters";

}
