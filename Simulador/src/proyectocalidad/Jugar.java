
package proyectocalidad;

import static java.lang.Thread.sleep;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class Jugar extends javax.swing.JFrame {

    private Codigo_juego cod_juego= new Codigo_juego();
    private boolean voltear = false;
    //comparar dos imagenes:
    private ImageIcon im1;
    private ImageIcon im2;
    
    //array para guardar el ultimo boton.
    private JButton[] botones = new JButton[2];
    private boolean hayCarta= false;
    
    //puntuación
    private int puntuacion=0;
    private int record=0;
    private String name;
    
    
    public Jugar(String nombre) throws InterruptedException  {
        initComponents();
      //  enseñarCarta();
     //   Thread.sleep(2000);
        record= record();
        aleatoriaCartas();
        SacarPuntuacion.setText(" "+ puntuacion );
        SiguienteNivel.setEnabled(false);
        SacarRecord.setText(" "+ record );
        name= nombre;
        record= record();
    }
    
    /*
    private void enseñarCarta() throws InterruptedException{
        Carta1.setEnabled(false);
        Carta2.setEnabled(false);
        Carta3.setEnabled(false);
        Carta4.setEnabled(false);
        Carta5.setEnabled(false);
        Carta6.setEnabled(false);
        Carta7.setEnabled(false);
        Carta8.setEnabled(false);
        Carta9.setEnabled(false);
        Carta10.setEnabled(false);
        Carta11.setEnabled(false);
        Carta12.setEnabled(false);
        
        Thread.sleep(2000);
        
        Carta1.setEnabled(true);
        Carta2.setEnabled(true);
        Carta3.setEnabled(true);
        Carta4.setEnabled(true);
        Carta5.setEnabled(true);
        Carta6.setEnabled(true);
        Carta7.setEnabled(true);
        Carta8.setEnabled(true);
        Carta9.setEnabled(true);
        Carta10.setEnabled(true);
        Carta11.setEnabled(true);
        Carta12.setEnabled(true);
        
        
    }
    */
    
     private void guardarPuntos(int puntos){
                 ConexionOracle con= new ConexionOracle();
         con.Conexion();
         Statement stm;
        try {
            stm = con.getConexion().createStatement();
            
           ResultSet rs= stm.executeQuery("SELECT PUNTOSPAR FROM USUARIO WHERE id='"+name+"'");
            
           if(rs.next())
           {
               int point=rs.getInt("PUNTOSPAR");
               
                    if(point<puntos){
                       stm.executeUpdate("UPDATE USUARIO SET PUNTOSPAR=" + puntos + " WHERE id='"+name+"'"); 
                    
           }
           }
           
        } catch (SQLException ex) {
            Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private int record(){
        int puntosMaximo= 0 ;
                 ConexionOracle con= new ConexionOracle();
         con.Conexion();
         Statement stm;
        
         try {
            stm = con.getConexion().createStatement();
            
           ResultSet rs= stm.executeQuery("SELECT  PUNTOSPAR  FROM USUARIO ORDER BY PUNTOSPAR DESC");
                  
           if(rs.next()){
                 puntosMaximo = rs.getInt(1);
        }              
               
               
           
        } catch (SQLException ex) {
            Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return puntosMaximo;
    }
    
    private void aleatoriaCartas(){
        int[] cartas= cod_juego.getCarta();
        
           
        Carta1.setDisabledIcon(new ImageIcon(getClass().getResource("../FOTOS/imagen"+cartas[0]+".jpg")));
        Carta2.setDisabledIcon(new ImageIcon(getClass().getResource("../FOTOS/imagen"+cartas[1]+".jpg")));
        Carta3.setDisabledIcon(new ImageIcon(getClass().getResource("../FOTOS/imagen"+cartas[2]+".jpg")));
        Carta4.setDisabledIcon(new ImageIcon(getClass().getResource("../FOTOS/imagen"+cartas[3]+".jpg")));
        Carta5.setDisabledIcon(new ImageIcon(getClass().getResource("../FOTOS/imagen"+cartas[4]+".jpg")));
        Carta6.setDisabledIcon(new ImageIcon(getClass().getResource("../FOTOS/imagen"+cartas[5]+".jpg")));
        Carta7.setDisabledIcon(new ImageIcon(getClass().getResource("../FOTOS/imagen"+cartas[6]+".jpg")));
        Carta8.setDisabledIcon(new ImageIcon(getClass().getResource("../FOTOS/imagen"+cartas[7]+".jpg")));
        Carta9.setDisabledIcon(new ImageIcon(getClass().getResource("../FOTOS/imagen"+cartas[8]+".jpg")));
        Carta10.setDisabledIcon(new ImageIcon(getClass().getResource("../FOTOS/imagen"+cartas[9]+".jpg")));
        Carta11.setDisabledIcon(new ImageIcon(getClass().getResource("../FOTOS/imagen"+cartas[10]+".jpg")));
        Carta12.setDisabledIcon(new ImageIcon(getClass().getResource("../FOTOS/imagen"+cartas[11]+".jpg")));
                  
        
}
    
    
    private void btnEnabled(JButton btn){
        
        if(!voltear){
            btn.setEnabled(false);
            im1= (ImageIcon)btn.getDisabledIcon();
            botones[0]=btn;
            voltear=true;
            hayCarta=false;
            
        }
        else{
            btn.setEnabled(false);
            im2= (ImageIcon)btn.getDisabledIcon();
            botones[1]=btn;            
            hayCarta= true;
            puntuacion +=2;
            
           // SacarPuntuacion.setText(" "+ puntuacion );
            ganar();
        }
    }
    
    
   // ****** COMPARAR  ***** /// 
    
    private void comparar(){
        if(hayCarta && voltear){
            if(im1.getDescription().compareTo(im2.getDescription())!=0){
                //si no es igual. Pierde 5 puntos.
                botones[0].setEnabled(true);
                botones[1].setEnabled(true);
                if(puntuacion > 1) puntuacion -=1;
                SacarPuntuacion.setText(" "+ puntuacion );
                
            }
            voltear=false;
         }
    }
    
    //*** REINICIAR JUEGO **** /// 
    
    private void reiniciar(){
        Carta1.setEnabled(true);
        Carta2.setEnabled(true);
        Carta3.setEnabled(true);
        Carta4.setEnabled(true);
        Carta5.setEnabled(true);
        Carta6.setEnabled(true);
        Carta7.setEnabled(true);
        Carta8.setEnabled(true);
        Carta9.setEnabled(true);
        Carta10.setEnabled(true);
        Carta11.setEnabled(true);
        Carta12.setEnabled(true);
     
        hayCarta=false;
        botones[0]=null;
        botones[1]=null;
        voltear=false;
        aleatoriaCartas();
        puntuacion=0;
    }
    
    
    //*** GANAR **** /// 
    
    private void ganar(){
        
        if(!Carta1.isEnabled() && !Carta2.isEnabled() && !Carta3.isEnabled() && 
           !Carta4.isEnabled() && !Carta5.isEnabled() && !Carta6.isEnabled() &&
           !Carta7.isEnabled() && !Carta8.isEnabled() && !Carta9.isEnabled() &&
           !Carta10.isEnabled() && !Carta11.isEnabled() && ! Carta12.isEnabled()){
            guardarPuntos(puntuacion);
            record= record();
            SacarRecord.setText(" "+ record );
            
            JOptionPane.showMessageDialog(this, "Felicidades. Su puntuacion es: " +puntuacion,"¡GANADOR!", JOptionPane.INFORMATION_MESSAGE);
        }
        SiguienteNivel.setEnabled(true);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Carta1 = new javax.swing.JButton();
        Carta2 = new javax.swing.JButton();
        Carta3 = new javax.swing.JButton();
        Carta5 = new javax.swing.JButton();
        Carta6 = new javax.swing.JButton();
        Carta7 = new javax.swing.JButton();
        Carta9 = new javax.swing.JButton();
        Carta10 = new javax.swing.JButton();
        Carta11 = new javax.swing.JButton();
        Carta4 = new javax.swing.JButton();
        Carta8 = new javax.swing.JButton();
        Carta12 = new javax.swing.JButton();
        Reiniciar = new javax.swing.JButton();
        Puntuacion = new javax.swing.JLabel();
        SacarPuntuacion = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        SacarRecord = new javax.swing.JLabel();
        SiguienteNivel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel1.setText("ENCUENTRA EL PAR");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Carta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta1.setBorder(null);
        Carta1.setBorderPainted(false);
        Carta1.setContentAreaFilled(false);
        Carta1.setFocusable(false);
        Carta1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo carta 2.png"))); // NOI18N
        Carta1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Carta1MouseExited(evt);
            }
        });
        Carta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Carta1ActionPerformed(evt);
            }
        });

        Carta2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta2.setBorder(null);
        Carta2.setBorderPainted(false);
        Carta2.setContentAreaFilled(false);
        Carta2.setFocusable(false);
        Carta2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo carta 2.png"))); // NOI18N
        Carta2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Carta2MouseExited(evt);
            }
        });
        Carta2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Carta2ActionPerformed(evt);
            }
        });

        Carta3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta3.setBorder(null);
        Carta3.setBorderPainted(false);
        Carta3.setContentAreaFilled(false);
        Carta3.setFocusable(false);
        Carta3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo carta 2.png"))); // NOI18N
        Carta3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Carta3MouseExited(evt);
            }
        });
        Carta3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Carta3ActionPerformed(evt);
            }
        });

        Carta5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta5.setBorder(null);
        Carta5.setBorderPainted(false);
        Carta5.setContentAreaFilled(false);
        Carta5.setFocusable(false);
        Carta5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo carta 2.png"))); // NOI18N
        Carta5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Carta5MouseExited(evt);
            }
        });
        Carta5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Carta5ActionPerformed(evt);
            }
        });

        Carta6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta6.setBorder(null);
        Carta6.setBorderPainted(false);
        Carta6.setContentAreaFilled(false);
        Carta6.setFocusable(false);
        Carta6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo carta 2.png"))); // NOI18N
        Carta6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Carta6MouseExited(evt);
            }
        });
        Carta6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Carta6ActionPerformed(evt);
            }
        });

        Carta7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta7.setBorder(null);
        Carta7.setBorderPainted(false);
        Carta7.setContentAreaFilled(false);
        Carta7.setFocusable(false);
        Carta7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo carta 2.png"))); // NOI18N
        Carta7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Carta7MouseExited(evt);
            }
        });
        Carta7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Carta7ActionPerformed(evt);
            }
        });

        Carta9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta9.setBorder(null);
        Carta9.setBorderPainted(false);
        Carta9.setContentAreaFilled(false);
        Carta9.setFocusable(false);
        Carta9.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo carta 2.png"))); // NOI18N
        Carta9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Carta9MouseExited(evt);
            }
        });
        Carta9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Carta9ActionPerformed(evt);
            }
        });

        Carta10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta10.setBorder(null);
        Carta10.setBorderPainted(false);
        Carta10.setContentAreaFilled(false);
        Carta10.setFocusable(false);
        Carta10.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo carta 2.png"))); // NOI18N
        Carta10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Carta10MouseExited(evt);
            }
        });
        Carta10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Carta10ActionPerformed(evt);
            }
        });

        Carta11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta11.setBorder(null);
        Carta11.setBorderPainted(false);
        Carta11.setContentAreaFilled(false);
        Carta11.setFocusable(false);
        Carta11.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo carta 2.png"))); // NOI18N
        Carta11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Carta11MouseExited(evt);
            }
        });
        Carta11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Carta11ActionPerformed(evt);
            }
        });

        Carta4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta4.setBorder(null);
        Carta4.setBorderPainted(false);
        Carta4.setContentAreaFilled(false);
        Carta4.setFocusable(false);
        Carta4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo carta 2.png"))); // NOI18N
        Carta4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Carta4MouseExited(evt);
            }
        });
        Carta4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Carta4ActionPerformed(evt);
            }
        });

        Carta8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta8.setBorder(null);
        Carta8.setBorderPainted(false);
        Carta8.setContentAreaFilled(false);
        Carta8.setFocusable(false);
        Carta8.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo carta 2.png"))); // NOI18N
        Carta8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Carta8MouseExited(evt);
            }
        });
        Carta8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Carta8ActionPerformed(evt);
            }
        });

        Carta12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta12.setBorder(null);
        Carta12.setBorderPainted(false);
        Carta12.setContentAreaFilled(false);
        Carta12.setFocusable(false);
        Carta12.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo carta 2.png"))); // NOI18N
        Carta12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Carta12MouseExited(evt);
            }
        });
        Carta12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Carta12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Carta5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Carta6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Carta7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Carta8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Carta9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Carta10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Carta11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Carta12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Carta1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Carta2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Carta3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Carta4)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Carta2)
                    .addComponent(Carta3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Carta4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Carta1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Carta6)
                    .addComponent(Carta5)
                    .addComponent(Carta7)
                    .addComponent(Carta8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Carta10)
                    .addComponent(Carta9)
                    .addComponent(Carta11)
                    .addComponent(Carta12))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        Reiniciar.setFont(new java.awt.Font("Sitka Small", 1, 12)); // NOI18N
        Reiniciar.setForeground(new java.awt.Color(0, 0, 102));
        Reiniciar.setText("REINICIAR");
        Reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReiniciarActionPerformed(evt);
            }
        });

        Puntuacion.setFont(new java.awt.Font("Cambria", 0, 13)); // NOI18N
        Puntuacion.setText("Puntuación:");

        SacarPuntuacion.setText(" ");

        jLabel2.setFont(new java.awt.Font("Cambria", 0, 13)); // NOI18N
        jLabel2.setText("Record:");
        jLabel2.setMaximumSize(new java.awt.Dimension(65, 16));
        jLabel2.setMinimumSize(new java.awt.Dimension(65, 16));
        jLabel2.setPreferredSize(new java.awt.Dimension(65, 16));

        SacarRecord.setMaximumSize(new java.awt.Dimension(3, 14));
        SacarRecord.setMinimumSize(new java.awt.Dimension(3, 14));
        SacarRecord.setPreferredSize(new java.awt.Dimension(3, 14));

        SiguienteNivel.setText("Siguiente Nivel");
        SiguienteNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SiguienteNivelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Puntuacion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(SacarPuntuacion, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SacarRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Reiniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SiguienteNivel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(Reiniciar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Puntuacion)
                        .addComponent(SacarPuntuacion)
                        .addComponent(SiguienteNivel)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(SacarRecord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Carta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta1ActionPerformed
        btnEnabled(Carta1);
    }//GEN-LAST:event_Carta1ActionPerformed

    private void Carta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta2ActionPerformed
        btnEnabled(Carta2);
    }//GEN-LAST:event_Carta2ActionPerformed

    private void Carta3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta3ActionPerformed
         btnEnabled(Carta3);
    }//GEN-LAST:event_Carta3ActionPerformed

   private void Carta4ActionPerfomed(java.awt.event.ActionEvent evt){
        btnEnabled(Carta4);
   }
    private void Carta5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta5ActionPerformed
         btnEnabled(Carta5);
    }//GEN-LAST:event_Carta5ActionPerformed

    private void Carta6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta6ActionPerformed
        btnEnabled(Carta6);
    }//GEN-LAST:event_Carta6ActionPerformed

    private void Carta7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta7ActionPerformed
        btnEnabled(Carta7);
    }//GEN-LAST:event_Carta7ActionPerformed

    private void Carta8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta8ActionPerformed
        btnEnabled(Carta8);
    }//GEN-LAST:event_Carta8ActionPerformed

    private void Carta9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta9ActionPerformed
         btnEnabled(Carta9);
    }//GEN-LAST:event_Carta9ActionPerformed

    private void Carta10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta10ActionPerformed
         btnEnabled(Carta10);
    }//GEN-LAST:event_Carta10ActionPerformed

    private void Carta11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta11ActionPerformed
         btnEnabled(Carta11);
    }//GEN-LAST:event_Carta11ActionPerformed

    private void Carta12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta12ActionPerformed
         btnEnabled(Carta12);
    }//GEN-LAST:event_Carta12ActionPerformed

    private void Carta1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta1MouseExited
        comparar();
    }//GEN-LAST:event_Carta1MouseExited

    private void Carta3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta3MouseExited
        comparar();
    }//GEN-LAST:event_Carta3MouseExited

    private void Carta2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta2MouseExited
        comparar();
    }//GEN-LAST:event_Carta2MouseExited

    private void Carta4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta4MouseExited
        // TODO add your handling code here:
        comparar();
    }//GEN-LAST:event_Carta4MouseExited

    private void Carta5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta5MouseExited
        // TODO add your handling code here:
        comparar();
    }//GEN-LAST:event_Carta5MouseExited

    private void Carta6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta6MouseExited
        // TODO add your handling code here:
        comparar();
    }//GEN-LAST:event_Carta6MouseExited

    private void Carta7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta7MouseExited
        // TODO add your handling code here:
        comparar();
    }//GEN-LAST:event_Carta7MouseExited

    private void Carta8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta8MouseExited
        // TODO add your handling code here:
        comparar();
    }//GEN-LAST:event_Carta8MouseExited

    private void Carta9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta9MouseExited
        // TODO add your handling code here:
        comparar();
    }//GEN-LAST:event_Carta9MouseExited

    private void Carta10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta10MouseExited
        // TODO add your handling code here:
        comparar();
    }//GEN-LAST:event_Carta10MouseExited

    private void Carta11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta11MouseExited
        // TODO add your handling code here:
        comparar();
    }//GEN-LAST:event_Carta11MouseExited

    private void Carta12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta12MouseExited
        // TODO add your handling code here:
        comparar();
    }//GEN-LAST:event_Carta12MouseExited

    private void ReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReiniciarActionPerformed
        reiniciar();
    }//GEN-LAST:event_ReiniciarActionPerformed

    private void Carta4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta4ActionPerformed
        // TODO add your handling code here:
         btnEnabled(Carta4);
    }//GEN-LAST:event_Carta4ActionPerformed

    private void SiguienteNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SiguienteNivelActionPerformed
        // TODO add your handling code here:
        
        Jugar2 j= new Jugar2();
        j.setVisible(true);
    }//GEN-LAST:event_SiguienteNivelActionPerformed

     
      
    
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Jugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Jugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Jugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Jugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                
//                try {
//                    new Jugar().setVisible(true);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(Jugar.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Carta1;
    private javax.swing.JButton Carta10;
    private javax.swing.JButton Carta11;
    private javax.swing.JButton Carta12;
    private javax.swing.JButton Carta2;
    private javax.swing.JButton Carta3;
    private javax.swing.JButton Carta4;
    private javax.swing.JButton Carta5;
    private javax.swing.JButton Carta6;
    private javax.swing.JButton Carta7;
    private javax.swing.JButton Carta8;
    private javax.swing.JButton Carta9;
    private javax.swing.JLabel Puntuacion;
    private javax.swing.JButton Reiniciar;
    private javax.swing.JLabel SacarPuntuacion;
    private javax.swing.JLabel SacarRecord;
    private javax.swing.JButton SiguienteNivel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
