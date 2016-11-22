package com.android.databluesv5;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class DrawingCircular extends SurfaceView implements SurfaceHolder.Callback   {
	float x,y;
	private Context context;
	
	String[] data = new String[10];
	String[] hold = new String[10];
	
	FunctionCircular df = new FunctionCircular();
	
	Canvas canvas;
	Paint p = new Paint();
	
	int quan = 4;
	int ii = 0;	
	
	int px = (int) getResources().getDisplayMetrics().density * 1;
	
    private Canvas c;
  
    public AnimationThread thread;
      
    public DrawingCircular(Context context, AttributeSet attrs) {
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
    	
    	ii--;
    	thread.setRunning(true);
    }
    
    public void setSize(int quant){
    	quan = quant;
    	
    	thread.setRunning(true);
    }
    
    public void setData(String axe){
    	data[ii] = axe;
    	ii++;
    	
       thread.setRunning(true);
       }
    
    public void reset(){
    	for(int ctr = 0 ; ctr < 10 ; ctr++){
    		data[ctr] = null;
    	}
    	ii = 0;
    	
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
         			bg.set(0, 0, DrawingCircular.this.getWidth(), DrawingCircular.this.getHeight());
         			p.setColor(Color.rgb(172, 227, 230));
         			p.setStyle(Paint.Style.FILL);
         			c.drawRect(bg, p);
         			
         			p.setColor(Color.BLACK);
         			p.setStyle(Paint.Style.STROKE);
         			p.setTextSize(13*px);
         			bg.set(1,1,DrawingCircular.this.getWidth()-1,DrawingCircular.this.getHeight()-1);
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
                	//sleep ka na dito
                	sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            
        }

        private void doDraw(Canvas canvas) {
        	resetcanvas();
            int initial = 110;
            int x = 70;
            int y = 50;
           if(data[0] == null && data[1] == null ){
           	return;
           }
           int ctr = 0;
           
           //444444444444444444
           if(quan == 4){
        	   initial = 80;
               for(int i = 0; i < data.length; i++){
               	if(data[i] == null && data[i+1] == null){
               		break;
               	}
               	if(i==11){
               		break;
               	}
               	Rect r = new Rect(initial*px,100*px,(initial+y)*px,70*px);
               	
               	if(ctr == 0){
   	        	r = new Rect(initial*px,100*px,(initial+y)*px,70*px);
   	 			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",(initial + 3)*px,90*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",(initial + 3)*px,90*px, p);
               	}
               	else if(ctr == 1){
       	        r = new Rect(initial*px,130*px,(initial+y)*px,100*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",(initial + 3)*px,120*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",(initial + 3)*px,120*px, p);
          	        if(!(ii > 2)){
              	       /* canvas.drawLine(175*px, 85*px, 175*px, 100*px,p);
              	        canvas.drawLine(130*px, 85*px, 175*px, 85*px, p);
              	        canvas.drawText("<", 132*px, 91*px, p);*/
	          	        canvas.drawLine(80*px, 100*px, 150*px, 130*px,p);
	          	        canvas.drawText(">", 145*px, 132*px, p);      	        	
          	        }
          	        else{
	          	        canvas.drawLine(80*px, 100*px, 150*px, 130*px,p);
	          	        canvas.drawText(">", 145*px, 132*px, p);
          	        }
               	}
               	else if(ctr == 2){
       	        r = new Rect(initial*px,100*px,(initial+y)*px,70*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",(initial + 3)*px,90*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",(initial + 3)*px,90*px, p);
          	        if(!(ii > 3)){
              	        /*canvas.drawLine(130*px, 85*px, 220*px, 85*px, p);
              	        canvas.drawText("<", 132*px, 91*px, p);*/
              	        canvas.drawLine(200*px, 130*px, 270*px, 100*px,p);
              	        canvas.drawText("^", 267*px, 106*px, p);     	        	
          	        }
          	        else{
          	        canvas.drawLine(200*px, 130*px, 270*px, 100*px,p);
          	        canvas.drawText("^", 267*px, 106*px, p);
          	        }
               	}
               	else if(ctr == 3){
           	    r = new Rect(150*px,70*px,(150+y)*px,40*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",153*px,60*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",153*px,60*px, p);
          	        canvas.drawLine(270*px, 70*px, 200*px, 40*px,p);
          	        canvas.drawText("<", 200*px, 46*px, p);
          	        canvas.drawLine(150*px, 40*px, 80*px, 70*px,p);
          	        canvas.drawText("v", 80*px, 72*px, p);
               	}
               	else if(ctr >= quan){
               		r = new Rect();
               	}

        			p.setStyle(Paint.Style.STROKE);
   	        	canvas.drawRect(r, p);
   	        	ctr++;
   	        	initial = initial + x;
               }
              }
              
              //5555555555555555555
              if(quan == 5){
           	   initial = 30;
                  for(int i = 0; i < data.length; i++){
                  	if(data[i] == null && data[i+1] == null){
                  		break;
                  	}
                  	if(i==11){
                  		break;
                  	}
                  	Rect r = new Rect(initial*px,100*px,(initial+y)*px,70*px);
                  	
                  	if(ctr == 0){
      	        	r = new Rect(initial*px,100*px,(initial+y)*px,70*px);
      	 			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",(initial + 3)*px,90*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",(initial + 3)*px,90*px, p);
               	}
                  	else if(ctr == 1){
          	        r = new Rect(initial*px,140*px,(initial+y)*px,110*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",(initial + 3)*px,130*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",(initial + 3)*px,130*px, p);
          	        if(!(ii > 2)){
              	        /*canvas.drawLine(125*px, 85*px, 125*px, 110*px,p);
              	        canvas.drawLine(80*px, 85*px, 126*px, 85*px, p);
              	        canvas.drawText("<", 80*px, 89*px, p);*/
              	        canvas.drawLine(30*px, 100*px, 100*px, 140*px, p);
       		        	canvas.drawText(">",97*px,145*px, p);
          	        }
          	        else{
          	        canvas.drawLine(30*px, 100*px, 100*px, 140*px, p);
   		        	canvas.drawText(">",97*px,145*px, p);
          	        }
               	}
                  	else if(ctr == 2){
          	        r = new Rect(initial*px,140*px,(initial+y)*px,110*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",(initial + 3)*px,130*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",(initial + 3)*px,130*px, p);
          	        if(!(ii > 3)){
              	        /*canvas.drawLine(195*px, 85*px, 195*px, 110*px,p);
              	        canvas.drawLine(80*px, 85*px, 196*px, 85*px, p);
              	        canvas.drawText("<", 80*px, 89*px, p);*/
              	        canvas.drawLine(150*px, 140*px, 170*px, 140*px, p);
       		        	canvas.drawText(">",167*px,143*px, p);
          	        }
          	        else{
          	        canvas.drawLine(150*px, 140*px, 170*px, 140*px, p);
   		        	canvas.drawText(">",167*px,143*px, p);
          	        }
               	}
                  	else if(ctr == 3){
              	    r = new Rect(initial*px, 100*px, (initial+y)*px, 70*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",(initial + 3)*px,90*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",(initial + 3)*px,90*px, p);
          	        if(!(ii > 4)){
              	        /*canvas.drawLine(80*px, 85*px, 240*px, 85*px, p);
              	        canvas.drawText("<", 80*px, 89*px, p);*/
              	        canvas.drawLine(290*px, 100*px, 220*px, 140*px, p);
       		        	canvas.drawText("^",287*px,108*px, p);
          	        }
          	        else{
          	        canvas.drawLine(290*px, 100*px, 220*px, 140*px, p);
   		        	canvas.drawText("^",287*px,108*px, p);
          	        }
               	}
                  	else if(ctr == 4){
                  	r = new Rect(135*px, 60*px, (135+y)*px, 30*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",138*px,50*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",138*px,50*px, p);
          	        canvas.drawLine(290*px, 70*px, 190*px, 30*px, p);
   		        	canvas.drawText("<",187*px,35*px, p);
          	        canvas.drawLine(135*px, 30*px, 30*px, 70*px, p);
   		        	canvas.drawText("v",32*px,72*px, p);
               	}
               	else if(ctr >= quan){
               		r = new Rect();
               	}
               	

        			p.setStyle(Paint.Style.STROKE);
      	        	canvas.drawRect(r, p);
      	        	ctr++;
      	        	initial = initial + x;
                  }
                 }
              
            //6
              if(quan == 6){
           	   initial = 30;
                  for(int i = 0; i < data.length; i++){
                  	if(data[i] == null && data[i+1] == null){
                  		break;
                  	}
                  	if(i==11){
                  		break;
                  	}
                  	Rect r = new Rect(initial*px,100*px,(initial+y)*px,70*px);
                  	
                  	if(ctr == 0){
      	        	r = new Rect(initial*px,100*px,(initial+y)*px,70*px);
      	 			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",(initial + 3)*px,90*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",(initial + 3)*px,90*px, p);
               	}
                  	else if(ctr == 1){
          	        r = new Rect(initial*px,140*px,(initial+y)*px,110*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",(initial + 3)*px,130*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",(initial + 3)*px,130*px, p);
          	        if(!(ii > 2)){
              	        /*canvas.drawLine(125*px, 85*px, 125*px, 110*px,p);
              	        canvas.drawLine(80*px, 85*px, 126*px, 85*px, p);
              	        canvas.drawText("<", 80*px, 89*px, p);*/
              	        canvas.drawLine(30*px, 100*px, 100*px, 140*px, p);
       		        	canvas.drawText(">",97*px,145*px, p);
          	        }
          	        else{
          	        canvas.drawLine(30*px, 100*px, 100*px, 140*px, p);
   		        	canvas.drawText(">",97*px,145*px, p);
          	        }
               	}
                  	else if(ctr == 2){
          	        r = new Rect(initial*px,140*px,(initial+y)*px,110*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",(initial + 3)*px,130*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",(initial + 3)*px,130*px, p);
          	        if(!(ii > 3)){
              	        /*canvas.drawLine(195*px, 85*px, 195*px, 110*px,p);
              	        canvas.drawLine(80*px, 85*px, 196*px, 85*px, p);
              	        canvas.drawText("<", 80*px, 89*px, p);*/
              	        canvas.drawLine(150*px, 140*px, 170*px, 140*px, p);
       		        	canvas.drawText(">",167*px,143*px, p);
          	        }
          	        else{
          	        canvas.drawLine(150*px, 140*px, 170*px, 140*px, p);
   		        	canvas.drawText(">",167*px,143*px, p);
          	        }
               	}
                  	else if(ctr == 3){
              	    r = new Rect(initial*px, 100*px, (initial+y)*px, 70*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",(initial + 3)*px,90*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",(initial + 3)*px,90*px, p);
          	        if(!(ii > 4)){
              	        /*canvas.drawLine(80*px, 85*px, 240*px, 85*px, p);
              	        canvas.drawText("<", 80*px, 89*px, p);*/
              	        canvas.drawLine(290*px, 100*px, 220*px, 140*px, p);
       		        	canvas.drawText("^",287*px,108*px, p);
          	        }
          	        else{
          	        canvas.drawLine(290*px, 100*px, 220*px, 140*px, p);
   		        	canvas.drawText("^",287*px,108*px, p);
          	        }
                }
                  	else if(ctr == 4){
                  	r = new Rect(170*px, 60*px, (170+y)*px, 30*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",173*px,50*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",173*px,50*px, p);
          	        if(!(ii > 5)){
              	        /*canvas.drawLine(195*px, 85*px, 195*px, 60*px,p);
              	        canvas.drawLine(80*px, 85*px, 196*px, 85*px, p);
              	        canvas.drawText("<", 80*px, 89*px, p);*/
              	        canvas.drawLine(290*px, 70*px, 220*px, 30*px, p);
       		        	canvas.drawText("<",220*px,36*px, p);
          	        }
          	        else{
              	        canvas.drawLine(290*px, 70*px, 220*px, 30*px, p);
       		        	canvas.drawText("<",220*px,36*px, p);
          	        }
               	}
                  	else if(ctr == 5){
                  	r = new Rect(100*px, 60*px, (100+y)*px, 30*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",103*px,50*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",103*px,50*px, p);
          	        canvas.drawLine(150*px, 30*px, 170*px, 30*px, p);
   		        	canvas.drawText("<",147*px,33*px, p);
          	        canvas.drawLine(100*px, 30*px, 30*px, 70*px, p);
   		        	canvas.drawText("v",30*px,73*px, p);
               	}
               	else if(ctr >= quan){
               		r = new Rect();
               	}
               	

        			p.setStyle(Paint.Style.STROKE);
      	        	canvas.drawRect(r, p);
      	        	ctr++;
      	        	initial = initial + x;
                  }
                 }
              
              //77777777
              if(quan == 7){
           	   initial = 40;
                  for(int i = 0; i < data.length; i++){
                  	if(data[i] == null && data[i+1] == null){
                  		break;
                  	}
                  	if(i==11){
                  		break;
                  	}
                  	Rect r = new Rect(initial*px,100*px,(initial+y)*px,70*px);
                  	
                  	if(ctr == 0){
      	        	r = new Rect(initial*px,120*px,(initial+y)*px,90*px);
      	 			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",(initial + 3)*px,110*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",(initial + 3)*px,110*px, p);
               	}
                  	else if(ctr == 1){
          	        r = new Rect(initial*px,160*px,(initial+y)*px,130*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",(initial + 3)*px,150*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",(initial + 3)*px,150*px, p);
          	        if(!(ii > 2)){
              	        /*canvas.drawLine(135*px, 105*px, 135*px, 130*px,p);
              	        canvas.drawLine(90*px, 105*px, 136*px, 105*px, p);
              	        canvas.drawText("<", 90*px, 109*px, p);*/
              	        canvas.drawLine(40*px, 120*px, 110*px, 160*px, p);
       		        	canvas.drawText(">",103*px,163*px, p);
          	        }
          	        else{
              	        canvas.drawLine(40*px, 120*px, 110*px, 160*px, p);
       		        	canvas.drawText(">",103*px,163*px, p);
          	        }
               	}
                  	else if(ctr == 2){
          	        r = new Rect(initial*px,160*px,(initial+y)*px,130*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",(initial + 3)*px,150*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",(initial + 3)*px,150*px, p);
          	        if(!(ii > 3)){
              	        /*canvas.drawLine(206*px, 105*px, 206*px, 130*px,p);
              	        canvas.drawLine(90*px, 105*px, 206*px, 105*px, p);
              	        canvas.drawText("<", 90*px, 109*px, p);*/
              	        canvas.drawLine(158*px, 160*px, 178*px, 160*px, p);
       		        	canvas.drawText(">",177*px,165*px, p);
          	        }
          	        else{
              	        canvas.drawLine(158*px, 160*px, 178*px, 160*px, p);
       		        	canvas.drawText(">",177*px,165*px, p);
          	        }
               	}
                  	else if(ctr == 3){
              	    r = new Rect(initial*px, 120*px, (initial+y)*px, 90*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",(initial + 3)*px,110*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",(initial + 3)*px,110*px, p);
          	        if(!(ii > 4)){
              	        /*canvas.drawLine(90*px, 105*px, 250*px, 105*px, p);
              	        canvas.drawText("<", 90*px, 109*px, p);*/
              	        canvas.drawLine(300*px, 120*px, 230*px, 160*px, p);
       		        	canvas.drawText("^",297*px,128*px, p);
          	        }
          	        else{
              	        canvas.drawLine(300*px, 120*px, 230*px, 160*px, p);
       		        	canvas.drawText("^",297*px,128*px, p);
          	        }
                  	}
                  	else if(ctr == 4){
                  	r = new Rect(200*px, 80*px, (200+y)*px, 50*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",203*px,70*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",203*px,70*px, p);
          	        if(!(ii > 5)){
              	        /*canvas.drawLine(225*px, 105*px, 225*px, 80*px,p);
              	        canvas.drawLine(90*px, 105*px, 226*px, 105*px, p);
              	        canvas.drawText("<", 93*px, 109*px, p);*/
              	        canvas.drawLine(300*px, 90*px, 250*px, 50*px, p);
       		        	canvas.drawText("<",247*px,56*px, p);
          	        }
          	        else{
              	        canvas.drawLine(300*px, 90*px, 250*px, 50*px, p);
       		        	canvas.drawText("<",247*px,56*px, p);
          	        }
               	}
                  	else if(ctr == 5){
                  	r = new Rect(145*px, 40*px, (145+y)*px, 10*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",148*px,30*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",148*px,30*px, p);
          	        if(!(ii > 6)){
              	        /*canvas.drawLine(173*px, 105*px, 173*px, 40*px,p);
              	        canvas.drawLine(90*px, 105*px, 171*px, 105*px, p);
              	        canvas.drawText("<", 90*px, 108*px, p);*/
              	        canvas.drawLine(250*px, 50*px, 200*px, 10*px, p);
       		        	canvas.drawText("<",197*px,16*px, p);
          	        }
          	        else{
              	        canvas.drawLine(250*px, 50*px, 200*px, 10*px, p);
       		        	canvas.drawText("<",197*px,16*px, p);
          	        }
               	}
                  	else if(ctr == 6){
                  	r = new Rect(90*px, 80*px, (90+y)*px, 50*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",93*px,70*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",93*px,70*px, p);
          	        canvas.drawLine(150*px, 10*px, 90*px, 50*px, p);
   		        	canvas.drawText("v",87*px,52*px, p);
          	        canvas.drawLine(90*px, 50*px, 40*px, 90*px, p);
   		        	canvas.drawText("v", 44*px, 93*px, p);
               	}
               	else if(ctr >= quan){
               		r = new Rect();
               	}
               	

        			p.setStyle(Paint.Style.STROKE);
      	        	canvas.drawRect(r, p);
      	        	ctr++;
      	        	initial = initial + x;
                  }
                 }
              
            //88888
              if(quan == 8){
           	   initial = 40;
                  for(int i = 0; i < data.length; i++){
                  	if(data[i] == null && data[i+1] == null){
                  		break;
                  	}
                  	if(i==11){
                  		break;
                  	}
                  	Rect r = new Rect(initial*px,100*px,(initial+y)*px,70*px);
                  	
                  	if(ctr == 0){
      	        	r = new Rect(initial*px,140*px,(initial+y)*px,110*px);
      	 			p.setStyle(Paint.Style.FILL);
      	        	if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",(initial + 3)*px,130*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",(initial + 3)*px,130*px, p);
               	}
                  	else if(ctr == 1){
          	        r = new Rect(initial*px,180*px,(initial+y)*px,150*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",(initial + 3)*px,170*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",(initial + 3)*px,170*px, p);
          	        if(!(ii > 2)){
/*              	        canvas.drawLine(135*px, 125*px, 135*px, 150*px,p);
              	        canvas.drawLine(90*px, 125*px, 136*px, 125*px, p);
              	        canvas.drawText("<", 90*px, 129*px, p);*/
                      	canvas.drawLine(40*px, 140*px, 110*px, 180*px, p);
                      	canvas.drawText(">", 105*px, 182*px, p);
          	        }
          	        else{
                      	canvas.drawLine(40*px, 140*px, 110*px, 180*px, p);
                      	canvas.drawText(">", 105*px, 182*px, p);
          	        }
                  	
                  	}
                  	else if(ctr == 2){
          	        r = new Rect(initial*px,180*px,(initial+y)*px,150*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",(initial + 3)*px,170*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",(initial + 3)*px,170*px, p);
          	        if(!(ii > 3)){
              	        /*canvas.drawLine(206*px, 125*px, 206*px, 150*px,p);
              	        canvas.drawLine(90*px, 125*px, 206*px, 125*px, p);
              	        canvas.drawText("<", 90*px, 129*px, p);*/
              	        canvas.drawLine(160*px, 180*px, 180*px, 180*px, p);
              	        canvas.drawText(">", 175*px, 184*px, p);
          	        }
          	        else{
              	        canvas.drawLine(160*px, 180*px, 180*px, 180*px, p);
              	        canvas.drawText(">", 175*px, 184*px, p);
          	        }
               	}
                  	else if(ctr == 3){
              	    r = new Rect(initial*px, 140*px, (initial+y)*px, 110*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",(initial + 3)*px,130*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",(initial + 3)*px,130*px, p);
          	        if(!(ii > 4)){
              	        /*canvas.drawLine(90*px, 120*px, 250*px, 120*px, p);
              	        canvas.drawText("<", 90*px, 124*px, p);*/
              	        canvas.drawLine(230*px, 180*px, 300*px, 140*px, p);
              	        canvas.drawText("^", 295*px, 148*px, p);
          	        }
          	        else{
              	        canvas.drawLine(230*px, 180*px, 300*px, 140*px, p);
              	        canvas.drawText("^", 295*px, 148*px, p);
          	        }
               	}
                  	else if(ctr == 4){
                  	r = new Rect((initial - x)*px, 100*px, (initial - x +y)*px, 70*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",(initial - x + 3)*px,90*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",(initial + 3 - x)*px,90*px, p);
          	        if(!(ii > 5)){
              	        /*canvas.drawLine(90*px, 120*px, 250*px, 80*px, p);
              	        canvas.drawText("<", 90*px, 124*px, p);*/
              	        canvas.drawLine(300*px, 110*px, 300*px, 100*px, p);
              	        canvas.drawText("^", 298*px, 107*px, p);
          	        }
          	        else{
              	        canvas.drawLine(300*px, 110*px, 300*px, 100*px, p);
              	        canvas.drawText("^", 298*px, 107*px, p);
          	        }
               	}
                  	else if(ctr == 5){
                  	r = new Rect(180*px, 60*px, (180 + y)*px, 30*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",183*px,50*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",183*px,50*px, p);
          	        if(!(ii > 6)){
          	        	/*canvas.drawLine(200*px, 60*px, 200*px, 120*px, p);
              	        canvas.drawLine(90*px, 120*px, 200*px, 120*px, p);
              	        canvas.drawText("<", 90*px, 124*px, p);*/
              	        canvas.drawLine(300*px, 70*px, 230*px, 30*px, p);
              	        canvas.drawText("<", 230*px, 37*px, p);
          	        }
          	        else{
              	        canvas.drawLine(300*px, 70*px, 230*px, 30*px, p);
              	        canvas.drawText("<", 230*px, 37*px, p);
          	        }
               	}
                  	else if(ctr == 6){
                  	r = new Rect(110*px, 60*px, (110 + y)*px, 30*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",113*px,50*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",113*px,50*px, p);
          	        if(!(ii > 7)){
          	        	/*canvas.drawLine(140*px, 60*px, 140*px, 120*px, p);
              	        canvas.drawLine(90*px, 120*px, 140*px, 120*px, p);
              	        canvas.drawText("<", 90*px, 124*px, p);*/
              	        canvas.drawLine(180*px, 30*px, 160*px, 30*px, p);
              	        canvas.drawText("<", 160*px, 35*px, p);
          	        }
          	        else{
              	        canvas.drawLine(180*px, 30*px, 160*px, 30*px, p);
              	        canvas.drawText("<", 160*px, 35*px, p);
          	        }
               	}
                  	else if(ctr == 7){
                   r = new Rect(40*px, 100*px, (40 + y)*px, 70*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",43*px,90*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",43*px,90*px, p);
          	        canvas.drawLine(110*px, 30*px, 40*px, 70*px, p);
          	        canvas.drawText("v", 39*px, 70*px, p);
          	        canvas.drawLine(40*px, 100*px, 40*px, 110*px, p);
          	        canvas.drawText("v", 38*px, 110*px, p);
               	}
               	else if(ctr >= quan){
               		r = new Rect();
               	}
               	

        			p.setStyle(Paint.Style.STROKE);
      	        	canvas.drawRect(r, p);
      	        	ctr++;
      	        	initial = initial + x;
                  }
                 }
              
            //99999
              if(quan == 9){
           	   initial = 30;
                  for(int i = 0; i < data.length; i++){
                  	if(data[i] == null && data[i+1] == null){
                  		break;
                  	}
                  	if(i==11){
                  		break;
                  	}
                  	Rect r = new Rect(initial*px,100*px,(initial+y)*px,70*px);
                  	
                  	if(ctr == 0){
      	        	r = new Rect(initial*px,110*px,(initial+y)*px,80*px);
      	 			p.setStyle(Paint.Style.FILL);
      	        	if(data[i].length() > 4)
      	        		canvas.drawText(data[i].substring(0, 4)+"",(initial + 3)*px,100*px, p);
      	        	else
      		        	canvas.drawText(data[i]+"",(initial + 3)*px,100*px, p);
                  	}
                  	else if(ctr == 1){
          	        r = new Rect(60*px,160*px, (60+y)*px,130*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",63*px,150*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",63*px,150*px, p);
          	        if(!(ii > 2)){
              	        /*canvas.drawLine(85*px, 100*px, 85*px, 130*px,p);
              	        canvas.drawLine(80*px, 100*px, 86*px, 100*px, p);
              	        canvas.drawText("<", 80*px, 104*px, p);*/
              	        canvas.drawLine(30*px, 110*px, 60*px, 160*px, p);
              	        canvas.drawText(">", 56*px, 162*px, p);
          	        }
          	        else{
              	        canvas.drawLine(30*px, 110*px, 60*px, 160*px, p);
              	        canvas.drawText(">", 56*px, 162*px, p);
          	        }
               	}
                  	else if(ctr == 2){
          	        r = new Rect(120*px,195*px, (120+y)*px,165*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",123*px,185*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",123*px,185*px, p);
          	        if(!(ii > 3)){
              	        /*canvas.drawLine(145*px, 100*px, 145*px, 165*px,p);
              	        canvas.drawLine(80*px, 100*px, 146*px, 100*px, p);
              	        canvas.drawText("<", 80*px, 104*px, p);*/
              	        canvas.drawLine(60*px, 160*px, 120*px, 195*px, p);
              	        canvas.drawText(">", 116*px, 197*px, p);
          	        }
          	        else{
              	        canvas.drawLine(60*px, 160*px, 120*px, 195*px, p);
              	        canvas.drawText(">", 116*px, 197*px, p);
          	        }
               	}
                  	else if(ctr == 3){
              	    r = new Rect(190*px, 195*px, (190+y)*px, 165*px);
        			p.setStyle(Paint.Style.FILL);
              	    if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",193*px,185*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",193*px,185*px, p);
          	        if(!(ii > 4)){
              	        /*canvas.drawLine(215*px, 100*px, 215*px, 165*px,p);
              	        canvas.drawLine(80*px, 100*px, 216*px, 100*px, p);
              	        canvas.drawText("<", 80*px, 104*px, p);*/
                  	    canvas.drawLine(170*px, 195*px, 190*px, 195*px, p);
                  	    canvas.drawText(">", 186*px, 198*px, p);
          	        }
          	        else{
                  	    canvas.drawLine(170*px, 195*px, 190*px, 195*px, p);
                  	    canvas.drawText(">", 186*px, 198*px, p);
          	        }
               	}
                  	else if(ctr == 4){
                  	r = new Rect(250*px, 160*px, (250 + y)*px, 130*px);
        			p.setStyle(Paint.Style.FILL);
                  	if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",253*px,150*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",253*px,150*px, p);
                  	if(!(ii > 5)){
              	        /*canvas.drawLine(275*px, 100*px, 275*px, 130*px,p);
              	        canvas.drawLine(80*px, 100*px, 276*px, 100*px, p);
              	        canvas.drawText("<", 80*px, 104*px, p);*/
                      	canvas.drawLine(240*px, 195*px, 300*px, 160*px, p);
                      	canvas.drawText("^", 296*px, 164*px, p);
          	        }
          	        else{
                      	canvas.drawLine(240*px, 195*px, 300*px, 160*px, p);
                      	canvas.drawText("^", 296*px, 164*px, p);
          	        }
               	}
                  	else if(ctr == 5){
                  	r = new Rect(270*px, 110*px, (270 + y)*px, 80*px);
        			p.setStyle(Paint.Style.FILL);
                  	if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",273*px,100*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",273*px,100*px, p);
                  	if(!(ii > 6)){
              	        /*canvas.drawLine(80*px, 90*px, 277*px, 90*px, p);
              	        canvas.drawText("<", 80*px, 94*px, p);*/
                      	canvas.drawLine(300*px, 160*px, 320*px, 110*px, p);
                      	canvas.drawText("^", 318*px, 114*px, p);
          	        }
          	        else{
                      	canvas.drawLine(300*px, 160*px, 320*px, 110*px, p);
                      	canvas.drawText("^", 318*px, 114*px, p);
          	        }
               	}
                  	else if(ctr == 6){
                  	r = new Rect(210*px, 70*px, (210 + y)*px, 40*px);
        			p.setStyle(Paint.Style.FILL);
                  	if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",213*px,60*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",213*px,60*px, p);
                  	if(!(ii > 7)){
                  		/*canvas.drawLine(225*px, 70*px, 225*px, 90*px, p);
              	        canvas.drawLine(80*px, 90*px, 226*px, 90*px, p);
              	        canvas.drawText("<", 80*px, 94*px, p);*/
                      	canvas.drawLine(320*px, 80*px, 260*px, 40*px, p);
                      	canvas.drawText("<", 258*px, 44*px, p);
          	        }
          	        else{
                      	canvas.drawLine(320*px, 80*px, 260*px, 40*px, p);
                      	canvas.drawText("<", 258*px, 44*px, p);
          	        }
               	}
                  	else if(ctr == 7){
                   r = new Rect(150*px, 35*px, (150 + y)*px, 5*px);
        			p.setStyle(Paint.Style.FILL);
                   if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",153*px,25*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",153*px,25*px, p);
                 	if(!(ii > 8)){
                  		/*canvas.drawLine(175*px, 35*px, 175*px, 90*px, p);
              	        canvas.drawLine(80*px, 90*px, 176*px, 90*px, p);
              	        canvas.drawText("<", 80*px, 94*px, p);*/
                      	canvas.drawLine(320*px, 80*px, 260*px, 40*px, p);
                      	canvas.drawText("<", 258*px, 44*px, p);
          	        }
          	        else{
                      	canvas.drawLine(320*px, 80*px, 260*px, 40*px, p);
                      	canvas.drawText("<", 258*px, 44*px, p);
          	        }
                   canvas.drawLine(260*px, 40*px, 200*px, 5*px, p);
                   canvas.drawText("<", 196*px, 10*px, p);
               	}
                  	else if(ctr == 8){
                   r = new Rect(90*px, 70*px, (90 + y)*px, 40*px);
        			p.setStyle(Paint.Style.FILL);
                   if(data[i].length() > 4)
   	        		canvas.drawText(data[i].substring(0, 4)+"",93*px,60*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",93*px,60*px, p);
                   canvas.drawLine(150*px, 5*px, 90*px, 40*px, p);
                   canvas.drawText("v", 88*px, 43*px, p);
                   canvas.drawLine(90*px, 40*px, 30*px, 80*px, p);
                   canvas.drawText("v", 28*px, 83*px, p);
               	}
               	else if(ctr >= quan){
               		r = new Rect();
               	}
               	

        			p.setStyle(Paint.Style.STROKE);
      	        	canvas.drawRect(r, p);
      	        	ctr++;
      	        	initial = initial + x;
                  }
                 }
              
            //101010101010
              if(quan == 10){
           	   initial = 10;
           	   y = 40;
                  for(int i = 0; i < data.length; i++){
                  	if(data[i] == null && data[i+1] == null){
                  		break;
                  	}
                  	if(i==11){
                  		break;
                  	}
                  	Rect r = new Rect(initial*px,100*px,(initial+y)*px,70*px);
                  	
                  	if(ctr == 0){
      	        	r = new Rect(initial*px,100*px,(initial+y)*px,70*px);
      	 			p.setStyle(Paint.Style.FILL);
      	        	if(data[i].length() > 3)
      	        		canvas.drawText(data[i].substring(0, 3)+"",(initial + 3)*px,90*px, p);
      	        	else
      		        	canvas.drawText(data[i]+"",(initial + 3)*px,90*px, p);
                  	}
                  	else if(ctr == 1){
          	        r = new Rect(10*px,140*px, (10 + y)*px,110*px);
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 3)
   	        		canvas.drawText(data[i].substring(0, 3)+"",13*px,130*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",13*px,130*px, p);
          	        if(!(ii > 2)){
              	        /*canvas.drawLine(30*px, 100*px, 30*px, 110*px, p);
              	        canvas.drawText("^", 28*px, 106*px, p);*/
              	        canvas.drawLine(10*px, 100*px, 10*px, 110*px, p);
              	        canvas.drawText("v", 7*px, 112*px, p);
          	        }
          	        else{
              	        canvas.drawLine(10*px, 100*px, 10*px, 110*px, p);
              	        canvas.drawText("v", 7*px, 112*px, p);
          	        }
                  	}
                  	else if(ctr == 2){
          	        r = new Rect(80*px,160*px, (80+y)*px,130*px);  
        			p.setStyle(Paint.Style.FILL);
          	        if(data[i].length() > 3)
   	        		canvas.drawText(data[i].substring(0, 3)+"",83*px,150*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",83*px,150*px, p);
          	        if(!(ii > 3)){
              	        /*canvas.drawLine(90*px, 130*px, 90*px, 80*px, p);
              	        canvas.drawLine(50*px, 80*px, 90*px, 80*px, p);
              	        canvas.drawText("<", 51*px, 84*px, p);*/
              	        canvas.drawLine(10*px, 140*px, 80*px, 160*px, p);
              	        canvas.drawText(">", 76*px, 163*px, p);
          	        }
          	        else{
              	        canvas.drawLine(10*px, 140*px, 80*px, 160*px, p);
              	        canvas.drawText(">", 76*px, 163*px, p);
          	        }
                  	}
                  	else if(ctr == 3){
              	    r = new Rect(150*px, 195*px, (150+y)*px, 165*px);
        			p.setStyle(Paint.Style.FILL);
              	    if(data[i].length() > 3)
   	        		canvas.drawText(data[i].substring(0, 3)+"",153*px,185*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",153*px,185*px, p);
          	        if(!(ii > 4)){
              	        /*canvas.drawLine(170*px, 165*px, 170*px, 80*px, p);
              	        canvas.drawLine(50*px, 80*px, 170*px, 80*px, p);
              	        canvas.drawText("<", 51*px, 84*px, p);*/
                  	    canvas.drawLine(80*px, 160*px, 150*px, 195*px, p);
                  	    canvas.drawText(">", 146*px, 198*px, p);
          	        }
          	        else{
                  	    canvas.drawLine(80*px, 160*px, 150*px, 195*px, p);
                  	    canvas.drawText(">", 146*px, 198*px, p);
          	        }
                  	}
                  	else if(ctr == 4){
                  	r = new Rect(220*px, 160*px, (220 + y)*px, 130*px);
        			p.setStyle(Paint.Style.FILL);
                  	if(data[i].length() > 3)
   	        		canvas.drawText(data[i].substring(0, 3)+"",223*px, 150*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",223*px,150*px, p);
          	        if(!(ii > 5)){
              	        /*canvas.drawLine(240*px, 130*px, 240*px, 80*px, p);
              	        canvas.drawLine(50*px, 80*px, 240*px, 80*px, p);
              	        canvas.drawText("<", 51*px, 84*px, p);*/
                      	canvas.drawLine(190*px, 195*px, 260*px, 160*px, p);
                      	canvas.drawText("^", 257*px, 167*px, p);
          	        }
          	        else{
                      	canvas.drawLine(190*px, 195*px, 260*px, 160*px, p);
                      	canvas.drawText("^", 257*px, 167*px, p);
          	        }
                  	}
                  	else if(ctr == 5){
                  	r = new Rect(290*px, 140*px, (290 + y)*px, 110*px);
        			p.setStyle(Paint.Style.FILL);
                  	if(data[i].length() > 3)
   	        		canvas.drawText(data[i].substring(0, 3)+"",293*px,130*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",293*px,130*px, p);
                  	if(!(ii > 6)){
              	        /*canvas.drawLine(310*px, 110*px, 310*px, 80*px, p);
              	        canvas.drawLine(50*px, 80*px, 310*px, 80*px, p);
              	        canvas.drawText("<", 51*px, 84*px, p);*/
                      	canvas.drawLine(260*px, 160*px, 330*px, 140*px, p);
                      	canvas.drawText("^", 327*px, 147*px, p);
          	        }
          	        else{
                      	canvas.drawLine(260*px, 160*px, 330*px, 140*px, p);
                      	canvas.drawText("^", 327*px, 147*px, p);
          	        }
                  	}
                  	else if(ctr == 6){
                  	r = new Rect(290*px, 100*px, (290 + y)*px, 70*px);
        			p.setStyle(Paint.Style.FILL);
                  	if(data[i].length() > 3)
   	        		canvas.drawText(data[i].substring(0, 3)+"",293*px,90*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",293*px,90*px, p);
                  	if(!(ii > 7)){
              	        /*canvas.drawLine(50*px, 80*px, 290*px, 80*px, p);
              	        canvas.drawText("<", 51*px, 84*px, p);*/
                      	canvas.drawLine(330*px, 100*px, 330*px, 110*px, p);
                      	canvas.drawText("^", 327*px, 112*px, p);
          	        }
          	        else{
                      	canvas.drawLine(330*px, 100*px, 330*px, 110*px, p);
                      	canvas.drawText("^", 327*px, 112*px, p);
          	        }
                  	}
                  	else if(ctr == 7){
                   r = new Rect(220*px, 70*px, (220 + y)*px, 40*px);
        			p.setStyle(Paint.Style.FILL);
                   if(data[i].length() > 3)
   	        		canvas.drawText(data[i].substring(0, 3)+"",223*px,60*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",223*px,60*px, p);
                 	if(!(ii > 8)){
              	        /*canvas.drawLine(240*px, 70*px, 240*px, 80*px, p);
              	        canvas.drawLine(50*px, 80*px, 240*px, 80*px, p);
              	        canvas.drawText("<", 51*px, 84*px, p);*/
                       	canvas.drawLine(330*px, 70*px, 260*px, 40*px, p);
                       	canvas.drawText("<", 258*px, 46*px, p);
          	        }
          	        else{
                       	canvas.drawLine(330*px, 70*px, 260*px, 40*px, p);
                       	canvas.drawText("<", 258*px, 46*px, p);
          	        }
                  	}
                  	else if(ctr == 8){
                   r = new Rect(150*px, 35*px, (150 + y)*px, 5*px);
        			p.setStyle(Paint.Style.FILL);
                   if(data[i].length() > 3)
   	        		canvas.drawText(data[i].substring(0, 3)+"",153*px,25*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",153*px,25*px, p);
         	        if(!(ii > 9)){
              	       /* canvas.drawLine(170*px, 35*px, 170*px, 80*px, p);
              	        canvas.drawLine(50*px, 80*px, 170*px, 80*px, p);
              	        canvas.drawText("<", 51*px, 84*px, p);*/
                  	    canvas.drawLine(80*px, 160*px, 150*px, 195*px, p);
                  	    canvas.drawText(">", 146*px, 198*px, p);
          	        }
          	        else{
                  	    canvas.drawLine(80*px, 160*px, 150*px, 195*px, p);
                  	    canvas.drawText(">", 146*px, 198*px, p);
          	        }
                   canvas.drawLine(260*px, 40*px, 190*px, 5*px, p);
                   canvas.drawText("<", 188*px, 11*px, p);
                  	}
                  	else if(ctr == 9){
                   r = new Rect(80*px, 70*px, (80 + y)*px, 40*px);
        			p.setStyle(Paint.Style.FILL);
                   if(data[i].length() > 3)
   	        		canvas.drawText(data[i].substring(0, 3)+"",83*px,60*px, p);
   	        	else
   		        	canvas.drawText(data[i]+"",83*px,60*px, p);
                   canvas.drawLine(150*px, 5*px, 80*px, 40*px, p);
                   canvas.drawText("v", 76*px, 44*px, p);
                   canvas.drawLine(80*px, 40*px, 10*px, 70*px, p);
                   canvas.drawText("v", 7*px, 74*px, p);
                  	}
               	else if(ctr >= quan){
               		r = new Rect();
               	}
               	

        			p.setStyle(Paint.Style.STROKE);
      	        	canvas.drawRect(r, p);
      	        	ctr++;
      	        	initial = initial + x;
                  }
                 }
              
              
               }
            
		public void setRunning(boolean b) {
            mRun = b;
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }
 
    public void surfaceCreated(SurfaceHolder holder) {
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