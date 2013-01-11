package org.silix.the9ull.microbit.model;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PersistenceUtility {

	private PersistenceUtility() {
	}
	
	public static String dictGet(String key, Session session) {
		DictP pair = (DictP) session.get(DictP.class, key);
		if(pair!=null)
			return pair.getValue();
		return null;
	}
	
	public static void dictSet(String key, String value, Session session) {
		DictP pair = (DictP) session.get(DictP.class, key);
		if(pair==null){
			pair = new DictP();
			pair.setKey(key);
		}
		pair.setValue(value);
		session.saveOrUpdate(pair);
	}
	
	public static UserP newUser(String email, String password, Session session) throws ConnectException {
		// TODO: move this code out of here
		int id_user;
		Bitcoin bc = new Bitcoin(true);
		UserP user = new UserP();
		
		user.setEmail(email);
		user.setFund(new BigDecimal(0.0));
		user.setPassword("Just a second");
		id_user = (Integer) session.save(user);
		System.out.println("User_id: "+id_user);
		user.setPassword(SHA1.HMAC_digest(""+id_user,password));
		System.out.println("Password: "+user.getPassword());
		
		//deposit address
		String daddress = bc.getnewaddress("user"+id_user);
		System.out.println("New address: "+daddress);
		
		user.setDeposit_address(daddress);
		session.update(user);
		return user;
	}
	
	public static void resetPasswords(Session session) {
		Query q = session.createQuery("select usr.id from UserP usr");
		@SuppressWarnings("unchecked")
		List<Integer> l = (List<Integer>) q.list();
		UserP u;
		for(int i : l) {
			u = (UserP) session.load(UserP.class, i);
			u.setPassword(SHA1.HMAC_digest(""+i,"1"));
			session.update(u);
		}
	}
	
	static public void main(String[] args) {
		Session session = SingletonSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		//resetPasswords(session); // All passwords → "1"
		tx.commit();
		SingletonSessionFactory.closeSession(session);
	}
}
