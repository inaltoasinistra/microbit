package org.silix.the9ull.microbit.controlinterface;

import java.util.List;

public class HTMLUtilities {

	static String printTable(List<List<String>> table, List<String> header) {
		String ret = "<table>";
		if(header!=null){
			ret += "<tr>";
			for(String s : header)
				ret += "<th>"+s+"</th>";
			ret += "</tr>";
		}
		for(List<String> l : table) {
			ret += "<tr>";
			for(String s : l)
				ret += "<td>"+s+"</td>";
			ret += "</tr>";
		}
		return ret+"</table>";
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
