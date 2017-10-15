
/*
 * author: Jiani Li
 * letter to number:
 * A  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P  Q  R  S  T  U  V  W  X  Y  Z
 * -----------------------------------------------------------------------------
 * 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
 * steps:
 * %%%%%%%%%%%%%%%%%% task1 %%%%%%%%%%%%%%%%%%
 * 1.calculate the frequency of each letter in the cipherText
 * 2.sort the frequency AND find that the cipherText letter "G" and "N" have the most frequency.
 * According to the frequency of letters, I assume "G" and "N" to be "T" and "E".
 * %%%%%%%%%%%%%%%%%% task2 %%%%%%%%%%%%%%%%%%
 * c=E(p)≡ap+b   (mod 26)
 * since E(T) = G, E(E) = N
 * such that 19a + b = 6  (mod 26)  and 4a + b = 13 (mod 26)
 * such that 15a = -7 (mod 26) = 19 (mod 26) = 45 (mod 26) 
 * such that a = 3, b = 1
 * such that c=E(p)≡3p+1   (mod 26)
 * %%%%%%%%%%%%%%%%%% task3 %%%%%%%%%%%%%%%%%%
 * 1.D(p) = (c-1)·3^(-1) mod 26
 * 2.calculate 
 */
public class HW1_P1 {
	  private static final String cipherText = "GWN GANBDJAN HWNDG ZD EJAZNK WNAN:\n" +
	    "DBZI QARL DFNINGRO ZDIBOK GR GWN NBDG-DRJGWNBDG.\n" +
	    "BQGNA GPR KBVD, VRJ PZII QZOK B KNDNAGNK ZDIBOK.\n" +
	    "HIZLE GR GWN WZTWNDG URZOG RQ GWN ZDIBOK BOK IRRF QRA B IBATN HIZQQ GR GWN ORAGW.\n" +
	    "PBIF B WJOKANK VBAKD GRPBAKD GWN HIZQQ BOK DGRU BG GWN GBII GANN.\n" +
	    "PBIF GNO QNNG GR GWN NBDG, BOK VRJ PZII QZOK B ARHF PZGW BO S UBZOGNK RO ZG.\n" +
	    "GWN GANBDJAN ZD EJAZNK RON QRRG ENIRP GWN ARHF.\n";
	  
	  static final int alphaIndex = (int) 'A';
	  static final int alphaLength = (int) 'Z' + 1 - (int) 'A';
	  
	  // needs to be filled out based on the ciphertext
	  static int[] frequency = new int[alphaLength];
	  static int[] Index = new int[alphaLength];
	  
	  // find the frequency of each letter in the cipherText
	  public static void frequencyCalculation(String cipherText)  
	    {  
		  for (int i = 0; i < cipherText.length(); i++) {
	            char  cipherChar = cipherText.charAt(i);
	           
	            if (Character.isLetter(cipherChar)) {
		        // following line converts letters to numbers between 0 and 25
		        int cipher = (int) cipherChar - alphaIndex;
		        frequency[cipher]++;
		        }
		  	}
		  
		  for(int i = 0; i < frequency.length;i++)
		  {
			  Index[i] = i;
		  }
		  
		  for(int i = 0; i < frequency.length;i++)
		  {
			  int max = frequency[0];
			  for(int j = i+1; j < frequency.length;j++)
			  {
				  if (frequency[j]>frequency[i])
				  {
					  int temp = frequency[i];
					  frequency[i] = frequency[j];
					  frequency[j] = temp;
					  int tempIndex = Index[i];
					  Index[i] = Index[j];
					  Index[j] = tempIndex;
				  }
			  }
		  }
		  /*
		  System.out.println("The frequency of the cipher:");
		  for(int i = 0; i < frequency.length;i++)
		  {
			  System.out.println(frequency[i]+" "+Index[i]);  
		  }  
		  */
	  }
	  
	  private static int decrypt(int index) {
	    // ... decrypt ...
	    // In task 2, I found a = 3, thus I calculate the a^(-1) as follows: 
		int i = 0;
		while(3*(++i)%26!=1);
		//D(p) = (c-1)·3^(-1) mod 26
		index = ((index-1) * i) %26;
		if (index < 0)
		{
			index = index +26;
		}
	    return index;
	  }
	  
	  
	  
	  public static void main(String [] args) {
		HW1_P1.frequencyCalculation(cipherText);
	    for (char cipherChar : cipherText.toCharArray())
	      if (Character.isLetter(cipherChar)) {
	        // following line converts letters to numbers between 0 and 25
	        int cipher = (int) cipherChar - alphaIndex;
	        int plain = decrypt(cipher);
	        // following line coverts numbers between 0 and 25 to letters
	        char plainChar = (char) (plain + alphaIndex);
	        System.out.print(plainChar);
	      }
	      else
	        System.out.print(cipherChar);
	  }  
	}
