
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.TransferHandler;

public class MainD {

    JFrame j;
    public static int x, y, total, total1;
    public int chx, chy;
    boolean left, right;
    public int hold;
    SharedObject s = new SharedObject();
    trial1 t1 = new trial1(s); // left panel
    trial2 t2 = new trial2(s);   // right panel 
    JLabel logolabel[] = new JLabel[4];
    public int holder = 100;
    JButton reset;

    public MainD() {

        j = new JFrame("Balancing Weight Activity"); // JFRAME OF OUR INTERFACE
        j.setLayout(null);
        j.setPreferredSize(new Dimension(800, 600));
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setResizable(false);
        j.setVisible(true);
        reset = new JButton("Reset");
        for (int i = 0; i < 10; i++) {
        }

        reset.setBounds(330, 500, 100, 20);
        j.add(reset);
        //JLabel label = new JLabel(imageIcon);
        t1.setBounds(0, 0, 400, 300);
        t2.setBounds(400, 0, 400, 300);
        j.add(t1);
        j.add(t2);
        final int value[] = {5, 10, 15, 20};

        for (int i = 0; i < 4; i++) {
            logolabel[i] = new JLabel();
            logolabel[i].setSize(50, 50);
            logolabel[i].setBackground(Color.red);
        }

        ImageIcon logoicon = new ImageIcon(getClass().getResource("/weight.png"));
        Image logo = logoicon.getImage();
        final JLabel rui = new JLabel();
        rui.setBounds(200, 500, 100, 30);
        j.add(rui);

        for (int i = 0; i < 4; i++) {
            Image newlogo = logo.getScaledInstance(logolabel[i].getWidth(), logolabel[i].getHeight(), java.awt.Image.SCALE_SMOOTH);
            logoicon = new ImageIcon(newlogo);
            logolabel[i].setIcon(logoicon);
            logolabel[i].setBounds(100 + (i * 170), 400, 50, 50);
            logolabel[i].setTransferHandler(new TransferHandler("icon"));
            j.add(logolabel[i]);

        }

        final JLabel weightinfo[] = {new JLabel("5 Kg"), new JLabel("10 Kg"), new JLabel("15 Kg"), new JLabel("20 Kg")}; // LABEL FOR KG of WeighT 1
        for (int i = 0; i < 4; i++) {
            weightinfo[i].setBounds(110 + (i * 170), 440, 50, 50);
            j.add(weightinfo[i]);
            j.repaint();
        }

        j.repaint();
        j.pack();

        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterbbActionPerformed(evt);
            }
        });
        //----------------------------------

        t1.addMouseListener(new MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                chx = evt.getX();
                chy = evt.getY();

                if ((chy >= 400 && chy <= 450) && (chx >= 100 && chx <= 150)) {
                    left = true;
                    right = false;

                    sub(5);

                } else if ((chy >= 400 && chy <= 450) && (chx >= 270 && chx <= 320)) {
                    left = true;
                    right = false;

                    sub(10);

                } else if ((chy >= 400 && chy <= 450) && (chx >= 440 && chx <= 490)) {
                    left = true;
                    right = false;

                    sub(15);

                } else if ((chy >= 400 && chy <= 450) && (chx >= 610 && chx <= 660)) {
                    left = true;
                    right = false;

                    sub(20);

                } else {
                    left = false;
                    right = false;
                }
            }
        });

        t2.addMouseListener(new MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                chx = evt.getX();
                chy = evt.getY();

                System.out.println(chy + " " + chx);
                if ((chy >= 400 && chy <= 450) && (chx >= -300 && chx <= -250)) {
                    left = false;
                    right = true;

                    sub(5);

                } else if ((chy >= 400 && chy <= 450) && (chx >= -130 && chx <= -80)) {
                    left = false;
                    right = true;

                    sub(10);

                } else if ((chy >= 400 && chy <= 450) && (chx >= 40 && chx <= 90)) {
                    left = false;
                    right = true;

                    sub(15);

                } else if ((chy >= 400 && chy <= 450) && (chx >= 210 && chx <= 260)) {
                    left = false;
                    right = true;

                    sub(20);

                } else {
                    left = false;
                    right = false;
                }
            }
        });

        logolabel[0].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                chx = evt.getX();
                chy = evt.getY();
                if ((chy >= -400 && chy <= -153) && (chx >= -100 && chx <= 153)) {
                    left = true;
                    right = false;
                    log5(evt);
                } else if ((chy >= -400 && chy <= -153) && (chx >= 300 && chx <= 695)) {
                    left = false;
                    right = true;
                    log5(evt);
                } else {
                    left = false;
                    right = false;
                }

            }
        });

        logolabel[1].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                chx = evt.getX();
                chy = evt.getY();
                if ((chy >= -270 && chy <= -153) && (chx >= -400 && chx <= 129)) {
                    left = true;
                    right = false;
                    log10(evt);
                } else if ((chy >= -400 && chy <= -153) && (chx >= 135 && chx <= 525)) {
                    left = false;
                    right = true;
                    log10(evt);
                } else {
                    left = false;
                    right = false;
                }
            }
        });
        logolabel[2].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                chx = evt.getX();
                chy = evt.getY();
                if ((chy >= -400 && chy <= -153) && (chx >= -440 && chx <= -39)) {
                    left = true;
                    right = false;
                    log15(evt);
                } else if ((chy >= -400 && chy <= -153) && (chx >= -40 && chx <= 351)) {
                    left = false;
                    right = true;
                    log15(evt);
                } else {
                    left = false;
                    right = false;
                }
            }
        });
        logolabel[3].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                chx = evt.getX();
                chy = evt.getY();
                if ((chy >= -400 && chy <= -153) && (chx >= -612 && chx <= -225)) {
                    left = true;
                    right = false;
                    log20(evt);
                } else if ((chy >= -400 && chy <= -153) && (chx >= -40 && chx <= 180)) {
                    left = false;
                    right = true;
                    log20(evt);
                } else {
                    left = false;
                    right = false;
                }
            }
        });
        logolabel[3].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logolabel3(evt);
            }
        });
        logolabel[2].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logolabel2(evt);
            }
        });
        logolabel[1].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logolabel1(evt);
            }
        });
        logolabel[0].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logolabel0(evt);
            }
        });
        logolabel[3].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                logolabel31(evt);
            }
        });
        logolabel[2].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                logolabel21(evt);
            }
        });
        logolabel[1].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                logolabel11(evt);
            }
        });
        logolabel[0].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                logolabel01(evt);
            }
        });
    }

    private void enterbbActionPerformed(java.awt.event.ActionEvent evt) {
        s.x = 100;
        s.y = 100;
        total1 = 0;
        total = 0;
        s.x1 = total;
        s.x2 = total1;
    }

    private void logolabel3(java.awt.event.MouseEvent evt) {
        logolabel[3].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private void logolabel2(java.awt.event.MouseEvent evt) {
        logolabel[2].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private void logolabel1(java.awt.event.MouseEvent evt) {
        logolabel[1].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private void logolabel0(java.awt.event.MouseEvent evt) {
        logolabel[0].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private void logolabel31(java.awt.event.MouseEvent evt) {
        logolabel[3].setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
    }

    private void logolabel21(java.awt.event.MouseEvent evt) {
        logolabel[2].setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
    }

    private void logolabel11(java.awt.event.MouseEvent evt) {
        logolabel[1].setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
    }

    private void logolabel01(java.awt.event.MouseEvent evt) {
        logolabel[0].setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
    }

    private void log5(java.awt.event.MouseEvent evt) {
        yo(5, evt);
    }

    private void log10(java.awt.event.MouseEvent evt) {
        yo(10, evt);
    }

    private void log15(java.awt.event.MouseEvent evt) {
        yo(15, evt);
    }

    private void log20(java.awt.event.MouseEvent evt) {
        yo(20, evt);
    }

    public void yo(int num, java.awt.event.MouseEvent evt) {
        //   System.out.println(chy + " "+chx);

        if (left) {

            if (total + num <= 100) {
                total += num;
                double t_total = total + total1;
                // System.out.println(t_total);
                double percent_x = 0, percent_y = 0;

                try {
                    percent_x = (double) (total) / t_total;
                    percent_y = (double) (total1) / t_total;
                } catch (Exception e) {
                    System.out.println(e);

                }
                System.out.println("PERCENT" + (percent_x * 200));
                System.out.println("PERCENT" + (percent_y * 200));

                s.x = (int) (percent_x * 200);
                System.out.println(s.x);
                s.y = (int) (percent_y * 200);
                System.out.println(s.y);
                s.x1 = total;
            } else if (total == 100) {
                JOptionPane.showMessageDialog(null, "You have reached the maximum weight", "Oops!", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "It can only handle less than 100 KG", "Oops!", JOptionPane.ERROR_MESSAGE);
            }

        } else if (right) {
            if (total1 + num <= 100) {

                total1 += num;

                double t_total = total + total1;
                // System.out.println(t_total);
                double percent_x = 0, percent_y = 0;

                try {
                    percent_x = (double) (total) / t_total;
                    percent_y = (double) (total1) / t_total;
                } catch (Exception e) {
                    System.out.println(e);

                }
                System.out.println("PERCENT" + (percent_x * 200));
                System.out.println("PERCENT" + (percent_y * 200));
                s.x = (int) (percent_x * 200);
                s.y = (int) (percent_y * 200);
                System.out.println(s.x);
                System.out.println(s.y);
                s.x2 = total1;
            } else if (total == 100) {
                JOptionPane.showMessageDialog(null, "You have reached the maximum weight", "Oops!", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "It can only handle less than 100 KG", "Oops!", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public void sub(int num) {
        if (left) {

            if (total == 0) {
                JOptionPane.showMessageDialog(null, "Noting can be taken", "Oops!", JOptionPane.ERROR_MESSAGE);
            } else if (total < num) {
                JOptionPane.showMessageDialog(null, "The total weight is just  " + total + " KG only", "Oops!", JOptionPane.ERROR_MESSAGE);
            } else {
                boolean check = false;
                total-=num;
                  double t_total = total + total1;
                  if(t_total==0){
                 check = true;
                  }
                  
                  if(check){
                  s.x=100;
                  s.y=100;
                  s.x1= total;
                  }else{
                  
                  
                 System.out.println(t_total);
                double percent_x = 0, percent_y = 0;
                
                try {
                    percent_x = (double) (total) / t_total;
                    percent_y = (double) (total1) / t_total;
                } catch (Exception e) {
                    System.out.println(e);

                }
                System.out.println("PERCENT" + (percent_x + 100));
                System.out.println("PERCENT" + (percent_y + 100));
                s.x = (int)(percent_x*200);
                s.y = (int)(percent_y*200);
                System.out.println(s.x);
                System.out.println(s.y);
                s.x1 = total;
                  }
            }
        }
        if (right) {

            if (total1 == 0) {
                JOptionPane.showMessageDialog(null, "Noting can be taken", "Oops!", JOptionPane.ERROR_MESSAGE);

            } else if (total1 < num) {
                JOptionPane.showMessageDialog(null, "The total weight is just " + total1 + " KG only", "Oops!", JOptionPane.ERROR_MESSAGE);
            } else {
                  boolean check = false;
                total1 -= num;
                 double t_total = total + total1;
                 if(t_total==0){
                 check = true;
                  }
                  
                  if(check){
                  s.x=100;
                  s.y=100;
                  s.x2 = total1;
                  }else{
                // System.out.println(t_total);
                double percent_x = 0, percent_y = 0;

                try {
                    percent_x = (double) (total) / t_total;
                    percent_y = (double) (total1) / t_total;
                } catch (Exception e) {
                    System.out.println(e);

                }
                System.out.println("PERCENT" + (percent_x * 200));
                System.out.println("PERCENT" + (percent_y * 200));
                s.y = (int)(percent_y*200);
                s.x = (int)(percent_x*200);
                System.out.println(s.x);
                System.out.println(s.y);
                s.x2 = total1;
            }
            }
        }
    }

    public static void main(String[] args) {
        new MainD();
    }
}
