package com.example.politics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class Signup1 extends Activity implements OnClickListener{
	public String user,state;
	String pass;
	EditText etName,etEmail,etPassword,etConfirm,etConstituency,etCity,etVoter;
	DatePicker dob;
	RadioGroup rgType,rgSex;
	RadioButton rMale,rFemale,rUser,rCandidate;
	Button submit;
	DatabaseExample db;
	
	JSONParser jsonParser = new JSONParser();
	private static String url_create_product = "http://10.0.2.2:80/android_connect/create_product.php";
	private static final String TAG_SUCCESS = "success";
	private ProgressDialog pDialog;
	
	  public static String filename= "Pref";
	    public SharedPreferences save;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup1);
		initialise();
	}
	public void initialise()
	{
		etName=(EditText)findViewById(R.id.etName);
		etEmail=(EditText)findViewById(R.id.etEmail);
		etConfirm=(EditText)findViewById(R.id.etConfirm);		
		etPassword=(EditText)findViewById(R.id.etPassword);
		etConstituency=(EditText)findViewById(R.id.etConstituency);
		etCity=(EditText)findViewById(R.id.etCity);
		etVoter=(EditText)findViewById(R.id.etVoter);
		rgType=(RadioGroup)findViewById(R.id.rgType);
		rgSex=(RadioGroup)findViewById(R.id.rgSex);
		rMale=(RadioButton)findViewById(R.id.rMale);
		rFemale=(RadioButton)findViewById(R.id.rFemale);
		rUser=(RadioButton)findViewById(R.id.rUser);
		rCandidate=(RadioButton)findViewById(R.id.rCandidate);
		submit=(Button)findViewById(R.id.bSubmit1);
		dob=(DatePicker)findViewById(R.id.dob);
		
		save= getSharedPreferences(filename,0);
		submit.setOnClickListener(Signup1.this);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		int a=0;
		try {
			
			new CreateNewProduct().execute();

		} catch(Exception e) { //eUser.setText(e.toString());a=1;
		}
		if(a==0)
		{
			//ePass.setText("Yeah");
			}	
	}
	/*public void connect() {
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
			Connection c= DriverManager.getConnection("jdbc:jtds:sqlserver://10.0.2.2:3306/new1", "ducat38", "ducat38");
			
			String t1="lalu";
			int t2=420;
		    Log.w("Connection","open");
			Statement s = c.createStatement();
			s.executeUpdate("create table emp162(name varchar(20), salary int)" );
			s.close();	
			
		} catch(Exception e){//eUser.setText(e.toString());
        }
		}
	

	public void connect1() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection c= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/new1", "ducat38", "ducat38");
				
			//String t1="lalu";
			//int t2=420;
			 Log.w("Connection","open");
			Statement s = c.createStatement();
			s.executeUpdate("create table emp162(name varchar(20), salary int); " );
			s.close();
		} catch(Exception e){//ePass.setText(e.toString());
        }
		}*/
	
	class CreateNewProduct extends AsyncTask<String, String,String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Signup1.this);
			pDialog.setMessage("Creating Product..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Creating product
		 * */
		protected String doInBackground(String... args) {
			String name = etName.getText().toString();
			String password = etPassword.getText().toString();
			String confirm = etConfirm.getText().toString();
			String email = etEmail.getText().toString();
			String voter = etVoter.getText().toString();
			String constituency = etConstituency.getText().toString();
			String city = etCity.getText().toString();
			String sex,type;
			String dob1;
			if(rMale.isChecked())
				sex="male";
			else
				sex="female";
			if(rUser.isChecked())
				type="user";
			else
				type="candidate";
			
			//dob1=""+dob.getYear()+dob.getMonth()+dob.getDayOfMonth();
			
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("name", name));
			params.add(new BasicNameValuePair("password", password));
			params.add(new BasicNameValuePair("email", email));
			params.add(new BasicNameValuePair("voter", voter));
			params.add(new BasicNameValuePair("constituency", constituency));
			params.add(new BasicNameValuePair("city", city));
			params.add(new BasicNameValuePair("sex", sex));
			params.add(new BasicNameValuePair("type", type));
			//params.add(new BasicNameValuePair("dob", dob1));
			
			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(url_create_product,
					"POST", params);
			//state=json.getString("message");
			// check log cat from response
			//Log.d("Create Response", json.toString());
			//ePass.setText("Extra Yeah");	
			// check for success tag
			try {
				state="New";
				state+=json.getInt("success");
				int success =json.getInt("success");// json.getInt(TAG_SUCCESS);

				if (success == 1) {
					save= getSharedPreferences(filename,0);
					SharedPreferences.Editor editor=save.edit();
					editor.putString("name", name);
					editor.putString("city", city);
					editor.putString("constituency", constituency);
					editor.putInt("id",success);
					//editor.putInt("id",json.getInt("id"));
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
					state="Hahaha";
				}
			} catch (Exception e) {
				//
				//state=
						//e.printStackTrace();
				
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
			etName.setText(state);
			etEmail.setText(state);			
		}

	}
}

