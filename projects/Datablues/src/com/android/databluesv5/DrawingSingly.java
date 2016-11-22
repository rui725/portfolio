package com.android.databluesv5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawingSingly extends SurfaceView implements SurfaceHolder.Callback   {
	float x,y;
	private Context context;
	
	String[] data = new String[10];
	
	String[] hold = new String[10];
	
	SinglyFunction df = new SinglyFunction();
	
	Canvas canvas;
	Paint p = new Paint();
	Paint pt = new Paint();
	int i = 0, ipos;	
    private Canvas c;
  public AnimationThread thread;
    
	int px = (int) getResources().getDisplayMetrics().density * 1;
      
    boolean fix, rem;
    
    public DrawingSingly(Context context, AttributeSet attrs) {
        super(context, attrs);
          
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        thread = new AnimationThread(holder);
         
    }
    
    public void delete(){
    	for(int ctr = 0 ; ctr < data.length - 1 ; ctr++){
    		data[ctr] = data[ctr+1];
    	}
    	data[9] = null;
    	
    	i--;
    	
    	rem = true;
    	
    	thread.setRunning(true);
    }
    
    public void setData(String axe){
    	data[i] = axe;
    	i++;
    	
       thread.setRunning(true);
       }
    
    public void fixedsetData(String axe, int pos){
    	String hold[] = new String[data.length];
        for(int i = 0; i < pos; i++)
            hold[i] = data[i];
        
        hold[pos] = axe;
        
        for(int i = pos + 1; i < data.length; i++)
            hold[i] = data[i - 1];
        
        for(int i = 0 ; i < 10 ; i++)
        	data[i] = hold[i];
        
    	i++;
    	
       thread.setRunning(true);
       
       ipos = pos;
       fix = true;
    }
    
    public void reset(){
    	for(int ctr = 0 ; ctr < 10 ; ctr++){
    		data[ctr] = null;
    	}
    	i = 0;
    	
    	thread.setRunning(true);
    }
    
    public void resetcanvas(){
    	for(int ctr = 0 ; ctr < 10 ; ctr++){
    		hold[ctr] = data[ctr];
    	}
    	
    	for(int ctr = 0 ; ctr < 10 ; ctr++){
    		data[ctr] = null;
    	}

    	thread.setRunning(true);
    	
    	for(int ctr = 0 ; ctr < 10 ; ctr++){
    		data[ctr] = hold[ctr];
    	}
    	
    	thread.setRunning(true);
    }
    
    class AnimationThread extends Thread {
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
         			bg.set(0, 0, DrawingSingly.this.getWidth(), DrawingSingly.this.getHeight());
         			p.setColor(Color.rgb(172, 227, 230));
         			p.setStyle(Paint.Style.FILL);
         			c.drawRect(bg, p);
         			
         			p.setColor(Color.BLACK);
         			p.setStyle(Paint.Style.STROKE);
         			p.setTextSize(13*px);
         			bg.set(1,1,DrawingSingly.this.getWidth()-1,DrawingSingly.this.getHeight()-1);
         			c.drawRect(bg,p);
         			if(i != 0 ){
                        synchronized(mSurfaceHolder){
                       	 try{
                   			doLine(c);
                        	}catch(Exception e){
                        		e.printStackTrace();
                        	}
                       	try{
                       		/*if(fix)
                       			Thread.sleep(2000);*/
                                	doText(c);
                               	doShape(c);
                       	}catch(Exception e){
                       		e.printStackTrace();
                       	}
                        }
         			}
                    	
                        
                } catch (Exception e) {                 
                    e.printStackTrace();
                }finally {
                    if (c != null) {
                        mSurfaceHolder.unlockCanvasAndPost(c);
                    }
                }
                try {
                	//sleep ka na dito
                	sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            
        }
        
        private void doLine(Canvas canvas){
 			p.setStyle(Paint.Style.FILL);
        	int initial = 60;
            int x = 40;
            int y = 25;
           if(data[0] == null && data[1] == null ){
           	return;
           }
            for(int i = 0; i < data.length; i++){
            	if((data[i] == null && data[i+1] == null) && i != 9){
            		break;
            	}
            	if(i==10){
            		break;
            	}
	        	if(i>0){
	        		if(fix && ipos == i){
	    				canvas.drawLine((initial + y - x - 8)*px, 103*px, (initial + y + 7 - x)*px, 103*px, p);
	    	        	canvas.drawText(">", (initial + y - x - 1)*px, 107*px, p);
	    	        	initial = (initial + x);
	        		}
	        		else{
	    				canvas.drawLine((initial + y - x - 8)*px, 103*px, (initial + y + 7 - x)*px, 103*px, p);
	    	        	canvas.drawText(">", (initial + y - 1 - x)*px, 107*px, p);
	    	        	initial = (initial + x);	        			
	        		}
	        	}
            }
            fix = false;
        	rem = false;
            }

        private void doText(Canvas canvas){
 			p.setStyle(Paint.Style.FILL);
        	int initial = 10;
            int x = 40;
            int y = 25;
           if(data[0] == null && data[1] == null ){
           	return;
           }
           for(int i = 0; i < data.length; i++){
           	if((data[i] == null && data[i+1] == null) && i != 9){
           		break;
           	}
           	if(i==10){
           		break;
           	}
           	if(data[i].length() > 2){
           		if(fix && ipos == i){
           			canvas.drawText(data[i].substring(0, 2)+"",(initial + 3)*px,50*px, p);}
           		else{
           			canvas.drawText(data[i].substring(0, 2)+"",(initial + 3)*px,110*px, p);
            		initial = (initial + x);
           		}
           	}
        	else{
        		if(fix && ipos == i){
        			canvas.drawText(data[i]+"",(initial + 3)*px,50*px, p);}
        		else{
        			canvas.drawText(data[i]+"",(initial + 3)*px,110*px, p);

            		initial = (initial + x);
        		}
        	}
        	
           }
        }
        
        private void doShape(Canvas canvas) {
 			p.setStyle(Paint.Style.STROKE);
        	if(fix)
        	resetcanvas();
            int initial = 10;
            int x = 40;
            int y = 25;
           if(data[0] == null && data[1] == null ){
           	return;
           }
            for(int i = 0; i < data.length; i++){
            	if((data[i] == null && data[i+1] == null) && i != 9){
            		break;
            	}
            	if(i==10){
            		break;
            	}
            	if(fix && ipos == i){
         			p.setStyle(Paint.Style.STROKE);
    	        	Rect r = new Rect(initial*px,30*px,(initial+y)*px,60*px);
    	        	canvas.drawRect(r, p);

    	 			p.setStyle(Paint.Style.FILL);
        			canvas.drawLine(((initial)+10)*px, 60*px, ((initial)+10)*px, 90*px, p);
        			canvas.drawText("v", ((initial)+18)*px, 60*px, p);
        			
            	}
            	else{
         			p.setStyle(Paint.Style.STROKE);
	        	Rect r = new Rect(initial*px,120*px,(initial+y)*px,90*px);
	        	canvas.drawRect(r, p);

	 			p.setStyle(Paint.Style.FILL);
	        	if(i==0){
	        		canvas.drawText("[0]",(initial + 8)*px,140*px, p);
		        	canvas.drawText("[head]", (initial - 3)*px, 160*px, p);
	        		if(data[i+1] == null){
	        		canvas.drawText("[tail]", (initial)*px, 180*px, p);
		        	canvas.drawLine((initial+y)*px, 105*px, (initial+y+6)*px, 105*px, p);
		        	canvas.drawText("> null", (initial+y+3)*px, 110*px, p);
		        	}
	        	}else
	        	{
	        		canvas.drawText("[" + i + "]",(initial + 8)*px,140*px, p);
	        		if(data[i+1] == null){
	        		canvas.drawText("[tail]", (initial)*px, 160*px, p);
	        		if(i == 7){
	        			canvas.drawLine((initial+x-30)*px, 90*px, (initial+x-30)*px, 60*px, p);
	        			canvas.drawText("^", (initial+x-33)*px, 63*px, p);
	        			canvas.drawText("null", (initial + x - 36)*px, 54*px, p);
	        		}
	        		else{
			        	canvas.drawLine((initial+y)*px, 105*px, (initial+y+6)*px, 105*px, p);
			        	canvas.drawText("> null", (initial+y+3)*px, 110*px, p);
	        		}
	        		}
	        	}
	        	
	            initial = (initial + x);}
            }
        }
        
		public void setRunning(boolean b) {
            mRun = b;
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }
 
    
    public void surfaceCreated(SurfaceHolder holder) {
    	 px = (int) (getResources().getDisplayMetrics().density * 1);
    	 pt = new Paint();
    	 pt.setTextSize(10*px);
    	   for(int i =0;i<10;i++){
    		   data[i] = null;
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
