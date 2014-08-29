package com.example.politics;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;


public class UserPhp {

}

class First extends AsyncTask<String, String,String> {
   
	Context c;
	UserLogin ref;
	 public SharedPreferences id;
	ProgressDialog pDialog;
    public First(Context con, UserLogin u)
    {
    	c=con;
    	ref=u;
    }
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new ProgressDialog(c);
		pDialog.setMessage("Loading..");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
		pDialog.show();
	}


	protected String doInBackground(String... args) {

		
		try {
			String postup;
			id= getSharedPreferences("filename",0);
			ref.user_id=""+id.getInt("id", 10);
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
		ref.t1.setText(ref.msg[1]);
		ref.t2.setText(ref.msg[2]);
		ref.t3.setText(ref.msg[3]);
		ref.t4.setText(ref.msg[4]);
		ref.t5.setText(ref.msg[5]);
		
	}

}



