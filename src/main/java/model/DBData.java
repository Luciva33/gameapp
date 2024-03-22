package model;

import java.io.Serializable;
import java.util.List;


public class DBData implements Serializable{
	private List<Account> results;
	
	public DBData() {}
	public DBData(List<Account> results) {
		this.results = results;
	}
	public List<Account> getResults() {
		return results;
	}
}
