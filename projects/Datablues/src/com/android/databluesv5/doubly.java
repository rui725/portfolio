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
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class doubly extends Activity {

	TextToSpeech speech;
	TextView algo;
	EditText f2;
	String hold, hold1;
	
	DrawingDoubly d;
	int ctr = 0;
	Dialog howto;
	boolean voice = false;
	Menu mvoice;
	
	AlertDialog.Builder builder1;
	
    DoublyFunction x;
    
    boolean fix, rem;
    boolean done = true;
    
    int[] xx = new int[10];
    
    
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

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setWindowAnimations(R.style.Fade);
        setContentView(R.layout.doubly_layout);
        
        
        builder1 =	new AlertDialog.Builder(this)
	    .setTitle("Warning!")
	    .setMessage("Are you sure you want to exit the simulation?")
	    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // continue with delete
	        	speech.stop();
				speech.shutdown();
				d.thread.interrupt();
				done= true;
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
        
        x = new DoublyFunction();
       // Toast.makeText(getApplicationContext(), "String inputted will be cut to the length of 2",Toast.LENGTH_LONG).show();
       
        speech = new TextToSpeech(getApplicationContext(), 
			      new TextToSpeech.OnInitListener() {
			      @Override
			      public void onInit(int status) {
			         if(status != TextToSpeech.ERROR){
			        	 speech.setSpeechRate(1);
			             speech.setLanguage(Locale.US);
			            }
			         else{
			        	 Toast.makeText(getApplicationContext(), "Beep",Toast.LENGTH_LONG).show();
			         }
			         }
			      });
        
        
        final EditText f = (EditText)findViewById(R.id.etDoublyData);
        
        /*SurfaceView ss = (SurfaceView)findViewById(R.id.surfaceView1);
        setContentView(ss);*/

        for(int i = 0 ; i < 10 ; i++){
        	xx[i] = i;
        }
        
        d = (DrawingDoubly)findViewById(R.id.svDoubly);
        //setContentView(d);
        
        fix = false;
        f2 = (EditText)findViewById(R.id.etPositionEnterDoubly);
        Button b = (Button)findViewById(R.id.btnDoublyEnter);
        Button brem = (Button)findViewById(R.id.btnDoublyRemove);
        Button fb = (Button)findViewById(R.id.btnDoublyPositionEnter);
        Button br = (Button)findViewById(R.id.btnResetDoubly);
        algo = (TextView)findViewById(R.id.tvAlgoWriteDoubly);
        algo.setMovementMethod(new ScrollingMovementMethod());
		f.setText("");
		f2.setText("");
		
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
				if(f.getText().toString().matches("")){
					 Toast.makeText(getApplicationContext(), "NO INPUT ON DATA",Toast.LENGTH_LONG).show();
				 }
				else if(ctr >= 8){
					 Toast.makeText(getApplicationContext(), "LIST IS FULL",Toast.LENGTH_LONG).show();
				}else{
					  d.setData(f.getText().toString());
					  	done = false;
						operate();
						if(f.getText().toString().length() > 2){
					        Toast.makeText(getApplicationContext(), "String inputted was cut to the length of 2",Toast.LENGTH_LONG).show();
					        x.add(f.getText().toString().substring(0, 2));
					        hold = f.getText().toString().substring(0, 2);
							}
						else{
							x.add(f.getText());
							hold = f.getText().toString();
						}
						hold1 = f2.getText().toString();
						f.setText("");
						f2.setText("");
						ctr++;
					 }
			}
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
				}
				else{
					rem = true;
					operate();
				x.remove(1);
				d.delete();
				f.setText("");
				f2.setText("");
				ctr--;
				}
				}
			}        	
        });
        
        fb.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if(!done){
					Toast.makeText(getApplicationContext(), "Simulation not Done",Toast.LENGTH_SHORT).show();
					return;
				}
				else{
				InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 

				inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
				
				if(f.getText().toString().matches("")){
					Toast.makeText(getApplicationContext(), "NO INPUT ON DATA",Toast.LENGTH_LONG).show();
				}
				else if(f2.getText().toString().matches("")){
					Toast.makeText(getApplicationContext(), "NO INPUT ON POSITION",Toast.LENGTH_LONG).show();
				}
				else if(ctr >= 8){
					 Toast.makeText(getApplicationContext(), "LIST IS FULL",Toast.LENGTH_LONG).show();
				}
				else if(Integer.parseInt(f2.getText().toString()) > 8){
					Toast.makeText(getApplicationContext(), "Max Position of Index is 8",Toast.LENGTH_LONG).show();
				}
				else{
				if(Integer.parseInt(f2.getText().toString())>x.size()){
					operate();
					Toast.makeText(getApplicationContext(), "Value inserted at last position, due to input being greater than last position.",Toast.LENGTH_LONG).show();
					if(f.getText().toString().length() > 2){
				        Toast.makeText(getApplicationContext(), "String inputted was cut to the length of 2",Toast.LENGTH_LONG).show();
						d.setData(f.getText().toString().substring(0, 2));
				        hold = f.getText().toString().substring(0, 2);
					}
					else{
						d.setData(f.getText().toString());
						hold = f.getText().toString();
					}
					hold1 = f2.getText().toString();
					ctr++;
				}
				else{
				if(Integer.parseInt(f2.getText().toString()) == 1 && ctr > 1){
					fix = true;
					operate();
					x.add(f.getText(), 1);
					if(f.getText().toString().length() > 2){
				        Toast.makeText(getApplicationContext(), "String inputted was cut to the length of 4",Toast.LENGTH_LONG).show();
						d.fixedsetData(f.getText().toString().substring(0, 2), Integer.parseInt(f2.getText().toString()));
						hold = f.getText().toString().substring(0, 2);
					}
					else{
						d.fixedsetData(f.getText().toString(), Integer.parseInt(f2.getText().toString()));
						hold = f.getText().toString();
					}
					hold1 = f2.getText().toString();
					
					ctr++;
				}
				else{
					fix = true;
					operate();
					x.add(f.getText(), Integer.parseInt(f2.getText().toString()));
					d.fixedsetData(f.getText().toString(), Integer.parseInt(f2.getText().toString()));
					if(f.getText().toString().length() > 4){
				        Toast.makeText(getApplicationContext(), "String inputted was cut to the length of 4",Toast.LENGTH_LONG).show();
						hold = f.getText().toString().substring(0, 4);
					}
					else
						hold = f.getText().toString();
					hold1 = f2.getText().toString();
					ctr++;
				}
				}
				f.setText("");
				f2.setText("");
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
				ctr=0;
				f.setText("");
				f2.setText("");
			}
        	
        });
        
        writeAlgo("Node head = new Node(null);");
        
    }

    public void operate(){
    	
    if(fix){
       	
    	new Thread(new Runnable(){
    			public void run(){
    				
    			try {
    				
    				runOnUiThread(new Runnable(){
                        @Override 
                        public void run() {
                            // display toast here;                    	
                            	writeAlgo("\n\nCreate New Node and assign value\n\nNode temp = new Node(data)");
                            	
                            }  });
    				Thread.sleep(2000);
    				runOnUiThread(new Runnable(){
                        @Override 
                        public void run() {
                            // display toast here;                    	
                            	writeAlgo("\n\nFind position and temp MUST connect to ahead Node to save the link\n\nif(i < index && current.getNext() != null)\n\ncurrent = current.getNext()\n\ntemp.setNext(current.getNext())");
                            }  });
    				if(!((ctr-1) != 0))
    					{Thread.sleep(11000);}
    				else
    					{Thread.sleep(10500);}
    				runOnUiThread(new Runnable(){
                        @Override 
                        public void run() {
                    		writeAlgo("\n\nCurrent will connect before temp\n\ncurrent.setNext(temp);");
                            // display toast here;
                        		if((ctr-1) == 0 && hold1.equals(("0"))){
                        		writeAlgo("\n\nTemp is Head.\n\nhead = temp");
                        		}
                        		else if(hold1.equals("0")){
                        		writeAlgo("\n\nTemp is Head.\n\nhead = temp");
                        		}
                        		else if((ctr-1) == 1 || hold1.equals(("1"))){
                        			Toast.makeText(getApplicationContext(), "Critical Difference of Doubly to Singly",Toast.LENGTH_LONG).show();
                        		writeAlgo("\n\nTemp ALSO points to current \n\ntemp.getPrev() = current");
                        		}else{
                        			Toast.makeText(getApplicationContext(), "Critical Difference of Doubly to Singly",Toast.LENGTH_LONG).show();
                        		writeAlgo("\n\nTemp ALSO points to current \n\ntemp.getPrev() = current");
                        		}
                            }  });
    				
    				runOnUiThread(new Runnable(){
                        @Override 
                        public void run() {
                            // display toast here;
                        		done = true;
                        		writeAlgo("\n\nConnection of list done.");
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
    
    else if(rem){
    	new Thread(new Runnable(){
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
    			public void run(){
    				
    			try {
    				
    				Thread.sleep(2000);
    				runOnUiThread(new Runnable(){
                        @Override 
                        public void run() {                 	
                        	writeAlgo("\n\nCreates new node and assign value and next\n\nNode temp = new Node(data);\n\ntemp.setNext(null);");
                            }  });
    				Thread.sleep(4000);
    				runOnUiThread(new Runnable(){
                        @Override 
                        public void run() {                 	
                        	writeAlgo("\n\nNode current = head;\n\nFind last node that points to null.\n\nif(current.getNext != null)\ncurrent = current.getNext()");
                            
                            }  });
    				if((ctr-1) != 0){
    					Thread.sleep(8800);}
    				runOnUiThread(new Runnable(){
                        @Override 
                        public void run() {
                        		if((ctr-1) != 0){
                        			done = true;
                            		writeAlgo("\n\nPoint to New Node(temp)\n\ncurrent.setNext(temp);");
                        		}
                            }  });
    				if(!((ctr-1) != 0))
    					{Thread.sleep(8800);}
    				else
    					{Thread.sleep(10500);}
    				runOnUiThread(new Runnable(){
                        @Override 
                        public void run() {
                        		if((ctr-1) == 0){
                        			done = true;
                        		writeAlgo("\n\nTemp is now Head.\n\nhead = temp;");
                        		}
                        		else if((ctr-1) == 1){
                        			Toast.makeText(getApplicationContext(), "Critical Difference of Doubly to Singly",Toast.LENGTH_LONG).show();
                        		writeAlgo("\n\nTemp ALSO points to Node before temp\n\ntemp.setPrev() = head");
                        		}else{
                        			Toast.makeText(getApplicationContext(), "Critical Difference of Doubly to Singly",Toast.LENGTH_LONG).show();
                        		writeAlgo("\n\nTemp ALSO points to Node before temp\n\ntemp.setPrev() = current");
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
    
    fix = false;
    rem = false;
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
    	case android.R.id.home:
    		if(d!=null){
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
