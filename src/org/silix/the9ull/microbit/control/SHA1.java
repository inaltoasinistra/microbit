package org.silix.the9ull.microbit.control;

import java.io.UnsupportedEncodingException; 
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
 
public class SHA1 { 
 
    public static String digest(String text) { 
	    MessageDigest md;
	    byte[] sha1hash = new byte[40];
	    try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(text.getBytes("iso-8859-1"), 0, text.length());
			sha1hash = md.digest();
			
			return String.format("%x", new BigInteger(1, sha1hash));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    return "";
    }
    
    // return: 40chars String
    public static String HMAC_digest(String key, String message) {

        SecretKeySpec keySpec = new SecretKeySpec(
                key.getBytes(),
                "HmacMD5");

        Mac mac;
        byte[] rawHmac = null;
		try {
			mac = Mac.getInstance("HmacSHA1");
			mac.init(keySpec);
			rawHmac = mac.doFinal(message.getBytes());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return String.format("%x", new BigInteger(1, rawHmac));
    }
    
    public static void main(String []args){
    	
		System.out.println(HMAC_digest("Chiave","Messaggio"));
		System.out.println(HMAC_digest("Chiave","M"));
		System.out.println(HMAC_digest("C","Messaggio"));
		System.out.println(HMAC_digest("Ch","Messaggio"));
		
    }
} 