package com.android.databluesv5;




import java.util.LinkedList;
import java.util.Locale;
import java.util.Stack;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
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

public class prefix extends Activity {
	VoiceOperation vo;
	TextView tv;	
	EditText et;
	Thread t;
	
	 @SuppressWarnings("rawtypes")
	LinkedList li = new LinkedList();
	 	@SuppressWarnings("rawtypes")
		Stack s = new Stack();
	
	String[] n;
	Button bplay;
	drawPrefix d;
	
	Dialog howto;
	boolean voice = false;
	Menu mvoice;
	Thread ts;
	//boolean back = false;
	AlertDialog.Builder builder1;
	 AlertDialog alert1;
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.HowTo:
			if(d!=null){
				if(!d.finish){
					
					 runOnUiThread(new Runnable() {
					        @Override
					        public void run() {
					Toast.makeText(getApplicationContext(), "You may access How to after the Simulation is done", Toast.LENGTH_SHORT).show();
					
					        }
					        });
					        return true;
				}
			}
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
		//	back = true;
			
			if(d!=null){
			//d.thread.setRunning(false);
				if(!d.finish){
					
					
				

			
			builder1.show();
				        	
				        	
				        	
					
					return true;
					
				}else{
				
				if(vo!=null){
				vo.ttobj.stop();
				vo.ttobj.shutdown();
				}
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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setWindowAnimations(R.style.Fade);
		setContentView(R.layout.prefix_layout);
		 vo = new VoiceOperation(getApplicationContext());
		 vo.execute();
		 
	builder1 =	new AlertDialog.Builder(this)
	    .setTitle("Warning!")
	    .setMessage("Are you sure you want to exit the simulation?")
	    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // continue with delete
	        	if(vo!=null){
	        	vo.ttobj.stop();
				vo.ttobj.shutdown();
	        	}
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
		getActionBar().setTitle("Topics");
		//getActionBar().setDisplayUseLogoEnabled(true);
		//getActionBar().setIcon(R.drawable.tut);
		
		
		/*getActionBar().setTitle("Stacks: Infix to Prefix");
		getActionBar().setDisplayShowHomeEnabled(false);*/
		
	//	getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		
		 
		
		 tv = (TextView)findViewById(R.id.tvAlgoWritePrefix);
		 tv.setMovementMethod(new ScrollingMovementMethod());
		 et = (EditText) findViewById(R.id.etPrefix);
		 bplay = (Button)findViewById(R.id.btnPlayPrefix);
		
		 d= (drawPrefix)findViewById(R.id.svPrefix);
		
		  
		
		
		 bplay.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// d= (drawPrefix)findViewById(R.id.svSingly);
				
				
				
				
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
								 if(n[i+1].equals("(")||n[i+1].equals(")")||n[i+1].equals("+")||n[i+1].equals("-")||n[i+1].equals("*")||n[i+1].equals("/")||n[i+1].equals("^")||n[i+1].matches("[A-Za-z0-9]")){
									 check = true;
									 i+=1;
								 }else{
									 check =false;
									 Toast.makeText(getApplicationContext(), "not an infix Expression", Toast.LENGTH_SHORT).show();
									 break;
								 }
							 }
						 }
						/*Stack pa = new Stack();
						 for(int i =0;i<n.length;i++){
							 if(n[i].equals("(")||n[i].equals(")")){
								 pa.push(n[i]);
								 
							 }
							 
						 }
						//cannot still do parenthesis balance
						 
						 for(int i =0;i<n.length;i++){
							 if(n[i].equals("(")||n[i].equals(")")){
								
								 
							 }
							 
						 }*/
						 
						 
						// Toast.makeText(getApplicationContext(), "Operator "+ operator +" Operand "+ operand+" op "+op +" oc "+ oc, Toast.LENGTH_LONG).show();
						 
						
						 
						 
						 if(!check){
							 Toast.makeText(getApplicationContext(), "No Special Characters only A-Z, 0-9, (,),*,-,+,/,^ are allowed", Toast.LENGTH_SHORT).show();
						 }else{
							
							 InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 

						     inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
							//Toast.makeText(getApplicationContext(), "Cannot Click or go back while simulation is in process", Toast.LENGTH_SHORT).show();
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
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if(d!=null){
			
			if(!d.finish){
			builder1.show();
			}else{
				finish();
				super.onBackPressed();
			}
		}
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(vo!=null){
		vo.ttobj.stop();
		vo.ttobj.shutdown();
		}
		
	}
	public void writeAlgo(String msg){
		
		tv.append("\n"+msg);		
		
		if(msg.contains("-"))
		msg =msg.replaceAll("\\-", "minus sign");
		if(msg.contains("+"))
		msg = msg. replaceAll("\\+","plus sign");
		//if(msg.contains(")"))
		//msg = msg. replaceAll("\\\"*)*\"","open parenthesis sign");
		//if(msg.contains("("))
		//msg = msg. replaceAll("\\\"*(*\"","close parenthesis sign");
		if(msg.contains("*"))
		msg = msg. replaceAll("\\*","multiplication sign");
		if(msg.contains("/"))
		msg = msg. replaceAll("\\/","division sign");
		if(msg.contains("^"))
		msg = msg. replaceAll("\\^","caret sign");
		if(msg.contains("("))
			msg = msg. replaceAll("\\(","");
		if(msg.contains(")"))
			msg = msg. replaceAll("\\)","");
		if(msg.contains("["))
			msg = msg.replaceAll("\\[", "index of ");
		if(msg.contains("]"))
			msg =msg.replaceAll("\\]", "");
		
			
			 
			if(voice){	
				 
				 if(vo.ttobj!=null){
					 
				 vo.ttobj.speak(msg, TextToSpeech.QUEUE_FLUSH, null);
				 vo.doInBackground(msg);
			        
			        
				 
			}
			 
		}
		
				
	}
	public void operate(){
		

	t =new Thread(new Runnable(){
		String []exp=n;
		
			@SuppressWarnings("unchecked")
			public void run(){
				
			for(int i = n.length-1;i>=0;i--){
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
			 if (exp[i].equals(")") || exp[i].equals("-") || exp[i].equals("+") || exp[i].equals("/") || exp[i].equals("*")|| exp[i].equals("^")) {
                 //if empty stacks
				 setMessage("exp[i] = \""+exp[i]+"\"");
              	if (s.empty()) {
              		s.push(exp[i]);                      
                      setMessage("s.push(\""+exp[i]+ "\")");
                      System.out.println((s.lastElement()));
                     continue;
                  } else {
                  	//if stacks not empty
                 
             //         System.out.println(s.lastElement());
               //       System.out.println(s);
                      
                     // if last element of stack is - , +
                       if (s.lastElement().equals("+") || s.lastElement().equals("-")) {
                      	 //push any operator
                    	   if(!s.empty())  
                    	   setMessage("if (exp[i]  <= \"" +s.lastElement()+"\")");
                    	   
                          s.push(exp[i]);                          
                          setMessage("s.push(\""+exp[i]+ "\")");
                    //      move--;
                          continue;
                      } else if (s.lastElement().equals("*") || s.lastElement().equals("/")) {
                      	// if stack last element is *,/
                          if (exp[i].equals("+") || exp[i].equals("-")) {
                          	//if input is +,-
                              while (s.empty()||(!s.lastElement().equals("+") &&!s.lastElement().equals("-"))) {
                                 // loops until is empty  or last element is not equal to +,- 
                              	
                              	// System.out.println(s.lastElement()+" "+exp[i]);
                                  if(s.empty()){
                                  	//if empty ends the loop
                                  break;
                                  }
                                  // add this to the list
                                  setMessage("if(s.peek() > \""+exp[i]+"\")");
                                  String msg =s.pop()+"";
                                  
                                  setMessage("linklist.add(s.pop()); "+msg);
                                 // setMessage("linklist.add(s.pop()); ");
                                  li.add(msg);
                                  
                           //       move--;
                                
                              }
                             //push the input regardless
                              if(!s.empty())  
                            	  setMessage("if (exp[i]  <= \"" +s.lastElement()+"\")");
                              
                              s.push(exp[i]);
                              setMessage("s.push(\""+exp[i]+ "\")");
                              continue;
                          } else {
                        	   if(!s.empty())  
                        		   setMessage("if (exp[i]  <= \"" +s.lastElement()+"\")");
                        	   
                              s.push(exp[i]);
                             setMessage("s.push(\""+exp[i]+ "\")");
                              
                            //  move--;
                              continue;
                          }
                      } else if (s.lastElement().equals("^")) {
                          if (!exp[i].equals("^") ||  !exp[i].equals(")")) {

                              while (s.empty()||s.lastElement().equals("^") || s.lastElement().equals(")")|| s.lastElement().equals("(")) {
                                  if(s.empty()){
                                      break;
                                  }
                                 
                                 setMessage("if(s.peek() > \""+exp[i]+"\")");
                                  String msg =s.pop()+"";
                                //  setMessage("pop "+msg+ " from stack");
                                  setMessage("linklist.add(s.pop()); ");
                                  li.add(msg);
                            //      move--;
                                  
                              }
                               if(!s.empty())  
                            	   setMessage("if (exp[i]  <= \"" +s.lastElement()+"\")");
                               
                              s.push(exp[i]);
                             setMessage("s.push(\""+exp[i]+ "\")");
                            //  move--;
                              continue;
                              }else{
                            	   if(!s.empty())  
                            		   setMessage("if (exp[i]  <= \"" +s.lastElement()+"\")");
                            	   
                                  s.push(exp[i]);
                                 setMessage("s.push(\""+exp[i]+ "\")");
                          //     move--;
                            continue;
                              }
//                         
                      }else if (s.lastElement().equals(")")){   
                    	   if(!s.empty())  
                    		   setMessage("if (exp[i]  <= \"" +s.lastElement()+"\")");
                    	   
                          s.push(exp[i]);
                         setMessage("s.push(\""+exp[i]+ "\")");
                       //   move--;
                        continue;
                      }  
                      
                      
                  }//empty
                      
                  }//numbers not
              if(exp[i].equals("(")&&!s.empty()){
            	 // setMessage("scanned "+exp[i]);
            	  setMessage("exp[i] = \""+exp[i]+"\"");
              	  while (s.empty()||!s.lastElement().equals(")")){
                        if(s.empty()){
                        break;
                        }
                        
                       setMessage("if(s.peek() > \""+exp[i]+"\")");                        
                        String msg =s.pop()+"";
                     //   setMessage("pop "+msg+ " from stack");  
                        setMessage("linklist.add(s.pop()); ");
                        li.add(msg);
                    
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
           //   move--;
              setMessage("linklist.add("+exp[i]+"); ");
              try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
              System.out.println(li);
             
              }
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!s.empty()){
				
				setMessage("while(!s.empty())");
				while (!s.empty()) {
					
                    String msg =s.pop()+"";
                 //   setMessage("pop "+msg+ " from stack");
                    
                    setMessage("linklist.add(s.pop()); ");
                    li.add(msg);
                    try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
				if(!voice)
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
		});
		
	t.start();
	/*if(voice)
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/	
	if(d.finish){
		t.interrupt();
		t.stop();
		
	}
	


	
	
	
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
		mvoice  = menu;
		return true;
	}
	private	class VoiceOperation extends AsyncTask<String, Void, String> {
		 public TextToSpeech ttobj;
		 Context con;
		 public VoiceOperation(Context con){
			 this.con=con;
			
		 }
		 
		 @Override
		    protected String doInBackground(String... params) {
				if(ttobj!=null){
				while(ttobj.isSpeaking()){
		        	String o = "RUI";
				}
				}
				if(ttobj==null){
				 ttobj=new TextToSpeech(con,new TextToSpeech.OnInitListener() {
				      @Override
				      public void onInit(int status) {
				         if(status != TextToSpeech.ERROR){
				             ttobj.setLanguage(Locale.US);
				            }				
				         }
				      });
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

