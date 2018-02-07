/*
 * minuvahard10.com Video Game Tetris
 */
package proyectocalidad;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class Tetris extends JFrame {

    JLabel panelInferior;
    JLabel panelSuperior;
    JLabel panelCentral;
 

    public Tetris(String nombre) {
        
        
        Border border = BorderFactory.createLineBorder(Color.gray, 2);
        panelSuperior = new JLabel("[Enter] NUEVO JUEGO - [ESC] SALIR");
        panelInferior = new JLabel("NUMERO DE LINEAS ELIMINADAS: 0 ");
        panelCentral = new JLabel("");
        panelSuperior.setBorder(border);
        panelInferior.setBorder(border);
        add(panelCentral, BorderLayout.AFTER_LINE_ENDS);
        add(panelSuperior, BorderLayout.NORTH);
        add(panelInferior, BorderLayout.SOUTH);
        setUndecorated(true);
        
        
        TableroTetris t = new TableroTetris(this,nombre);
        
        
        
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

}    

