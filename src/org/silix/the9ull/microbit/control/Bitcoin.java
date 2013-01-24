package org.silix.the9ull.microbit.control;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.Authenticator;
import java.net.ConnectException;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.stringtree.json.JSONReader;// test only
import org.stringtree.json.JSONValidatingReader;
import org.stringtree.json.JSONWriter;

import com.googlecode.jj1.JsonRpcException;
import com.googlecode.jj1.ServiceProxy;

public class Bitcoin {

	static String rpcuser;
	static String rpcpassword; 
	
	private int minconf = 2;
	private ServiceProxy proxy;

	public Bitcoin(boolean authentication) throws BitcoinConnectionException {
		
		System.out.println("new Bitcoin()");
	
		if(authentication)
			try {
				if(!Bitcoin.readRpcCredentials()){
					System.out.println("Credential not found");
				}
			} catch (IOException e) {
				System.out.println("Credential not found");
			}
		
		System.out.println("Cred:"+rpcuser+" "+rpcpassword);
		
		// Set Authenticator
		Authenticator.setDefault(new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication (rpcuser, rpcpassword.toCharArray());
		    }
		});
		
		proxy = new ServiceProxy("http://localhost:8332");
		System.out.println("Bitcoin: init done");
	}
	
	boolean validateaddress(String address) throws BitcoinConnectionException {
		try {
			@SuppressWarnings("unchecked")
			HashMap<String,Object> result = (HashMap<String,Object>) proxy.call("validateaddress",address);
			return (Boolean)result.get("isvalid");
		} catch (JsonRpcException e) {
			throw new BitcoinConnectionException();
		}
	}
	
	public String sendtoaddress(String address, BigDecimal amount) throws BitcoinConnectionException {
		try {
			return (String) proxy.call("sendtoaddress", address, amount, "From Microbit (com.googlecode.jj1), sendtoaddress");
		} catch (JsonRpcException e) {
			throw new BitcoinConnectionException();
		}
	}
	public String sendtoaddress(int user_id, String address, BigDecimal amount) throws BitcoinConnectionException {
		try {
			return (String) proxy.call("sendfrom", "user"+user_id, address, amount);
		} catch (JsonRpcException e) {
			throw new BitcoinConnectionException();
		}
	}
	
	
	public String getnewaddress(String account) throws BitcoinConnectionException {
		String result;
		try {
			if(account==null)
				result = (String) proxy.call("getnewaddress");
			else
				result = (String) proxy.call("getnewaddress", account);
		} catch (JsonRpcException e) {
			throw new BitcoinConnectionException();
		}
		return result;
	}
	

	double getbalance(String account) throws BitcoinConnectionException {
		try {
			return (Double) proxy.call("getbalance",account,minconf);
		} catch (JsonRpcException e) {
			throw new BitcoinConnectionException();
		}
	}
	
	String getblockhash(long index) throws BitcoinConnectionException {
		try {
			return (String) proxy.call("getblockhash",index);
		} catch (JsonRpcException e) {
			throw new BitcoinConnectionException();
		}
	}
	
	@SuppressWarnings("unchecked")
	Map<String,Object> listsinceblock(String hash) throws BitcoinConnectionException {
		Map<String,Object> result; // "transactions" : {tx obj (see getTx)}, "lastblock" : "hash"
		try {
			if(hash==null)
				result = (Map<String,Object>)proxy.call("listsinceblock");
			else
				result = (Map<String,Object>)proxy.call("listsinceblock", hash);
		} catch (JsonRpcException e) {
			throw new BitcoinConnectionException();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	Map<String,Object> getblock(String hash) throws BitcoinConnectionException {
		try {
			return (Map<String,Object>)proxy.call("getblock",hash);
		} catch (JsonRpcException e) {
			throw new BitcoinConnectionException();
		}
	}
	Map<String,Object> getblock(long index)  throws BitcoinConnectionException {
		return getblock(getblockhash(index));
	}
	
	public BigDecimal getbalance() throws BitcoinConnectionException {
		try {
			return new BigDecimal((Double)proxy.call("getbalance"));
		} catch (JsonRpcException e) {
			throw new BitcoinConnectionException();
		}
	}
	
	@Deprecated
	public BigDecimal getbalanceall() throws BitcoinConnectionException {
		BigDecimal balance = new BigDecimal(0.0);
		for(double d: listaccounts().values()){
			System.out.println("!!! "+d);
			balance = balance.add(new BigDecimal(d));
			System.out.println("!!! Balance "+balance);
		}
		return balance.setScale(8,BigDecimal.ROUND_HALF_DOWN);
	}
	
	
	@SuppressWarnings("unchecked")
	HashMap<String,Double> listaccounts() throws BitcoinConnectionException {
		try {
			return (HashMap<String,Double>) proxy.call("listaccounts",minconf);
		} catch (JsonRpcException e) {
			throw new BitcoinConnectionException();
		}
	}
	
	void movetoaccount(BigDecimal amount, int user_id) throws BitcoinConnectionException {
		try {
			proxy.call("move","","user"+user_id,amount);
		} catch (JsonRpcException e) {
			throw new BitcoinConnectionException();
		}
	}
	
	@SuppressWarnings("unchecked")
	Collection<Map<String,Object>> listtransactions() throws BitcoinConnectionException {
		try {
			return (Collection<Map<String,Object>>) proxy.call("listtransactions","",50);
		} catch (JsonRpcException e) {
			throw new BitcoinConnectionException();
		}
	}

	@SuppressWarnings("unchecked")
	Collection<Map<String,Object>> listtransactions(int user_id, int count, int from) throws BitcoinConnectionException {
		try {
			return (Collection<Map<String,Object>>) proxy.call("listtransactions","user"+user_id,count,from);
		} catch (JsonRpcException e) {
			throw new BitcoinConnectionException();
		}
	}
	Collection<Map<String,Object>> listtransactions(int user_id) throws BitcoinConnectionException {
		return listtransactions(user_id, 50, 0);
	}

	@SuppressWarnings("unchecked")
	Map<String,Object> lasttransaction(int user_id) throws BitcoinConnectionException {
		Collection<Map<String,Object>> ret;
		try {
			ret = (Collection<Map<String,Object>>) proxy.call("listtransactions","user"+user_id,1);
		} catch (JsonRpcException e) {
			throw new BitcoinConnectionException();
		}
		if(ret==null || ret.isEmpty())
			return null;
		return new ArrayList<Map<String, Object>>(ret).get(0);
	}
	
	public long getblockcount() throws BitcoinConnectionException {
		try {
		return (Long) proxy.call("getblockcount");
		} catch (JsonRpcException e) {
			throw new BitcoinConnectionException();
		}
	}
	
	public static boolean readRpcCredentials() throws IOException {
	
		String home = System.getProperty("user.home");
		
	    FileInputStream fstream = new FileInputStream(home+"/.bitcoin/bitcoin.conf");
	    DataInputStream in = new DataInputStream(fstream);
	    BufferedReader br = new BufferedReader(new InputStreamReader(in));
	    String strLine;
	    boolean bu=false, bp=false;
	    while ((strLine = br.readLine()) != null){
	    	if(strLine.startsWith("rpcuser=")){
	    		rpcuser = strLine.substring(8);
	    		bu=true;
	    	} else if(strLine.startsWith("rpcpassword=")) {
	    		rpcpassword = strLine.substring(12);
	    		bp=true;
	    	}
	    }
	
	    in.close();
		
	    //System.out.println(rpcuser+" "+rpcpassword);
		
	    return bu && bp;
	}

	public static Object jsonToJava(String json) {
		//debug
		JSONReader reader = new JSONValidatingReader();
		Object result = reader.read(json);
		
		//System.out.println("JSONReader result is " + result +
		//		" of class " + result.getClass());
		
		return result;
	}
	public static String javaToJson(Object o){
		//debug
		JSONWriter writer = new JSONWriter();
		return writer.write(o);
	}
	
	/**
	 * @param args
	 * @throws BitcoinConnectionException 
	 * @throws ConnectException 
	 */
	public static void main(String[] args) {
		
		try {
			Bitcoin.readRpcCredentials();
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		
		Bitcoin bc = null;
		try {
			bc = new Bitcoin(false);
		} catch (BitcoinConnectionException e) {
			System.out.println("Bitcoin server connection problem");
			return;
		}
		
		//System.out.println(bc);
		//System.out.println( bc.validateaddress("n2NAjt6oZBPZY5bhTGEBHkENTDtrpYc7fS") );
		//System.out.println( bc.validateaddress("14UWwaX1BdCHEfXQF9KqivMmK3EbWjHDoV") );
		//System.out.println(bc.listaccounts());
		//System.out.println(bc.getb`ckcount());
		//System.out.println(bc.listtransactions());
		//System.out.println(bc.getnewaddress(null));
		

		//System.out.println(bc.getbalanceall());
		
		Random r = new Random();
		//Tx tx = bc.sendtoaddress("mrZQpbfo4RqEoBeJw4u5E9u6CEJsycjEM5", new BigDecimal(r.nextFloat()/1000.0));
		
		
		//System.out.println(""+tx.getAmount().multiply(new BigDecimal(-1))+" + "+
		//		tx.getFee().multiply(new BigDecimal(-1))+" = "+
		//		tx.getAmount().add(tx.getFee()).multiply(new BigDecimal(-1)));
		//System.out.println(bc.getbalanceall());
		
		
		//bc.updatefunds(false);

		try {
			System.out.println(bc.sendtoaddress("mrZQpbfo4RqEoBeJw4u5E9u6CEJsycjEM5", new BigDecimal("0.2")));
		} catch (BitcoinConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(Bitcoin.jsonToJava("{\"minconf\":\"2\"}"));
		
		  
	}
	

}
