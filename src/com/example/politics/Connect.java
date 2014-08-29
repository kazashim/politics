package com.example.politics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import android.util.Log;

public class Connect {
	Connect() {
try {
	Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
	Connection c= DriverManager.getConnection("jdbc:mysql://localhost/ducat38", "ducat38", "ducat38");
	
	String t1="lalu";
	int t2=420;
    Log.w("Connection","open");
	Statement s = c.createStatement();
	s.executeUpdate("create table emp162(name varchar(20), salary int)" );
	s.close();	
	
} catch(Exception e){}
}
}
