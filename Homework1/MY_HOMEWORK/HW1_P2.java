
/*
 * author: Jiani Li
 * %%%%%%%%%%%%%%%%%% Steps %%%%%%%%%%%%%%%%%%
 * 1.Since the plaintext is an HTTP GET request encoded in ASCII, the first four ciphertext
 * must be the XOR of plaintext and key, and the value must be "GET "
 * 2.For each possible keylength, calculate the XOR of the cipherText and "GET " and print out
 * the corresponding plaintext.
 * 3.Find the readable plaintext and it should be the solution of the problem. 
 */

public class HW1_P2 {

	  private static byte[] cipherText = new byte[] { -119, 119, 48, -18, 29, 23, -85, 81, 22, -85, 70, 74, -66, 90, 20, -15, 66, 5, -67, 65, 19, -95, 64, 0, -13, 83, 5, -68, 86, 18, -81, 64, 15, -18, 122, 48, -102, 98, 75, -1, 28, 85, -60 };
	  	    
	  public static void main(String [] args) {
	    // key may be of different length and - obviously - contain different values	
		// Assuming key has the length less than 4
		for(int i = 0; i < 4; i++)
		{
			byte[] key = new byte[i+1];
			byte[] GETbytes = "GET ".getBytes();
			//for(i = 0; i < GETbytes.length;i++)
				//System.out.println(GETbytes[i]);
			//The first four byte must be the XOR of the key and the plaintext
			//which should be the byte of "GET "
			for(int j = 0; j < key.length; j++)
			{
				key[j] = (byte)(cipherText[j] ^ GETbytes[j]);
			}
		    for (int k = 0; k < cipherText.length; k++) 
			      System.out.print((char) (cipherText[k] ^ key[k % key.length]));
		}      
	  }  
	}
//From the output, the third one is readable, thus the plaintext is
//"GET /secret.php?password=aardvark HTTP/1.1"
