package noobChain;

import java.security.Key;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class StringUtil {

	// apply SHA256 to a string and returns the results
	public static String applySha256(String input) {
		try {

			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			/*
			 * MessageDigest -
			 * 
			 * This MessageDigest class provides applications the functionality of a message
			 * digest algorithm, such as SHA-1 or SHA-256.Message digests are secure one-way
			 * hash functions that take arbitrary-size data and output a fixed-length hash
			 * value.
			 */

			// applies sha256 to our input

			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer(); // this will contain hash in hexadecimal
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);

			}
			return hexString.toString();

		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}

	//Applies ECDSA Signature and returns the result ( as bytes ).
			public static byte[] applyECDSASig(PrivateKey privateKey, String input) {
			Signature dsa;
			byte[] output = new byte[0];
			try {
				dsa = Signature.getInstance("ECDSA", "BC");
				dsa.initSign(privateKey);
				byte[] strByte = input.getBytes();
				dsa.update(strByte);
				byte[] realSig = dsa.sign();
				output = realSig;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return output;
		}
		
		//Verifies a String signature 
		public static boolean verifyECDSASig(PublicKey publicKey, String data, byte[] signature) {
			try {
				Signature ecdsaVerify = Signature.getInstance("ECDSA", "BC");
				ecdsaVerify.initVerify(publicKey);
				ecdsaVerify.update(data.getBytes());
				return ecdsaVerify.verify(signature);
			}catch(Exception e) {
				throw new RuntimeException(e);
			}
		}

		public static String getStringFromKey(Key key) {
			return Base64.getEncoder().encodeToString(key.getEncoded());
		}

}

/*
 * Don’t worry too much about understanding the contents of these methods. All
 * you really need to know is : applyECDSASig takes in the senders private key
 * and string input, signs it and returns an array of bytes. verifyECDSASig
 * takes in the signature, public key and string data and returns true or false
 * if the signature is valid. getStringFromKey returns encoded string from any
 * key.
 */