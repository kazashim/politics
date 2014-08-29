package com.example.politics;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.ActionBar.Tab;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

import com.example.politics.Cand_login.CreateNew;
import com.example.politics.Cand_login.Post;

public class UserLogin extends Activity  implements OnClickListener {
	 public static String filename= "Pref";
	    public SharedPreferences id;
	    public int count=0;
	    public String user_id,user_name;
		JSONParser jsonParser = new JSONParser();
		private static String url_create_product = "http://10.0.2.2:80/android_connect/user_front_page.php";

		private static final String TAG_SUCCESS = "success";
		private ProgressDialog pDialog;
	    public String msg[],time[],likes[],candName[];	
	    //Tab tab2;
	    
	    TextView t1,t2,t3,t4,t5;
	    
	    Button see,pre;
	    int success;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.user_login);
			id= getSharedPreferences("filename",0);
		    
			TabHost th =(TabHost) findViewById(R.id.usertab);
		    th.setup();
		    
		    TabSpec specs= th.newTabSpec("tag1");
		    specs.setContent(R.id.usertab1);   
		    specs.setIndicator("Updates From Followers");
		    th.addTab(specs);
		    
		    specs= th.newTabSpec("tag2");
		    specs.setContent(R.id.usertab2);   
		    specs.setIndicator("Competetors Updates");
		    th.addTab(specs);
		    
		    t1=(TextView) findViewById(R.id.tvuser1);
		    t2=(TextView) findViewById(R.id.tvuser2);
		    t3=(TextView) findViewById(R.id.tvuser3);
		    t4=(TextView) findViewById(R.id.tvuser4);
		    t5=(TextView) findViewById(R.id.tvuser5);
		    see=(Button) findViewById(R.id.bUserSee);
		    pre=(Button) findViewById(R.id.bUserPre);
		    see.setOnClickListener(UserLogin.this);
		    pre.setOnClickListener(UserLogin.this);
		    
		    msg=new String[6];
		    time=new String[6];
		    likes=new String[6];
		    user_id=""+id.getInt("id", 10);
			user_name=id.getString("name","none");
			t1.setText(user_id);
			t2.setText(user_name);
		    //new CreateNew().execute();
		    
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch(arg0.getId())
			{
			case R.id.bPost: //new Post().execute();
				             
				            break;
			
			case R.id.bSee: count++;
			//new CreateNew().execute();
			
			break;
			case R.id.bPre:
				if(count>0)
				count--;
				//new CreateNew().execute();
				
				break;
			
			}
			
		}


}
