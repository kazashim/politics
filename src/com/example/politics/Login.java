package com.example.politics;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.example.politics.Signup1.CreateNewProduct;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class Login extends Activity implements OnClickListener{
   
	String user;
	String pass;
	EditText eUser,ePass;
	Button log,signCan;
	RadioButton rC,rU;
	
	  public static String filename= "Pref";
	    public SharedPreferences save;
	
	JSONParser jsonParser = new JSONParser();
	private static String url_create_product = "http://10.0.2.2:80/android_connect/login.php";
	private static final String TAG_SUCCESS = "success";
	private ProgressDialog pDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		initialise();
	}
	public void initialise()
	{
		eUser=(EditText)findViewById(R.id.etUser);
		ePass=(EditText)findViewById(R.id.etPass);
		log=(Button)findViewById(R.id.bLogin);
		log.setOnClickListener(Login.this);
		rC=(RadioButton)findViewById(R.id.rC);
		rU=(RadioButton)findViewById(R.id.rU);
		signCan=(Button)findViewById(R.id.bSign1);
		signCan.setOnClickListener(Login.this);
		
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()) {
		case R.id.bSign1 : Intent j=new Intent("com.example.politics.SIGNUP1");
			               startActivity(j);
			               break;
		case R.id.bLogin:   new CreateNewProduct().execute();
			                break;		
	 }
   }
	class CreateNewProduct extends AsyncTask<String, String,String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		JSONObject json;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Login.this);
			pDialog.setMessage("Creating Product..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Creating product
		 * */
		protected String doInBackground(String... args) {
			
			String password = ePass.getText().toString();
			
			String email = eUser.getText().toString();
		
			String sex,type;
			String dob1;
		
			if(rU.isChecked())
				type="user";
			else
				type="candidate";
			
					
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("password", password));
			params.add(new BasicNameValuePair("email", email));
			
			params.add(new BasicNameValuePair("type", type));
			//params.add(new BasicNameValuePair("dob", dob1));
			
			// getting JSON Object
			// Note that create product url accepts POST method
			
			String city="",constituency="",name="";
			int id=0;
			try {
			json = jsonParser.makeHttpRequest(url_create_product,
					"POST", params);
			
			      city=json.getString("city");
			      constituency=json.getString("constituency");
			      id=json.getInt("id");
			      name=json.getString("name");
			} catch(Exception e){} 
			// check log cat from response
			//Log.d("Create Response", json.toString());
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					save= getSharedPreferences("filename",0);
					SharedPreferences.Editor editor=save.edit();
					editor.putString("city", city);
					editor.putString("name", name);
					editor.putString("constituency", constituency);
					editor.putInt("id",id);
					editor.commit();
					if(type.contentEquals("candidate")) {
						Intent i = new Intent("com.example.politics.CAND_LOGIN");
		                startActivity(i);
					}
					else {
						Intent i = new Intent("com.example.politics.CAND_LOGIN");
		                startActivity(i);						
					}
					// closing this screen
					//finish();
				} else {
					//state=json.getString("message");
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}
	
			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			try {
			eUser.setText("Hahaha");
			pDialog.dismiss();
			} catch(Exception e) {}
			
		}

	}
}

