package proyectocalidad;
import java.util.Random;


public class Codigo_juego {
       
    public int[] getCarta(){
        int[]numbers= new int[12];
        int cont=0;
        
       while(cont<12){
           
           Random r= new Random();
           int n= r.nextInt(5)+1;
           int contador=0;
        
            for(int i=0; i<12 ; i++){
                if(numbers[i]==n)
                    contador++;
            }
        
         if(contador<2)
             numbers[cont]=n;
             cont++;
         
         
        }
       
            
        return numbers;
    }
    
    
    public int[] getCarta2(){
        int []numbers= new int[20];
        int cont=0;
        
        while(cont<20){
            Random r= new Random();
            int n= r.nextInt(9)+1;
            int contador=0;
            
            for(int i=0; i<20; i++){
                if(numbers[i]==n){
                    contador++;
                }
            }
            if(contador<2){
                numbers[cont]=n;
            }
            cont++;
        }
        
        return numbers;
    }
    
}



