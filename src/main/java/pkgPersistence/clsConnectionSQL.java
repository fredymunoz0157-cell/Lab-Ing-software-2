package pkgPersistence;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Punto único de acceso a la base de datos SQLite.
 * La base vive en data/data_base.db (relativa a la carpeta del proyecto).
 *
 * @author Acer3
 */
public final class clsConnectionSQL {

    public static final String DB_FOLDER = "data";
    public static final String DB_FILE = DB_FOLDER + File.separator + "data_base.db";

    private clsConnectionSQL() {
    }

    public static Connection opGetConnection() throws SQLException {
        try {
            // Carga explícita de la clase del driver de SQLite
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC de SQLite no encontrado en el classpath.");
        }
        // SQLite crea el archivo, pero no la carpeta
        new File(DB_FOLDER).mkdirs();
        return DriverManager.getConnection("jdbc:sqlite:" + DB_FILE);
    }

    public static boolean opExecuteDDL(String prmSql) {
        // try-with-resources ya cierra la conexión y el statement
        try (Connection varCon = opGetConnection(); Statement varStmt = varCon.createStatement()) {
            varStmt.execute(prmSql);
            return true;
        } catch (SQLException e) {
            System.err.println("Error al ejecutar DDL: " + e.getMessage());
            return false;
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
