package noobChain;

import java.security.MessageDigest;

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

}
