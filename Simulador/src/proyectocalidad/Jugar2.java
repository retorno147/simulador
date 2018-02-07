package proyectocalidad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class Jugar2 extends javax.swing.JFrame {
    
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
    String name;
    int record=0;
    
    
    public Jugar2() {
                setUndecorated(true);

        initComponents();
        aleatoriaCartas();
        //SacarRecord.setText(" "+ record );
        //name= nombre;
        record= record();
      //  SacarPuntuacion.setText(" "+ puntuacion );
       
    }
    
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
        int[] cartas= cod_juego.getCarta2();
        
           
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
        Carta13.setDisabledIcon(new ImageIcon(getClass().getResource("../FOTOS/imagen"+cartas[12]+".jpg")));
        Carta14.setDisabledIcon(new ImageIcon(getClass().getResource("../FOTOS/imagen"+cartas[13]+".jpg")));
        Carta15.setDisabledIcon(new ImageIcon(getClass().getResource("../FOTOS/imagen"+cartas[14]+".jpg")));
        Carta16.setDisabledIcon(new ImageIcon(getClass().getResource("../FOTOS/imagen"+cartas[15]+".jpg")));
        Carta17.setDisabledIcon(new ImageIcon(getClass().getResource("../FOTOS/imagen"+cartas[16]+".jpg")));
        Carta18.setDisabledIcon(new ImageIcon(getClass().getResource("../FOTOS/imagen"+cartas[17]+".jpg")));
        Carta19.setDisabledIcon(new ImageIcon(getClass().getResource("../FOTOS/imagen"+cartas[18]+".jpg")));
        Carta20.setDisabledIcon(new ImageIcon(getClass().getResource("../FOTOS/imagen"+cartas[19]+".jpg")));
        
                  
        
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
               // SacarPuntuacion.setText(" "+ puntuacion );
                
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
        Carta13.setEnabled(true);
        Carta14.setEnabled(true);
        Carta15.setEnabled(true);
        Carta16.setEnabled(true);
        Carta17.setEnabled(true);
        Carta18.setEnabled(true);
        Carta19.setEnabled(true);
        Carta20.setEnabled(true);
     
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
           !Carta10.isEnabled() && !Carta11.isEnabled() && !Carta12.isEnabled() &&
           !Carta13.isEnabled() && !Carta14.isEnabled() && !Carta15.isEnabled() && 
           !Carta16.isEnabled() && !Carta17.isEnabled() && !Carta18.isEnabled() && 
           !Carta19.isEnabled() && !Carta20.isEnabled()){
            
            guardarPuntos(puntuacion);
            record= record();
            //SacarRecord.setText(" "+ record );
            
            JOptionPane.showMessageDialog(this, "Felicidades. Su puntuacion es: " +puntuacion,"¡GANADOR!", JOptionPane.INFORMATION_MESSAGE);
        }
       // SiguienteNivel.setEnabled(true);
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
        Carta1 = new javax.swing.JButton();
        Carta2 = new javax.swing.JButton();
        Carta3 = new javax.swing.JButton();
        Carta4 = new javax.swing.JButton();
        Carta6 = new javax.swing.JButton();
        Carta7 = new javax.swing.JButton();
        Carta8 = new javax.swing.JButton();
        Carta20 = new javax.swing.JButton();
        Carta10 = new javax.swing.JButton();
        Carta11 = new javax.swing.JButton();
        Carta12 = new javax.swing.JButton();
        Carta13 = new javax.swing.JButton();
        Carta15 = new javax.swing.JButton();
        Carta16 = new javax.swing.JButton();
        Carta17 = new javax.swing.JButton();
        Carta18 = new javax.swing.JButton();
        Carta19 = new javax.swing.JButton();
        Carta14 = new javax.swing.JButton();
        Carta9 = new javax.swing.JButton();
        Carta5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        REINICIAR = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Carta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta1.setBorderPainted(false);
        Carta1.setContentAreaFilled(false);
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
        Carta2.setBorderPainted(false);
        Carta2.setContentAreaFilled(false);
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
        Carta3.setBorderPainted(false);
        Carta3.setContentAreaFilled(false);
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

        Carta4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta4.setBorderPainted(false);
        Carta4.setContentAreaFilled(false);
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

        Carta6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta6.setBorderPainted(false);
        Carta6.setContentAreaFilled(false);
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
        Carta7.setBorderPainted(false);
        Carta7.setContentAreaFilled(false);
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

        Carta8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta8.setBorderPainted(false);
        Carta8.setContentAreaFilled(false);
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

        Carta20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta20.setBorderPainted(false);
        Carta20.setContentAreaFilled(false);
        Carta20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Carta20MouseExited(evt);
            }
        });
        Carta20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Carta20ActionPerformed(evt);
            }
        });

        Carta10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta10.setBorderPainted(false);
        Carta10.setContentAreaFilled(false);
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
        Carta11.setBorderPainted(false);
        Carta11.setContentAreaFilled(false);
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

        Carta12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta12.setBorderPainted(false);
        Carta12.setContentAreaFilled(false);
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

        Carta13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta13.setBorderPainted(false);
        Carta13.setContentAreaFilled(false);
        Carta13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Carta13MouseExited(evt);
            }
        });
        Carta13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Carta13ActionPerformed(evt);
            }
        });

        Carta15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta15.setBorderPainted(false);
        Carta15.setContentAreaFilled(false);
        Carta15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Carta15MouseExited(evt);
            }
        });
        Carta15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Carta15ActionPerformed(evt);
            }
        });

        Carta16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta16.setBorderPainted(false);
        Carta16.setContentAreaFilled(false);
        Carta16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Carta16MouseExited(evt);
            }
        });
        Carta16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Carta16ActionPerformed(evt);
            }
        });

        Carta17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta17.setBorderPainted(false);
        Carta17.setContentAreaFilled(false);
        Carta17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Carta17MouseExited(evt);
            }
        });
        Carta17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Carta17ActionPerformed(evt);
            }
        });

        Carta18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta18.setBorderPainted(false);
        Carta18.setContentAreaFilled(false);
        Carta18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Carta18MouseExited(evt);
            }
        });
        Carta18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Carta18ActionPerformed(evt);
            }
        });

        Carta19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta19.setBorderPainted(false);
        Carta19.setContentAreaFilled(false);
        Carta19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Carta19MouseExited(evt);
            }
        });
        Carta19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Carta19ActionPerformed(evt);
            }
        });

        Carta14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta14.setBorderPainted(false);
        Carta14.setContentAreaFilled(false);
        Carta14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Carta14MouseExited(evt);
            }
        });
        Carta14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Carta14ActionPerformed(evt);
            }
        });

        Carta9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta9.setBorderPainted(false);
        Carta9.setContentAreaFilled(false);
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

        Carta5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTOS/fondo cartas.png"))); // NOI18N
        Carta5.setBorderPainted(false);
        Carta5.setContentAreaFilled(false);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(Carta1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Carta10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Carta6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Carta15, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Carta16, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Carta17, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Carta11, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Carta12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Carta8, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(Carta2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Carta3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(Carta7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Carta4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Carta5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Carta18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(Carta20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(Carta13, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Carta19, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Carta14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(Carta9, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Carta11)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Carta5)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Carta1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(Carta2)
                                .addComponent(Carta3, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(Carta4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Carta7)
                                    .addComponent(Carta6)
                                    .addComponent(Carta8)
                                    .addComponent(Carta20))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Carta10)
                                        .addComponent(Carta12, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(Carta13)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(Carta9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Carta14)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Carta15)
                    .addComponent(Carta16)
                    .addComponent(Carta17)
                    .addComponent(Carta18)
                    .addComponent(Carta19))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jLabel1.setText("NIVEL 2");

        REINICIAR.setText("REINICIAR");
        REINICIAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REINICIARActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(REINICIAR))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(REINICIAR))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void REINICIARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REINICIARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_REINICIARActionPerformed

    private void Carta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta1ActionPerformed
       btnEnabled(Carta1);
    }//GEN-LAST:event_Carta1ActionPerformed

    private void Carta1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta1MouseExited
       comparar();
    }//GEN-LAST:event_Carta1MouseExited

    private void Carta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta2ActionPerformed
        btnEnabled(Carta2);
    }//GEN-LAST:event_Carta2ActionPerformed

    private void Carta3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta3ActionPerformed
        btnEnabled(Carta3);
    }//GEN-LAST:event_Carta3ActionPerformed

    private void Carta4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta4ActionPerformed
     btnEnabled(Carta4);
    }//GEN-LAST:event_Carta4ActionPerformed

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

    private void Carta20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta20ActionPerformed
        btnEnabled(Carta20);
    }//GEN-LAST:event_Carta20ActionPerformed

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

    private void Carta13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta13ActionPerformed
        btnEnabled(Carta13);
    }//GEN-LAST:event_Carta13ActionPerformed

    private void Carta14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta14ActionPerformed
       btnEnabled(Carta14);
    }//GEN-LAST:event_Carta14ActionPerformed

    private void Carta15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta15ActionPerformed
        btnEnabled(Carta15);
    }//GEN-LAST:event_Carta15ActionPerformed

    private void Carta16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta16ActionPerformed
        btnEnabled(Carta16);
    }//GEN-LAST:event_Carta16ActionPerformed

    private void Carta17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta17ActionPerformed
        btnEnabled(Carta17);
    }//GEN-LAST:event_Carta17ActionPerformed

    private void Carta18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta18ActionPerformed
       btnEnabled(Carta18);
    }//GEN-LAST:event_Carta18ActionPerformed

    private void Carta19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carta19ActionPerformed
        btnEnabled(Carta19);
    }//GEN-LAST:event_Carta19ActionPerformed

    private void Carta2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta2MouseExited
      comparar();
    }//GEN-LAST:event_Carta2MouseExited

    private void Carta3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta3MouseExited
        comparar();
    }//GEN-LAST:event_Carta3MouseExited

    private void Carta4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta4MouseExited
       comparar();
    }//GEN-LAST:event_Carta4MouseExited

    private void Carta5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta5MouseExited
       comparar();
    }//GEN-LAST:event_Carta5MouseExited

    private void Carta6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta6MouseExited
        comparar();
    }//GEN-LAST:event_Carta6MouseExited

    private void Carta7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta7MouseExited
       comparar();
    }//GEN-LAST:event_Carta7MouseExited

    private void Carta8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta8MouseExited
        comparar();
    }//GEN-LAST:event_Carta8MouseExited

    private void Carta20MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta20MouseExited
       comparar();
    }//GEN-LAST:event_Carta20MouseExited

    private void Carta9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta9MouseExited
     comparar();
    }//GEN-LAST:event_Carta9MouseExited

    private void Carta10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta10MouseExited
        comparar();
    }//GEN-LAST:event_Carta10MouseExited

    private void Carta11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta11MouseExited
      comparar();
    }//GEN-LAST:event_Carta11MouseExited

    private void Carta12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta12MouseExited
        comparar();
    }//GEN-LAST:event_Carta12MouseExited

    private void Carta13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta13MouseExited
        comparar();
    }//GEN-LAST:event_Carta13MouseExited

    private void Carta14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta14MouseExited
        comparar();
    }//GEN-LAST:event_Carta14MouseExited

    private void Carta15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta15MouseExited
        comparar();
    }//GEN-LAST:event_Carta15MouseExited

    private void Carta16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta16MouseExited
        comparar();
    }//GEN-LAST:event_Carta16MouseExited

    private void Carta17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta17MouseExited
      comparar();
    }//GEN-LAST:event_Carta17MouseExited

    private void Carta18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta18MouseExited
        comparar();
    }//GEN-LAST:event_Carta18MouseExited

    private void Carta19MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Carta19MouseExited
       comparar();
    }//GEN-LAST:event_Carta19MouseExited

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
            java.util.logging.Logger.getLogger(Jugar2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Jugar2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Jugar2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Jugar2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Jugar2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Carta1;
    private javax.swing.JButton Carta10;
    private javax.swing.JButton Carta11;
    private javax.swing.JButton Carta12;
    private javax.swing.JButton Carta13;
    private javax.swing.JButton Carta14;
    private javax.swing.JButton Carta15;
    private javax.swing.JButton Carta16;
    private javax.swing.JButton Carta17;
    private javax.swing.JButton Carta18;
    private javax.swing.JButton Carta19;
    private javax.swing.JButton Carta2;
    private javax.swing.JButton Carta20;
    private javax.swing.JButton Carta3;
    private javax.swing.JButton Carta4;
    private javax.swing.JButton Carta5;
    private javax.swing.JButton Carta6;
    private javax.swing.JButton Carta7;
    private javax.swing.JButton Carta8;
    private javax.swing.JButton Carta9;
    private javax.swing.JButton REINICIAR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
