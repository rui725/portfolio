package com.android.databluesv5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class menu extends Activity  {

	
	 Button btnTopics,btnAbout,btnExit,btnSim;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setWindowAnimations(R.style.Fade);
		setContentView(R.layout.home_layout);
		 
			//getActionBar().setDisplayUseLogoEnabled(false);
			getActionBar().setDisplayShowHomeEnabled(false);
			btnTopics = (Button)findViewById(R.id.btnInfo);
			btnAbout = (Button)findViewById(R.id.btnAbout);
			btnExit = (Button)findViewById(R.id.btnExit);
			btnSim = (Button)findViewById(R.id.btnSimul);
			
			btnSim.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent i = new Intent(getApplicationContext(),Simul.class);
					
					startActivity(i);
				}
				
			});
			
			
			btnTopics.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent i = new Intent(getApplicationContext(),topics.class);
					
					startActivity(i);
				}});
			btnAbout.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(getApplicationContext(),about.class);
					
					startActivity(i);
					
					
				}});
			btnExit.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}});
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.none, menu);
			return true;
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// TODO Auto-generated method stub
			
			if(item.getItemId()==R.id.HowTo)
			Toast.makeText(getApplicationContext(), "YOU CLICK HOW TO", Toast.LENGTH_LONG).show();
			return super.onOptionsItemSelected(item);
		}

	
	
	
	
 }
	
