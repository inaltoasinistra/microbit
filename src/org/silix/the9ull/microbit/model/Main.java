package org.silix.the9ull.microbit.model;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.Date;
import java.util.Random;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/* Empty DB
delete from `History`;
delete from `Contact`;
delete from `User`;
delete from `Dict`;

 */

public class Main {

	public Main() {
	}
	
	/**
	 * @param args
	 * @throws BitcoinConnectionError 
	 */
	public static void main(String[] args) throws BitcoinConnectionError {
		///
		Session session = null;
		SessionFactory sessionFactory;
		
		try {
			if(!Bitcoin.readRpcCredentials()){
				System.out.println("Credentials missed!");
				return;
			}
		} catch (IOException e1) {
			System.out.println("Credentials missed!");
			return;
		}
				
		try 
		{
			try
			{
				sessionFactory = SingletonSessionFactory.getSessionFactory();
			}
			catch (Throwable ex)
			{
				System.err.println("Failed to create sessionFactory object."+ ex);
				throw new ExceptionInInitializerError(ex);
			}
			
			session = sessionFactory.openSession();

			Random r = new Random();
			
			Transaction tx = session.beginTransaction();//needed by newUser()
			UserP user = PersistenceUtility.newUser("user@anche.no", "pwd", session);
			
			
			//person.setId(0);
			
			System.out.println(session.save(user));
			
			ContactP contact = new ContactP();
			ContactP contact2 = new ContactP();
			HistoryP history = new HistoryP();
			
			contact.setUser(user);
			contact.setAddress("24UWwaX1BdCHEfXQF9KqivMmK3EbWjHDoV");
			contact.setAlias("Bho");
			
			Date d = new Date();
			System.out.println( d.toString() );
			
			BigDecimal b = new BigDecimal("10.022").add(new BigDecimal(".1234"/*piccoli ciccia*/));
			history.setFrom(user);
			history.setTo(user);
			history.setHowmuch(b);
			history.setWhen(new Date());
			user.setFund(user.getFund().add(b));
			
			
			session.update(user);//update fund
			
			System.out.println("Ancora iddì "+session.save(history));
			
			tx.commit();
			System.out.println("Done");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
			}
		
		///
		
		//Facciamo una select?
		sessionFactory = SingletonSessionFactory.getSessionFactory();
		session = sessionFactory.openSession();
		Query q = session.createQuery("from UserP usr where usr.id=100");
		UserP u = (UserP) q.list().get(0);
		Transaction tx = session.beginTransaction();
		Set<ContactP> contacts = u.getContacts();//new HashSet();
		
		Random r = new Random();
		for(int i=0 ; i<3 ; i++){
			ContactP cc = new ContactP();
			cc.setUser(u);
			cc.setAlias("G"+abs(r.nextInt()));
			cc.setAddress("2"+abs(r.nextInt()));
			contacts.add(cc);
			session.save(cc);
			System.out.println("→ "+cc.getAlias()+" "+cc.getAddress());
		}
		System.out.println(contacts.toString());
		//u.setContacts(contacts);
		session.saveOrUpdate(u);
		
		//Collections.sort(usrList);
		for(ContactP c : u.getContacts()){
			System.out.println("Contact : "+c.getAlias()+" "+c.getAddress());
		}
		tx.commit();
		session.close();
		
		Bitcoin bitcoin = new Bitcoin(false);
		
	}

	private static double abs(double a) {
		if(a>0)
			return a;
		return -a;
	}

	private static int abs(int a) {
		if(a>0)
			return a;
		return -a;
	}
}