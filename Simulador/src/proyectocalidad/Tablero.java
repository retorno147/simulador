package proyectocalidad;

import control.EngineGraphics;
import proyectocalidad.EventoTeclado;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tablero extends JPanel implements ActionListener {

    private boolean ingame = false;
    Raqueta r1 = new Raqueta(10, 200);
    Raqueta r2 = new Raqueta(794 - 10 - Raqueta.ANCHO, 200);
    Pelota p = new Pelota();
    Graphics2D g2;
    private Timer timer;
    Ventana v;
    String name;

    public Tablero(Ventana v1, String nombre) {
        this.name = nombre;
        this.setBackground(Color.PINK);
        EventoTeclado.space = false;
        EventoTeclado.escape = false;
       
        v = v1;

    }

    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        g2 = (Graphics2D) g;
        g2.setPaint(Color.BLACK);
        dibujarPuntaje(g2);
        
        if (EventoTeclado.space == true) {
               
            dibujar(g2);
        } else {
            
            showIntroScreen(g2);
        }
        if (EventoTeclado.escape == true) {
            if(Pelota.finJuego)
               Pelota.finJuego=false;
           v.setVisible(false);
        }
    }

    private void showIntroScreen(Graphics2D g2d) {

        g2d.setColor(new Color(0, 32, 48));
        g2d.fillRect(140, Raqueta.ANCHO*Raqueta.ALTO / 2 -110, Raqueta.ANCHO*Raqueta.ALTO - 2,150);
        g2d.setColor(Color.white);
        g2d.drawRect(140, Raqueta.ANCHO*Raqueta.ALTO / 2 - 110, Raqueta.ANCHO*Raqueta.ALTO - 2, 150);

        String s = "PONG pulsa Espacio para empezar.";
        String s1 = "Mover Jugador 1: Arriba(W), Abajo(S)";
        String s2 = "Mover Jugador 2: Arriba(up), Abajo(down)";
        
        Font small = new Font("Helvetica", Font.BOLD, 15);
        FontMetrics metr = this.getFontMetrics(small);

        g2d.setColor(Color.white);
        g2d.setFont(small);
        if(Pelota.finJuego)
        {
            if (p.getPuntuacion1() == 5) 
            s="GANÓ El JUGADOR 1";
            
            else
                s="GANÓ EL JUGADOR 2";
            s1="[Espacio] Nueva Partida";
            s2="[ESC] Salir";
            g2d.drawString(s, (Raqueta.ANCHO*Raqueta.ALTO - metr.stringWidth(s)) / 2 + 160, (Raqueta.ANCHO*Raqueta.ALTO / 2)-60);
        g2d.drawString(s1, (Raqueta.ANCHO*Raqueta.ALTO - metr.stringWidth(s)) / 2 + 130, (Raqueta.ANCHO*Raqueta.ALTO / 2)-20);
        g2d.drawString(s2, (Raqueta.ANCHO*Raqueta.ALTO - metr.stringWidth(s)) / 2 + 155, (Raqueta.ANCHO*Raqueta.ALTO / 2));

        }
        else
        {
        g2d.drawString(s, (Raqueta.ANCHO*Raqueta.ALTO - metr.stringWidth(s)) / 2 + 130, (Raqueta.ANCHO*Raqueta.ALTO / 2)-60);
        g2d.drawString(s1, (Raqueta.ANCHO*Raqueta.ALTO - metr.stringWidth(s)) / 2 + 130, (Raqueta.ANCHO*Raqueta.ALTO / 2)-20);
        g2d.drawString(s2, (Raqueta.ANCHO*Raqueta.ALTO - metr.stringWidth(s)) / 2 + 130, (Raqueta.ANCHO*Raqueta.ALTO / 2));
        }
    }

    private void dibujar(Graphics2D g) {

        Line2D.Double linea = new Line2D.Double(getBounds().getCenterX(), 0, getBounds().getCenterX(), getBounds().getMaxY());

        g.draw(linea);

        g.fill(r1.getRaqueta());
        update();

        g.fill(r2.getRaqueta());
        update();

        g.fill(p.getForma());
        update();
    }

//actualiza el estado (posicion) de las raquetas y pelota
    private void update() {

        p.moverPelota(getBounds(), colision(r1.getRaqueta()), colision(r2.getRaqueta()));

        r1.moverR1(getBounds());
        r2.moverR2(getBounds());
    }

    //detecta si existe una colision entre la raqueta y la pelota
    private boolean colision(Rectangle2D r) {
        return p.getForma().intersects(r);
    }

    private void dibujarPuntaje(Graphics2D g) {
        Graphics2D g1 = g, g2 = g;
        Font score = new Font("Arial", Font.BOLD, 30);
        g.setFont(score);

        g1.drawString(Integer.toString(p.getPuntuacion1()), (float) getBounds().getCenterX() - 50, 30);
        g2.drawString(Integer.toString(p.getPuntuacion2()), (float) getBounds().getCenterX() + 25, 30);
        if (p.getPuntuacion1() == 5) {
            EventoTeclado.space=false;
            Pelota.finJuego = true;
        }
        if (p.getPuntuacion2() == 5) {
            EventoTeclado.space=false;
            Pelota.finJuego = true;
        }
        

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
