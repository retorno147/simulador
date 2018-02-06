
package proyectocalidad;

import java.applet.AudioClip;
import java.awt.geom.Rectangle2D;



public class Pelota {

    private static final int TAMX = 15;
    private static final int TAMY = 15;
    private double x = 0;
    private static double y = 0;
    private double dx = 1;
    private double dy = 1;
   
    private Integer puntuacion1 = 0, puntuacion2 = 0;
    public static boolean finJuego = false;
    
   

    
    //retorna la pelota (Rectangle2D implementa la interface Shape)
    public Rectangle2D getForma() {
        return new Rectangle2D.Double(x, y, TAMX, TAMY);
    }

    public void moverPelota(Rectangle2D limites, boolean colisionR1, boolean colisionR2) {
        x += dx;
        y += dy;

        if (colisionR1) {
            dx = -dx;
            x = 20;
            
        }
        if (colisionR2) {
            dx = -dx;
            x = 755;
           
        }

        if (x < limites.getMinX()) {
            puntuacion2++; //el puntaje del jugador2 aumenta en uno
           
            x = limites.getCenterX();
            y = limites.getCenterY();
            dx = -dx;
           
        }

        if (x + TAMX >= limites.getMaxX()) {
            puntuacion1++; //el puntaje del jugador1 aumenta en uno
            
            x = limites.getCenterX();
            y = limites.getCenterY();
            dx = -dx;
            
        }

        if (y < limites.getMinY()) {

            y = limites.getMinY();

            dy = -dy;
            
        }

        if (y + TAMY >= limites.getMaxY()) {

            y = limites.getMaxY() - TAMY;

            dy = -dy;
            
        }

    }

    public int getPuntuacion1() {
        return puntuacion1;
    }

    public int getPuntuacion2() {
        return puntuacion2;
    }

    
    
}
