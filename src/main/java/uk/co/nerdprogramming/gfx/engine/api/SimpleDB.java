package uk.co.nerdprogramming.gfx.engine.api;

import java.util.HashMap;

public class SimpleDB {
	private HashMap<String, String> db;
	public SimpleDB() {
		db  = new HashMap<String, String>();
	}
	
	public SimpleDB(HashMap<String, String> src) {
		db = src;
	}
	
	public boolean ContainsEntry(String key) {
		return db.containsKey(key);
	}
	
	public String GetString(String key) {
		if(db.containsKey(key)) {
			return db.get(key);
		} else {
			System.err.println("[SimpleDB] Database Doesn't Contain Entry '"+key+"'");
			return "000";
		}
	}
	
	public void SetString(String key, String value) {
		db.put(key, value);
	}
	
	public int  GetInt(String key           ) { return Integer.parseInt(GetString(key)); }
	public void SetInt(String key, int value) { SetString(key, ""+value);                }
} 

