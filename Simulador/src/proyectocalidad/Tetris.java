/*
 * minuvahard10.com Video Game Tetris
 */
package proyectocalidad;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class Tetris extends JFrame {

    JLabel panelInferior;
    JLabel panelSuperior;
    JLabel panelCentral;
 

    public Tetris() {
        
        
        Border border = BorderFactory.createLineBorder(Color.gray, 2);
        panelSuperior = new JLabel("[Enter] NUEVO JUEGO - [Q] SALIR");
        panelInferior = new JLabel("NUMERO DE LINEAS ELIMINADAS: 0 ");
        panelCentral = new JLabel("");
        panelSuperior.setBorder(border);
        panelInferior.setBorder(border);
        add(panelCentral, BorderLayout.AFTER_LINE_ENDS);
        add(panelSuperior, BorderLayout.NORTH);
        add(panelInferior, BorderLayout.SOUTH);
        
        
        
        TableroTetris t = new TableroTetris(this);
        
        
        
        t.setBackground(Color.white);
        t.setBorder(border);
        add(t);
        t.iniciar();
        setSize(400, 700);
        setTitle("Tetris");
        setIconImage(new ImageIcon(getClass().getResource("../FOTOS/te.jpg")).getImage());
        
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   public JLabel getPanelInferior() {
       return panelInferior;
   }
    public JLabel getPanelCentral() {
       return panelCentral;
   }

//    public static void main(String[] args) {
//
//        Tetris game = new Tetris();
//        game.setLocationRelativeTo(null);
//       
//        game.setVisible(true);
//
//    } 
}    

