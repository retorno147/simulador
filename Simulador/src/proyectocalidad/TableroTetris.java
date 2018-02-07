
package proyectocalidad;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import proyectocalidad.piezaTetris.PTetris;

public class TableroTetris extends JPanel implements ActionListener {

////TAMAÑO DEL TABLERO//////
    final int tWidth = 10;
    final int tHeight = 22;
   
    Timer tiempo;
    boolean isFallingFinished = false;
    boolean comienza = false;
    boolean isPaused = false;
    int numLinesRemoved;
    int curX = 0;
    int curY = 0;
int record=0;
    JLabel panelInferior;
    JLabel panelCentral;
    JLabel panelCentral1;
    piezaTetris curPieza;
    PTetris[] tablero;
    //int record1= record;
    
    String name;

    public TableroTetris(Tetris parent,String nombre) {
        
       setFocusable(true);
       curPieza = new piezaTetris();
       tiempo = new Timer(400, this);
       tiempo.start(); 
       name=nombre;
       panelInferior = parent.getPanelInferior();
       panelCentral = parent.getPanelCentral();
       tablero = new PTetris[tWidth * tHeight];
       addKeyListener(new TAdapter(parent));
       clearBoard();  
       
        record = record();
    }

   

    

    public void actionPerformed(ActionEvent e) {
        if (isFallingFinished) {
            isFallingFinished = false;
            newPiece();
        } else {
            oneLineDown();
        }
    }


    int squareWidth() { return (int) getSize().getWidth() / tWidth; }
    int squareHeight() { return (int) getSize().getHeight() / tHeight; }
    PTetris shapeAt(int x, int y) { return tablero[(y * tWidth) + x]; }


    public void iniciar()
    {
        if (isPaused)
            return;
        
        
        comienza = true;
        isFallingFinished = false;
        
        numLinesRemoved = 0;
        clearBoard();

        newPiece();
        tiempo.start();
    }

    private void pause()
    {
        if (!comienza)
            return;

        isPaused = !isPaused;
        if (isPaused) {
            tiempo.stop();
            panelInferior.setText("PAUSA");
        } else {
            tiempo.start();
            
            panelInferior.setText("NUMERO DE LINEAS ELIMINADAS: " + String.valueOf( numLinesRemoved)+"          RECORD: "+record);
            
            
        }
        repaint();
    }

    public void paint(Graphics g)
    { 
        super.paint(g);

        Dimension size = getSize();
        int boardTop = (int) size.getHeight() - tHeight * squareHeight();


        for (int i = 0; i < tHeight; ++i) {
            for (int j = 0; j < tWidth; ++j) {
                PTetris shape = shapeAt(j, tHeight - i - 1);
                if (shape != PTetris.Noforma)
                    drawSquare(g, 0 + j * squareWidth(),
                               boardTop + i * squareHeight(), shape);
            }
        }

        if (curPieza.getShape() != PTetris.Noforma) {
            for (int i = 0; i < 4; ++i) {
                int x = curX + curPieza.x(i);
                int y = curY - curPieza.y(i);
                drawSquare(g, 0 + x * squareWidth(),
                           boardTop + (tHeight - y - 1) * squareHeight(),
                           curPieza.getShape());
            }
        }
    }

    private void dropDown()
    {
        int newY = curY;
        while (newY > 0) {
            if (!tryMove(curPieza, curX, newY - 1))
                break;
            --newY;
        }
        pieceDropped();
    }

    private void oneLineDown()
    {
        if (!tryMove(curPieza, curX, curY - 1))
            pieceDropped();
    }


    private void clearBoard()
    {
        for (int i = 0; i < tHeight * tWidth; ++i){
            tablero[i] = PTetris.Noforma;}
        panelCentral.setText("");
        
        panelInferior.setText("NUMERO DE LINEAS ELIMINADAS: " + String.valueOf(numLinesRemoved)+ "          RECORD: " + String.valueOf(record));
    }

    private void pieceDropped()
    {
        for (int i = 0; i < 4; ++i) {
            int x = curX + curPieza.x(i);
            int y = curY - curPieza.y(i);
            tablero[(y * tWidth) + x] = curPieza.getShape();
        }

        borrarLineaCompleta();

        if (!isFallingFinished)
            newPiece();
    }

