package noobChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

public class Wallet {
	
	public PrivateKey privateKey;
	public PublicKey publicKey;
	
	
	public Wallet() {
		generateKeyPair();
		// TODO Auto-generated constructor stub
	}


	private void generateKeyPair() {
		// TODO Auto-generated method stub
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
			
			//Initialize the key generator and generate a KeyPair
			keyGen.initialize(ecSpec, random);  //256 byte provides an acceptable security level
			KeyPair keypair = keyGen.generateKeyPair();
			
			//Set the public and private keys from the keyPair
			privateKey = keypair.getPrivate();
			publicKey = keypair.getPublic();
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		
		
		//All you need to understand about this method is it uses Java.security.KeyPairGenerator to generate an Elliptic Curve KeyPair. 
		//This methods makes and sets our Public and Private keys
	}
	
	

}
