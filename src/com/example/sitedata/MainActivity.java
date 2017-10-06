package com.example.sitedata;

import java.sql.SQLException;

//import DB.DB_Connect;
//import dbjar.DB_Connect;
import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.EditText;
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("inside oncreater___________");
		setContentView(R.layout.activity_main);
		ApplicationInfo ai;
		try {
			ai = this.getPackageManager().getApplicationInfo(this.getPackageName(), PackageManager.GET_META_DATA);
			//String username = ai.metaData.get("username").toString();
			//String password = (String) ai.metaData.get("password");
			//String url =  (String) ai.metaData.get("url");
			//int colCount = (Integer) ai.metaData.get("ColCount");
			String fieldMap[ ] = new String[/*colCount*/2];
			/*
			for(int i=1;i<colCount;i++)
			{
				fieldMap[i-1] = (String) ai.metaData.get(""+i);
			}
			System.out.println(fieldMap);*/
			try {
				//DB_Connect dbObj = new DB_Connect(url,username,password,fieldMap);
				DB_Connect dbObj = new DB_Connect("jdbc:mysql://localhost/siteDB","root","tiger",fieldMap);
				String keyWord = ((EditText)this.findViewById(R.id.ed)).getText().toString();
				((EditText)this.findViewById(R.id.ed)).setText(dbObj.search(keyWord).toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch (NameNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
		
}
