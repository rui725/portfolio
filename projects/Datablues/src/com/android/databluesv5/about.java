package com.android.databluesv5;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class about extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setWindowAnimations(R.style.Fade);
		setContentView(R.layout.about_layout);
		
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		//getActionBar().setDisplayUseLogoEnabled(true);
		//getActionBar().setIcon(R.drawable.tut);
		getActionBar().setTitle("Home");
		
		/*getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setTitle("About");*/
		
		
		TextView tv = (TextView)findViewById(R.id.tvDescription);
		tv.setText(" - is a CAI(Computer Aided Instructor) which aims to help student learn more about Data Structures and it's fundamentals through the use of Simulations");
		TextView tvdev = (TextView)findViewById(R.id.tvDevelopers);
		String []devlist = getResources().getStringArray(R.array.Developers);
		for (String d : devlist){
			tvdev.append(d+"\n");
		}
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case android.R.id.home: finish();
		break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.none, menu);
		return true;
	}
   
}
