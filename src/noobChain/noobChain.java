package noobChain;

import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class noobChain {

	public static ArrayList<Block> blockChain = new ArrayList<Block>();
	public static int difficulty = 5;
	

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

		blockChain.add(new Block("Hi i am the first block", "0"));
		System.out.println("Trying to mine block 1...");
		blockChain.get(0).mineBlock(difficulty);
		
		blockChain.add(new Block("Hi i am second block", blockChain.get(blockChain.size() - 1).hash));
		System.out.println("Trying to mine block 2...");
		blockChain.get(1).mineBlock(difficulty);
		
		blockChain.add(new Block("Hi i am third block", blockChain.get(blockChain.size() - 1).hash));
		System.out.println("Trying to mine block 3...");
		blockChain.get(2).mineBlock(difficulty);
		
		System.out.println("\nBlockchain is valid : " + isChainValid());

		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
		System.out.println("\nThe Block Chain :");
		System.out.println(blockchainJson);

	}

	// Lets create an isChainValid() Boolean method in the NoobChain class,
	// that will loop through all blocks in the chain and compare the hashes.

	public static Boolean isChainValid() {
		
		Block currentBlock;
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		// loop through blockChain to check hashes  :
		for( int i=1; i < blockChain.size(); i++) {
			
			currentBlock = blockChain.get(i);
			previousBlock = blockChain.get(i-1);
			
			// compare registerd hash and calculated hash
			if(!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("current hashes are not equal");
				return false;
			}
			
			//compare previous hash and registered previous hash
			if( !previousBlock.hash.equals(currentBlock.previousHash)) {
				System.out.println("Previous Hashes are not equal");
				return false;
			}
			
			
		}
		
		return true;

	}
}
