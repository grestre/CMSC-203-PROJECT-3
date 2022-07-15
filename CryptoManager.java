

public class CryptoManager {
	
	private static final char LOWER_BOUND = ' ';
	private static final char UPPER_BOUND = '_';
	private static final int RANGE = UPPER_BOUND - LOWER_BOUND + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_BOUND and UPPER_BOUND characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean stringInBounds (String plainText) {
		boolean bounds = true; 
		for (int i = 0; i< plainText.length(); i++) {
			if(!(plainText.charAt(i) >= LOWER_BOUND && plainText.charAt(i) <= UPPER_BOUND))			//Return false if it is out of bounds
				bounds = false;
		}
		return bounds;
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String encryptCaesar(String plainText, int key) {
		String eC = ""; 	//eC : encryptedCaesar
		if(stringInBounds(plainText)) {
			for(int i = 0; i<plainText.length(); i++) {
				char first = plainText.charAt(i); 		//Letter at the position according to the for loop is stored in char first
				int encryptedFirst = ((int)first+key);	 //encryptedFirst is char first added with the key. Turn char first into an int
				while(UPPER_BOUND < encryptedFirst) 	
					encryptedFirst-=RANGE;  			//encryptedFirst subtracted with the range
				eC += (char)encryptedFirst;				//encryptedFirst is turned into a char
			}
		}
		return eC; 
	}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String encryptBellaso(String plainText, String bellasoStr) {
		String eB = ""; 								//eB: encryptedBellaso
		int bLength = bellasoStr.length();				//bLength: String length for Bellaso
			for(int i = 0; i<plainText.length(); i++) {
				char first = plainText.charAt(i); 
				int encryptedFirst = ((int)first + (int)bellasoStr.charAt(i%bLength));
				while(encryptedFirst > UPPER_BOUND)
					encryptedFirst -= RANGE; 
				eB += (char)encryptedFirst; 
			}
		return eB; 
	}
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String decryptCaesar(String encryptedText, int key) {
		String dC = ""; 									//dC: decryptedCaesar
		for(int i = 0; i<encryptedText.length(); i++) {
			char first = encryptedText.charAt(i); 			//character at the ith position according to for loop
			int decryptFirst = ((int)first - key); 
			while(decryptFirst < LOWER_BOUND)				//Opposite of encrypt caesar to revert the string back
				decryptFirst += RANGE; 
			dC += (char)decryptFirst; 
		}
		return dC; 
	}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String decryptBellaso(String encryptedText, String bellasoStr) {
		String dB = ""; 									//dB: decryptedBellaso
		int bLength = bellasoStr.length(); 
		for(int i = 0; i<encryptedText.length(); i++) {
			char first = encryptedText.charAt(i); 
			int decryptedFirst = ((int)first-(int)bellasoStr.charAt(i%bLength)); 
			while(decryptedFirst < LOWER_BOUND)			//Opposite of encrypt bellaso to revert the string back
				decryptedFirst += RANGE; 
			dB += (char)decryptedFirst; 
		}
		return dB; 
	}
}
