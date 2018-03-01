
import java.awt.Image;
import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rui
 */
public class SharedObject {
    public int x;// CAN BE ACCESED BY BOTH PANEL 1 AND PANEL 2 WHICH WILL BE USED TO MOVE OUR LEVER x values
    public int y;// CAN BE ACCESED BY BOTH PANEL 1 AND PANEL 2 WHICH WILL BE USED TO MOVE OUR LEVER y values
    public int x1;// CAN BE ACCESED BY BOTH PANEL 1 AND PANEL 2 WHICH WILL BE USED TO MOVE OUR LEVER x values
    public int x2;
 
    //COORDINATES IN THE PANEL IS THE KEY TO MOVING THE LEVER AND EVERYTHING OK
    public SharedObject() {
        this.x =100;
        this.y = 100;
      
        
      // SETTING OUR WEIGHTS TO BALANCED
    }
    
}
