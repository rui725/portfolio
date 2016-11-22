package com.android.databluesv5;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getActionBar().setTitle("");
		Thread t = new Thread(){
			public void run(){
				
				try{
				
					sleep(3000);
					
				}catch(Exception e){
					Toast.makeText(getApplicationContext(), "ERROR"+e, Toast.LENGTH_LONG).show();
					System.out.println("ERROR"+e);
				}finally{
					
					Intent openMenu = new Intent(getApplicationContext(),menu.class);
					
					startActivity(openMenu);
				}
				
				
			}
			
		};
		
		
		t.start();
		
	}
	
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		
		getMenuInflater().inflate(R.menu.none, menu);
		return true;
	}

}
