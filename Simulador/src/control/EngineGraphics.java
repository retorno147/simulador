
package control;

import proyectocalidad.Tablero;
import proyectocalidad.Pelota;


public class EngineGraphics extends Thread {

    private final Tablero canvas;

    public EngineGraphics(Tablero canvas) {
        this.canvas = canvas;
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
    }
}
