
package proyectocalidad;


import control.EngineGraphics;
import proyectocalidad.EventoTeclado;
import javax.swing.*;


public class Ventana extends JFrame {

    Tablero canvas;
    Pelota p = new Pelota();

    public Ventana(String nombre) {
        setTitle("Pong"); // pong DEMO
        setSize(800, 500);
        setLocationRelativeTo(null);
        setResizable(false);
                setUndecorated(true);

        canvas = new Tablero(this,nombre);
        add(canvas);

        addKeyListener(new EventoTeclado());
        new EngineGraphics(this, canvas).start();
    }

}
