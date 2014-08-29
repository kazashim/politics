package com.example.politics;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread t1=new Thread(){
        public void run()
        {
        	try {
        		sleep(1000);
        	}
        	catch(Exception e){}
        	finally {
        		Intent i=new Intent("com.example.politics.LOGIN");
                startActivity(i);		
        		
        	}
        }
        	
        };
        t1.start();
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
