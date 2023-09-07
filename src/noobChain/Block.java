package noobChain;

import java.util.Date;

public class Block {

	public String hash;
	public String previousHash;
	private String data; // our data will be sample message
	private long timeStamp; // as number of millisecond since 1/1/1970.
	
	private int nonce;

	// Block Constructor

	public Block(String hash, String previousHash) {
	
		this.hash = calculateHash();  //Making sure we do this after we set the other values.
		this.previousHash = previousHash;
		this.data = data;
		this.timeStamp = new Date().getTime();
	}


	// use our applySha256 helper,
	// in a new method in the Block class, to calculate the hash
	
	//calculate new hash based on blocks contents

	public String calculateHash() {

		String calculatedhash = StringUtil.applySha256(
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) +
				data);
		return calculatedhash;

	}


	// and lets add this method to the Block constructor…

	/*
	 * this.hash = calculateHash(); //Making sure we do this after we set the other
	 * values. 
	 */
	
	public void mineBlock( int difficulty) {
		String target = new String(new char [difficulty]).replace('\0', '0'); // create a string with difficulty * "0"
		while (!hash.substring(0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		
		System.out.println("Block Mined....." + hash);
	}

}
