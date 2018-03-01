
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rui
 */
public class trial2 extends JPanel implements ActionListener{
    SharedObject s;
    int x,y;
   boolean check=false;
   public trial2(SharedObject s){
   this.s = s;
   
   x=100;y=100;
   }
  //BASICALLY THIS IS PANEL 2 AND EVERYTHING IS THE SAME AS PANEL 1(TRIAL 1)
  
Timer t = new Timer(60,this); // 
    @Override
    public void paintComponent(Graphics g){
    super.paintComponent(g);
    //x=150 low x=50 high
   // setBackground(Color.GREEN);
    
    setBounds(400,0,400,300);
     BufferedImage img2;
       try {
                   
          img2 = ImageIO.read(getClass().getResource("weight.png")); 
            g.drawImage(img2,280,y,this);
            
       } catch (IOException ex) {
           Logger.getLogger(trial1.class.getName()).log(Level.SEVERE, null, ex);
       }
    g.drawLine(0,101, 301, y+1);
   g.drawLine(0,100, 300, y);
   g.drawLine(0,99, 299, y-1);
   
   
     g.setColor(Color.black);
     //TRiangle
    // g.drawLine(200, 100, 170, 150);
     g.drawLine(0, 100, 150, 250);
     g.drawLine(0,250,400,250);
    //g.fillOval(185,100, 30, 30);
  
    //ARROW
     g.drawLine(330,100,330,y);
     if(y>100){
      g.drawLine(320,100+(y-100)-10,330,y);
      g.drawLine(340,100+(y-100)-10,330,y);
     }else if(y<100){
     g.drawLine(320,100+(y-100)+10,330,y);
     g.drawLine(340,100+(y-100)+10,330,y);
     }else{
     
     }
     
    // g.drawString("Weight 1",50 ,100 );
      g.drawString("Weight: "+s.x2+" kg",170 ,200 );
      g.drawString("Acceleration:9.8m/s/s", 150, 220);
      g.drawString("Equilibrium Force:", 40, 40);
         g.drawString("F=-F", 40, 60);
       g.drawString("F="+(((int)(s.x2*9.8))-((int)(s.x1*9.8)))+"N",345,100);
    t.start();
    
    }
    
     @Override
    public void actionPerformed(ActionEvent e) {
        
        if(s.y<(y)){
       x+=1;
        y-=1;
        }else{
        x-=1;
        y+=1;
        
        }
        repaint();
    }
}
