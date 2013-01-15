package org.silix.the9ull.microbit.model;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.zip.CRC32;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/*
 * Singleton. This class is a wrapper of Bitcoin,
 * moreover it implements persistence transactions.
 */
public class Transactions {

	static Transactions instance = null;
	
	private static final String MINIMUMFEE = "0.005"; // Maximum minimum fee
	
	Bitcoin bc;
	
	static private BigDecimal fee = null;
	
	private Transactions() throws IOException {
		
		Bitcoin.readRpcCredentials();
		bc = new Bitcoin(false);
		
	}
	
	static public Transactions getInstance() throws IOException {
		if(instance==null)
			instance = new Transactions();
		return instance;
	}
	
	public boolean sendtouser(UserP from, UserP to, BigDecimal amount, Session session) {
		boolean ok=false;
		BigDecimal fee;
		String fee_s = PersistenceUtility.dictGet("internalfee", session);
		
		if(fee_s==null)
			fee = new BigDecimal(0);
		else
			fee = new BigDecimal(fee_s);
		
		if(amount.add(fee).compareTo(from.getFund()) <= 0){
			System.out.println("Transaction: #55");
			from.setFund(from.getFund().subtract(amount).subtract(fee));
			to.setFund(to.getFund().add(amount));
			
			HistoryP history = new HistoryP();
			history.setFrom(from);
			history.setTo(to);
			history.setHowmuch(amount);
			history.setWhen(new Date());
			if(fee.signum()!=0) //fee!=0
				history.setFee(fee);
			
			session.update(from);
			session.update(to);
			session.save(history);
			ok=true;
		}
		return ok;
	}

	public Tx sendtoaddress(UserP from, String address, BigDecimal amount, Session session) {
		String txid = null;
		Tx tx;
		
		if(fee==null) {
			String sfee = (String) PersistenceUtility.dictGet("minimumfee", session);
			if(sfee==null)
				sfee = MINIMUMFEE;
			fee = new BigDecimal(sfee);
			System.out.println("Transactions: Fee: "+fee);
		}
			
		if(bc.validateaddress(address)
				&& bc.getbalanceall().compareTo(amount.add(fee)) >= 0
				&& from.getFund().compareTo(amount.add(fee)) >= 0
				) {
			
			//
			txid = bc.sendtoaddress(address, amount);
			tx = new Tx();
			tx.setTxid(txid);
			
			Collection<Map<String,Object>> txs = bc.listtransactions(); //recent transactions
			
			for(Map<String,Object> t : txs){
				if(txid.equals((String)t.get("txid")) && 
						address.equals((String)t.get("address")) && 
						"send".equals((String)t.get("category"))) {
					
					System.out.println("Found tx "+t);
					tx.setTxid((String)t.get("txid"));
					tx.setAddress(address);
					tx.setAmount(new BigDecimal((Double)t.get("amount")).setScale(8,BigDecimal.ROUND_HALF_DOWN));
					// Get fee and make it positive
					tx.setFee(new BigDecimal((Double)t.get("fee")).setScale(8,BigDecimal.ROUND_HALF_DOWN));
					tx.setCategory("send");
					//time: ms
					Date time = new Date(1000 * (Long)t.get("time"));
					tx.setWhen(time);
					

					
					//update fee
					System.out.println("Fees: prev: "+fee+" next: "+tx.getFee());
					if(!fee.equals(tx.getFee()) && tx.getFee().signum()!=0){
						fee = tx.getFee();
						PersistenceUtility.dictSet("minimumfee", ""+fee, session);
						System.out.println("Fee updated to "+fee);
					}

					// Insert History
					HistoryP history = new HistoryP();
					history.setFrom(from);
					history.setTo(from);
					
					history.setWhen(time);
					history.setHowmuch(tx.getAmount());
					history.setFee(fee); //equals to tx.getFee()
					
					CRC32 hash = new CRC32();
					hash.update(((String)tx.getTxid()).getBytes());
					history.setTxidcrc(hash.getValue());
					
					//update fund
					from.setFund(from.getFund().add(fee).add(tx.getAmount()));
					
					session.save(history);
					session.update(from);
					
					break;
				}
			}
			
			return tx;
		}
		
		System.out.println("Transactions: sendtoaddress: Transaction infeasible");
		System.out.println("Valid address: "+bc.validateaddress(address));
		System.out.println("Server Balance: "+bc.getbalanceall());
		System.out.println("User Balance: "+from.getFund());
		System.out.println("Amount+fee: "+amount.add(fee));
		
		return null;
	}
	

