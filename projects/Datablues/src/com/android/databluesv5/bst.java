package com.android.databluesv5;

import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
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

public class bst extends Activity {
	//Interface
	TextView tv;
	Handler h = new Handler(); //may or may not use
	EditText et;
	Button bAdd,bDel,bRes;
	
	//SurfaceView
	drawtrees d;
	
	VoiceOperation vo;
    //Input Variables
	int inputNumbers; // all input numbers goes here
	int ctr=0;
	boolean chd = true;
	boolean exceed= false;
	
	//Tree variables
	 public Node root; // head root
	 public Node focusNode;
	int xNode;
	public Node parent;
	int tempNumber;
	
	Dialog howto;
	Menu mvoice;
	boolean voice  = false;
	boolean simul = false;
	AlertDialog.Builder builder1;
	AlertDialog.Builder builder2;
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
    	case R.id.HowTo:
    		//Toast.makeText(getApplicationContext(), "Dialog here", Toast.LENGTH_SHORT).show();
    		howto = new Dialog(this);
		howto.setContentView(R.layout.howto_layout);
		ImageView imv = (ImageView)howto.findViewById(R.id.howtopic);
						imv.setImageResource(R.drawable.t2);
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
    		//back = true;
		
		if(d!=null){
		//d.thread.setRunning(false);
			if(!d.done){
				builder1.show();
				return true;
				
			}else{
			
			if(vo!=null){
			vo.ttobj.stop();
			vo.ttobj.shutdown();
			}
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




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setWindowAnimations(R.style.Fade);
		setContentView(R.layout.trees);
		vo = new VoiceOperation(getApplicationContext());
		vo.execute();
		builder1 =	new AlertDialog.Builder(this)
	    .setTitle("Warning!")
	    .setMessage("Are you sure you want to exit the simulation?")
	    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // continue with delete
	        	vo.ttobj.stop();
				vo.ttobj.shutdown();
				d.thread.interrupt();
				d.done = true;
				d.thread.setRunning(false);
				finish();
	        }
	     })
	    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // do nothing
	        }
	     }).setIcon(android.R.drawable.ic_dialog_alert);
		
		builder2 =	new AlertDialog.Builder(this)
	    .setTitle("Information")
	    .setMessage("Simulation is Done!")	    
	    .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // do nothing
	        }
	     }).setIcon(android.R.drawable.ic_dialog_alert);
		
		
		
		
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		//getActionBar().setDisplayUseLogoEnabled(true);
		//getActionBar().setIcon(R.drawable.tut);
		getActionBar().setTitle("Topics");
		
		/*getActionBar().setTitle("Trees: Binary Search Trees");
		getActionBar().setDisplayShowHomeEnabled(false);*/
		
		
		
		 
		
		 
		
		 tv = (TextView)findViewById(R.id.tvWriteAlgoTrees);
		 tv.setMovementMethod(new ScrollingMovementMethod());
		 et = (EditText) findViewById(R.id.etTrees);
		 
		 bAdd = (Button)findViewById(R.id.bAddTrees);
		 bDel = (Button)findViewById(R.id.bDel);
		 bRes = (Button)findViewById(R.id.bResetTrees);
		 d= (drawtrees)findViewById(R.id.svTrees);
		 
		 d.setNode(-999);
		 d.done=true;
		 
			 
		  bRes.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ctr=0;
				tv.setText("");
				
				root = null;
				d.resetNode();
				d.done=true;
			}});
		  
		  /*bDel.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String value = et.getText().toString();
				boolean check = false;				 
				 if(value.matches("")){
					 Toast.makeText(getApplicationContext(), "NO INPUT ",Toast.LENGTH_LONG).show();
				 }else{
					
					 if (value.length()-1 >2){
						 Toast.makeText(getApplicationContext(), "Limit Exceeded: Maximum number of input is 2 digits",Toast.LENGTH_LONG).show();
					 }else{
					     for(int i = 0;i<value.length();i++){
						   if((value.charAt(i)>='0'&&value.charAt(i)<='9')||value.charAt(i)=='-'){
							 check = true;
						   }
						 
					     }
					       if(check){
					    	   inputNumbers = Integer.parseInt(et.getText().toString().trim());
								et.setText("");	
								
						   deleteNode(inputNumbers);	
						   ctr--;
						   if(chd){
							   d.copyNode(root);
						  d.delNode(inputNumbers);
						   }else{
							   Toast.makeText(getApplicationContext(), "Input Cannot be deleted", Toast.LENGTH_SHORT).show();
						   }
						   
					       }else{
					    	   Toast.makeText(getApplicationContext(), "Can not have special characters only Integer numbers and spaces only",Toast.LENGTH_LONG).show();
					       }
					}
				 }
			}});*/
		  
		 bAdd.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String value = et.getText().toString();
				boolean check = false;
			
				
				 InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 

			   inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
				
			   
			   if(!d.done){
					 Toast.makeText(getApplicationContext(), "Simulation is still in process please wait for it to be done", Toast.LENGTH_SHORT).show();
					 return;
				 }
				 
				 if(ctr>=15){
					 Toast.makeText(getApplicationContext(), "Tree is Full cannot add anymore value to the tree", Toast.LENGTH_SHORT).show();
					 return;
				 }
				 
				 if(value.matches("")){
					 Toast.makeText(getApplicationContext(), "NO INPUT ",Toast.LENGTH_LONG).show();
				 }else{
					
					 if (value.length() >=4){
						 Toast.makeText(getApplicationContext(), "Limit Exceeded: Maximum number of input is 3 digits",Toast.LENGTH_LONG).show();
					 }else{
					     for(int i = 0;i<value.length();i++){
					    	 if(value.charAt(i)=='-'&&i==value.length()-1){
					    		 check = false;
					    		 break;
					    	 }
					    		 if(value.charAt(i)==' '||value.charAt(i)=='+'){
					    			 check = false;
						    		 break; 
					    		 }
					    		 
						   if((value.charAt(i)>='0'&&value.charAt(i)<='9')||value.charAt(i)=='-'){
							 check = true;
						   }else{
							   check = false;
							   break;
						   }
						 
					     }
					     
					     inputNumbers = Integer.parseInt(et.getText().toString().trim());
					     
					     if(inputNumbers>99||inputNumbers<-99){
					    	Toast.makeText(getApplicationContext(), "Range are only from -99 to 99",Toast.LENGTH_SHORT).show();
					    	 return;
					     }
					       if(check){	
					    	   Toast.makeText(getApplicationContext(), "Simulation has started", Toast.LENGTH_SHORT).show(); 
					    	
								et.setText("");	
								if(ctr!=15){
								ctr++;
								operate();
								if(d.done&&!exceed){
								d.setNode(inputNumbers);
								}
								
						   
								}
					       }else{
					    	   Toast.makeText(getApplicationContext(), "Can not have special characters only Integer numbers",Toast.LENGTH_SHORT).show();
					       }
					}
				 }
				
			}});
		
	}
	
	
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(vo!=null){
		vo.ttobj.stop();
		vo.ttobj.shutdown();
		}
		finish();
	}




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		mvoice = menu;
		return true;
	}
	
	public void writeAlgo(String msg){
		
		if(msg.contains("|")){
			String num = msg.substring(1, msg.lastIndexOf("|"));
			msg = num.concat(msg.substring(msg.indexOf(" "),msg.length()));
			
		}
		
		msg.replaceAll("-", "negative");
		msg.replaceAll(">", "greater than");
		msg.replaceAll("<", "less than");
		msg.replaceAll("=", "equal");
		tv.append("\n"+msg);
		
		if(voice){	
			 
			 if(vo.ttobj!=null){
			 vo.ttobj.speak(msg, TextToSpeech.QUEUE_FLUSH, null);
			 vo.doInBackground(msg);
		        
		        
			 }
		}
		
	}
	
		
	public void deleteNode(int num){
		
		if(root==null){
			return;
			
		}else{
			
			focusNode= root;
			@SuppressWarnings("unused")
			Node head= focusNode;
			parent = root;
			
			//focusNode.rightChild = new Node(23);
			
			
			
			
			
			//writeAlgo(root==null?"yes":"No");
			
			
			
			while(true){
				
				if(focusNode==null){
					writeAlgo("Cannot be found "+parent.num);	
					chd = false;
					break;
				}
			
				if(num==focusNode.num){
					if(focusNode==parent.leftChild&&focusNode.rightChild==null&&focusNode.leftChild==null){
						writeAlgo("Deleted Left leave"+parent.leftChild.num);
						writeAlgo(focusNode==null?"OK":"Not OK" +focusNode.num);
						writeAlgo(focusNode==parent.leftChild?"Equal":"NotEqual"+parent.leftChild.num);
						parent.leftChild=null;
						
					//	focusNode=null;
						chd=true;
						break;						
					}
					if(focusNode==parent.rightChild&&focusNode.rightChild==null&&focusNode.leftChild==null){
						writeAlgo("Deleted Right leave"+parent.rightChild.num);
						writeAlgo(focusNode==null?"OK":"Not OK" +focusNode.num);
						writeAlgo(focusNode==parent.rightChild?"Equal":"NotEqual"+parent.rightChild.num);
						parent.rightChild=null;
						
					//	focusNode=null;
						chd=true;
						break;	
					}
					
				}
				
				
				if(num<focusNode.num){
					parent =focusNode;
					writeAlgo(focusNode==focusNode.leftChild?"CHECK EQ":"NOT EQ"+focusNode.num);
					focusNode=focusNode.leftChild;
					writeAlgo("Left moving"+ focusNode.num);
					
					continue;
				}
				if(num>focusNode.num){
					parent = focusNode;
					writeAlgo(focusNode==focusNode.rightChild?"CHECK EQ":"NOT EQ"+focusNode.num);
					focusNode=focusNode.rightChild;
					writeAlgo("Right moving"+ focusNode.num);
					continue;
				}
				//else{
					/*	if(num<focusNode.num){
							parent =focusNode;
							focusNode=focusNode.leftChild;
						}else{
							parent = focusNode;
							focusNode=focusNode.rightChild;
						}
					*///}
					
			
					
				
				
				
			}
			
			//root = head;
			
			writeAlgo("root "+root.num);
			writeAlgo(root.leftChild==null?"left is null":"left"+root.leftChild.num);
			
		}
		
		
		
	}
	
	public void operate() {

	Thread s = new Thread(new Runnable() {

			public void run() {
									
					AddNode(inputNumbers);	

			}
		});
		s.start();
		
		/*try {
			s.join();
			Toast.makeText(getApplicationContext(), "Hi!", Toast.LENGTH_SHORT).show();	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	
	
	
	
	public void AddNode(final int num){
		tempNumber=num;
		 Node newNode = new Node(num);
		
	        if(root ==null){
	        root = newNode;
	        root.level =0;
	        runOnUiThread(new Runnable(){
                @Override 
            public void run() {
                // dispaly toast here;                    	
                	writeAlgo("if(root==null)\nroot = newNode("+root.num+");");
                	
                	 Toast.makeText(getApplicationContext(), "Simulation Done!", Toast.LENGTH_SHORT).show(); 
                }  });
	         
	           
	        }else{
	        	 runOnUiThread(new Runnable(){
	                 @Override 
	             public void run() {
	                 // dispaly toast here;                    	
	                 	writeAlgo("if(root!=null)\nfocusNode = root;\nnum = "+num);
	                 }  });
	        	parent = root;
	            focusNode = root; // if root is not null   
	           xNode= root.num;
	          int level = 1;
                   
	            while(true){
	            	 	try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	                parent = focusNode;
	                if(level>3){
	               		 runOnUiThread(new Runnable(){
	   	                     @Override 
	   	                	 public void run() {	
	   	                    	
	   	                    //	 Toast.makeText(getApplicationContext(),"ERROR: "+focusNode.num+"", Toast.LENGTH_SHORT).show();
	   	                		 writeAlgo("Maximum Level of tree Reached");
	   	                		// Toast.makeText(getApplicationContext(), "VALUE", Toast.LENGTH_LONG).show();
	   	                		
	   	                         }  });
	               		 ctr--;
	               		 
	               		 return;
	               	}
	               
	                if(num < focusNode.num){
	                	
	               
	                	 runOnUiThread(new Runnable(){
	                     @Override 
	                	 public void run() {	
	                    	
	                    //	 Toast.makeText(getApplicationContext(),"ERROR: "+focusNode.num+"", Toast.LENGTH_SHORT).show();
	                		 writeAlgo("if(num < focusNode.num)\nfocusNode = focusNode.leftChild;");
	                		// Toast.makeText(getApplicationContext(), "VALUE", Toast.LENGTH_LONG).show();
	                		
	                         }  });
	                	 xNode = focusNode.num;
	                    focusNode = focusNode.leftChild;
	                    
	                    if(focusNode == null){
	                        parent.leftChild = newNode;
	                       parent.leftChild.level=level;
	                      
	                       try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                       runOnUiThread(new Runnable(){
	  	                     @Override 
	  	                	 public void run() {
	  	                    	 writeAlgo("if(focusNode==null)\nparent.leftChild=newNode("+parent.leftChild.num+")");
	                     //  writeAlgo("Assign left child " +parent.leftChild.num +" at Level " +parent.leftChild.level);
	  	                    	try {
									Thread.sleep(3000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	  	                    	 Toast.makeText(getApplicationContext(), "Simulation Done!", Toast.LENGTH_SHORT).show();
	  	                     }});
	                      
	                        return;
	                    }
	                    
	                   
	                }else{
	                	
	                	/*try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
	                	 runOnUiThread(new Runnable(){
	  	                     @Override 
	  	                	 public void run() {
	  	                    	writeAlgo("if(num >= focusNode.num)\nfocusNode = focusNode.rightChild;");
	                     //writeAlgo("|"+tempNumber +"| >= "+xNode);
	  	                     }});
	                	 xNode = focusNode.num;	
	                    focusNode = focusNode.rightChild;  
	                    
	                    if(focusNode == null){	                     
	                    parent.rightChild = newNode;
	                    parent.rightChild.level=level;
	                   // xNode = parent;
	                    try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                    runOnUiThread(new Runnable(){
	  	                     @Override 
	  	                	 public void run() {
	  	                    	 writeAlgo("if(focusNode==null)\nparent.rightChild=newNode("+parent.rightChild.num+");");
	                   // writeAlgo("Assign right child " +parent.rightChild.num+" at Level "+parent.rightChild.level);
	  	                    	try {
									Thread.sleep(3000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	  	                    	 Toast.makeText(getApplicationContext(), "Simulation Done!", Toast.LENGTH_SHORT).show();
	  	                     }});
	                    
	                    return;
	                    }
	                   
	                }
	               
	             	try {	             
	             		if(root== focusNode){
	             			Thread.sleep(100);
	             		}
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	              level++;
	              
	            }
	         
	        }
		
	}




	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if(!d.done){
			builder1.show();
			}else{
				finish();
				super.onBackPressed();
			}
	}
private	class VoiceOperation extends AsyncTask<String, Void, String> {
		 public TextToSpeech ttobj;
		 Context con;
		 public VoiceOperation(Context con){
			 this.con=con;
		 }
		 
		 @Override
		    protected String doInBackground(String... params) {
				ttobj=new TextToSpeech(con,new TextToSpeech.OnInitListener() {
				      @Override
				      public void onInit(int status) {
				         if(status != TextToSpeech.ERROR){
				             ttobj.setLanguage(Locale.US);
				            }				
				         }
				      });
				while(ttobj.isSpeaking()){
		        	try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		        return null;
		    }

		    @Override
		    protected void onPostExecute(String result) {
		    }

		    @Override
		    protected void onPreExecute() {
		    
		    }

		    @Override
		    protected void onProgressUpdate(Void... values) {
		    	
		    }
		    
		} 
}

	
//ETO CORE NG LAMAN NG BAWAT INPUT KO

class Node {
	public int num; // IMPORTANT	
	public Node leftChild; // IMPORTANT NEW CLASS SA LOOB NG CLASS
	public Node rightChild; // IMPORTANT CLASS sA LOOB NG CLASS	
	public boolean select =false;
	public int level;
	public int order=-1;
	public Node(int num) {
		this.num = num;

	}
}


