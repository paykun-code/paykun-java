package Paykun;

public class TransactionResponse {
	public String payment_id;
    public String merchant_email;
    public String merchant_id;
    public String status;
    public int status_flag;
    public String payment_mode;
    public OrderResponse order;
    public CustomerResponse customer;
    public ShippingResponse shipping;
    public BillingResponse billing;
    public String custom_field_1;
    public String custom_field_2;
    public String custom_field_3;
    public String custom_field_4;
    public String custom_field_5;
    public String date;
}
