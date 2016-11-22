package com.android.databluesv5;

import java.util.LinkedList;
import java.util.Stack;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class drawPostfix extends SurfaceView implements SurfaceHolder.Callback   {
	int x,y;
	@SuppressWarnings("unused")
	private Context context;
	
	Canvas canvas;
	Paint p = new Paint();
	String [] exp = new String[1];
	
	int move=-2;
	boolean finish =true;
	 @SuppressWarnings("rawtypes")
	LinkedList li = new LinkedList();
 	@SuppressWarnings("rawtypes")
	Stack s = new Stack();
 	Object[] ans,st;
	String message="";
 	
 	
    private Canvas c;
  
    public AnimationThread thread;
      
    public drawPostfix(Context context, AttributeSet attrs) {
        super(context, attrs);

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
               
        thread = new AnimationThread(holder);
        
    }
    
   
  
    public void setString(String [] exp){
    	this.exp = new String[exp.length];
    	this.exp=exp;
    	finish= false;
    	move=0;
    	li.clear();
       thread.setRunning(true);
       }
    
    public String getMessage(){
    	return message;
    }
    
    class AnimationThread extends Thread {
    	 Bitmap nbox = BitmapFactory.decodeResource(getResources(), R.drawable.nullbox);
    	 Bitmap singlybox = BitmapFactory.decodeResource(getResources(), R.drawable.sgbox);
    	private boolean mRun;       
        private SurfaceHolder mSurfaceHolder;        
        
        public AnimationThread(SurfaceHolder surfaceHolder) {
            mSurfaceHolder = surfaceHolder;
           p= new Paint();
        }

        @Override
        public void run() {
        	
            while (mRun) {
                c = null;
                try {
                	 c = mSurfaceHolder.lockCanvas(null);
                	 Rect bg = new Rect();
         			bg.set(0, 0, drawPostfix.this.getWidth(), drawPostfix.this.getHeight());
         			p.setColor(Color.rgb(172, 227, 230));
         			p.setStyle(Paint.Style.FILL);
         			c.drawRect(bg, p);
         			
         			p.setColor(Color.BLACK);
         			p.setStyle(Paint.Style.STROKE);
         			p.setTextSize(13);
         			bg.set(1,1,drawPostfix.this.getWidth()-1,drawPostfix.this.getHeight()-1);
         			c.drawRect(bg,p);
                     synchronized(mSurfaceHolder){                    		
                    	doDraw(c);                    	
                     }
                    	
                        
                } catch (Exception e) {                 
                    e.printStackTrace();
                }finally {
                    if (c != null) {
                        mSurfaceHolder.unlockCanvasAndPost(c);
                    }
                }
                try {
                	
					sleep(4500);
                	
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            
        }

        
       
        
        
        @SuppressWarnings("unchecked")
		private void doDraw(Canvas canvas) {
        	int px = (int) (getResources().getDisplayMetrics().density * 1);
        	int x =20;
        	Paint moveColor = new Paint();
        	moveColor.setColor(Color.YELLOW);
        	moveColor.setShadowLayer(20, 0, 0, Color.BLACK);
        	Paint pt = new Paint();
        	pt.setTextSize(10*px);
        	x=55;
        	y=30;
        	
        	canvas.drawText("Stack", 20, 50, pt);
        	canvas.drawText("Infix Expression ", (getWidth()/2)-x, 20, pt);
        	canvas.drawText("Postfix Expression", (getWidth()/2)-x, getHeight()-10, pt);
        	//UPPER SET GIVEN BY THE USER
        	pt.setTextSize(20*px);
        	
        	for(int i = 0;i<=9;i++){
    			Rect r = new Rect(x*px,y*px,(x+(30-(5*px)))*px,(y+20)*px);
    		//	canvas.drawText(move+"", 200, 10, p);
    			if(move==i&&move!=-2&&move!=exp.length){    				
    				canvas.drawRect(r, moveColor);    				
    			}else{
            	canvas.drawRect(r, p);
    			}
					if (i < exp.length&&exp[0]!=null) {
						canvas.drawText(exp[i]+"",(x + 8)*px, (y + 16)*px, pt);

					}
            	
					x+=(40-(6*px));            	
            }
        	
        	
        	@SuppressWarnings("unused")
			int ctr =0;
        	if(s.isEmpty()){ 
        		st = null;
        	}else{
        		st = s.toArray();
        		ctr=st.length;
        	}
        	
        	//STACKS
        	 x =20;
        	 int y =(70)-(10*px);
         	pt.setTextSize(20*px);
        	
        	
        	
        	// canvas.drawText(exp[move], 200, 10, p);
        	
        	for(int i = 0;i<=7;i++){
			Rect r = new Rect(x,y*px,(x+(30-(5*px)))*px,(y+(20-(3*px)))*px);
			if(st!=null && i ==7-(st.length-1)){
				canvas.drawRect(r, moveColor);
			}else{
        	canvas.drawRect(r, p);
			}
        	if(st!=null&&ctr!=-1&& i==7-(ctr-1))  {
    			canvas.drawText(st[--ctr]+"",(x+8)*px, (y+16)*px, pt);
            }
        	y+=20-(2*px);
        	}
        	
        	
        	
        	//final ANSWER
        	
        	x=55-(5*px);
        	y=getHeight()-(40*px);//getHeight()-(50*px);
        	pt.setTextSize(20*px);x=55;
        	
        	 ctr=0;
        	if(li.isEmpty()){ 
        		ans = null;
        	}else{
        		ans = li.toArray();
        		ctr= ans.length;
        	}
        	//canvas.drawText(li.toArray().length+" "+move, getWidth()-100, 10, p);
        
        	
        	
        	//logic
        	for(int i = 0;i<=9;i++){
    			Rect r = new Rect(x*px,y,(x+(30-(5*px)))*px,(y+(20*px)));
            	canvas.drawRect(r, p);
            	if(ans!=null&&i<ans.length)  {         	
    			canvas.drawText(ans[i]+"", (x+8)*px, (y+(16*px)), pt);
            	}
            	x+=40-(6*px);
            	}
        	
        	//li.clear();
        	while (!s.empty()&& move==exp.length) {
                li.add(s.pop());
                
                return;
            }
        	if((li.contains(")")||li.contains("("))&&move==exp.length){
        		li.remove(")");
        		li.remove("(");
        	}
        	if(move==exp.length){
        		finish = true;
        		pt.setTextSize(12*px);
        		canvas.drawText("done!", 10*px, 10*px, pt);
        		return;
        	}
        	
        	int i =0;
        	
               i=move;
               if (exp[i].equals("(")|| exp[i].equals("-") || exp[i].equals("+") || exp[i].equals("/") || exp[i].equals("*")|| exp[i].equals("^")) {
                   if (s.empty()) {
                       s.push(exp[i]);
                       move++;
                       System.out.println((s.lastElement()));
                       return;
                   } else {
                	  
                       //if stack not empty
                      
                        if (s.lastElement().equals("+") || s.lastElement().equals("-")) {
                        	// last element is +,-
                            if(exp[i].equals("+")||exp[i].equals("-")){
                            // input is +,-	
                            while(!s.empty()||s.lastElement().equals("-")||s.lastElement().equals("+")){
                            	//do until it is empty
                            li.add(s.pop());
                            }
                            //push current value
                           s.push(exp[i]);
                           move++;
                           return;
                            }else{
                            	//do if input != -,+
                            	move++;
                             s.push(exp[i]);
                          return;
                            }
                            
                       } else if (s.lastElement().equals("*") || s.lastElement().equals("/")) {
                           if (exp[i].equals("+") || exp[i].equals("-")) {

                               while (!s.empty()) {
                               System.out.println("hi");    
                                  // System.out.println(s.lastElement()+" "+exp[i]);
                                   if(s.empty()){
                                   break;}
                                   li.add(s.pop());
                                   return;
                               }
                              
                               s.push(exp[i]);
                               move++;
                              return;
                           } else {
                               if(exp[i].equals("*")||exp[i].equals("/")){
                            	   while(s.empty()||s.lastElement().equals("*")||s.lastElement().equals("/")){
                            		   	if(s.empty()){
                            		   		break;}
                                   li.add(s.pop());
                                  
                                   return;
                               		}
                               }
                               s.push(exp[i]);
                               move++;
                               return;
                           }
                       } else if (s.lastElement().equals("^")) {
                           if (!exp[i].equals("(") || !exp[i].equals(")")) {
                               
                               while (s.empty()||s.lastElement().equals("^") || s.lastElement().equals("(")) {
                                   if(s.empty()){
                                       break;
                                   }
                                   if(s.lastElement().equals("^")){
                                       li.add(s.pop()); 
                                   }else{
                                   li.add(s.pop());
                                   }
                               }
                                  
                               s.push(exp[i]);
                               move++;
                             return;
                               }else{
                                s.push(exp[i]);
                                move++;
                               return;
                               }
                           
                       }else if (s.lastElement().equals("(")){                        
                           s.push(exp[i]);
                           move++;
                           return;
                       }  
                       
                       
                   }//empty
                       
                   }//numbers not
               if(exp[i].equals(")")&&!s.empty()){
             	  while (s.empty()||!s.lastElement().equals("(")){
                       if(s.empty()){
                       break;
                       }
                   li.add(s.pop());
                   
                  return;
                   
                   }
             	  
             	  s.pop();
             	  move++;
                   return;
                 //when WE MEET ( THE MATCH
                }
               if(!exp[i].equals("(") || !exp[i].equals(")")) {
               li.add(exp[i]);
               move++;
               return;               
             }
         
          
           
          
        	
        	
    }	
  

        public void setRunning(boolean b) {
            mRun = b;
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    public void surfaceCreated(SurfaceHolder holder) {
    	  for(int i =0;i<exp.length;i++){
    		  exp[i]=null;
    	  }
        thread.setRunning(true);     	   
        thread.start();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        thread.setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
            
            }
        }
    }
}
	