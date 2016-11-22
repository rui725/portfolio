package com.android.databluesv5;



import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class sorting extends Activity { 
	private  int[] a;
    private  int n;
    private  int left;
    private  int right;
    private  int largest;
    private  int fixed;
    
    TextToSpeech ttobj;
    EditText input;
    Button enter1;
    TextView showAlgo;
    public int[] numConverted;
    drawHeap d;
    Dialog howto;
    
    Menu mvoice;
    boolean checkCom = false;
    String[] num;
    AlertDialog.Builder builder1;
    boolean voice = false;//on
    boolean don = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setWindowAnimations(R.style.Fade);
		setContentView(R.layout.heap_layout);
		showAlgo = (TextView)findViewById(R.id.tvAlgoWriteHeap);
		showAlgo.setMovementMethod(new ScrollingMovementMethod());
		input = (EditText)findViewById(R.id.etInputHeap);
		
		
		builder1 =	new AlertDialog.Builder(this)
	    .setTitle("Warning!")
	    .setMessage("Are you sure you want to exit the simulation?")
	    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // continue with delete
	        	ttobj.stop();
				ttobj.shutdown();
				d.thread.interrupt();
				
				d.thread.setRunning(false);
				finish();
	        }
	     })
	    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // do nothing
	        }
	     }).setIcon(android.R.drawable.ic_dialog_alert);
		
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("Topics");
		ttobj=new TextToSpeech(getApplicationContext(), 
			      new TextToSpeech.OnInitListener() {
			      @Override
			      public void onInit(int status) {
			         if(status != TextToSpeech.ERROR){
			             ttobj.setLanguage(Locale.US);
			            }				
			         }
			      });
		enter1 = (Button)findViewById(R.id.btnPlayHeap);
		d = (drawHeap)findViewById(R.id.svHeap);
		enter1.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				if(!don){
					Toast.makeText(getApplicationContext(),"wait for the simulation to be done", Toast.LENGTH_LONG).show();
					return;
				}
				String str = input.getText().toString();
				boolean checker = false;
				
				if(str.matches("")){
					Toast.makeText(getApplicationContext(),"No input value.", Toast.LENGTH_LONG).show();
				}else{
					for(int i = 0;i<str.length();i++){
				    	if(str.charAt(i)==' '||str.charAt(i)=='.'){
				    			 checker = false;
					    		 break; 
				    	}
				    	else if((str.charAt(i)>='0'&&str.charAt(i)<='9')||str.charAt(i)==','){
						 continue;
					    }else{
						   checker = false;
						   break;
					   }
					 
				     }
					
					if(str.contains(",,")){
						Toast.makeText(getApplicationContext(),"Invalid. Repeating comma(s).", Toast.LENGTH_LONG).show();
		                return;
					}
					
					
					
					
					if(!checkCom){
					num = str.split(",");
					if(num.length <= 2){
						Toast.makeText(getApplicationContext(),"Number of data must be atleast 3 input.", Toast.LENGTH_LONG).show();
						//checker = false;
					}
					else if(num.length > 10){
						Toast.makeText(getApplicationContext(),"Number of data have only a maximum of 10 input.", Toast.LENGTH_LONG).show();
						//checker = false;
					}
					else{
						    checker = true;
							numConverted = new int[num.length];
							for(int i = 0; i < numConverted.length; i++){
								numConverted[i] = Integer.parseInt(num[i]);
								if(!(numConverted[i]>=0 && numConverted[i] <=999)){
									Toast.makeText(getApplicationContext(),"Data range must be 0-999 only.", Toast.LENGTH_LONG).show();
									checker = false;
									break;
								}
							}
							
					}
					}else{
						Toast.makeText(getApplicationContext(),"Data contains invalid type of input.", Toast.LENGTH_LONG).show();
					}
				
		        }//else sa pinaka taas
				
				if(checker){
					 InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 
				     inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
				    
		             Toast.makeText(getApplicationContext(), "Simulation has Started", Toast.LENGTH_SHORT).show();
		                    
				     input.setText("");
				     showAlgo.setText("");
				     d.setInteger(numConverted);
				     fixed = numConverted.length;
				     don=false;
				operate();
				}
				else{
					Toast.makeText(getApplicationContext(),"Data contains invalid type of input.", Toast.LENGTH_LONG).show();
				}
			}});	
	}
	
	public void showAlg(String msg){
		showAlgo.append("\n"+msg);
		if(msg.contains("]")){
			msg = msg.replaceAll("\\]", "");
			}
		if(msg.contains("[")){
		msg = msg.replaceAll("\\[", " index of");
		}
		if(msg.contains("->")){
			msg = msg.replaceAll("\\->", " value of");
			}
		if(msg.contains("D O N E")){
			msg = msg.replaceAll("D O N E", " Done");
			}
		//if voice on then YOU CAN TALK TO ME
		if(voice)
		ttobj.speak(msg, TextToSpeech.QUEUE_FLUSH, null);
	
		while(ttobj.isSpeaking()){
			try{
			Thread.sleep(100);
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			}
	}
	
			@Override
			protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			ttobj.stop();
			ttobj.shutdown();
			
			
	} 
			
			// Build-Heap Function
						public void buildheap( final int []a){
							n=a.length-1;
							runOnUiThread(new Runnable(){
					            @Override 
					        public void run() {
					    	showAlg("Build Heap"+"\n for i = array length divide 2, i >= 0, decrease i");
					        }  });
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							for(int i=n/2;i>=0;i--){	
						    d.setInteger(a);
							maxheap(a,i);
							}
							}
							// Max-Heap Function
							public void maxheap(int[] a, int i){
							d.setInteger(a);
							left=(2*i)+1;
							right=(2*i)+2;
							final int iHold2 = i;
							final int aHolder[] = a;
							
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							runOnUiThread(new Runnable(){
					            @Override 
					        public void run() {
					    	//showAlg("\nMaxHeap index = a["+iHold2+"]");
					        }  });
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//d.setMHindex(i, 7);
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(left <= n && a[left] > a[i]){
								runOnUiThread(new Runnable(){
					                @Override 
					            public void run() {
						    	showAlg("largest = a["+left+"] -> "+aHolder[left]);
					            }  });
							largest=left;
							}
							else{
								try {
									Thread.sleep(2000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								runOnUiThread(new Runnable(){
					                @Override 
					            public void run() {
						    	showAlg("largest = a["+iHold2+"] -> "+aHolder[iHold2]);
					            }  });
							largest=i;
							}
							
							if(right <= n && a[right] > a[largest]){
								try {
									Thread.sleep(2000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	
								runOnUiThread(new Runnable(){
					                @Override 
					            public void run() {
						    	showAlg("largest = a["+right+"] -> "+aHolder[right]);
					            }  }); 
							largest=right;
							}
							if(largest!=i){
							exchange(i,largest);
							maxheap(a, largest);
							}
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							}
							
							// Exchange Function
							public void exchange( int i, int j){
								final int iHold3 = i;
								final int jHold1 = j;
								
							final int t=a[i];
							runOnUiThread(new Runnable(){
					            @Override 
					        public void run() {
					    	showAlg("\n\tSwap data a["+iHold3+"] -> "+a[iHold3]);
					        }  });
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							d.setMHindex(i, 8);
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							a[i]=a[j];
							runOnUiThread(new Runnable(){
					            @Override 
					        public void run() {
					    	showAlg("to a["+jHold1+"] -> "+a[jHold1]);
					        }  });
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							d.setMHindex(j, 9);
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							a[j]=t;
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							d.setInteger(a);
							d.setSWAPPED(i, j);
							try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							}
							// Sort Function
							public void sort(int []a0){
							fixed = fixed - 1;
							a=a0;
							buildheap(a);
							for(int i=n;i>0;i--){
							exchange(0, i);
							n=n-1;
							d.fixedNum(fixed,true);
							fixed--;
							maxheap(a, 0);
							}
							runOnUiThread(new Runnable(){
					            @Override 
					        public void run() {
					    	showAlg("D O N E.");
					    	runOnUiThread(new Runnable(){
			                    @Override 
			                public void run() {
			                    // dispaly toast here;                    	
			                    	//writeAlgo("creates new node\nNode n = new Node;  ");
			                    	Toast.makeText(getApplicationContext(), "Simulation Done!", Toast.LENGTH_SHORT).show();
			                    }  });
					    	don = true;
					        }  });
							}
								    
						//heap end
					
	public void operate(){
		new Thread(new Runnable(){
				public void run(){
				    sort(numConverted);
					try {
						Thread.sleep(2000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		}).start();
		}
	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if(!don){
			builder1.show();
			}else{
				finish();
				super.onBackPressed();
			}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		
		mvoice = menu;
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
    	case R.id.HowTo:
    		//Toast.makeText(getApplicationContext(), "RED BORDER - represent for the max heap\n GREEN BORDER -> the variable to be replace into another variable\n YELLOW BORDER -> place which the variable will be replace.", Toast.LENGTH_LONG).show();
    		howto = new Dialog(this);
    		howto.setContentView(R.layout.howto_layout);
    		ImageView imv = (ImageView)howto.findViewById(R.id.howtopic);
    						imv.setImageResource(R.drawable.s2);
    						howto.setCancelable(true);
    						howto.setTitle("How to ?");    						
    						howto.show();
    						break;
    	case R.id.voiceControl: 
    					if(voice){
    						Toast.makeText(getApplicationContext(), "Voice off", Toast.LENGTH_SHORT).show();
    						
    						voice = false;
    						mvoice.getItem(0).setIcon(android.R.drawable.ic_lock_silent_mode);
    						
    					}else{
    						voice= true;
    						
    						Toast.makeText(getApplicationContext(), "Voice On", Toast.LENGTH_SHORT).show();
    						mvoice.getItem(0).setIcon(android.R.drawable.ic_lock_silent_mode_off);
    					}
    					break;
    	case android.R.id.home: if(d!=null){
		//d.thread.setRunning(false);
			if(!don){
				builder1.show();
				return true;
				
			}else{
			
			
			ttobj.stop();
			ttobj.shutdown();
			d.thread.interrupt();
			d.done = true;
			d.thread.setRunning(false);
			}
		//	d.thread.stop();
			finish();
		}
		
    					break;
    	}
		return super.onOptionsItemSelected(item);
	}
}
