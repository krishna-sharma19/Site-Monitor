package com.example.sitedata;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface DB_Handler {
	
	public boolean update(HashMap packetData) throws SQLException;
	public ArrayList<HashMap<String, Object>> search(String keyWord) throws SQLException;
	public ArrayList<HashMap<String, Object>> view(String siteName) throws SQLException;

}
