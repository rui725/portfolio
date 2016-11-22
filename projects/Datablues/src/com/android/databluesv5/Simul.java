package com.android.databluesv5;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Simul extends Activity{
	ImageButton btnLinkList,btnStack,btnTrees,btnSort,btnsingly,btndoubly,btncir,btnpre,btnpost,btnsort,btnbst;
	Dialog Topic;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setWindowAnimations(R.style.Fade);
		setContentView(R.layout.simul);
		
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		//getActionBar().setDisplayUseLogoEnabled(true);
		//getActionBar().setIcon(R.drawable.tut);
		getActionBar().setTitle("Home");
		
		btnLinkList= (ImageButton)findViewById(R.id.imlinklist);
		btnStack= (ImageButton)findViewById(R.id.imStack);
		btnTrees= (ImageButton)findViewById(R.id.imTrees);
		btnSort= (ImageButton)findViewById(R.id.imSort);
		Topic = new Dialog(this);
		 Window window = Topic.getWindow();
		 window.setGravity(Gravity.CENTER);	
	        
		btnLinkList.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Topic.setContentView(R.layout.subtopic);
				Topic.setCancelable(true);
				Topic.setTitle("Link List");
				btnsingly = (ImageButton)Topic.findViewById(R.id.sub1);
				btndoubly = (ImageButton)Topic.findViewById(R.id.sub2);
				btncir = (ImageButton)Topic.findViewById(R.id.sub3);
				
				btnsingly.setImageResource(R.drawable.singly);
				btnsingly.setVisibility(ImageView.VISIBLE);
				btndoubly.setImageResource(R.drawable.doubly);
				btndoubly.setVisibility(ImageView.VISIBLE);
				btncir.setImageResource(R.drawable.cir);
				btncir.setVisibility(ImageView.VISIBLE);
			
				btncir.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i = new Intent(getApplicationContext(),circular.class);
						Topic.dismiss();
						startActivity(i);
					}});
				
				btnsingly.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i = new Intent(getApplicationContext(),singly.class);
						Topic.dismiss();
						startActivity(i);
					}});
				
				btndoubly.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i = new Intent(getApplicationContext(),doubly.class);
						Topic.dismiss();
						startActivity(i);
					}});
				
				
				
				
			     Topic.show();        
			             
			}});
		
		btnStack.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Topic.setContentView(R.layout.subtopic);
				Topic.setCancelable(true);
				Topic.setTitle("Stacks");
				btnpre = (ImageButton)Topic.findViewById(R.id.sub1);
				btnpost = (ImageButton)Topic.findViewById(R.id.sub2);
				
				
				btnpre.setImageResource(R.drawable.pre);
				btnpre.setVisibility(ImageView.VISIBLE);
				btnpost.setImageResource(R.drawable.po);
				btnpost.setVisibility(ImageView.VISIBLE);
				
				btnpre.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i = new Intent(getApplicationContext(),prefix.class);
						Topic.dismiss();
						startActivity(i);
					}});
				btnpost.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i = new Intent(getApplicationContext(),postfix.class);
						Topic.dismiss();
						startActivity(i);
					}});
				
			     Topic.show(); 
			}});
		
		btnTrees.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Topic.setContentView(R.layout.subtopic);
				Topic.setCancelable(true);
				Topic.setTitle("Trees");
				btnbst = (ImageButton)Topic.findViewById(R.id.sub1);
				
				
				
				btnbst.setImageResource(R.drawable.bst);
				btnbst.setVisibility(ImageView.VISIBLE);
				
				btnbst.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i = new Intent(getApplicationContext(),bst.class);
						Topic.dismiss();
						startActivity(i);
					}});
				
				
			     Topic.show(); 
			}});
		
		btnSort.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Topic.setContentView(R.layout.subtopic);
				Topic.setCancelable(true);
				Topic.setTitle("Sorting");
				btnsort = (ImageButton)Topic.findViewById(R.id.sub1);
				
				
				btnsort.setImageResource(R.drawable.heap);
				btnsort.setVisibility(ImageView.VISIBLE);
				
				btnsort.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i = new Intent(getApplicationContext(),sorting.class);
						Topic.dismiss();
						startActivity(i);
					}});
				
			     Topic.show(); 
			}});
		
		
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
