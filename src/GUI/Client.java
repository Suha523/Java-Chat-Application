/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Toolkit;
import static java.awt.event.KeyEvent.VK_ENTER;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author suha
 */
public class Client extends javax.swing.JFrame {

    private String clientName;
    private Socket textS = null;
    private PrintWriter pr = null;
    private BufferedReader br = null;
    private String clientMsg;
    private FileInputStream ois = null;
    private FileOutputStream oos = null;
    private OutputStream os;
    private InputStream is;
    private File f;

    /**
     * Creates new form Client
     *
     * @throws java.io.IOException
     */
    public Client() throws IOException {
        initComponents();
        clientMsg = "";
        text_ara.setEditable(false);
        textS = new Socket(InetAddress.getLocalHost(), 1900);
        pr = new PrintWriter(textS.getOutputStream());
        br = new BufferedReader(new InputStreamReader(textS.getInputStream()));
        os = textS.getOutputStream();
        is = textS.getInputStream();
        name.setText("Enter Your Name");
        text_ara.disable();
        text_txt.disable();
        send_btn.setEnabled(false);

        new Thread(() -> {
            try {
                while (true) {
                    String ss = "";
                    while ((ss = br.readLine()) != null) {
                        if (clientName != null) {
                            if (ss.contains("$")) {
                                String[] a = ss.split(" ");
                                String s = a[0];
                                c_lbl.setText(s);
                            } else {
                                if (ss.contains(" is left")) {
                                    String[] a = ss.split(" ");
                                    String s1 = a[0];
                                    String s2 = a[1];
                                    String s3 = a[2];
                                    String s4 = a[3];
                                    String s5 = a[4];
                                    c_lbl.setText(s1);
                                    text_ara.append(s2 + " is left" + "\n");
                                } else {
                                    if (ss.contains(".txt")) {
                                        String[] d;
                                        d = ss.split("%");
                                        String m = d[0];
                                        String fName = d[1];
                                        System.out.println(m);
                                        FileOutputStream fos = new FileOutputStream("F:\\" + fName);
                                        for (int j = 0; j < m.length(); j++) {
                                            int c = m.charAt(j);
                                            fos.write(c);
                                        }
                                        String[] dataList = {fName};
                                        list.setListData(dataList);
                                        list.setSelectedValue(dataList, false);
                                    } else {
                                        text_ara.append(ss + "\n");
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }).start();
    }

    public void send() {
        new Thread(() -> {
            if (text_txt.getText().trim().equals("$") || text_txt.getText().trim().contains(".txt") || text_txt.getText().trim().contains("is left")) {
                JOptionPane.showMessageDialog(null, "You can't send this message !");
            } else {
                String msg = text_txt.getText().trim();

//       if (clientName.equals("suha")){
//       String rev="";
//       for (int i=msg.length()-1;i>=0;i--){
//           rev+=msg.charAt(i);
//       }
//       System.out.println(clientName+" : "+rev);
//       pr.println(clientName+" : "+rev);
//       pr.flush();
//       text_txt.setText("");
//       }else{
                if (clientName != null) {
                    pr.println(clientName + " : " + msg);
                    pr.flush();
                    text_txt.setText("");
                    send_btn.setEnabled(false);
                }
            }
        }).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        text_txt = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        send_btn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        c_lbl = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        text_ara = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/icon4.png")));
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                jPanel1ComponentRemoved(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/photo.png"))); // NOI18N

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/file.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jButton3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton3KeyPressed(evt);
            }
        });

        text_txt.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        text_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                text_txtKeyPressed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/video.png"))); // NOI18N

        send_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/send.png"))); // NOI18N
        send_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                send_btnMouseReleased(evt);
            }
        });
        send_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                send_btnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Snap ITC", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 163, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("By Suha Shehadeh");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 163, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("active");

        c_lbl.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        c_lbl.setForeground(new java.awt.Color(0, 163, 0));

        name.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        name.setForeground(new java.awt.Color(204, 204, 204));
        name.setText("Enter Your Name");
        name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nameFocusLost(evt);
            }
        });
        name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nameMouseClicked(evt);
            }
        });
        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nameKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Snap ITC", 0, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 163, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Chat Application ");

        text_ara.setColumns(20);
        text_ara.setFont(new java.awt.Font("Times New Roman", 2, 24)); // NOI18N
        text_ara.setRows(5);
        text_ara.setEnabled(false);
        jScrollPane1.setViewportView(text_ara);

        list.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jScrollPane2.setViewportView(list);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(c_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(294, 294, 294)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(text_txt))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(send_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(5, 5, 5)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(c_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(name)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(text_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(send_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void send_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_send_btnActionPerformed
        send();
        text_txt.grabFocus();
    }//GEN-LAST:event_send_btnActionPerformed

    private void nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameMouseClicked

        name.setText("");
        name.setForeground(Color.black);
    }//GEN-LAST:event_nameMouseClicked

    private void nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameKeyPressed
        if (evt.getKeyCode() == VK_ENTER && name.getForeground().equals(Color.black)) {
            clientMsg = "";
            clientName = name.getText();
            this.setTitle(clientName);

            name.setText("");
            name.setEnabled(false);
            text_ara.setEnabled(true);
            text_txt.setEnabled(true);

            new Thread(() -> {
                if (clientName != null) {
                    pr.println("$");
                    pr.flush();
                }
            }).start();
        }
    }//GEN-LAST:event_nameKeyPressed

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed

    }//GEN-LAST:event_nameActionPerformed

    private void nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameFocusLost

    }//GEN-LAST:event_nameFocusLost

    private void send_btnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_send_btnMouseReleased

    }//GEN-LAST:event_send_btnMouseReleased

    private void jPanel1ComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jPanel1ComponentRemoved

    }//GEN-LAST:event_jPanel1ComponentRemoved

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        new Thread(() -> {
            if (clientName != null) {
                String msg = "is left";
                pr.println(clientName + "  " + msg);
                pr.flush();
                text_txt.setText("");
            }
        }).start();

    }//GEN-LAST:event_formWindowClosing

    private void text_txtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_text_txtKeyPressed
        new Thread(() -> {
            String msg = text_txt.getText().trim();

//       if (clientName.equals("suha")){
//       String rev="";
//       for (int i=msg.length()-1;i>=0;i--){
//           rev+=msg.charAt(i);
//       }
//       System.out.println(clientName+" : "+rev);
//       pr.println(clientName+" : "+rev);
//       pr.flush();
//       text_txt.setText("");
//       }else{
            if (text_txt.getText().trim().isEmpty()) {
                send_btn.setEnabled(false);
            } else {
                send_btn.setEnabled(true);
                if (evt.getKeyCode() == VK_ENTER) {
                    pr.println(clientName + " : " + msg);
                    pr.flush();
                    text_txt.setText("");
                    send_btn.setEnabled(false);
                }
            }
        }).start();
    }//GEN-LAST:event_text_txtKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened


    }//GEN-LAST:event_formWindowOpened

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showDialog(jButton3, "choose file");
        f = new File(chooser.getSelectedFile().toString());
        new Thread(() -> {
            try {
                ois = new FileInputStream(f);
                int data = 0;
                String str = "";
                while ((data = ois.read()) != -1) {
                    str += (char) data;
                }
                pr.println(str + "%" + f.getName());
                pr.flush();
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }).start();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed

    }//GEN-LAST:event_jButton3KeyPressed

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String args[]) throws IOException {

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new Client().setVisible(true);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel c_lbl;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> list;
    private javax.swing.JTextField name;
    private javax.swing.JButton send_btn;
    private javax.swing.JTextArea text_ara;
    private javax.swing.JTextField text_txt;
    // End of variables declaration//GEN-END:variables
}
