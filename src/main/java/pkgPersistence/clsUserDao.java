package pkgPersistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Persistencia de usuarios. Solo habla con la base de datos: recibe la
 * contraseña ya cifrada (el cifrado lo hace clsSecurityUtils desde el
 * controlador).
 *
 * @author Acer3
 */
public class clsUserDao {

    public static Boolean opSaveUser(String prmOUID, String prmName, String prmDescription,
            String prmNickName, String prmOUIDRole, Boolean prmAsset, String prmHashedPassword) {

        String sql = "INSERT INTO tbl_users (id_user, user_name, user_description, nickname, password, asset, id_role) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?);";

        try (Connection varCon = clsConnectionSQL.opGetConnection();
                PreparedStatement pstmt = varCon.prepareStatement(sql)) {
            pstmt.setString(1, prmOUID);
            pstmt.setString(2, prmName);
            pstmt.setString(3, prmDescription);
            pstmt.setString(4, prmNickName);
            pstmt.setString(5, prmHashedPassword);
            pstmt.setInt(6, (prmAsset != null && prmAsset) ? 1 : 0);
            if (prmOUIDRole != null) {
                pstmt.setString(7, prmOUIDRole);
            } else {
                pstmt.setNull(7, Types.VARCHAR);
            }
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al guardar el usuario en BD: " + e.getMessage());
            return false;
        }
    }
}
