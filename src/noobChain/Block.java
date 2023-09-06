package noobChain;

import java.util.Date;

public class Block {

	public String hash;
	public String previousHash;
	private String data; // our data will be sample message
	public long timeStamp; // as number of millisecond since 1/1/1970.

	// Block Constructor

	public Block(String hash, String previousHash) {
		super();

		this.previousHash = previousHash;
		this.data = data;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash(); // making sure we do this after we set other values
	}

	// use our applySha256 helper,
	// in a new method in the Block class, to calculate the hash

	public String calculateHash() {

		String calculatedhash = StringUtil.applySha256(previousHash + Long.toString(timeStamp) + data);
		return calculatedhash;

	}

	// and lets add this method to the Block constructor…

	/*
	 * this.hash = calculateHash(); //Making sure we do this after we set the other
	 * values. 
	 */

}
