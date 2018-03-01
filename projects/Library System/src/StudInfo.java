/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jaang
 */
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane; 
public class StudInfo extends javax.swing.JFrame {
int i = 0,sid;
 String bordate="",curdate,tid="";
    /**
     * Creates new form StudInfo
     */
    String usern,pass;
    public StudInfo(int sid) {
        this.sid = sid;
        initComponents();
        setLocationRelativeTo(null);
        String cname="",ccourse="";
       
        studid.setText(sid+"");
         try {
        Class.forName("oracle.jdbc.OracleDriver");
        

        Connection conn;
           
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "LibJirk","1234");
             Statement stmt = conn.createStatement();
            
        String query;
            query = "SELECT S_FNAME || ' ' || S_LNAME, S_COURSE  FROM STUDENTS Where S_ID = "+sid;
            ResultSet rs = stmt.executeQuery(query);
        
            while(rs.next()){
           cname = rs.getString(1);
            ccourse = rs.getString(2);
            }
            name.setText(cname);
            course.setText(ccourse);
         //-------------------------------------------------------------------------------   
            boolean check = true;
            int curday, prevday,fine,days; 
            query = "SELECT  T_BOR_DATE,T_ID,T_RET_DATE FROM TRANSACTION WHERE S_ID = "+sid ;
             
             rs = stmt.executeQuery(query);
            
            while(rs.next()){
            bordate = rs.getString(1);                 
            tid = rs.getString(2);
            if(rs.getString(3)!=null){
            check = false;
            continue;
            }
            
            if(bordate ==null){
            check = false;
            continue;
            }
            if(check){
                
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy"); 
            DateFormat input = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date date = new java.util.Date();
            Date d= input.parse(bordate);
            bordate = dateFormat.format(d);
                System.out.println(bordate);
           
        curdate = dateFormat.format(date);          
            curday = Integer.parseInt(curdate.substring(0,2));
        prevday = Integer.parseInt(bordate.substring(0,2));
        days = curday - prevday;
        if(days <0){
        fine =0;
        }else{
        fine = (days*3);
                
           query = "UPDATE TRANSACTION SET T_FINE ="+fine+ ", T_FINE_STATUS = 'OVERDUE' WHERE S_ID ="+sid +"AND T_ID="+tid;
            stmt.execute(query);
        }  
            
            
            
            }   
            
            } 
     //--------------------------------------------------------------------------------------       
        query = "SELECT b.B_TITLE, t.T_BOR_DATE,t.T_RET_DATE,t.T_DUE_DATE,t.T_FINE,t.T_FINE_STATUS FROM TRANSACTION t, BOOK b WHERE t.S_ID="+sid +"OR t.B_ID = b.B_ID";     
          rs = stmt.executeQuery(query);
          String cdate;
          SimpleDateFormat sdf = new SimpleDateFormat("MMMMMMMMM dd, yyyy");
           DateFormat input = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         Date tdate;
         
          int i=0,j=0;
            while(rs.next()){
            jTable3.setValueAt(rs.getString(1),i,j++);
             tdate= input.parse(rs.getString(2));
            cdate = sdf.format(tdate);
            jTable3.setValueAt(cdate,i,j++);            
            tdate= input.parse(rs.getString(4));
            cdate = sdf.format(tdate);
            jTable3.setValueAt(cdate,i,j++);
            if(rs.getString(3)!=null){
                tdate= input.parse(rs.getString(3));
            cdate = sdf.format(tdate);
            jTable3.setValueAt(cdate,i,j++);
            }else{
            jTable3.setValueAt(rs.getString(3),i,j++);
            }
            jTable3.setValueAt(rs.getString(5),i,j++);
            jTable3.setValueAt(rs.getString(6),i,j++);
            i++;
            j=0;
            
            }
            
            
         }catch(Exception e){
             System.out.println(e);}
        
        
        jPanel1.setBackground(new Color(78,186,75,75));
        //jPanel1.setBackground(new Color(240,240,240,75));
        
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
                    System.out.println(e.getStackTrace());
                }
            }

        };
        t.start();
        
        
    }

    StudInfo(String username, String password) {
       usern = username;
       pass = password;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        course = new javax.swing.JLabel();
        studid = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(960, 670));
        setResizable(false);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paris.jpg"))); // NOI18N

        jPanel1.setPreferredSize(new java.awt.Dimension(914, 650));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 231, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tempus Sans ITC", 0, 24)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/header_1.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 770, -1));

        jLabel3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Account Information");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, -1, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("NAME:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 237, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("COURSE:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 265, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("STUDENT ID:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 293, -1, -1));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "BOOK TITLE", "DATE BORROWED", "DUE DATE", "DATE RETURNED", "FINE", "FINE STATUS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable3);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 364, 757, 141));

        jLabel8.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("TRANSACTION");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 333, -1, -1));

        name.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(191, 237, 169, 17));

        course.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        course.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(course, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 124, 17));

        studid.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        studid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(studid, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 112, 17));

        jButton3.setBackground(new java.awt.Color(72, 186, 75));
        jButton3.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jButton3.setText("SEARCH BOOK");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 516, -1, -1));

        jButton1.setBackground(new java.awt.Color(72, 186, 75));
        jButton1.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jButton1.setText("LOGOUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(723, 198, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
        new Main().launch();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
        new BookListStud(sid).launch();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(StudInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudInfo(sid).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel course;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel name;
    private javax.swing.JLabel studid;
    // End of variables declaration//GEN-END:variables
}
