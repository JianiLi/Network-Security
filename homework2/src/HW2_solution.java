/*
 * author: Jiani Li
 * Txt Solutions:
 * P1. The plaintext is: The quick brown fox jumps over the lazy dog.
 * P2. Message Digest Hash: [-28, -39, 9, -62, -112, -48, -5, 28, -96, 104, -1, -83, -33, 34, -53, -48]8]
 * P3. The five numbers are: -65, 6,-39,-69,115
 * P5. P5_msg2.txt is from an authentic source using HMAC with the hash function SHA256
 */

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.BadPaddingException;
import java.security.MessageDigest;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
 
public class HW2_solution {    
  static void P1() throws Exception {
    byte[] iv = new byte[] { 0, 0, 0, 0, 
                             0, 0, 0, 0, 
                             0, 0, 0, 0, 
                             0, 0, 0, 0 };
    byte[] key = Files.readAllBytes(Paths.get("./src/P1_key"));
    byte[] cipherText = Files.readAllBytes(Paths.get("./src/P1_cipher.txt"));

    // BEGIN SOLUTION
    byte[] plainText = cipherText; 
    SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
    Cipher cipher = Cipher.getInstance("AES/CBC/ISO10126Padding");
    IvParameterSpec ivSpec = new IvParameterSpec(iv);
    cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
    plainText = cipher.doFinal(cipherText);
    // END SOLUTION
    
    System.out.println(new String(plainText, StandardCharsets.UTF_8));
  }

  static void P2() throws Exception {
    byte[] cipherBMP = Files.readAllBytes(Paths.get("./src/P2_cipher.bmp"));
    
    // BEGIN SOLUTION
    byte[] P1_plaintext = "The quick brown fox jumps over the lazy dog.".getBytes(StandardCharsets.UTF_8);
    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
    messageDigest.update( P1_plaintext );
    System.out.println( "\nMessage Digest Hash: " );
    byte[] key2 = messageDigest.digest();
    System.out.println( Arrays.toString(key2));
    
    byte[] plainBMP = cipherBMP;   
    SecretKeySpec skeySpec = new SecretKeySpec(key2, "AES");
    Cipher cipher = Cipher.getInstance("AES/ECB/ISO10126Padding");
    cipher.init(Cipher.DECRYPT_MODE, skeySpec);
    plainBMP = cipher.doFinal(cipherBMP);
    // END SOLUTION
    Files.write(Paths.get("./src/P2_plain.bmp"), plainBMP);
  }

  static void P3() throws Exception {
    byte[] cipherBMP = Files.readAllBytes(Paths.get("./src/P3_cipher.bmp"));
    int headerLen = 138;
    // BEGIN SOLUTION
    byte[] modifiedBMP = cipherBMP;
    byte[] P2_plainBMP= Files.readAllBytes(Paths.get("./src/P2_plain.bmp"));
    System.arraycopy(P2_plainBMP, 0, modifiedBMP, 0, headerLen);
    // END SOLUTION
    
    Files.write(Paths.get("./src/P3_cipher_modified.bmp"), modifiedBMP);
  }

  static void P4() throws Exception {
    byte[] iv = new byte[] { 0, 0, 0, 0, 
                             0, 0, 0, 0, 
                             0, 0, 0, 0, 
                             0, 0, 0, 0 };
    byte[] cipherBMP = Files.readAllBytes(Paths.get("./src/P4_cipher.bmp"));
    
    // BEGIN SOLUTION
    byte hour = 0;
    byte minute = 0;
    byte second = 0;
    byte[] key = new byte[] {   0,   0,    0,   0, 
                                0,   hour, minute, second,
                                0,   0,    0,   0,
                                0,   0,    0,   0 }; 
    byte[] plainBMP = cipherBMP;   
    byte[] plainBMPtemp = cipherBMP;  
    key[0] = -65;
    key[1] = 6;
    key[2] = -39;
    key[3] = -69;
    key[4] = 115;
    byte[] P2_plainBMP= Files.readAllBytes(Paths.get("./src/P2_plain.bmp"));
    byte[] P2_plainBMPFirstSix = new byte[6];
    System.arraycopy(P2_plainBMP, 0, P2_plainBMPFirstSix, 0, 6);
    
	    for(hour=0;hour<=23;hour++)
	    	for(minute = 0; minute <= 59; minute++)
	    		for(second = 0; second < 59; second++)
	    			try{
		    			key[5] = hour;
		    			key[6] = minute;
		    			key[7] = second;
		    			SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
		    		    Cipher cipher = Cipher.getInstance(" AES/CBC/ISO10126Padding");
		    		    IvParameterSpec ivSpec = new IvParameterSpec(iv);
		    		    cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
		    		    plainBMPtemp = cipher.doFinal(cipherBMP);
		    		    byte[] plainBMPtempFirstSix = new byte[6];
		    		    System.arraycopy(plainBMPtemp, 0, plainBMPtempFirstSix, 0, 6);
		    		    if(Arrays.equals(plainBMPtempFirstSix, P2_plainBMPFirstSix))
		    		    {
		    		    	plainBMP = plainBMPtemp;
		    		    	break;
		    		    }
	    			}catch(BadPaddingException e){
	    				continue;}
    // END SOLUTION
    
    Files.write(Paths.get("./src/P4_plain.bmp"), plainBMP);
  }