	public Map<String,BigDecimal> updatefunds(boolean all,Session session) {
		
		Map<String,BigDecimal> funds = new HashMap<String,BigDecimal>(); // address, new fund
		String lastblock = null;
		Query q;
		
		if(!all)
			lastblock = PersistenceUtility.dictGet("lastblock",session);
		//System.out.println(lastblock);
		
		//
		//session.update(dict);
		
		
		Map<String,Object> listsince = bc.listsinceblock(lastblock); // transactions, lastblock
		Collection<Map<String,Object>> transactions = (Collection<Map<String,Object>>)listsince.get("transactions");
		lastblock = (String)listsince.get("lastblock");
		
		for(Map<String,Object> tx : transactions) {
			if("receive".equals((String)tx.get("category"))) {
				HistoryP history = new HistoryP();
				BigDecimal howmuch = new BigDecimal((Double)tx.get("amount")).setScale(8,BigDecimal.ROUND_HALF_DOWN); 
				history.setHowmuch(howmuch);
				CRC32 hash = new CRC32();
				hash.update(((String)tx.get("txid")).getBytes());
				history.setTxidcrc(hash.getValue());
				
				//Object x = tx.get("timereceived");
				//System.out.println(x);
				Date timereceived = new Date(1000 * (Long)tx.get("timereceived"));
				history.setWhen(timereceived);
				
				String address = (String)tx.get("address");
				q = session.createQuery("from UserP user where user.deposit_address='"+address+"'");
				if(q.list().size()==0) {
					System.out.println("Unknown deposit_address: "+address);
					// Save new funds only in funds variable
					funds.put(address, new BigDecimal((Double)tx.get("amount")).setScale(8,BigDecimal.ROUND_HALF_DOWN));
					continue;
				}
				UserP user = (UserP)q.list().get(0);
				System.out.println("User id: "+user.getId());
				
				history.setFrom(user);
				history.setTo(user);
				
				// Is history object already persistance?
				q = session.createQuery("from HistoryP h where h.txidcrc="+history.getTxidcrc());
				if(q.list().size()>0){
					//xSystem.out.println("Oh, crc trovato!");
					boolean exists = false;
					for(Object o : q.list()) {
						HistoryP h = (HistoryP)o;
						//System.out.println("for interno :o "+h);
						//System.out.println(""+history);
						
						if( h.getFrom().equals(history.getFrom()) &&
								h.getTo().equals(history.getTo()) &&
								h.getHowmuch().equals(history.getHowmuch()) &&
								h.getWhen().compareTo(history.getWhen())==0
								) {
							exists = true;
							continue;
						}
						
					}
					if(exists)
						continue;
				}
				// update howmuch and save history and user. Only if object is not into db
				user.setFund(user.getFund().add(howmuch));
					
				session.save(history);
				session.update(user);	
				
			}
			
		}
		
		PersistenceUtility.dictSet("lastblock", lastblock, session);
		PersistenceUtility.dictSet("updatefundstime", "" + new Date().getTime(), session);
		PersistenceUtility.dictSet("updatefundshumantime", "" + new Date(), session);
		
		return funds;
	}
	
	public boolean isAddressValid(String address) {
		return bc.validateaddress(address);
	}
	
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		 
		Transactions bt = Transactions.getInstance();
		
		//Bitcoin bc = bt.bc;
		//System.out.println( bc.validateaddress("n2NAjt6oZBPZY5bhTGEBHkENTDtrpYc7fS") );
		//System.out.println( bc.validateaddress("14UWwaX1BdCHEfXQF9KqivMmK3EbWjHDoV") );
		//System.out.println(bc.listaccounts());
		//System.out.println(bc.getb`ckcount());
		//System.out.println(bc.listtransactions());
		//System.out.println(bc.getnewaddress(null));
		

		//System.out.println(bc.getbalanceall());
		Session session = SingletonSessionFactory.getSession();
		Transaction htx = session.beginTransaction();
		UserP from, to;
		from = (UserP) session.load(UserP.class, 1);
		String address = "mvBXcQ3ZAPsbZh8kg9G3X1mRg8g8bwJXCR";
		Tx tx = bt.sendtoaddress(from, address, new BigDecimal(0.00234), session);
		//sendtoaddress("mrZQpbfo4RqEoBeJw4u5E9u6CEJsycjEM5", new BigDecimal(0.00123));
		System.out.println(tx);
		htx.commit();
		
		htx = session.beginTransaction();
		from = (UserP) session.load(UserP.class, 1);
		to = (UserP) session.load(UserP.class, 2);
		System.out.println("To user: "+bt.sendtouser(from, to, new BigDecimal(10.12345678), session));
		htx.commit();
	}

}
