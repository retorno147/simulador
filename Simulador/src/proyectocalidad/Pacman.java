package proyectocalidad;

import java.awt.EventQueue;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Pacman extends JFrame {
String name;
    public Pacman(String nombre) {
        name=nombre;
        initUI();
    }
    
    private void initUI() {
        this.setResizable(false);
        add(new tableroPuigman(this,name));
        setTitle("PUIG-MAN");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(420, 460);
        setLocationRelativeTo(null);
        setVisible(true);        
    }
}