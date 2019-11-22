# paykun-java
PayKun java SDK

# <h3>How To Generate Access token and API Secret :</h3>
You can find your Merchant Id in Paykun Dashboard.

You can generate Or Regenerate Access token and API Secret from login into your paykun admin panel, Then Go To : Settings -> Security -> API Keys. There you will find the generate button if you have not generated api key before.

If you have generated api key before then you will see the date of the api key generate, since you will not be able to retrieve the old api key (For security reasons) we have provided the re-generate option, so you can re-generate api key in case you have lost the old one.

Note : Once you re-generate api key your old api key will stop working immediately. So be cautious while using this option.

# <h3>Prerequisite</h3>
    1. Merchant Id (You can find your Merchant id in your Paykun Admin panel after login)
    2. Access Token (Please read 'How To Generate Access token and API Secret :')
    3. Encryption Key (Please read 'How To Generate Access token and API Secret :')
    
# <h3>Set all the required credentials</h3>
    1. From the extracted zip open the file 'paykunCheckout/views.py'
    2. In views.py file find the method with the name 'payNow'
    3. In 'payNow' replace all the dummy detail with real one provided from Paykun
    4. Detail like order, customer, shipping, billing should be set by your own.
    
# <h3>How to run app</h3>
    1. Extract downloaded zip
    2. From command line navigate to the mysite directory in extracted directory
    3. Open this project in eclipse
    4. Run the project
	5. String form = payment.submit(); While calling this function from your payment class, this will return one form, render this form and it will redirected to the checkout page.
	
  
#<h3> In case of any query, please contact to support@paykun.com.</h3>