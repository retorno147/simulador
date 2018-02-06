package proyectocalidad;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Pacman extends JFrame {

    public Pacman() {
        
        initUI();
    }
    
    private void initUI() {
        this.setResizable(false);
        add(new tableroPuigman());
        setTitle("PUIG-MAN");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420, 460);
        setLocationRelativeTo(null);
        setVisible(true);        
    }
//
//    public static void main(String[] args) {
//
//        EventQueue.invokeLater(new Runnable() {
//
//            @Override
//            public void run() {
//                Pacman ex = new Pacman();
//                ex.setVisible(true);
//            }
//        });
//    }
}