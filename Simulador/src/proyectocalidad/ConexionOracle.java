/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocalidad;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionOracle {

    private Connection conexion;
     
    public void Conexion(){
         

            try
                {
                     conexion=DriverManager.getConnection("jdbc:derby://localhost:1527/SIMULADOR","juego","juego");
                    
                }
           catch (SQLException se)
           {
               System.out.println("No se ha conectado " + se.getMessage());   
           }

              
    }
     
    public Connection getConexion(){
         
        return conexion;
    }
     
    public void inicio_transaccion(){
         
        try {
            conexion.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void fin_transaccion_commit(){
         
        try {
            conexion.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void fin_transaccion_rollback(){
         
        try {
            conexion.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void Desconexion(){
         
        try {
            conexion.close();
            System.out.println("Se ha desconectado de la base de datos\n Gracias por su visita :)");
        } catch (SQLException ex) {
             
            System.out.println("No se ha podido desconectar " + ex.getMessage());
 
        }
         
    }
 
    CallableStatement prepareCall(String sql) {
 
        throw new UnsupportedOperationException("Not supported yet.");
    }
 
    void setAutoCommit(boolean b) {
 
        throw new UnsupportedOperationException("Not supported yet.");
    }
}