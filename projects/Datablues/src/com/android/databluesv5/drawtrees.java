package com.android.databluesv5;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class drawtrees extends SurfaceView implements SurfaceHolder.Callback   {
	//Threading Animation
	public AnimationThread thread;
	
	//Drawing
	private Canvas c;
	Paint p = new Paint();
	
	//Values	
	Node root;	
	public Node focusNode;	
	public Node parent;
	
	int ctr=1;
	int num;
	
	 ArrayList<Integer> li = new ArrayList<Integer>();
	 ArrayList<Integer> pre = new ArrayList<Integer>();
	 ArrayList<Integer> post = new ArrayList<Integer>();
	 ArrayList<Integer> inor = new ArrayList<Integer>();
	static int x_change;
	static int y_change;
	int x,y;
	
	boolean done = false;
    
    
    String value="";
    
    public drawtrees(Context context, AttributeSet attrs) {
        super(context, attrs);

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
               
        thread = new AnimationThread(holder);
        
    }
    
    
    public void resetNode(){
    	root=null;
    	li.clear();
    	done =false;
    	
    }
    
    
    
    public void delNode(int num){    	
    	if(li.size()==1){
    		root = null;    		
    	li.clear();	
    	}else{
    	li.remove(li.lastIndexOf(num));
    	}
    	
    	
    	
    }
     
    public void copyNode(Node n){
    	root = n;
    	
    }
    
    public void setNode(int num){
    	this.num = num;
    	if(num==-999){
    		
    	}else{
    		
    		if(!li.isEmpty()){
        		root.select=true;
        	}
    		
    	li.add(num);
    	}
    	
    	
    	done = false;
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
         			bg.set(0, 0, drawtrees.this.getWidth(), drawtrees.this.getHeight());
         			p.setColor(Color.rgb(172, 227, 230));
         			p.setStyle(Paint.Style.FILL);
         			c.drawRect(bg, p);
         			
         			p.setColor(Color.BLACK);
         			p.setStyle(Paint.Style.STROKE);
         			bg.set(1,1,drawtrees.this.getWidth()-1,drawtrees.this.getHeight()-1);
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
                	if(!done){
                		sleep(3000);
                	}else{
					sleep(3000);
                	}
                	
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        	
        }
        
        //inorder traversal
        public void inorder()
        {
            inorder(root);
        }
        private void inorder(Node r)
        {
            if (r != null)
            {
                inorder(r.leftChild);
                inor.add(r.num);
                inorder(r.rightChild);
            }
        }
        
        //POST ORDER Traversal
        public void postorder()
        {
            postorder(root);
        }
        private void postorder(Node r)
        {
            if (r != null)
            {
                postorder(r.leftChild);             
                postorder(r.rightChild);
                post.add(r.num);
            }
        }     
        
        //TRAVERSAL OF PRE ORDER
        public void preOrderTraversal()
        {
           preOrderHelper(root);
        }
        private void preOrderHelper(Node r)
        {
           if (r != null)
           {
              pre.add(r.num);
              preOrderHelper(r.leftChild);
              preOrderHelper(r.rightChild);
           }
        }
        
        
       
        @SuppressWarnings("unused")
		private void doDraw(Canvas canvas) {
			// clear the canvas
        	int px = (int) (getResources().getDisplayMetrics().density * 1);
        	Paint pt = new Paint();
        	pt.setTextSize(12*px);
        	Paint moveColor = new Paint();
			moveColor.setColor(Color.YELLOW);
			moveColor.setStyle(Style.FILL_AND_STROKE);
        	x = (drawtrees.this.getWidth()/2);
			y = 50;
			
		
			
			canvas.drawText("User Inputs:", 10, getHeight()-(60*px), pt);
			canvas.drawText("Array Size: "+li.size()+"", 10,getHeight()-(10*px),pt);
        	if(li.isEmpty()){
        		return;
        	}else{
        		Object[] inputs = li.toArray();        		
        		String wo="";
        		for( Object n : inputs){
        			wo += n+" ";
        		}
        		canvas.drawText(wo, 10, getHeight()-(45*px), pt);
        		
        	}
        	 
        	
        	
if(done){
        		preOrderTraversal();
        		postorder();
        		inorder();
        		focusNode=root;
            	for(int i = 0 ; i <li.size();i++){
            		
            		if(i==0){
            			canvas.drawCircle(x, y, 11+(5*px), p);
            			if(root.num>9||root.num<-9){
            				canvas.drawText(root.num+"", x - (5+(5*px)), y + (3*px), pt);
            			}else{
            			canvas.drawText(root.num+"", x - 10, y + (3*px), pt);    
            			}
            			continue;
            		}
            	
            		int yprev=y;
            		y+=45*px;
            		        		
            		if(li.get(i)<focusNode.num){
            			int xprev =x;            		
            			focusNode = focusNode.leftChild;
            		switch(focusNode.level){
            		case 1: x-=45+(px*22);
            				break;
            		case 2: x-=((45)+(px*23))/2;
            				break;
            		case 3: x-=(45+(px*25))/4;
            		}
		            	
		            		
            		if(i==focusNode.order){
            			
            			canvas.drawLine(xprev, yprev+10+(5*px), x, y-10-(5*px), p);
       				 canvas.drawCircle(x, y, 11+(5*px), p);
       				 if(focusNode.num>9||focusNode.num<-9){
       					canvas.drawText(focusNode.num+" ",x -(5+(5*px)), y+(3*px), pt);  
       				 }else{
           			 canvas.drawText(focusNode.num+" ", x-10, y+(3*px), pt);     
       				 }
           			 focusNode =root;
           			x = (drawtrees.this.getWidth()/2);
        			y = 50;
           			 continue;
            		}else{
            			canvas.drawLine(xprev, yprev+10+(5*px), x, y-10-(5*px), p);
          				 canvas.drawCircle(x, y, 11+(5*px), p);
          				 if(focusNode.num>9||focusNode.num<-9){
          					canvas.drawText(focusNode.num+" ",x - (5+(5*px)), y+(3*px), pt);  
          				 }else{
              			 canvas.drawText(focusNode.num+" ", x-10, y+(3*px), pt);     
          				 }
              			 --i;
            		}
            		
            		}else{
            			
            			focusNode =focusNode.rightChild;
            	
            			int xprev=x;
            		
            			
            			switch(focusNode.level){
                		case 1: x+=45+(px*22);
                				break;
                		case 2: x+=(45+(px*23))/2;
                				break;
                		case 3: x+=(45+(25*px))/4;
                		}

            			
            			if(i==focusNode.order){
                			canvas.drawLine(xprev, yprev+10+(5*px), x, y-10-(5*px), p);
           				 canvas.drawCircle(x, y, 11+(5*px), p);
           				 if(focusNode.num>9||focusNode.num<-9){
            					canvas.drawText(focusNode.num+" ",x -(5+(5*px)), y+(3*px), pt);  
            				 }else{
                			 canvas.drawText(focusNode.num+" ", x-10, y+(3*px), pt);     
            				 }
               			 focusNode =root;
               			x = (drawtrees.this.getWidth()/2);
            			y = 50;
               			 continue;
                		}else{
                			canvas.drawLine(xprev, yprev+10+(5*px), x, y-10-(5*px), p);
              				 canvas.drawCircle(x, y, 11+(5*px), p);
              				 if(focusNode.num>9||focusNode.num<-9){
                					canvas.drawText(focusNode.num+" ",x -(5+(5*px)), y+(3*px), pt);  
                				 }else{
                    			 canvas.drawText(focusNode.num+" ", x-10, y+(3*px), pt);     
                				 }                 			 
                      			 --i;
                  			 
                		}
            		}
            		
            	}
            	
            	//inorder
            	canvas.drawText("Inorder:", 10, getHeight()-(125*px), pt);
            	Object [] inors = inor.toArray();
            	String wo="";
            	for(Object n :inors){
        			wo+=n+" ";
        		}
            	canvas.drawText(wo, 10, getHeight()-(115*px), pt);
            	inor.clear();
            	//PREORDER
            	canvas.drawText("Preorder:", 10, getHeight()-(85*px), pt);
            	Object [] pr = pre.toArray();
            	wo="";
            	for(Object n :pr){
        			wo+=n+" ";
        		}
            	canvas.drawText(wo, 10, getHeight()-(75*px), pt);
            	pre.clear();
            	//POstORDER
            	canvas.drawText("Postorder:", 10, getHeight()-(105*px), pt);
            	Object [] pos = post.toArray();
            	wo="";
            	for(Object n :pos){
        			wo+=n+" ";
        		}
            	canvas.drawText(wo, 10, getHeight()-(95*px), pt);
            	post.clear();
            	
            	
            	
            	
            	
            	
    			canvas.drawText("Done", 10*px, 10*px, pt);    			
    			return;
    			
    		}
        	
        	
        	
        	if(root==null){
        		root = new Node(num);
        		root.order=0;
        		
        	}
        	
        	//if done final layout of tree
        	
        	//////////////////////////////////////////////////////////////////////////////////
        	//TREE SO FAR
        	
        	
        	
        	
        	x = (drawtrees.this.getWidth()/2);
			y = 50;
        	
        	// tree so far
        	focusNode=root;
        	for(int i = 0 ; i <li.size()-1;i++){
        		
        		if(i==0){
        			canvas.drawCircle(x, y, 11+(5*px), p);
        			 if(focusNode.num>9||focusNode.num<-9){
        					canvas.drawText(focusNode.num+" ",x -(5+(5*px)), y+(3*px), pt);  
        				 }else{
            			 canvas.drawText(focusNode.num+" ", x-10, y+(3*px), pt);     
        				 }    			
        			continue;
        		}
        		
        		int yprev=y;
        		y+=45*px;
        		        		
        		if(li.get(i)<focusNode.num){
        			int xprev =x;
        	
        			focusNode = focusNode.leftChild;
        		switch(focusNode.level){
        		case 1:x-=45+(px*22);
        				break;
        		case 2:  x-=((45)+(px*23))/2;
        				break;
        		case 3:  x-=(45+(px*25))/4;
        		}
	            	
	            		
        		if(i==focusNode.order){
        			
        			canvas.drawLine(xprev, yprev+10+(5*px), x, y-10-(5*px), p);
   				 canvas.drawCircle(x, y, 11+(5*px), p);
   				 if(focusNode.num>9||focusNode.num<-9){
    					canvas.drawText(focusNode.num+" ",x -(5+(5*px)), y+(3*px), pt);  
    				 }else{
        			 canvas.drawText(focusNode.num+" ", x-10, y+(3*px), pt);     
    				 }
       			 focusNode =root;
       			x = (drawtrees.this.getWidth()/2);
    			y = 50;
       			 continue;
        		}else{
        			canvas.drawLine(xprev, yprev+10+(5*px), x, y-10-(5*px), p);
      				 canvas.drawCircle(x, y, 11+(5*px), p);
      				 if(focusNode.num>9||focusNode.num<-9){
        					canvas.drawText(focusNode.num+" ",x -(5+(5*px)), y+(3*px), pt);  
        				 }else{
            			 canvas.drawText(focusNode.num+" ", x-10, y+(3*px), pt);     
        				 }
          		
          			 --i;
        		}
        		
        		}else{
        			
        			focusNode =focusNode.rightChild;
        		
        			int xprev=x;
        		
        			
        			switch(focusNode.level){
            		case 1:x+=45+(px*22);
            				break;
            		case 2: x+=((45)+(px*23))/2;
            				break;
            		case 3: x+=(45+(25*px))/4;
            		}

        			
        			if(i==focusNode.order){
            			canvas.drawLine(xprev, yprev+10+(5*px), x, y-10-(5*px), p);
       				 canvas.drawCircle(x, y, 11+(5*px), p);
       				 if(focusNode.num>9||focusNode.num<-9){
        					canvas.drawText(focusNode.num+" ",x -(5+(5*px)), y+(3*px), pt);  
        				 }else{
            			 canvas.drawText(focusNode.num+" ", x-10, y+(3*px), pt);     
        				 }
           			 focusNode =root;
           			x = (drawtrees.this.getWidth()/2);
        			y = 50;
           			 continue;
            		}else{
            			canvas.drawLine(xprev, yprev+10+(5*px), x, y-10-(5*px), p);
          				 canvas.drawCircle(x, y, 11+(5*px), p);
          				 if(focusNode.num>9||focusNode.num<-9){
            					canvas.drawText(focusNode.num+" ",x -(5+(5*px)), y+(3*px), pt);  
            				 }else{
                			 canvas.drawText(focusNode.num+" ", x-10, y+(3*px), pt);     
            				 }                  			 
                  			 --i;
              			 
            		}
        		}
        		
        	}
    		
        	
        	
        	
        	
        	
        	//------------------------------------------------------------------------
        	//inserting
        	
        	x = (drawtrees.this.getWidth()/2);
			y = 50;
        	Node newNode = new Node(num);
        	canvas.drawText("Inserting "+ num, 10,getHeight()-(30*px),pt);
        	String in="";
        	focusNode = root;        	
        	int level = 1;
        	while(true){
        		parent = focusNode;
        		if(li.size()==1){
        			canvas.drawCircle(x, y, 11+(5*px), p);
        			 if(focusNode.num>9||focusNode.num<-9){
        					canvas.drawText(focusNode.num+" ",x -(5+(5*px)), y+(3*px), pt);  
        				 }else{
            			 canvas.drawText(focusNode.num+" ", x-10, y+(3*px), pt);     
        				 }
        			break;
        		}
        		
        		if(level>3){
        			done =true;
        			li.remove(li.lastIndexOf(num));
        			return;
        			
        		}
        		
        		if(root==focusNode&&root.select){
        			
        			 canvas.drawCircle(x, y, 11+(5*px), moveColor);
        			 if(focusNode.num>9||focusNode.num<-9){
        					canvas.drawText(focusNode.num+" ",x -(5+(5*px)), y+(3*px), pt);  
        				 }else{
            			 canvas.drawText(focusNode.num+" ", x-10, y+(3*px), pt);     
        				 }
        			// level++;
        			 root.select=false;
        			 if(num<focusNode.num){
        				 focusNode.leftChild.select=true;
        			 }else{
        				 focusNode.rightChild.select=true;
        			 }
        			return;
        		}else{
        			 canvas.drawCircle(x, y, 11+(5*px), p);
        			 if(focusNode.num>9||focusNode.num<-9){
        					canvas.drawText(focusNode.num+" ",x -(5+(5*px)), y+(3*px), pt);  
        				 }else{
            			 canvas.drawText(focusNode.num+" ", x-10, y+(3*px), pt);     
        				 }
        			//level++;
        		}        	
        		
        		int yprev=y;        		
        		y+=45*px;
        	//	canvas.drawText(focusNode.num+"", 10, getHeight()-50, p);
        		if(num<focusNode.num){
        			
        			int xprev=x;
        			
        			// canvas.drawText("GO"+ "     "+focusNode.level, 10, getHeight()-90, p);
        	//		canvas.drawText(focusNode.num+"", 50, getHeight()-50, p);
        			focusNode = focusNode.leftChild;
        			
        			
        			
                    if(focusNode == null){
                    	switch(level){        			 
                		case 1:  x-=45+(px*22);
                				break;
                		case 2: x-=((45)+(px*23))/2;
                				break;
                		case 3:  x-=(45+(px*25))/4;
                		}
                    	// canvas.drawText(focusNode==null?"yes":"no", 70, getHeight()-70, p);
                        parent.leftChild = newNode;
                       parent.leftChild.order=li.size()-1;
                       parent.leftChild.level=level;
                       canvas.drawLine(xprev, yprev+10+(5*px), x, y-10-(5*px), p);
        				 canvas.drawCircle(x, y, 11+(5*px), p);
        				 if(parent.leftChild.num>9||parent.leftChild.num<-9){
		       					canvas.drawText(parent.leftChild.num+" ",x -(5+(5*px)), y+(3*px), pt);  
		       				 }else{
		           			 canvas.drawText(parent.leftChild.num+" ", x-10, y+(3*px), pt);     
		       				 }
            			
            			break; 
            		}else{
            			switch(focusNode.level){        			 
                		case 1: x-=45+(px*22);
                				break;
                		case 2: x-=((45)+(px*23))/2;
                				break;
                		case 3:  x-=(45+(px*25))/4;
                		}
            			
            			 
            			if(focusNode.select){
            				canvas.drawLine(xprev, yprev+10+(5*px), x, y-10-(5*px), p);
            				 canvas.drawCircle(x, y,11 +(5*px), moveColor);
                			 canvas.drawText(focusNode.num+" ", x-10, y+3, pt);
                			 focusNode.select=false;
                			 if(num<focusNode.num){                				
                				 focusNode.leftChild.select=true;
                			 }else{
                				 focusNode.rightChild.select=true;
                			 }
                			 return;
            			}else{
            			canvas.drawLine(xprev, yprev+10+(5*px), x, y-10-(5*px), p);
         				 canvas.drawCircle(x, y, 11+(5*px), p);
         				 if(focusNode.num>9||focusNode.num<-9){
         					canvas.drawText(focusNode.num+" ",x -(5+(5*px)), y+(3*px), pt);  
         				 }else{
             			 canvas.drawText(focusNode.num+" ", x-10, y+(3*px), pt);     
         				 }
            			}
            		}
                   
        		}else{
        			int xprev =x;
        			
        			focusNode = focusNode.rightChild;
        			
        			// controlling x 
        			
					if (focusNode == null) {
						switch(level){        			 
	            		case 1:x+=45+(px*22);
	            				break;
	            		case 2: x+=((45)+(px*23))/2;
	            				break;
	            		case 3: x+=(45+(25*px))/4;
	            		}
						parent.rightChild = newNode;
						parent.rightChild.order = li.size() - 1;
						 parent.rightChild.level=level;
						 canvas.drawLine(xprev, yprev+10+(5*px), x, y-10-(5*px), p);
						canvas.drawCircle(x, y, 11+(5*px), p);
						 if(parent.rightChild.num>9||parent.rightChild.num<-9){
		       					canvas.drawText(parent.rightChild.num+" ",x -(5+(5*px)), y+(3*px), pt);  
		       				 }else{
		           			 canvas.drawText(parent.rightChild.num+" ", x-10, y+(3*px), pt);     
		       				 }
						
						break;

					}else{
						switch(focusNode.level){        			 
	            		case 1:x+=45+(px*22);
	            				break;
	            		case 2: x+=((45)+(px*23))/2;
	            				break;
	            		case 3: x+=(45+(25*px))/4;
	            		}
						if(focusNode.select){
							canvas.drawLine(xprev, yprev+10+(5*px), x, y-10-(5*px), p);
	         				 canvas.drawCircle(x, y, 11+(5*px), moveColor);
	         				 if(focusNode.num>9||focusNode.num<-9){
	            					canvas.drawText(focusNode.num+" ",x -(5+(5*px)), y+(3*px), pt);  
	            				 }else{
	                			 canvas.drawText(focusNode.num+" ", x-10, y+(3*px), pt);     
	            				 }
	             			 focusNode.select=false;
	             			 if(num<focusNode.num){
	            				 focusNode.leftChild.select=true;
	            			 }else{
	            				 focusNode.rightChild.select=true;
	            			 }
	             			 return;
						}else{
						canvas.drawLine(xprev, yprev+10+(5*px), x, y-10-(5*px), p);
         				 canvas.drawCircle(x, y, 11+(5*px), p);
         				 if(focusNode.num>9||focusNode.num<-9){
            					canvas.drawText(focusNode.num+" ",x -(5+(5*px)), y+(3*px), pt);  
            				 }else{
                			 canvas.drawText(focusNode.num+" ", x-10, y+(3*px), pt);     
            				 }
						}             			
					}
					
        				
        		}
        		level++;
        	}
        	
        	
        	canvas.drawText("FINISH", 10, getHeight()-(80*px), pt);
        	        
        	done = true;
        	
			
		}
        
        public void setRunning(boolean b) {
            mRun = b;
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    public void surfaceCreated(SurfaceHolder holder) {
    	setMeasuredDimension(DRAWING_CACHE_QUALITY_HIGH, DRAWING_CACHE_QUALITY_HIGH);
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

	