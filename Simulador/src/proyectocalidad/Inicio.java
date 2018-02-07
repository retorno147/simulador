package proyectocalidad;


import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import proyectocalidad.Jugar;
import proyectocalidad.Pacman;
import proyectocalidad.Pacman;
import proyectocalidad.Tetris;
import proyectocalidad.Tetris;
import proyectocalidad.*;



public class Inicio extends javax.swing.JFrame {

  static Formulario f;
  String nombreuser;
    public Inicio() throws SQLException {
        initComponents();
        
    }
    public Inicio(Formulario f1) throws SQLException {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("../FOTOS/logo3.png")).getImage());
        if(!"".equals(f1.nombreUsuario())){
        mostrarUsuario.setText(f1.nombreUsuario());
        nombreuser=f1.nombreUsuario();
        }
        else{
        mostrarUsuario.setText(f1.nombreNuevo());
        nombreuser=f1.nombreNuevo();
        }
          
       f=f1;
    }
    

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        bTetris = new javax.swing.JButton();
        bPuigman = new javax.swing.JButton();
        bencuentra = new javax.swing.JButton();
        bPong = new javax.swing.JButton();
        MostrarRanking = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        mostrarUsuario = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setResizable(false);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bTetris.setBackground(new java.awt.Color(255, 0, 0));
        bTetris.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bTetris.setForeground(new java.awt.Color(255, 255, 255));
        bTetris.setText("TETRIS");
        bTetris.setDebugGraphicsOptions(javax.swing.DebugGraphics.LOG_OPTION);
        bTetris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTetrisActionPerformed(evt);
            }
        });
        jPanel2.add(bTetris, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 250, 32));

        bPuigman.setBackground(new java.awt.Color(51, 51, 255));
        bPuigman.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bPuigman.setForeground(new java.awt.Color(255, 255, 255));
        bPuigman.setText("PUIGMAN");
        bPuigman.setMaximumSize(new java.awt.Dimension(67, 23));
        bPuigman.setMinimumSize(new java.awt.Dimension(67, 23));
        bPuigman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPuigmanActionPerformed(evt);
            }
        });
        jPanel2.add(bPuigman, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 250, 32));

        bencuentra.setBackground(new java.awt.Color(0, 255, 51));
        bencuentra.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bencuentra.setForeground(new java.awt.Color(255, 255, 255));
        bencuentra.setText("ENCUENTRA EL PAR");
        bencuentra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bencuentraActionPerformed(evt);
            }
        });
        jPanel2.add(bencuentra, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 250, 32));

        bPong.setBackground(new java.awt.Color(204, 0, 204));
        bPong.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bPong.setForeground(new java.awt.Color(255, 255, 255));
        bPong.setText("PONG");
        bPong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPongActionPerformed(evt);
            }
        });
        jPanel2.add(bPong, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 250, 32));

        MostrarRanking.setBackground(new java.awt.Color(0, 102, 102));
        MostrarRanking.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        MostrarRanking.setForeground(new java.awt.Color(255, 255, 255));
        MostrarRanking.setText("Ranking");
        MostrarRanking.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MostrarRanking.setDebugGraphicsOptions(javax.swing.DebugGraphics.LOG_OPTION);
        MostrarRanking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarRankingActionPerformed(evt);
            }
        });
        jPanel2.add(MostrarRanking, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 130, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("User:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 50, 30));

        mostrarUsuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        mostrarUsuario.setForeground(new java.awt.Color(153, 153, 153));
        jPanel2.add(mostrarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 110, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo43.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 440));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 439, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bTetrisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTetrisActionPerformed
       Tetris game = new Tetris(nombreuser);
        game.setLocationRelativeTo(null);
       
        game.setVisible(true);  // TODO add your handling code here:
    }//GEN-LAST:event_bTetrisActionPerformed

    private void bPuigmanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPuigmanActionPerformed
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Pacman ex = new Pacman(nombreuser);
                ex.setVisible(true);
            }
        });
    }//GEN-LAST:event_bPuigmanActionPerformed

    private void bencuentraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bencuentraActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                try {
                    
                    new Jugar(nombreuser).setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Jugar.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
    }//GEN-LAST:event_bencuentraActionPerformed

    private void bPongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPongActionPerformed
        Ventana ventana = new Ventana(nombreuser);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//GEN-LAST:event_bPongActionPerformed

    private void MostrarRankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarRankingActionPerformed
        // TODO add your handling code here:

                 Ranking r1=new Ranking();
                 r1.setVisible(true);
                
    
    }//GEN-LAST:event_MostrarRankingActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Inicio().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton MostrarRanking;
    private javax.swing.JButton bPong;
    private javax.swing.JButton bPuigman;
    private javax.swing.JButton bTetris;
    private javax.swing.JButton bencuentra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel mostrarUsuario;
    // End of variables declaration//GEN-END:variables
}
