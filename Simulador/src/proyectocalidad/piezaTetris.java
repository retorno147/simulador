
package proyectocalidad;
import java.util.Random;
public class piezaTetris {

    enum PTetris { Noforma, ZShape, SShape, LineShape, 
               TShape, FormaCuadrada, FormaL, MirroredLShape };

    private PTetris tipoPieza;
    private int coordenadas[][];
    private int[][][] coordenadasTabla;


    public piezaTetris() {

        coordenadas = new int[4][2];
        setShape(PTetris.Noforma);

    }

    public void setShape(PTetris shape) {

         coordenadasTabla = new int[][][] {
            { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },
            { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } },
            { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } },
            { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } },
            { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } },
            { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } },
            { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } },
            { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }
        };

        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 2; ++j) {
                coordenadas[i][j] = coordenadasTabla[shape.ordinal()][i][j];
            }
        }
        tipoPieza = shape;

    }

    private void setX(int index, int x) { coordenadas[index][0] = x; }
    private void setY(int index, int y) { coordenadas[index][1] = y; }
    public int x(int index) { return coordenadas[index][0]; }
    public int y(int index) { return coordenadas[index][1]; }
    public PTetris getShape()  { return tipoPieza; }

    public void setRandomPieza()
    {
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        PTetris[] valor = PTetris.values(); 
        setShape(valor[x]);
    }

    public int minX()
    {
      int m = coordenadas[0][0];
      for (int i=0; i < 4; i++) {
          m = Math.min(m, coordenadas[i][0]);
      }
      return m;
    }


    public int minY() 
    {
      int m = coordenadas[0][1];
      for (int i=0; i < 4; i++) {
          m = Math.min(m, coordenadas[i][1]);
      }
      return m;
    }

    public piezaTetris rotarIzquierda() 
    {
        if (tipoPieza == PTetris.FormaCuadrada)
            return this;

        piezaTetris result = new piezaTetris();
        result.tipoPieza = tipoPieza;

        for (int i = 0; i < 4; ++i) {
            result.setX(i, y(i));
            result.setY(i, -x(i));
        }
        return result;
    }

    public piezaTetris rotarDerecha()
    {
        if (tipoPieza == PTetris.FormaCuadrada)
            return this;

        piezaTetris result = new piezaTetris();
        result.tipoPieza = tipoPieza;

        for (int i = 0; i < 4; ++i) {
            result.setX(i, -y(i));
            result.setY(i, x(i));
        }
        return result;
    }
}    