  static void P5() throws Exception {
    byte[] msg1withTag = Files.readAllBytes(Paths.get("./src/P5_msg1.txt"));
    byte[] msg2withTag = Files.readAllBytes(Paths.get("./src/P5_msg2.txt"));
    byte[] key = Files.readAllBytes(Paths.get("./src/P1_key"));
    
    // BEGIN SOLUTION
    byte[] hmacData1 = new byte[32];
    byte[] hmacData2 = new byte[32];
    byte[] msg1 = new byte[28];
    byte[] msg2 = new byte[11];
    byte[] msg1Tag = new byte[32];
    byte[] msg2Tag = new byte[32];
    
    SecretKeySpec secretKey = new SecretKeySpec(key,"HmacSHA256");
    Mac mac = Mac.getInstance("HmacSHA256");
    mac.init(secretKey);
    
    System.arraycopy(msg1withTag, 0, msg1, 0, 28);
    System.arraycopy(msg2withTag, 0, msg2, 0, 11);
    hmacData1 = mac.doFinal(msg1);
    hmacData2 = mac.doFinal(msg2);
    System.arraycopy(msg1withTag, 28, msg1Tag, 0, 32);
    System.arraycopy(msg2withTag, 11, msg2Tag, 0, 32);
    if(Arrays.equals(msg1Tag,hmacData1))
    {
    	System.out.println("P5_msg1.txt is from an authentic source using HMAC with the hash function SHA256");
    }else if(Arrays.equals(msg2Tag,hmacData2))
    {
    	System.out.println("P5_msg2.txt is from an authentic source using HMAC with the hash function SHA256");
    }
    // END SOLUTION
  }

  static void P6() throws Exception {
    byte[] key = Files.readAllBytes(Paths.get("./src/P1_key"));
    byte[] iv = new byte[] { 0, 0, 0, 0, 
                             0, 0, 0, 0, 
                             0, 0, 0, 0, 
                             0, 0, 0, 0 };
    byte[] cipherBMP = Files.readAllBytes(Paths.get("./src/P6_cipher.bmp"));
    
    // BEGIN SOLUTION
    // modify ciphertext
    byte[] cipherModifiedBMP = cipherBMP;
    byte[] header = new byte[144];
    System.arraycopy(cipherBMP, 0, header, 0, 144);		
    byte[] h1 = new byte[80000];
    System.arraycopy(cipherBMP, 144, h1, 0, 80000);	
    byte[] h2 = new byte[80000];
    System.arraycopy(cipherBMP, 80144, h2, 0, 80000);	
    byte[] h3 = new byte[80000];
    System.arraycopy(cipherBMP, 160144, h3, 0, 80000);	
    byte[] h4 = new byte[80000];
    System.arraycopy(cipherBMP, 230144, h4, 0, 70000);
    System.arraycopy(h3, 0, cipherModifiedBMP , 144, 80000);
    System.arraycopy(h1, 0, cipherModifiedBMP , 160144, 80000);
    // decrypt ciphertext into plaintext;
    byte[] plainModifiedBMP = cipherModifiedBMP;
    SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
    Cipher cipher = Cipher.getInstance("AES/CBC/ISO10126Padding");
    IvParameterSpec ivSpec = new IvParameterSpec(iv);
    cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
    plainModifiedBMP = cipher.doFinal(cipherBMP);
    // END SOLUTION    
    
    Files.write(Paths.get("./src/P6_plain_modified.bmp"), plainModifiedBMP);
  }

  public static void main(String [] args) {
    try {  
      P1();
      P2();
      P3();
      P4();
      P5();
      P6();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
