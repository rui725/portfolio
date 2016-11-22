package com.android.databluesv5;




import java.util.LinkedList;
import java.util.Locale;
import java.util.Stack;

import android.annotation.SuppressLint;
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

@SuppressLint("DefaultLocale")
public class postfix extends Activity {
		
	TextView tv;	
	EditText et;
	Thread t;
	
	 @SuppressWarnings("rawtypes")
	LinkedList li = new LinkedList();
	 	@SuppressWarnings("rawtypes")
		Stack s = new Stack();
	
	String[] n;
	Button bplay;
	drawPostfix d;
	TextToSpeech ttobj;
	Dialog howto;
	Menu mvoice;
	AlertDialog.Builder builder1;
	boolean voice = false;
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.HowTo:
			//Toast.makeText(getApplicationContext(), "RED BORDER - represent for the max heap\n GREEN BORDER -> the variable to be replace into another variable\n YELLOW BORDER -> place which the variable will be replace.", Toast.LENGTH_LONG).show();
		howto = new Dialog(this);
		howto.setContentView(R.layout.howto_layout);
		ImageView imv = (ImageView)howto.findViewById(R.id.howtopic);
						imv.setImageResource(R.drawable.st2);
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
			if(!d.finish){
				builder1.show();
				return true;
				
			}else{
			
			
			ttobj.stop();
			ttobj.shutdown();
			
			d.thread.interrupt();
			d.finish = true;
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
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if(!d.finish){
			builder1.show();
			}else{
				finish();
				super.onBackPressed();
			}
	}

	@SuppressLint("DefaultLocale")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setWindowAnimations(R.style.Fade);
		setContentView(R.layout.postfix_layout);
		
		builder1 =	new AlertDialog.Builder(this)
	    .setTitle("Warning!")
	    .setMessage("Are you sure you want to exit the simulation?")
	    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // continue with delete
	        	ttobj.stop();
				ttobj.shutdown();
				d.thread.interrupt();
				d.finish = true;
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
		//getActionBar().setDisplayUseLogoEnabled(true);
		//getActionBar().setIcon(R.drawable.tut);
		getActionBar().setTitle("Topics");

		/*getActionBar().setTitle("Stacks: Infix to Postfix");
		getActionBar().setDisplayShowHomeEnabled(false);*/
		
		 
		
		 
		 
		 ttobj=new TextToSpeech(getApplicationContext(), 
			      new TextToSpeech.OnInitListener() {
			      @Override
			      public void onInit(int status) {
			         if(status != TextToSpeech.ERROR){
			             ttobj.setLanguage(Locale.US);
			            }				
			         }
			      });
		
		
		 tv = (TextView)findViewById(R.id.tvAlgoWritePostfix);
		 tv.setMovementMethod(new ScrollingMovementMethod());
		 et = (EditText) findViewById(R.id.etPostfix);
		 bplay = (Button)findViewById(R.id.btnPlayPostfix);
		
		 d= (drawPostfix)findViewById(R.id.svPostfix);
		// d.setString("");
		  
