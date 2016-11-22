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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class circular extends Activity {

	String hold, hold1;
	DrawingCircular d;
	TextToSpeech speech;
	TextView algo;
	 Button b,bm,br,brem,bs;
	int ctr = 0;
	
	AlertDialog.Builder builder1;
	
	Dialog howto;
	Menu mvoice;
	boolean voice = false;
	
	FunctionCircular x;
	
	boolean rem = false;
	
	boolean done = true;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setWindowAnimations(R.style.Fade);
        setContentView(R.layout.circular_layout);
       
        builder1 =	new AlertDialog.Builder(this)
	    .setTitle("Warning!")
	    .setMessage("Are you sure you want to exit the simulation?")
	    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // continue with delete
	        	speech.stop();
				speech.shutdown();
				d.thread.interrupt();
				done = true;
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
        
        speech=new TextToSpeech(getApplicationContext(), 
        new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {
        if(status != TextToSpeech.ERROR){
        speech.setLanguage(Locale.US);
        } 
        }
        });
        
        x = new FunctionCircular();
     /*   getActionBar().setTitle("Link List: Circular");
        getActionBar().setDisplayShowHomeEnabled(false);*/
        
      //  Toast.makeText(getApplicationContext(), "Default size of Circle is 4. Min = 4, Max = 10.\nString inputted will be cut to the length of 6",Toast.LENGTH_LONG).show();
        
        speech = new TextToSpeech(getApplicationContext(), 
			      new TextToSpeech.OnInitListener() {
			      @Override
			      public void onInit(int status) {
			         if(status != TextToSpeech.ERROR){
			             speech.setLanguage(Locale.US);
			            }
			         else{
			        	 Toast.makeText(getApplicationContext(), "Beep",Toast.LENGTH_LONG).show();
			         }
			         }
			      });
        
         b = (Button)findViewById(R.id.btnCircularEnter);
         brem = (Button)findViewById(R.id.btnCircularRemove);
         br = (Button)findViewById(R.id.btnResetCircular);
         bs = (Button)findViewById(R.id.btnSizeCircular);
        algo = (TextView)findViewById(R.id.tvAlgoWriteCircular);
        algo.setMovementMethod(new ScrollingMovementMethod());
        
        final EditText f = (EditText)findViewById(R.id.etCircularData);
        final EditText f2 =  (EditText)findViewById(R.id.etSizeCircular);
        
        final FunctionCircular x = new FunctionCircular();
        d = (DrawingCircular)findViewById(R.id.svCircular);
        
        
        b.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if(!done){
					Toast.makeText(getApplicationContext(), "Simulation not Done",Toast.LENGTH_SHORT).show();
					return;
				}
				else{
				InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 

				inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
				
				// TODO Auto-generated method stub
				if(f.getText().toString().matches("")){
					 Toast.makeText(getApplicationContext(), "NO INPUT ON DATA",Toast.LENGTH_LONG).show();
				 }
				else if(ctr == 10){
					 Toast.makeText(getApplicationContext(), "Data Quantity at Maximum",Toast.LENGTH_LONG).show();
				}
				else{
					if(f.getText().toString().length() > 4)
						Toast.makeText(getApplicationContext(), "Input was cut to the length fitted to the box",Toast.LENGTH_LONG).show();
						x.add(f.getText());
						done = false;
						operate();
						d.setData(f.getText().toString());
						//operate();
						if(f.getText().toString().length() > 6)
							hold = f.getText().toString().substring(0, 6);
						else
							hold = f.getText().toString();
						f.setText("");
						ctr++;
					 }
			}
			}
        	
        });
        
        bs.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 

				inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
				
				// TODO Auto-generated method stub
				if(f2.getText().toString().equals(""))
					Toast.makeText(getApplicationContext(), "No Input",Toast.LENGTH_LONG).show();
				else if(Integer.parseInt(f2.getText().toString()) < 4 || Integer.parseInt(f2.getText().toString()) > 10)
					Toast.makeText(getApplicationContext(), "Input must be either greater than or equal to 4, or less than or equal10.",Toast.LENGTH_LONG).show();
				else if(Integer.parseInt(f2.getText().toString()) >= 4 || Integer.parseInt(f2.getText().toString()) <= 10)
				d.setSize(Integer.parseInt(f2.getText().toString()));
			}
        	
        });
        
        brem.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				if(!done){
					Toast.makeText(getApplicationContext(), "Simulation not Done",Toast.LENGTH_SHORT).show();
					return;
				}
				else{
				if(x.size() == 0){
					Toast.makeText(getApplicationContext(), "No DATA available to be removed.",Toast.LENGTH_LONG).show();
				}else{
				rem = true;
				done = false;
				x.remove(1);
				d.delete();
				operate();
				f.setText("");
				ctr--;
				}
				}
			}
        	
        });
        
        br.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				if(!done){
					Toast.makeText(getApplicationContext(), "Simulation not Done",Toast.LENGTH_SHORT).show();
					return;
				}
				Toast.makeText(getApplicationContext(), "Please wait for the simulation to reset. Thank You.",Toast.LENGTH_SHORT).show();
				do{
					x.remove(1);
					d.reset();
				}while(x.size()!=0);
				algo.setText("");
				ctr = 0;
				f.setText("");
				f2.setText("");
			}
        	
        });
        writeAlgo("Node head = new Node(null);");
    }

  public void operate(){

  if(rem){
	  new Thread(new Runnable(){
  		String num;
  		int i,k;
  			public void run(){
  				
  			try {
  				
  				runOnUiThread(new Runnable(){
                      @Override 
                      public void run() {
                          // display toast here; 
                    	  	done = true;
                        	writeAlgo("\n\nRemoving a Node at the Beginning(Queue).\n\nhead = head.getNext();");
                          }  });
  				
  				     
  			} catch (Exception e) {
  				e.printStackTrace();
  				//Toast.makeText(getApplicationContext(), "ERROR" + e,
  				//		Toast.LENGTH_LONG).show();

  			}
  			//Thread.interrupted();
  			
  			}
  		}).start();
  }
  else{
    	new Thread(new Runnable(){
    		String num;
    		int i,k;
    			public void run(){
    				
    			try {
    				
    				Thread.sleep(2000);
    				runOnUiThread(new Runnable(){
                        @Override 
                        public void run() {
                            // display toast here;
                            	writeAlgo("\n\nCreates new node and assign value and next\n\nNode temp = new Node(data);\n\ntemp.setNext(null);");
                            }  });
    				if(!((ctr-1) != 0))
    				Thread.sleep(4000);
    				runOnUiThread(new Runnable(){
                        @Override 
                        public void run() {
                            // display toast here;                    	
                    		writeAlgo("\n\nNode current = head;\n\nFind last node that points to null.\n\nif(current.getNext != null)\ncurrent = current.getNext()");                           
                            }  });
    				
    				if(!((ctr-1) != 0))
    					{Thread.sleep(6800);}
    				runOnUiThread(new Runnable(){
                        @Override 
                        public void run() {
                        	int prev = x.size()-1;
                        		if((ctr-1) != 0){
                        			done = true;
                            		writeAlgo("\n\nPoint to New Node(temp)\n\ncurrent.setNext(temp);");
                        		}
                            }  });
    				Thread.sleep(8800);
    				runOnUiThread(new Runnable(){
                        @Override 
                        public void run() {
                            // display toast here;
                        	SinglyFunction x = new SinglyFunction();	
                        	int prev = x.size()-1;
                        		if((ctr-1) == 0){
                                    done = true;
                                    writeAlgo("\n\nNew node is head.\n\nhead = temp");
                        		}
                            }  });
    				     
    			} catch (Exception e) {
    				e.printStackTrace();
    				//Toast.makeText(getApplicationContext(), "ERROR" + e,
    				//		Toast.LENGTH_LONG).show();

    			}
    			//Thread.interrupted();
    			
    			}
    		}).start();
  }
    	
  		rem = false;
    	}

  
	@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
		if(!done){
			builder1.show();
			}else{
				finish();
				super.onBackPressed();
			}
}

	public void writeAlgo(String msg){
		algo.append("\n"+msg);
		
		if(voice)
		speech.speak(msg, TextToSpeech.QUEUE_FLUSH, null);
		
		new Thread( new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(speech.isSpeaking()){
					try{
					Thread.sleep(100);
					} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
					}
			}}).start();
		
		/*while(speech.isSpeaking()){
		
			try{
			Thread.sleep(100);
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}*/
		
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        mvoice = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	switch(item.getItemId()){
    	case R.id.HowTo:
    		//Toast.makeText(getApplicationContext(), "RED BORDER - represent for the max heap\n GREEN BORDER -> the variable to be replace into another variable\n YELLOW BORDER -> place which the variable will be replace.", Toast.LENGTH_LONG).show();
    		howto = new Dialog(this);
    		howto.setContentView(R.layout.howto_layout);
    		ImageView imv = (ImageView)howto.findViewById(R.id.howtopic);
    						imv.setImageResource(R.drawable.l3);
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
    	case android.R.id.home:if(d!=null){
		//d.thread.setRunning(false);
			if(!done){
				builder1.show();
				return true;
				
			}else{
			
			
			speech.stop();
			speech.shutdown();
			d.thread.interrupt();
			
			d.thread.setRunning(false);
			}
		//	d.thread.stop();
			finish();
		}
    	break;
    	}
       
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onPause() {
    // TODO Auto-generated method stub
    super.onPause();
    
    speech.stop();
    
    speech.shutdown();
    
    
    }
}
