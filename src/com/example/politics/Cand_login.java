package com.example.politics;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;


public class Cand_login extends Activity  implements OnClickListener {
	
    public static String filename= "Pref";
    public SharedPreferences id;
    public int count=0;
    public String cand_id,cand_name;
	JSONParser jsonParser = new JSONParser();
	private static String url_create_product = "http://10.0.2.2:80/android_connect/cand_front_page.php";
	private static String url_post = "http://10.0.2.2:80/android_connect/post.php";
	private static final String TAG_SUCCESS = "success";
	private ProgressDialog pDialog;
    public String msg[],time[],likes[];	
    Tab tab2;
    
    TextView t1,t2,t3,t4,t5;
    EditText update;
    Button post,see,pre;
    int success;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cand_login);
		id= getSharedPreferences("filename",0);
	    
		TabHost th =(TabHost) findViewById(R.id.tab);
	    th.setup();
	    
	    TabSpec specs= th.newTabSpec("tag1");
	    specs.setContent(R.id.tab1);   
	    specs.setIndicator("My Updates");
	    th.addTab(specs);
	    
	    specs= th.newTabSpec("tag2");
	    specs.setContent(R.id.tab2);   
	    specs.setIndicator("Others Updates");
	    th.addTab(specs);
	    
	    t1=(TextView) findViewById(R.id.tv1);
	    t2=(TextView) findViewById(R.id.tv2);
	    t3=(TextView) findViewById(R.id.tv3);
	    t4=(TextView) findViewById(R.id.tv4);
	    t5=(TextView) findViewById(R.id.tv5);
	    
	    
	    
	    update=(EditText) findViewById(R.id.etUpdates);
	   
	    post=(Button) findViewById(R.id.bPost);
	    see=(Button) findViewById(R.id.bSee);
	    pre=(Button) findViewById(R.id.bPre);
	    
	    post.setOnClickListener(Cand_login.this);
	    see.setOnClickListener(Cand_login.this);
	    pre.setOnClickListener(Cand_login.this);
	    
	    msg=new String[6];
	    time=new String[6];
	    likes=new String[6];
	    cand_id=""+id.getInt("id", 10);
		cand_name=id.getString("name","none");
		t1.setText(cand_id);
		t2.setText(cand_name);
	    new CreateNew().execute();
	    
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.bPost: new Post().execute();
			             
			            break;
		
		case R.id.bSee: count++;
		new CreateNew().execute();
		
		break;
		case R.id.bPre:
			if(count>0)
			count--;
			new CreateNew().execute();
			
			break;
		
		}
		
	}
	
	class CreateNew extends AsyncTask<String, String,String> {


		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Cand_login.this);
			pDialog.setMessage("Loading..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}


		protected String doInBackground(String... args) {

			
			try {
				
				id= getSharedPreferences("filename",0);
				cand_id=""+id.getInt("id", 10);
				cand_name=id.getString("name","none");
				
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("cand_id", cand_id));
				params.add(new BasicNameValuePair("seemore", ""+count));
				JSONObject json = jsonParser.makeHttpRequest(url_create_product,
						"POST", params);
				
				success = json.getInt(TAG_SUCCESS);
                //msg[1]=json.getString("message1") + success;
				if (success==1) {
	                   for(int i=1;i<=5;i++) {
						msg[i]=json.getString("message"+i);
	                   time[i]=json.getString("time"+i);
	                   likes[i]=json.getString("likes"+i);
	                   msg[i]="* " +cand_name +" at "+ time[i] +"\n"+msg[i]+"\n "+likes[i]+ "likes" ;}
				}  
				//} //else {
				/*	for(int i=1;i<=5;i++) {
						msg[i]="no";
	                   time[i]="no";
	                   likes[i]="no"; }*/	
				//}
			} catch (Exception e) {
				//e.printStackTrace();
				//msg[1]=e.toString() + success;
			}

			return null;
		}

		
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
			t1.setText(msg[1]);
			t2.setText(msg[2]);
			t3.setText(msg[3]);
			t4.setText(msg[4]);
			t5.setText(msg[5]);
			
		}

	}
	
	class Post extends AsyncTask<String, String,String> {


		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Cand_login.this);
			pDialog.setMessage("Loading..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}


		protected String doInBackground(String... args) {

			
			try {
				String postup;
				id= getSharedPreferences("filename",0);
				cand_id=""+id.getInt("id", 10);
				cand_name=id.getString("name","none");
				// Building Parameters
				postup=update.getText().toString();
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("cand_id", cand_id));	
				params.add(new BasicNameValuePair("post", postup));
				
				JSONObject json = jsonParser.makeHttpRequest(url_post,
						"POST", params);
				
				success = json.getInt(TAG_SUCCESS);
                //msg[1]=json.getString("message1") + success;
		
			
				if (success==1) {
                for(int i=4;i>0;i--) {
					msg[i+1]=msg[i];
                }
                msg[1]="* " +cand_name +" at "+ "Now" +"\n"+postup+"\n "+0+ "likes" ;
			} 
			
			} catch (Exception e) {
				//e.printStackTrace();
				//msg[1]=e.toString() + success;
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
			t1.setText(msg[1]);
			t2.setText(msg[2]);
			t3.setText(msg[3]);
			t4.setText(msg[4]);
			t5.setText(msg[5]);
			update.setText("");
			
		}

	}

}
