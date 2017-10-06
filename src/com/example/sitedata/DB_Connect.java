package com.example.sitedata;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.sql.rowset.BaseRowSet;
import javax.sql.rowset.CachedRowSet;

import com.sun.javafx.collections.MappingChange.Map;
import com.sun.rowset.CachedRowSetImpl;
import com.sun.rowset.internal.Row;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;


/*
 * This class uses a disconnected architecture so 
 * the developer using the class must initialize
 * the class accordingly
 * 
 */
public class DB_Connect  extends HashMap<String, Object> implements DB_Handler
{
		private String url,  username,  pass;
		private CachedRowSetImpl crs;
		private String[] fieldMap;
			
		public DB_Connect(String url, String username, String pass, String[] arr) throws SQLException {
		
			this.url = url;
			this.username = username;
			this.pass = pass;
			this.fieldMap = arr;
			crs = new CachedRowSetImpl();
			crs.setUrl(url);
			crs.setUsername(username);
			crs.setPassword(pass);
		
				
		}
		public  CachedRowSetImpl establishConnectionWithDB() throws SQLException 
		{
			//make connection again in the same crs object
		 crs = new CachedRowSetImpl();
		crs.setUrl(url);
		crs.setUsername(username);
		crs.setPassword(pass);
		return crs;
		}

	@Override
	public ArrayList<HashMap<String, Object>> view(String siteName) throws SQLException {
		// TODO Auto-generated method stub
		//crs = establishConnectionWithDB();
		
		crs.setCommand("select*from SiteDetails where siteName='"+siteName+"'");
		crs.execute();
		
		return convertToArrayListOfHashMaps(crs);
		
	}

	private ArrayList<HashMap<String, Object>> convertToArrayListOfHashMaps(CachedRowSetImpl crsv) throws SQLException {
		// TODO Auto-generated method stub
		
		ResultSetMetaData md = crsv.getMetaData();
		ArrayList<HashMap<String,Object>> ls = new ArrayList<HashMap<String, Object>>();
		Collection<Row> c = (Collection<Row>) crsv.toCollection();
		for(Row row : c)
		{
			 HashMap<String, Object> hm = new HashMap<String, Object>();
			 for (int i = 1; i <= md.getColumnCount(); i++) {
			        hm.put(md.getColumnName(i), row.getColumnObject(i));
			    }
			 ls.add(hm);
			 
		}
		
		return ls;
	}
	@Override
	public boolean update(HashMap packetData) throws SQLException {
		// TODO Auto-generated method stub
		crs.next();
		for(String col : fieldMap)
		{
			String attribute = packetData.get(col).toString();
			crs.updateString(col, attribute);
		}
		crs.updateRow();	
		crs.acceptChanges();
		return true;
		
	}
	@Override
	public  ArrayList<HashMap<String, Object>> search(String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		crs.setCommand("select*from SiteDetails where siteName like '"+"%"+keyWord+"%'");
		crs.execute();
		return convertToArrayListOfHashMaps(crs);
	}

	@Override
	public boolean equals(java.lang.Object o) {
		// TODO Auto-generated method stub
		return this.toString().equals(o.toString());
		
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.toString().hashCode();
	}
	
	

}
