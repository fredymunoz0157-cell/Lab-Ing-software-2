/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Acer3
 */
public final class clsConnectionSQL {
    
    public static Connection opGetConnection() throws SQLException {
        try {
            // Carga explícita de la clase del driver de SQLite
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC de SQLite no encontrado en el classpath.");
        }
        return DriverManager.getConnection("jdbc:sqlite:data_base.db");
    }
    
    public static boolean opExecuteDDL(String prmSql) throws SQLException {
        try (Connection varCon = opGetConnection();
            Statement stmt = varCon.createStatement()) {
            
            stmt.execute(prmSql);
            return true;
        } catch (SQLException e) {
            System.err.println("Error al ejecutar DDL: " + e.getMessage());
            return false;
        }finally{
            opCloseConnection(opGetConnection());
        }
    }
    
    public static void opCloseConnection(Connection prmCon) {
        try {
            if (prmCon != null && !prmCon.isClosed()) {
                prmCon.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
    
    
}
