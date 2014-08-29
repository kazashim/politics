package com.example.politics;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseExample {
	public static final String KEY_ID="id";
	public static final String KEY_NAME="name";
	public static final String KEY_PASS="pass";
	
	public static final String DB_NAME="test";
	public static final String DB_TABLE="data";
	public static final int DB_VER=1;
	
	private DbHelp ourHelp;
	private Context ourContext;
	private SQLiteDatabase database;
	
	private static class DbHelp extends SQLiteOpenHelper {

		public DbHelp(Context context) {
			super(context, DB_NAME, null, DB_VER);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(" CREATE TABLE " + DB_TABLE +" ( "+KEY_ID +
					" INTEGER PRIMARY KEY AUTOINCREMENT , "+KEY_NAME+" TEXT NOT NULL ,  " +
					KEY_PASS+ " TEXT ); ");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE );
			onCreate(db);
		}
		
		
	}
	public DatabaseExample(Context c)
	{
		ourContext=c;
		
	}
	public DatabaseExample open()
	{
		ourHelp=new DbHelp(ourContext);
		database=ourHelp.getWritableDatabase();
		return this;

	}
	public void close()
	{
		ourHelp.close();
	}
	public long enter(String user, String pass) {
		// TODO Auto-generated method stub
		ContentValues cv=new ContentValues();
		cv.put(KEY_NAME, user);
		cv.put(KEY_PASS, pass);
		return database.insert(DB_TABLE, null, cv);
		
	}

}
