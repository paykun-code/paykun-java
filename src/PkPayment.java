import Paykun.Payment;
import Paykun.TransactionStatusResponse;
import Paykun.ValidationException;

public class PkPayment {
	
	public static void main(String args[]) {
		
		try {
			
			
			//Use this code to redirect to PayKun payment
			//Just uncomment this code and place your data by removing "<>"
			Payment payment = new Payment("<merchantId>", "<accessToken>", "<encKey>", true); //true for live and false for testing mode
			//TransactionStatusResponse response = payment.getTransactionInfo("<payment-id>"); //Use this line of code in success or failed url 
			//System.out.println(response.data.transaction);
//			ReflectionToStringBuilder.toString(object)

			payment.initOrder("12358sasdasd", "Teasting", 10.33, "<successUrl>", "<failUrl>");
			
			payment.addCustomer("Test", "test@gmail.com", "9999999999");
			
			payment.addShippingAddress("", "", "", "", "");
			
			payment.addBillingAddress("", "", "", "", "");
			String form = payment.submit();
			System.out.println(form);
			
			
			//use this "form" variable to render the form in your html page 
			
			//NOTE:
			//Place this code in your success and fail URL handler
			//Payment payment = new Payment("<merchantId>", "<accessToken>", "<encryptionKey>", true);
			//TransactionStatusResponse response = payment.getTransactionInfo("<payment-id>"); // parameter accepted from success or fail URL
			
			
		} catch(ValidationException e) { //Uncomment this exception handling
			e.printStackTrace();
		} 
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
}
