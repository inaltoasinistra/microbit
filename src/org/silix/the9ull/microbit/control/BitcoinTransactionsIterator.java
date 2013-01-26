package org.silix.the9ull.microbit.control;

import java.net.ConnectException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import com.mysql.jdbc.Buffer;

public class BitcoinTransactionsIterator implements Iterator<Map<String,Object>> {

	private int user_id, from;
	private Bitcoin bc;
	private Collection<Map<String,Object>> buffer;
	private Iterator<Map<String,Object>> bufferi;
	
	private final static int COUNT = 1;
	
	public BitcoinTransactionsIterator(int user_id, Bitcoin bc) throws BitcoinConnectionException {
		this.user_id = user_id;
		this.bc = bc;
		fillBuffer();
		//System.out.println("!!! Inited the iterator");
	}
	
	private void fillBuffer() throws BitcoinConnectionException {
		//System.out.println("!!! Filling the buffer");
		buffer = bc.listtransactions(user_id,COUNT,from);
		//System.out.println("!!! Size new buffer: "+buffer.size());
		bufferi = buffer.iterator();
		from += buffer.size();
	}
	
	@Override
	public boolean hasNext() {
		//System.out.println("!!! Next");
		if(bufferi.hasNext())
			return true;
		//System.out.println("!!! Next 2");
		try {
			fillBuffer();
		} catch (BitcoinConnectionException e) {
			return false; // hasNext can't throws exceptions
		}
		//System.out.println("!!! Next 3");
		if(buffer.isEmpty())
			return false;
		else
			return true;
	}

	@Override
	public Map<String, Object> next() {
		//System.out.println("!!! Next");
		Map<String, Object> tx = bufferi.next();
		if(tx!=null) {
			return tx;
		} else {
			try {
				fillBuffer();
			} catch (BitcoinConnectionException e) {
				return null; // next can't throws exceptions
			}
			return bufferi.next();
		}
	}

	@Override
	public void remove() throws IllegalStateException {
		
	}

	public static void main(String []args) throws BitcoinConnectionException {
		BitcoinTransactionsIterator bt = null;
		bt = new BitcoinTransactionsIterator(1, new Bitcoin(true));
		
		while(bt.hasNext()) {
			Map<String,Object> t = bt.next();
			System.out.println(t);
		}
	}
	
}