		 ttobj=new TextToSpeech(getApplicationContext(), 
			      new TextToSpeech.OnInitListener() {
			      @Override
			      public void onInit(int status) {
			         if(status != TextToSpeech.ERROR){
			             ttobj.setLanguage(Locale.US);
			             ttobj.setSpeechRate(0.8f);
			            }				
			         }
			      });
		 
		 
		 bplay.setOnClickListener(new OnClickListener(){
			@SuppressLint("DefaultLocale")
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// d= (drawPostfix)findViewById(R.id.svPostfix);
				if(!d.finish){
					Toast.makeText(getApplicationContext(), "Simulation is on going wait for it to Finish", Toast.LENGTH_SHORT).show();
					return;
				}
				boolean check=false;
				String value = et.getText().toString();				
				
				 if(value.matches("")){
					 Toast.makeText(getApplicationContext(), "NO INPUT ",Toast.LENGTH_SHORT).show();
				 }else{
					 String expression = value;
					expression = expression.trim();
				    expression = expression.replace(" ", "");
				    expression = expression.replace("   ", "");
				    expression = expression.replace("+", " + ");
				    expression = expression.replace("*", " * ");
				    expression = expression.replace("/", " / ");
				    expression = expression.replace("(", " ( ");
				    expression = expression.replace(")", " ) ");
				    expression = expression.replace("^", " ^ ");
				    //    expression = expression.replace(" - -", "--");
				    expression = expression.replace("-", " - ");
				    expression = expression.replace("--", " - -");
					expression = expression.replace(")  +",") +");
			        expression = expression.replace(")  *",") *");
			        expression = expression.replace(")  ^",") ^");
			        expression = expression.replace(")  -",") -");
			        expression = expression.replace(")  /",") /");
			        expression = expression.replace("+  (","+ (");
			        expression = expression.replace("*  (","* (");
			        expression = expression.replace("^  (","^ (");
			        expression = expression.replace("-  (","- (");
			        expression = expression.replace("/  (","/ (");
			        expression = expression.trim();
					 
					 
					 
					 
					 
					 n = expression.split(" ");
					 if(expression.contains("* )")){
						 check = false;
						 Toast.makeText(getApplicationContext(), "not an infix Expression", Toast.LENGTH_SHORT).show();
						 return;
					 }else if(expression.contains("^ )")){
						 check = false;
						 Toast.makeText(getApplicationContext(), "not an infix Expression", Toast.LENGTH_SHORT).show();
						 return;
					 }else if(expression.contains("/ )")){
						 check = false;
						 Toast.makeText(getApplicationContext(), "not an infix Expression", Toast.LENGTH_SHORT).show();
						 return;
					 }else if(expression.contains("+ )")){
						 check = false;
						 Toast.makeText(getApplicationContext(), "not an infix Expression", Toast.LENGTH_SHORT).show();
						 return;
					 }else if(expression.contains("- )")){
						 check = false;
						 Toast.makeText(getApplicationContext(), "not an infix Expression", Toast.LENGTH_SHORT).show();
						 return;
					 }else if(expression.contains("( ^")){
						 check = false;
						 Toast.makeText(getApplicationContext(), "not an infix Expression", Toast.LENGTH_SHORT).show();
						 return;
					 }else if(expression.contains("( *")){
						 check = false;
						 Toast.makeText(getApplicationContext(), "not an infix Expression", Toast.LENGTH_SHORT).show();
						 return;
					 }else if(expression.contains("( /")){
						 check = false;
						 Toast.makeText(getApplicationContext(), "not an infix Expression", Toast.LENGTH_SHORT).show();
						 return;
					 }else if(expression.contains("( +")){
						 check = false;
						 Toast.makeText(getApplicationContext(), "not an infix Expression", Toast.LENGTH_SHORT).show();
						 return;
					 }else if(expression.contains("( -")){
						 check = false;
						 Toast.makeText(getApplicationContext(), "not an infix Expression", Toast.LENGTH_SHORT).show();
						 return;
					 }
					 
					 
					 
					 n = expression.split(" ");
					 if (n.length<3||n.length>=11){
						 if(n.length==1){
							 Toast.makeText(getApplicationContext(), "not an infix Expression",Toast.LENGTH_SHORT).show();
							 return;
						 }
						 Toast.makeText(getApplicationContext(), "Limit Exceeded: Maximum count of Characters is 10 Minimum is 3: Currently at length "+n.length,Toast.LENGTH_SHORT).show();
						 return;
					 }else{
					      
						 int operator=0;
						 int operand=0;
						 int op =0;
						 int oc=0;
						 for(int i = 0; i<n.length;i++){
							 if((n[i].matches("^[A-Za-z0-9]"))){
								 operand++;
								 continue;
							 }
							 if((n[i].equals("/") || n[i].equals("*")||n[i].equals("^") ||n[i].equals("+")||n[i].equals("-"))){
		 						operator++;
		 						continue;
		 					 }
							 if(n[i].equals("(")){
								 oc++;
								 continue;
							 }
							 if(n[i].equals(")")){
								 op++;
								 continue;
							 }
							 
							 
						 }
						
						// Toast.makeText(getApplicationContext(), "Operator "+ operator +" Operand "+ operand+" op "+op +" oc "+ oc, Toast.LENGTH_LONG).show();
						 if(oc!=op&&op!=0&&oc!=0){
							 check =false;
							 Toast.makeText(getApplicationContext(), "not an infix Expression", Toast.LENGTH_SHORT).show();
							 return;
					      }else{
					    	  if(operand==(operator+1)){
					    		  check = true;
					    	  }else{
					    		  Toast.makeText(getApplicationContext(), "not an infix Expression", Toast.LENGTH_SHORT).show();
					    		  return;
					    	  }
					      }
						 
						 if(expression.lastIndexOf("(")>expression.lastIndexOf(")")){
							 check = false;
							 Toast.makeText(getApplicationContext(), "not an infix Expression", Toast.LENGTH_SHORT).show();
							 return;
						 };
						 
						 if(expression.indexOf(")")<expression.indexOf("(")){
							 check = false;
							 Toast.makeText(getApplicationContext(), "not an infix Expression", Toast.LENGTH_SHORT).show();
							 return;
						 };
						 
						 
						 for (int i = 0; i<n.length-1;i++){
							 if(n[i].matches("[A-Za-z0-9]")){
								 if(n[i+1].equals("(")||n[i+1].equals(")")||n[i+1].equals("+")||n[i+1].equals("-")||n[i+1].equals("*")||n[i+1].equals("/")||n[i+1].equals("^")){
									 check = true;
									 i+=1;
								 }else{
									 check =false;
									 Toast.makeText(getApplicationContext(), "not an infix Expression", Toast.LENGTH_SHORT).show();
									 break;
								 }
							 }
						 }
					 //
					
						 /*for(int i=0;i<expression.length();i++){
							 if((expression.toUpperCase().charAt(i)>='A'&&expression.toUpperCase().charAt(i)<='Z')||(expression.toUpperCase().charAt(i)>='/'&&expression.toUpperCase().charAt(i)<='9')||(expression.toUpperCase().charAt(i)>='('&&expression.toUpperCase().charAt(i)<='+')||(expression.toUpperCase().charAt(i)=='-')||(expression.toUpperCase().charAt(i)==' '||(expression.toUpperCase().charAt(i)=='^'))){
								 check = true;
							 }else{
								 check = false;
								 break;
							 }
						 }*/
						 if(!check){
							 Toast.makeText(getApplicationContext(), "No Special Characters only A-Z, 0-9, (,),*,-,+,/,^ are allowed", Toast.LENGTH_SHORT).show();
						 }else{
							 InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 

						     inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
						     Toast.makeText(getApplicationContext(), "Simulation has Started!", Toast.LENGTH_SHORT).show();
						     et.setText("");
							 tv.setText("");
						 d.setString(n);
						 operate();
						 }
						 //  ttobj.stop();
						   //d.setString(et.getText().toString().trim());						   
						   //operate();
						   //et.setText("");
					      
					}
				 }
			}});
		 	
		
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ttobj.stop();
		ttobj.shutdown();
		finish();
	}
	public void writeAlgo(String msg){
		tv.append("\n"+msg);		
		if(msg.contains("-"))
		msg =msg.replaceAll("\\-", "minus sign");
		if(msg.contains("+"))
		msg = msg. replaceAll("\\+","plus sign");
		if(msg.contains(")"))
		msg = msg. replaceAll("\\)","open parenthesis sign");
		if(msg.contains("("))
		msg = msg. replaceAll("\\(","close parenthesis sign");
		if(msg.contains("*"))
		msg = msg. replaceAll("\\*","multiplication sign");
		if(msg.contains("/"))
		msg = msg. replaceAll("\\/","division sign");
		if(msg.contains("^"))
		msg = msg. replaceAll("\\^","caret sign");
		
		if(voice)
		ttobj.speak(msg, TextToSpeech.QUEUE_FLUSH, null);
		
		try {
		while(ttobj.isSpeaking()){
			
				Thread.sleep(100);
			
		}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	public void operate(){
		

	new Thread(new Runnable(){
		String []exp=n;
		
			@SuppressWarnings("unchecked")
			public void run(){
				
			for(int i = 0;i<n.length;i++){
				try{
					if(d.finish){
						return;
					}
					if(!voice){
					Thread.sleep(3500);
					}else{
						Thread.sleep(10);
					}
				}catch(Exception e){
					System.out.println("ERROR"+e);
				}
				 if (exp[i].equals("(")|| exp[i].equals("-") || exp[i].equals("+") || exp[i].equals("/") || exp[i].equals("*")|| exp[i].equals("^")) {
					  setMessage("exp[i] = \""+exp[i]+"\"");
					 if (s.empty()) {
						 setMessage("s.push(\""+exp[i]+ "\")");
	                       s.push(exp[i]);
	                       
	                       System.out.println((s.lastElement()));
	                       continue;
	                   } else {
	                	  
	                       //if stack not empty
	                      
	                        if (s.lastElement().equals("+") || s.lastElement().equals("-")) {
	                        	// last element is +,-
	                            if(exp[i].equals("+")||exp[i].equals("-")){
	                            // input is +,-	
	                            while(!s.empty()){
	                            	//do until it is empty
	                            	if(!s.empty())
	                            		  setMessage("if(s.peek() >= \""+exp[i]+"\")");
	                            	
	                                  String msg =s.pop()+"";
	                                 // setMessage("pop "+msg+ " from stack");
	                                  setMessage("linklist.add(s.pop()); ");
	                            }
	                            //push current value
	                            if(!s.empty())
	                            	 setMessage("if (exp[i]  < \"" +s.lastElement()+"\")");
	                              s.push(exp[i]);
	                              setMessage("s.push(\""+exp[i]+ "\")");
	                             // setMessage("push "+exp[i]+ " to stack");
	                          
	                          continue;
	                            }else{
	                            	//do if input != -,+
	                            	 if(!s.empty())
	                            		 setMessage("if (exp[i]  < \"" +s.lastElement()+"\")");
	                            	 
	                                 s.push(exp[i]);
	                                 setMessage("s.push(\""+exp[i]+ "\")");
	                               //  setMessage("push "+exp[i]+ " to stack");
	                          continue;
	                            }
	                            
	                       } else if (s.lastElement().equals("*") || s.lastElement().equals("/")) {
	                           if (exp[i].equals("+") || exp[i].equals("-")) {

	                               while (!s.empty()) {
	                               System.out.println("hi");    
	                                  // System.out.println(s.lastElement()+" "+exp[i]);
	                                   if(s.empty()){
	                                   break;
	                                   }
	                                   setMessage("if(s.peek() >= \""+exp[i]+"\")");
	                                   setMessage("linklist.add(s.pop()); ");
	                                   li.add(s.pop());
	                                   continue;
	                               }
	                              
	                               if(!s.empty())
	                            	   setMessage("if (exp[i]  < \"" +s.lastElement()+"\")");
	                               
	                               s.push(exp[i]);
	                               setMessage("s.push(\""+exp[i]+ "\")");
	                               
	                             continue;
	                           } else {
	                               if(exp[i].equals("*")||exp[i].equals("/")){
	                            	   while(s.empty()||s.lastElement().equals("*")||s.lastElement().equals("/")){
	                            		   	if(s.empty()){
	                            		   		break;}
	                            		    setMessage("if(s.peek() >= \""+exp[i]+"\")");
	                            		   	setMessage("linklist.add(s.pop()); ");
	                                   li.add(s.pop());
	                                  
	                                  continue;
	                               		}
	                               }
	                               
	                               if(!s.empty())
	                            	   setMessage("if (exp[i]  < \"" +s.lastElement()+"\")");
	                               
	                               s.push(exp[i]);
	                               setMessage("s.push(\""+exp[i]+ "\")");
	                              
	                               continue;
	                           }
	                       } else if (s.lastElement().equals("^")) {
	                           if (!exp[i].equals("(") || !exp[i].equals(")")) {
	                               
	                               while (s.empty()||s.lastElement().equals("^") || s.lastElement().equals("(")) {
	                                   if(s.empty()){
	                                       break;
	                                   }
	                                   setMessage("if(s.peek() >= \""+exp[i]+"\")");
	                                   if(s.lastElement().equals("^")){
	                                	   
	                                		setMessage("linklist.add(s.pop()); ");
	                                       li.add(s.pop()); 
	                                   }else{
	                                		setMessage("linklist.add(s.pop()); ");
	                                   li.add(s.pop());
	                                   }
	                               }
	                               if(!s.empty())
	                            	   setMessage("if (exp[i]  < \"" +s.lastElement()+"\")");
	                               
	                               s.push(exp[i]);
	                               setMessage("s.push(\""+exp[i]+ "\")");                            
	                             continue;
	                               }else{
	                            	   if(!s.empty())
	                            		   setMessage("if (exp[i]  < \"" +s.lastElement()+"\")");
	                            	   
	                                   s.push(exp[i]);
	                                   setMessage("s.push(\""+exp[i]+ "\")");
	                             
	                             continue;
	                               }
	                           
	                       }else if (s.lastElement().equals("(")){             
	                    	   if(!s.empty())
	                    		   setMessage("if (exp[i]  < \"" +s.lastElement()+"\")");
	                    	   
	                              s.push(exp[i]);
	                              setMessage("s.push(\""+exp[i]+ "\")");
	                           continue;
	                       }  
	                       
	                       
	                   }//empty
                      
                  }//numbers not
              if(exp[i].equals(")")&&!s.empty()){
            	   setMessage("exp[i] = \""+exp[i]+"\"");
              	  while (s.empty()||!s.lastElement().equals("(")){
                        if(s.empty()){
                        break;
                        }
                        if(!s.empty())
                        	setMessage("if(s.peek() >= \""+exp[i]+"\")");
                        
                        String msg =s.pop()+"";
                        //setMessage("pop "+msg+ " from stack");
                        setMessage("linklist.add(s.pop()); ");
                        li.add(msg);
                        try{
                        	Thread.sleep(2000);
                        }catch(Exception e){
                        	
                        }
                    }
              	  
              	  s.pop();
              	 // move--;
                    continue;
                  //when WE MEET ( THE MATCH
                 }
               
              //when we encounter DIGITS
              if(!exp[i].equals("(") || !exp[i].equals(")")) {
            	  setMessage("if(exp[i]==digit)");
              li.add(exp[i]);
              try{
              	Thread.sleep(2000);
              }catch(Exception e){
              	
              }
           //   move--;
              setMessage("linklist.add("+exp[i]+"); ");
              System.out.println(li);
             
              }
			}
			if(!s.empty()){
				setMessage("while(!s.empty())");
				while (!s.empty()) {
					
                    String msg =s.pop()+"";
                  //  setMessage("pop "+msg+ " from stack");
                    setMessage("linklist.add(s.pop()); ");
                    li.add(msg);
                    try{
                    	Thread.sleep(2000);
                    }catch(Exception e){
                    	
                    }
				}
				runOnUiThread(new Runnable(){
                    @Override 
                public void run() {
                    // dispaly toast here;                    	
                    	//writeAlgo("creates new node\nNode n = new Node;  ");
                    	Toast.makeText(getApplicationContext(), "Simulation Done!", Toast.LENGTH_SHORT).show();
                    }  });
			}else{
				setMessage("Done");
			}
			
			
                
                
            
        	if((li.contains(")")||li.contains("("))){
        		li.remove(")");
        		li.remove("(");
        	}
			 
			try {
				
				Thread.sleep(2000);
				runOnUiThread(new Runnable(){
                    @Override 
                public void run() {
                    // dispaly toast here;                    	
                    	//writeAlgo("creates new node\nNode n = new Node;  ");
                    	
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

	public void setMessage( final String m){
		runOnUiThread(new Runnable(){
            @Override 
        public void run() {
            // dispaly toast here;                    	
            	writeAlgo(m);
            	
            }  });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		mvoice= menu;
		return true;
	}

}
