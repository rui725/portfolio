
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Librarian extends javax.swing.JFrame {
    int i = 0,id;
    
    /**
     * Creates new form Librarian
     */
    public Librarian(int num) {
        initComponents();
        setLocationRelativeTo(null); 
        id =num;
        String cname="",cusname="",cpass="";
        try {
        Class.forName("oracle.jdbc.OracleDriver");
        

        Connection conn;
           
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "LibJirk","1234");
            
            
        String query;
            query = "SELECT L_FNAME|| ' '||L_LNAME,L_USNAME,L_PASS FROM Librarian Where L_ID = "+id;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        while(rs.next()){
            cname = rs.getString(1);
            cusname = rs.getString(2);
            cpass = rs.getString(3);
        }
        usname.setText(cusname);
        name.setText(cname);
        libid.setText(id+"");
        pass.setText(cpass);
        }catch(Exception e){
            System.out.println(e);}
        
        
        
        
        
        
        
        jPanel1.setBackground(new Color(78,186,75,75));
        
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    //do{
                            for (; i <= 200; i++) {
                                Thread.sleep(5);
                                jPanel1.setBackground(new Color(78, 186, 75, i));
                                repaint();
                            }
                            //jPanel2.setBackground(new Color(78, 186, 75, 50));
                            for (; i > 90; i--) {
                                        Thread.sleep(5);
                                        jPanel1.setBackground(new Color(78, 186, 75, i));
                                        repaint();
                                        }
                    //}while(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        };
        t.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        libid = new javax.swing.JLabel();
        usname = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        pass = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(960, 670));
        setResizable(false);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paris.jpg"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(915, 650));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tempus Sans ITC", 0, 24)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/header_1.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 0, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Librarian Account");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 191, -1, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("NAME:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 242, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("LIBRARIAN ID:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 270, -1, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("USERNAME:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 293, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(726, 224, -1, -1));

        name.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 242, 139, 17));

        libid.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        libid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(libid, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 270, 133, 17));

        usname.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        usname.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(usname, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 293, 180, 20));

        jButton1.setBackground(new java.awt.Color(72, 186, 75));
        jButton1.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jButton1.setText("LOGOUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(739, 191, -1, -1));

        jButton2.setBackground(new java.awt.Color(72, 186, 75));
        jButton2.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jButton2.setText("CREATE NEW STUDENT ACCOUNT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 352, 273, -1));

        jButton3.setBackground(new java.awt.Color(72, 186, 75));
        jButton3.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jButton3.setText("UPDATE BOOK INFORMATION");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 383, 273, -1));

        jButton4.setBackground(new java.awt.Color(72, 186, 75));
        jButton4.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jButton4.setText("REGISTER BOOK");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 416, 273, -1));

        jButton5.setBackground(new java.awt.Color(72, 186, 75));
        jButton5.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jButton5.setText("TRANSACTION");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 449, 273, -1));

        jButton6.setBackground(new java.awt.Color(72, 186, 75));
        jButton6.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jButton6.setText("EDIT ACCOUNT");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 482, 273, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("PASSWORD:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 324, -1, -1));

        pass.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pass.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 319, 111, 17));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
        new Main().launch();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       dispose();
       new NewAcct(id).launch();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int bid=0,cid; boolean check = false;
        
        try{
        bid=Integer.parseInt(JOptionPane.showInputDialog(null,"Please Enter BOOK ID"));
        }catch(NumberFormatException e){
        JOptionPane.showMessageDialog(null, "You've Entered Wrong Input","FAILED",JOptionPane.ERROR_MESSAGE);
        }
        
        
        try {
        Class.forName("oracle.jdbc.OracleDriver");
        

        Connection conn;
           
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "LibJirk","1234");
             Statement stmt = conn.createStatement();
            
        String query;
            query = "SELECT B_ID  FROM BOOK";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
            cid= Integer.parseInt(rs.getString(1));
            if(cid==bid){
            check =true;
            break;
            }
            
            }
            if(check){
                dispose();
            new UpdateBook(id,bid).setVisible(true);
            
            }else{
            JOptionPane.showMessageDialog(null,"Wrong Book ID please try again!","FAILED",JOptionPane.ERROR_MESSAGE);
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        dispose();
        new RegBook(id).setVisible(true);
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       dispose();
       new BookList(id).launch();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    String fnam,lnam,cusnam,cpass;
        boolean check = true;
        
        fnam= JOptionPane.showInputDialog("Enter Librarian First Name: ");
        lnam= JOptionPane.showInputDialog("Enter Librarian Last Name: ");
        cusnam= JOptionPane.showInputDialog("Enter Librarian Username: ");
        cpass= JOptionPane.showInputDialog("Enter Librarian Password: ");
        
        
        if(fnam.equals("")||lnam.equals("")||cusnam.equals("")||cpass.equals("")){
        JOptionPane.showMessageDialog(null, "Missing Input","FAILED",JOptionPane.ERROR_MESSAGE);
        check = false;
        }
        if(cpass.length()>6){
        JOptionPane.showMessageDialog(null, "Password Maximum of 6 characters","FAILED",JOptionPane.ERROR_MESSAGE);
        check=false;
        }
        
        if(check){
          try {
        Class.forName("oracle.jdbc.OracleDriver");
        

        Connection conn;
           
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "LibJirk","1234");
            
            
        String query;
            query = "UPDate Librarian set L_FNAME = '"+fnam+"', L_LNAME ='"+lnam+"',L_USNAME='"+cusnam+"',L_PASS='"+cpass+"' Where L_ID="+id;
              System.out.println(query);
            Statement stmt = conn.createStatement();
        stmt.execute(query);
        
        
        JOptionPane.showMessageDialog(null, "Account Updated","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
        
        }catch(Exception e){
            System.out.println(e);}
        

        name.setText(fnam+ " "+lnam);
        libid.setText(id+"");
        pass.setText(cpass);
        usname.setText(cusnam);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed
    }
    /**
     * @param args the command line arguments
     */
    public void launch() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Librarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Librarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Librarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Librarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Librarian(id).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel libid;
    private javax.swing.JLabel name;
    private javax.swing.JLabel pass;
    private javax.swing.JLabel usname;
    // End of variables declaration//GEN-END:variables
}
