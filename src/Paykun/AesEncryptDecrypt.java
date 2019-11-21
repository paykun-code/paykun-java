package Paykun;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.codec.binary.Hex;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class AesEncryptDecrypt
{
	
	public static String encrypt(String keysec, String plaintext) throws Exception {
    	
    	
    	byte[] keyValue = keysec.getBytes("UTF-8");
        Key key = new SecretKeySpec(keyValue, "AES");
        //serialize
        String serializedPlaintext = "s:" + plaintext.getBytes().length + ":\"" + plaintext + "\";";
        
        byte[] plaintextBytes = serializedPlaintext.getBytes("UTF-8");
        
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] iv = cipher.getIV();
        
        byte[] encVal = cipher.doFinal(plaintextBytes);
        //This below live getting an exception
        //System.out.println(Base64.encodeToString(encVal, Base64.NO_WRAP));
        String encryptedData = 
        		java.util.Base64.getEncoder().encodeToString(encVal);
        
        
        SecretKeySpec macKey = new SecretKeySpec(keyValue, "HmacSHA256");
        Mac hmacSha256 = Mac.getInstance("HmacSHA256");
        hmacSha256.init(macKey);
        
        hmacSha256.update(
        		java.util.Base64.getEncoder().encode(iv)
        		//Base64.encode(iv, Base64.NO_WRAP)
        		);
        
        byte[] calcMac = hmacSha256.doFinal(encryptedData.getBytes("UTF-8"));
        String mac = new String(Hex.encodeHex(calcMac));
        

        AesEncryptionData aesData = new AesEncryptionData(
        		java.util.Base64.getEncoder().encodeToString(iv)
        		//Base64.encodeToString(iv, Base64.NO_WRAP)
                ,
                encryptedData,
                mac);
        
        Gson gson = new GsonBuilder().disableHtmlEscaping()
        		//.setPrettyPrinting()
        		.create();
        String aesDataJson = gson.toJson(aesData);
        return java.util.Base64.getEncoder().encodeToString(aesDataJson.getBytes("UTF-8"));
    }
    
}

class AesEncryptionData {
    public String iv;
    public String value;
    public String mac;

    public AesEncryptionData(String iv, String value, String mac) {
        this.iv = iv;
        this.value = value;
        this.mac = mac;
    }

}