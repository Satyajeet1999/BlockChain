package noobChain;

import java.security.Security;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class noobChain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;
	public static Wallet walletA;
	public static Wallet walletB;

	public static void main(String[] args) {

		// *****************************************************************************

		/*
		 * 
		 * for hash value testing
		 * 
		 * Block genesisBlock = new Block("Hi i am the first Block", "0");
		 * System.out.println("Hash for Block 1 : " + genesisBlock.hash);
		 * 
		 * Block secondBlock = new Block("Hi i am second Block", genesisBlock.hash);
		 * System.out.println("Hash for Block 2 : " + secondBlock.hash);
		 * 
		 * Block thirdBlock = new Block("Hey im the third block",secondBlock.hash);
		 * System.out.println("Hash for block 3 : " + thirdBlock.hash);
		 */

		// *********************************************************************

		// add our blocks to the blockchain ArrayList:

		blockchain.add(new Block("Hi i am the first block", "0"));
		System.out.println("Trying to mine block 1...");
		blockchain.get(0).mineBlock(difficulty);

		blockchain.add(new Block("Hi i am second block", blockchain.get(blockchain.size() - 1).hash));
		System.out.println("Trying to mine block 2...");
		blockchain.get(1).mineBlock(difficulty);

		blockchain.add(new Block("Hi i am third block", blockchain.get(blockchain.size() - 1).hash));
		System.out.println("Trying to mine block 3...");
		blockchain.get(2).mineBlock(difficulty);

		System.out.println("\nBlockchain is valid : " + isChainValid());

		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nThe Block Chain :");
		System.out.println(blockchainJson);

		// *************************************************************************************

		// Setup Bouncy castle as a Security Provider
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		//Create the new wallets
		walletA = new Wallet();
		walletB = new Wallet();
		//Test public and private Key
		System.out.println("Private & Public keys");
		System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
		System.out.println(StringUtil.getStringFromKey(walletA.publicKey));
		
		//create a test transaction from walletA from from wallletB
		Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, 5, null);
		transaction.generateSignature(walletA.privateKey);
		
		//verify the signature works and verify it from the public key
		System.out.println("Is signature verified");
		System.out.println(transaction.verifiySignature());
	}

	// Lets create an isChainValid() Boolean method in the NoobChain class,
	// that will loop through all blocks in the chain and compare the hashes.

	public static Boolean isChainValid() {

		Block currentBlock;
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');

		// loop through blockChain to check hashes :
		for (int i = 1; i < blockchain.size(); i++) {

			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i - 1);

			// compare registerd hash and calculated hash
			if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("current hashes are not equal");
				return false;
			}

			// compare previous hash and registered previous hash
			if (!previousBlock.hash.equals(currentBlock.previousHash)) {
				System.out.println("Previous Hashes are not equal");
				return false;
			}

		}

		return true;

	}
}
