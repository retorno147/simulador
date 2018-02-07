
package proyectocalidad;

import java.awt.event.*;


public class EventoTeclado extends KeyAdapter {

    public static boolean w, s, up, down,space, escape,m;

    @Override
    public void keyPressed(KeyEvent e) {

        int id = e.getKeyCode();
        
        if(id==KeyEvent.VK_SPACE)
        {
            space=true;
                        
        }
        
        
        if(id==KeyEvent.VK_ESCAPE)
        {
            escape=true;
                        
        }
        //Jugador 1
        if (id == KeyEvent.VK_W) {
            w = true; // sube

        }
        if (id == KeyEvent.VK_S) {
            s = true; // baja

        }
        
        //Jugador 2
        if (id == KeyEvent.VK_UP) {
            up = true;
        }
        if (id == KeyEvent.VK_DOWN) {
            down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int id = e.getKeyCode();
        if (id == KeyEvent.VK_W) {
            w = false;
        }
        if (id == KeyEvent.VK_S) {
            s = false;
        }
        if (id == KeyEvent.VK_UP) {
            up = false;
        }
        if (id == KeyEvent.VK_DOWN) {
            down = false;
        }
        
      
    }

}
