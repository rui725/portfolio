
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 *
 * @author Rui
 */
public class trial1 extends JPanel implements ActionListener {

    SharedObject s;
    int x, y;
    boolean check = false;
    int def = 80;
    Timer t = new Timer(60, this); // TIMER sTARTS AND THE ACTIONLISTENER IS THE "this" in this part which executes 
    
    public trial1(SharedObject s) {
        this.s = s;  // to set OUR SHARED OBJECT
        x = 100;
        y = 100;   //LOCAL VARIABLE which is like the when LEVER HAS NO WEIGHTS THEY ARE BALANCED
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);// AUTOMATICALLY RUNS
        //x=150 low x=50 high   
        //setBackground(Color.BLUE);
     
        setBounds(0, 0, 400, 300);
        BufferedImage img;
        try {
            img = ImageIO.read(getClass().getResource("weight.png"));
          
            g.drawImage(img, def, x, this); // IMAGE POSITION DEPENDING in our X WHICH IS CHANGING
        } catch (IOException ex) {
            Logger.getLogger(trial1.class.getName()).log(Level.SEVERE, null, ex);
        }

        //LEVER LINES
        g.drawLine(101, x + 1, 401, 101);   //TO MAKE IT Look THICKER I USED ANOTHER LINE
        g.drawLine(100, x, 400, 100); // MAIN LINE
        g.drawLine(99, x - 1, 399, 99);   //TO MAKE IT Look THICKER I USED ANOTHER LINE
        g.setColor(Color.black);
        //TRiangle
        g.drawLine(400, 100, 250, 250);  // THE SLANT LINE FOR THE TRIANGLE(PANEL1) IN THE MIDDLE COORDINATES IS THE KEY
        // g.drawLine(200, 100, 230, 150);
        g.drawLine(0, 250, 400, 250);      // THE BASE LINE FOR THE TRIANGLE
        //g.fillOval(185,100, 30, 30);

        //ARROW
        g.drawLine(50, 100, 50, x);   //MAINLINE
        if (x > 100) {
            g.drawLine(40, 100 + (x - 100) - 10, 50, x); //ARROW FACING DOWN
            g.drawLine(60, 100 + (x - 100) - 10, 50, x);//ARROW FACING DOWN
        } else if (x < 100) {
            g.drawLine(60, 100 + (x - 100) + 10, 50, x);//ARRW FACING UP
            g.drawLine(40, 100 + (x - 100) + 10, 50, x);//ARROW FACING UP
        } else {
        }
        g.drawString("Weight: " + s.x1+" kg", 180, 200); // THE STRING FOR "WEIGHT 1" to display in our PANEL 1
        // g.drawString("Weight 2",300 ,100 );
         g.drawString("Acceleration:9.8m/s/s", 160, 220);
         g.drawString("Force Formula:", 300, 40);
         g.drawString("Force = mg", 300, 60);
        g.drawString("F="+(((int)(s.x1*9.8))-((int)(s.x2*9.8)))+"N",2,100);
        t.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {//THIS WILL EXECUTE ONCE WE START OUR TIMER 

        if (s.x > (x)) {// CHECKING OUR SHARED OBJECT WHICH WE ADDED WEIGHTS IN x would be the max movement that our lever can go
            x += 1;   // moves the lever up PANEL 1
            y -= 1;  //moves the lever down PANEL 2

        } else {
            x -= 1;   //MOVES THE LEVER down PANEL 1
            y += 1;   // MOves the lever UP PANEL 2

        }
        repaint(); // U KNOW THIS
    }
}