    private void newPiece()
    {
       curPieza.setRandomPieza();
        curX = tWidth / 2 + 1;
        curY = tHeight - 1 + curPieza.minY();
        

        if (!tryMove(curPieza, curX, curY)) {
            curPieza.setShape(PTetris.Noforma);
            tiempo.stop();
            comienza = false;
            
            if(record< numLinesRemoved){
                record= numLinesRemoved;
                Font font= new Font("SansSerif", Font.ITALIC, 12);
                panelCentral.setFont(font);
                panelCentral.setText("PUNTOS: " + String.valueOf( numLinesRemoved)+ "  NUEVO RECORD: "+ String.valueOf(record));
                panelCentral.setText("NUEVO RECORD: "+ String.valueOf(record));
            }else{
                
            
            panelCentral.setText("PUNTOS: " + String.valueOf( numLinesRemoved));
            panelCentral.getAlignmentY();
            }
            guardarPuntos(numLinesRemoved);
            panelInferior.setText("                                                    GAME OVER");

            
        }
    }
    private void guardarPuntos(int puntos){
                 ConexionOracle con= new ConexionOracle();
         con.Conexion();
         Statement stm;
        try {
            stm = con.getConexion().createStatement();
            
           ResultSet rs= stm.executeQuery("SELECT PUNTOSTETRIS FROM USUARIO WHERE id='"+name+"'");
            
           if(rs.next())
           {
               int point=rs.getInt("PUNTOSTETRIS");
               
                    if(point<puntos){
                       stm.executeUpdate("UPDATE USUARIO SET PUNTOSTETRIS=" + puntos + " WHERE id='"+name+"'"); 
                    
           }
           }
           
        } catch (SQLException ex) {
            Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private int record(){
        int puntosMaximo= 0 ;
                 ConexionOracle con= new ConexionOracle();
         con.Conexion();
         Statement stm;
        
         try {
            stm = con.getConexion().createStatement();
            
           ResultSet rs= stm.executeQuery("SELECT  PUNTOSTETRIS  FROM USUARIO ORDER BY PUNTOSTETRIS DESC");
                  
           if(rs.next()){
                 puntosMaximo = rs.getInt(1);
        }              
               
               
           
        } catch (SQLException ex) {
            Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return puntosMaximo;
    }
    
    private boolean tryMove(piezaTetris newPiece, int newX, int newY) 
    {  
        for (int i = 0; i < 4; ++i) {
            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);
            if (x < 0 || x >= tWidth || y < 0 || y >= tHeight)
                return false;
            if (shapeAt(x, y) != PTetris.Noforma)
                return false;
        }

        curPieza = newPiece;
        curX = newX;
        curY = newY;
        repaint();
        return true;
    }
   
    private void borrarLineaCompleta()
    {
        int numLineaCompleta = 0;

        for (int i = tHeight - 1; i >= 0; --i) {
            boolean lineIsFull = true;

            for (int j = 0; j < tWidth; ++j) {
                if (shapeAt(j, i) == PTetris.Noforma) {
                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {
                ++numLineaCompleta;
                for (int k = i; k < tHeight - 1; ++k) {
                    for (int j = 0; j < tWidth; ++j)
                         tablero[(k * tWidth) + j] = shapeAt(j, k + 1);
                }
            }
        }

        if (numLineaCompleta > 0) {
            numLinesRemoved += numLineaCompleta;
            panelInferior.setText("NUMERO DE LINEAS ELIMINADAS: " + String.valueOf(numLinesRemoved)+ "  RECORD: " + String.valueOf(record));
            isFallingFinished = true;
            curPieza.setShape(PTetris.Noforma);
            repaint();
        }
     }

    private void drawSquare(Graphics g, int x, int y, PTetris shape)
    {
        Color colors[] = { new Color(0, 0, 0), new Color(204, 102, 102), 
            new Color(102, 204, 102), new Color(102, 102, 204), 
            new Color(204, 204, 102), new Color(204, 102, 204), 
            new Color(102, 204, 204), new Color(218, 170, 0)
        };


        Color color = colors[shape.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1, x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1, x + squareWidth() - 1, y + 1);
    }
    
    class TAdapter extends KeyAdapter {
        Tetris t;
        public TAdapter(Tetris t1)
        {
            t=t1;
        }
         public void keyPressed(KeyEvent e) {
             int keycode = e.getKeyCode();
             if (keycode==KeyEvent.VK_ENTER){
                 String[] args={"HOLA"};
                 //Tetris.main(args);
                 iniciar();
             }   
             if (keycode==KeyEvent.VK_ESCAPE){
                 
                     t.setVisible(false);
                 
             }
             if (!comienza || curPieza.getShape() == PTetris.Noforma) {  
                 return;
             }

             if (keycode == 'p' || keycode == 'P') {
                 pause();
                 return;
             }
            
             if (isPaused)
                 return;

             switch (keycode) {
             case KeyEvent.VK_LEFT:
                 tryMove(curPieza, curX - 1, curY);
                 break;
             case KeyEvent.VK_RIGHT:
                 tryMove(curPieza, curX + 1, curY);
                 break;
//             case KeyEvent.VK_DOWN:
//                 tryMove(curPiece.rotateRight(), curX, curY);
                 
//                 break;
             case KeyEvent.VK_UP:
                 tryMove(curPieza.rotarIzquierda(), curX, curY);
                 
                 break;
             case KeyEvent.VK_DOWN:
                 dropDown();
                 break;
             case 'd':
                 oneLineDown();
                 break;
             case 'D':
                 oneLineDown();
                 break;
             
             }

         }
     }    
}
