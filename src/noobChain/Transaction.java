package noobChain;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

public class Transaction {
	
	public String transactionId; // this is also the hash of the transaction
	public PublicKey sender; 	// sender address public key
	public PublicKey reciepient; //Recipient address/public key
	public float value;
	public byte[] signature; //this is to prevent anybody else from spending funds from our wallet
	

	public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
	public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();
	
	private static int sequence = 0; //a rough count of how many transaction have been generated.

	
	
	//Constructor :
	
	public Transaction(String transactionId, PublicKey sender, PublicKey reciepient, float value, byte[] signature,
			ArrayList<TransactionInput> inputs, ArrayList<TransactionOutput> outputs) {
		super();
		this.transactionId = transactionId;
		this.sender = sender;
		this.reciepient = reciepient;
		this.value = value;
		this.signature = signature;
		this.inputs = inputs;
		this.outputs = outputs;
	}
	
	//This Calculates the transaction hash  ( which will be used as its id)
	
	private String calaculateHash() {
		sequence ++; //increase the sequence to avoide 2 identical transaction having same hash
		return StringUtil.applySha256(
				StringUtil.getStringFromKey(sender) +
				StringUtil.getStringFromKey(reciepient) +
				Float.toString(value) +
				sequence
				);
	}
	
	//Signs all the data we dont wish to tamperd with.
	
	public boolean generateSignature(PrivateKey privateKey) {
		String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + Float.toString(value) ;
		return StringUtil.verifyECDSASig(sender, data, signature);
	}
	
}
