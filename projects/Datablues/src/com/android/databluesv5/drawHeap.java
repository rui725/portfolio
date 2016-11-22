package com.android.databluesv5;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class drawHeap extends SurfaceView implements SurfaceHolder.Callback   {
	int x,y;
	int m,type,num1,num2,ind3x;
	Rect rec[];
	int[] data = new int[10];
	int[] fixedNum;
	int[] indxing;
    private  int[] arrData;
	Canvas canvas;
	Paint p = new Paint();
	Paint t = new Paint();
	Paint swap1 = new Paint();
	Paint swap2 = new Paint();
	Paint fixed = new Paint();
    private Canvas c;
    public AnimationThread thread;
    boolean done = true;
    boolean nxt = false;
    boolean swap = false;
    boolean fixedData = false;
    boolean userData = true;
    int ctr;
	int px = (int) getResources().getDisplayMetrics().density * 1;
    public drawHeap(Context context, AttributeSet attrs) {
        super(context, attrs);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        thread = new AnimationThread(holder);   
    }
    
    public void setInteger(int[] num){
    	for(int i = 0; i < num.length; i++){
    		data[i] = num[i];
    		done = false;
    		if(userData){
    	     	   arrData = data;
    	    }
    		userData = false;
    	}
       
    	
       rec= new Rect[num.length];
       thread.setRunning(true);
       }
    public void setMHindex(int colorIndex,int typ){
    	m = colorIndex;
    	type = typ;
    	done = true;
       }
    public void setSWAPPED(int x1, int x2){
    	swap = true;
    	num1 =x1;
    	num2 =x2;
       }
    public void fixedNum(int indx, boolean con){
    	fixedData = con;
    	ind3x = indx;
    	//indxing[indx];
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
         			bg.set(0, 0, drawHeap.this.getWidth(), drawHeap.this.getHeight());
         			p.setColor(Color.rgb(172, 227, 230));
         			p.setStyle(Paint.Style.FILL);
         			c.drawRect(bg, p);
         			p.setColor(Color.BLACK);
         			p.setStyle(Paint.Style.STROKE);
         			p.setTextSize(50);
         			swap1.setColor(Color.RED);
                	swap1.setStyle(Style.FILL_AND_STROKE);
                	swap1.setShadowLayer(2,2,2, Color.RED);
                	swap2.setColor(Color.MAGENTA);
                	swap2.setStyle(Style.FILL_AND_STROKE);
                	swap2.setShadowLayer(2,2,2, Color.RED);
                	fixed.setColor(Color.BLACK);
                	fixed.setStyle(Style.FILL_AND_STROKE);
                	fixed.setShadowLayer(2,2,2, Color.BLACK);
        			t.setColor(Color.WHITE);
         			t.setStyle(Paint.Style.STROKE);
         			t.setTextSize(50);
         			bg.set(1,1,drawHeap.this.getWidth()-1,drawHeap.this.getHeight()-1);
         			c.drawRect(bg,p);
                     synchronized(mSurfaceHolder){
                    	 doDraw(c,m);
                    	 
                    	 if(m!=-1){ 
                    		 doDraw(c,m);
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
                	if(!nxt){
                		sleep(2000);
                	}else{
					sleep(2000);
                	}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
 
        private void doDraw(Canvas canvas,int o)  {
        	int px = (int) (getResources().getDisplayMetrics().density * 1);
        	Paint pt = new Paint();
        	pt.setTextSize(20*px);
        	int addloc = 200;
         	canvas.drawText("Users Data:", getWidth()-200,((getHeight())-40), pt);
         	String arD="";
         	for(int w = 0; w < data.length;w++){
         		
         		if(arrData[w]!=-1){
         			arD+=data[w]+" ";
         			pt.setTextSize(10*px);
         			canvas.drawText(arD+" ",getWidth()-178,((getHeight())-25), pt);
         			//addloc = addloc + (20);
         			}
         	}
         	//coloring	 
            t.setTextSize(px*10);
            if(type==8 && done){
            	canvas.drawText("SWAP data "+data[o]+" ",10,((getHeight())-50), pt);
            	//canvas.drawCircle(getWidth()/3-10, 90, 15+(5), swap1);
            	canvas.drawRect(rec[o], swap1);
                //done = false;
            	}
            if(type==9 && done){
            	canvas.drawText("TO data "+data[o]+" ",10,((getHeight())-50), pt);
            	//canvas.drawCircle(getWidth()/3-10, 90, 15+(5), swap2);
            	canvas.drawRect(rec[o], swap2);
                //done = false;
            	}
            t.setTextSize(10*px);
            x=10/px;
            y=10;
            pt.setTextSize(10*px);
            for(int i = 0;i<data.length;i++){
            	if(data[i]!=-1){
    			Rect r = new Rect(x*px,y*px,(x+(30-(5*px)))*px,(y+20)*px);
    			rec[i]=r;
        	    canvas.drawRect(r, p);
        	    if(data[i]<100){
        	    canvas.drawText(data[i]+"",x*px+14,20*px, pt);
		       	canvas.drawText("["+i+"]",x*px+12,40*px, pt);
		       	if(fixedData && i==ind3x){
		       		canvas.drawRect(rec[ind3x], fixed);
		       		canvas.drawText(data[i]+"",x*px+14,20*px, pt);
		       	}
        	    }else{
        	    	canvas.drawText(data[i]+"",x*px+6,20*px, pt);
    		       	canvas.drawText("["+i+"]",x*px+12,40*px, pt);
    		       	if(fixedData && i==ind3x){
    		       		canvas.drawRect(rec[ind3x], fixed);
    		       		canvas.drawText(data[i]+"",x*px+6,20*px, t);
    		       	}
        	    }
            	
            	x+=(40-(8*px));
            	}
            }
            
            
         	/*int i = 0;
         	
         	//array box
            if(data[i]!= -1){
            	    Rect r21 = new Rect(10,40*px,30+(20*px), 10);
            	    rec[i]=r21;
            	    canvas.drawRect(r21, p);
            	    canvas.drawText(data[i]+"",30,30, pt);
    		       	canvas.drawText("["+i+"]",30,60, pt);
    		       	if(fixedData && i==ind3x){
    		       		canvas.drawRect(rec[ind3x], fixed);
    		       		canvas.drawText(data[i]+"",30,30, t);
    		       	}
    		       	i++;}
            if(data[i]!= -1){
            	    Rect r22 = new Rect(30+(20*px),40*px,50+(40*px),10);
            	    rec[i]=r22;
          			canvas.drawRect(r22, p);
          			canvas.drawText(data[i]+"",70,30, pt);
          			canvas.drawText("["+i+"]",70,60, pt);
          			if(fixedData && i==ind3x){
    		       		canvas.drawRect(rec[ind3x], fixed);
    		       		canvas.drawText(data[i]+"",70,30, t);
    		       	}
          			i++;}	
            if(data[i]!= -1){
            	   Rect r23 = new Rect(50+(40*px),40*px,90+(40*px),10);
            	   rec[i]=r23;
           	       canvas.drawRect(r23, p);
    			   canvas.drawText(data[i]+"",110,30, pt);
    			   canvas.drawText("["+i+"]",110,60, pt);
    			   if(fixedData && i==ind3x){
   		       		canvas.drawRect(rec[ind3x], fixed);
   		       		canvas.drawText(data[i]+"",110,30, t);
   		       		}
    			   i++;}
            if(data[i]!= -1){
            	   Rect r24 = new Rect(90+(40*px),40*px,130+(40*px),10);
            	   rec[i]=r24;
    			   canvas.drawRect(r24, p);
    			   canvas.drawText(data[i]+"",150,30, pt);
    			   canvas.drawText("["+i+"]",150,60, pt);
    			   if(fixedData && i==ind3x){
      		       		canvas.drawRect(rec[ind3x], fixed);
      		       		canvas.drawText(data[i]+"",150,30, t);
      		       		}
    			   i++;}
            if(data[i]!= -1){
            	   Rect r25 = new Rect(130+(40*px), 40*px,170+(40*px),10);
            	   rec[i]=r25;
           	       canvas.drawRect(r25, p);
    			   canvas.drawText(data[i]+"",190,30, pt);
    			   canvas.drawText("["+i+"]",190,60, pt);
    			   if(fixedData && i==ind3x){
     		       		canvas.drawRect(rec[ind3x], fixed);
     		       		canvas.drawText(data[i]+"",190,30, t);
     		       		}
    			   i++;}
            if(data[i]!= -1){
            	   Rect r26 = new Rect(170+(40*px), 40*px,210+(40*px),10);
            	   rec[i]=r26;
           	       canvas.drawRect(r26, p);
           	       canvas.drawText(data[i]+"",230,30, pt);
           	       canvas.drawText("["+i+"]",230,60, pt);
           	       if(fixedData && i==ind3x){
 		       		canvas.drawRect(rec[ind3x], fixed);
 		       		canvas.drawText(data[i]+"",230,30, t);
 		       		}
           	       i++;}
            if(data[i]!= -1){
            	  Rect r27 = new Rect(210+(40*px), 40*px,250+(40*px),10);
            	  rec[i]=r27;
           	      canvas.drawRect(r27, p);
           	      canvas.drawText(data[i]+"",270,30, pt);
           	      canvas.drawText("["+i+"]",270,60, pt);
           	      if(fixedData && i==ind3x){
		       		canvas.drawRect(rec[ind3x], fixed);
		       		canvas.drawText(data[i]+"",270,30, t);
		       		}
           	      i++;}
            if(data[i]!= -1){
            	  Rect r28 = new Rect(250+(40*px), 40*px,290+(40*px),10);
            	  rec[i]= r28;
           	   canvas.drawRect(r28,p);
           	   canvas.drawText(data[i]+"",310,30, pt);
			   canvas.drawText("["+i+"]",310,60, pt);
			   if(fixedData && i==ind3x){
		       		canvas.drawRect(rec[ind3x], fixed);
		       		canvas.drawText(data[i]+"",310,30, t);
		       		}  	
			   i++;}
            if(data[i]!= -1){
            	  Rect r29 = new Rect(290+(40*px), 40*px,330+(40*px),10);
            	  rec[i]=r29;
           	   canvas.drawRect(r29, p);
           	   canvas.drawText(data[i]+"",350,30, pt);
			   canvas.drawText("["+i+"]",350,60, pt);
			   if(fixedData && i==ind3x){
		       		canvas.drawRect(rec[ind3x], fixed);
		       		canvas.drawText(data[i]+"",350,30, t);
		       		}     	
			   i++;}
            if(data[i]!= -1){
            	  Rect r30 = new Rect(330+(40*px), 40*px,370+(40*px),10);
            	  rec[i]=r30;
           	   canvas.drawRect(r30, p);
           	   canvas.drawText(data[i]+"",390,30, pt);
			   canvas.drawText("["+i+"]",390,60, pt);
			   if(fixedData && i==ind3x){
		       		canvas.drawRect(rec[ind3x], fixed);
		       		canvas.drawText(data[i]+"",390,30, t);
		       }
			   i++;}*/
            //end array box
            int z = 0;
            //drawtrees////////////////////////////////////////
            x=getWidth()/2;
            y=120;
          
            if(data[z]!= -1){
             	canvas.drawCircle(x, y-10, 15+(5), p);
             	canvas.drawText(data[z]+"",x,y-10, pt);
                if((type==8 && done)&&(data[z]==data[o])){
                	canvas.drawText("SWAP data "+data[o]+" ",x-10,((getHeight()/2)+100)-50, pt);
                	canvas.drawCircle(x, y-10, 15+(5), swap1);
                    done = false;}
                if((type==9 && done)&&(data[z]==data[o])){
                	canvas.drawText("TO data "+data[o]+" ",(x-10),((getHeight()/2)+100)-50, pt);
                	canvas.drawCircle(x, y-10, 15+(5), swap2);
                    done = false; }
             	z++;}//0
             	//--
             	if(data[z]!= -1){
             	canvas.drawLine(x, y+8, x-52, 168, p);
             	canvas.drawCircle(x-70, 170, 15+(5), p);
             	canvas.drawText(data[z]+"",getWidth()/3-10+20,170, pt);
                if((type==8 && done)&&(data[z]==data[o])){
                	//canvas.drawText("SWAP data "+data[o]+" ",x-10,((getHeight()/2)+100), pt);
                	canvas.drawCircle(x-90+20, 170, 15+(5), swap1);
                    done = false;}
                if((type==9 && done)&&(data[z]==data[o])){
                	//canvas.drawText("TO data "+data[o]+" ",x-10,((getHeight()/2)+100), pt);
                	canvas.drawCircle(x-90+20, 170, 15+(5), swap2);
                    done = false; }
             	z++;}//1
             	if(data[z]!= -1){
             		canvas.drawLine(x, y+8, x+72, 168, p);
             	canvas.drawCircle(x+90, 170, 15+(5), p);
             	canvas.drawText(data[z]+"",x+90,170, pt);
                if((type==8 && done)&&(data[z]==data[o])){
                	//canvas.drawText("SWAP data "+data[o]+" ",x-10,((getHeight()/2)+100), pt);
                	canvas.drawCircle(x+110, 170, 15+(5), swap1);
                    done = false; }
                if((type==9 && done)&&(data[z]==data[o])){
                	//canvas.drawText("TO data "+data[o]+" ",x-10,((getHeight()/2)+100), pt);
                	canvas.drawCircle(x+110, 170, 15+(5), swap2);
                    done = false;}
             	z++;}//2
             	//---
             	if(data[z]!= -1){
             		canvas.drawLine(x-70, 188,x-120,230, p);
             	canvas.drawCircle(x-140, 230, 15+(5), p);
             	canvas.drawText(data[z]+"",x-140,230, pt);
                if((type==8 && done)&&(data[z]==data[o])){
                	//canvas.drawText("SWAP data "+data[o]+" ",(getWidth()/2)-10,((getHeight()/2)+100), pt);
                	canvas.drawCircle(x-140, 230, 15+(5), swap1);
                    done = false;}
                if((type==9 && done)&&(data[z]==data[o])){
                	//canvas.drawText("TO data "+data[o]+" ",(getWidth()/2)-10,((getHeight()/2)+100), pt);
                	canvas.drawCircle(x-140, 230, 15+(5), swap2);
                    done = false; }
             	z++;}//3
             	if(data[z]!= -1){
             	canvas.drawLine(x-70, 188,x-30,220, p);
             	canvas.drawCircle(x-10, 230, 15+(5), p);
             	canvas.drawText(data[z]+"",x-10,230, pt);
                if((type==8 && done)&&(data[z]==data[o])){
                	//canvas.drawText("SWAP data "+data[o]+" ",(getWidth()/2)-10,((getHeight()/2)+100), pt);
                	canvas.drawCircle(x-20, 230, 15+(5), swap1);
                    done = false;}
                if((type==9 && done)&&(data[z]==data[o])){
                	//canvas.drawText("TO data "+data[o]+" ",x-10,((getHeight()/2)+100), pt);
                	canvas.drawCircle(x-20, 230, 15+(5), swap2);
                    done = false; }
             	z++;}//4
             	if(data[z]!= -1){
             		canvas.drawLine(x+90, 188,x+50,220, p);
             	canvas.drawCircle(x+30, 230, 15+(5), p);
             	canvas.drawText(data[z]+"",x+30,230, pt);
                if((type==8 && done)&&(data[z]==data[o])){
                	//canvas.drawText("SWAP data "+data[o]+" ",(getWidth()/2)-10,((getHeight()/2)+100), pt);
                	canvas.drawCircle(x+30, 230, 15+(5), swap1);
                    done = false; }
                if((type==9 && done)&&(data[z]==data[o])){
                	//canvas.drawText("TO data "+data[o]+" ",(x)-10,((getHeight()/2)+100), pt);
                	canvas.drawCircle(x+30, 230, 15+(5), swap2);
                    done = false;}
             	z++;}//5
             	
             	if(data[z]!= -1){
             		canvas.drawLine(x+90, 188,x+160-20,220, p);
             	canvas.drawCircle(x+160, 230, 15+(5), p);
             	canvas.drawText(data[z]+"",x+160,230, pt);
                if((type==8 && done)&&(data[z]==data[o])){
                	//canvas.drawText("SWAP data "+data[o]+" ",(getWidth()/2)-10,((getHeight()/2)+100), pt);
                	canvas.drawCircle(x+160, 230, 15+(5), swap1);
                    done = false; }
                if((type==9 && done)&&(data[z]==data[o])){
                	//canvas.drawText("TO data "+data[o]+" ",(getWidth()/2)-10,((getHeight()/2)+100), pt);
                	canvas.drawCircle(x+160, 230, 15+(5), swap2);
                    done = false; }
             	z++;}//6
             	//----
             	if(data[z]!= -1){
             		canvas.drawLine(x-140, 250,x-170,290, p);
             	canvas.drawCircle(x-170, 310, 15+(5), p);
             	canvas.drawText(data[z]+"",x-170,310, pt);
                if((type==8 && done)&&(data[z]==data[o])){
                //	canvas.drawText("SWAP data "+data[o]+" ",(getWidth()/2)-10,((getHeight()/2)+100), pt);
                	canvas.drawCircle(x-220+50, 310, 15+(5), swap1);
                    done = false;}
                if((type==9 && done)&&(data[z]==data[o])){
                //	canvas.drawText("TO data "+data[o]+" ",(getWidth()/2)-10,((getHeight()/2)+100), pt);
                	canvas.drawCircle(x-220+50, 310, 15+(5), swap2);
                    done = false;  }
             	z++;}//7
             	if(data[z]!= -1){
             		canvas.drawLine(x-160+20, 250,x-100,310, p);
             	canvas.drawCircle(x-100+20, 320-50+20+20, 15+(5), p);
             	canvas.drawText(data[z]+"",x-80,310, pt);
                if((type==8 && done)&&(data[z]==data[o])){
                	//canvas.drawText("SWAP data "+data[o]+" ",(getWidth()/2)-10,((getHeight()/2)+100), pt);
                	canvas.drawCircle(x-80, 310, 15+(5), swap1);
                    done = false; }
                if((type==9 && done)&&(data[z]==data[o])){
                	//canvas.drawText("TO data "+data[o]+" ",(getWidth()/2)-10,((getHeight()/2)+100), pt);
                	canvas.drawCircle(x-80, 310, 15+(5), swap2);
                    done = false;}
             	z++;}//8
             	if(data[z]!= -1){
             		canvas.drawLine(x-30+20,250,x-60+20,290, p);
             	canvas.drawCircle(x-60+20, 310, 15+(5), p);
             	canvas.drawText(data[z]+"",x-60+20,310, pt);
                if((type==8 && done)&&(data[z]==data[o])){
                	//canvas.drawText("SWAP data "+data[o]+" ",(getWidth()/2)-10,((getHeight()/2)+100), pt);
                	canvas.drawCircle(x-60+20, 310, 15+(5), swap1);
                    done = false; }
                if((type==9 && done)&&(data[z]==data[o])){
                	//canvas.drawText("TO data "+data[o]+" ",(getWidth()/2)-10,((getHeight()/2)+100), pt);
                	canvas.drawCircle(x-60+20, 310, 15+(5), swap2);
                    done = false;}
             	z++;}//9
            //endtrees
            
             if(swap){
            	 canvas.drawRect(rec[num1], swap2);
            	 canvas.drawRect(rec[num2], swap1);
            	 swap = false;
             }
    		
             	return;
                
      }//end do draw

        protected void runOnUiThread(Runnable runnable) {
				// TODO Auto-generated method stub
			}
		public void setRunning(boolean b) {
            mRun = b;
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
 
    }
    public void surfaceCreated(SurfaceHolder holder) {
    	
    	   for(int i =0;i<10;i++){
    		   data[i] = -1;
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
	