

package control;

import java.util.logging.Level;
import java.util.logging.Logger;
import proyectocalidad.EventoTeclado;
import proyectocalidad.Tablero;
import proyectocalidad.Pelota;
import proyectocalidad.Ventana;



public class EngineGraphics extends Thread {

    private final Tablero canvas;
    private final Ventana v1;
//    public boolean final=false;

    public EngineGraphics(Ventana v1,Tablero canvas) {
        this.canvas = canvas;
        this.v1=v1;
    }

    @Override
    public void run() {
        while (!Pelota.finJuego) {
            canvas.repaint();
            try {
                //Paint Velocity 
                Thread.sleep(6);
            } catch (Exception ex) {
                System.out.println("error in graphics engine: " + ex.getMessage());
            }
        }
        while(Pelota.finJuego)
        {  canvas.repaint();
            try {
                Thread.sleep(6);
            } catch (InterruptedException ex) {
                Logger.getLogger(EngineGraphics.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(EventoTeclado.space)
            {
                Pelota.finJuego=false;
                
                Ventana v1=new Ventana("Nombre");
                v1.setVisible(true);
                this.v1.setVisible(false);
            }
    }
}
}
