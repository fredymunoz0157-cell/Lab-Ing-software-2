package pkgPersistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Persistencia de roles. Solo habla con la base de datos.
 *
 * @author Acer3
 */
public class clsRoleDao {

    public static Boolean opSaveRole(String prmOUID, String prmName, String prmDescription) {
        String sql = "INSERT INTO tbl_roles (id_role, role_name, role_description) VALUES (?, ?, ?);";
        try (Connection varCon = clsConnectionSQL.opGetConnection();
                PreparedStatement pstmt = varCon.prepareStatement(sql)) {
            pstmt.setString(1, prmOUID);
            pstmt.setString(2, prmName);
            pstmt.setString(3, prmDescription);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al guardar el rol en BD: " + e.getMessage());
            return false;
        }
    }
}
