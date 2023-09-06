package noobChain;

import java.util.ArrayList;

public class noobChain {
	
	public static ArrayList<Block> blockChain = new ArrayList<Block>();

	public static void main(String[] args) {
		
		
		
		/*
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
		
		//add our blocks to the blockchain ArrayList:
		
		Block genesisBlock = new Block("Hi i am the first Block", "0");
		System.out.println("Hash for Block 1 : " + genesisBlock.hash);
		
		Block secondBlock = new Block("Hi i am second Block", genesisBlock.hash);
		System.out.println("Hash for Block 2 : " + secondBlock.hash);
		
		Block thirdBlock = new Block("Hey im the third block",secondBlock.hash);
		System.out.println("Hash for block 3 : " + thirdBlock.hash);
	}

}
