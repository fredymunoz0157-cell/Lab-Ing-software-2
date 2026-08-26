/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import pkgDomain.clsController;
import pkgDomain.clsRole;

/**
 *
 * @author Acer3
 */
public class clsUserDao {

    public static Boolean opSaveUser(String prmOUID, String prmName, String prmDescription,
            String prmNickName, clsRole prmRole, Boolean prmAsset, String prmPassword) {

        // CORREGIDO: Se cambia id_rol por id_role
        String sql = "INSERT INTO tbl_users (id_user, user_name, user_description, nickname, password, asset, id_role) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?);";
        Connection con = null;

        try {
            con = clsConnectionSQL.opGetConnection();
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {

                // Aplicar Hash irreversible con BCrypt a la contraseña antes de guardar
                String hashedPassword = org.mindrot.jbcrypt.BCrypt.hashpw(prmPassword, org.mindrot.jbcrypt.BCrypt.gensalt(12));

                pstmt.setString(1, prmOUID);
                pstmt.setString(2, prmName);
                pstmt.setString(3, prmDescription);
                pstmt.setString(4, prmNickName);
                pstmt.setString(5, hashedPassword);
                pstmt.setInt(6, (prmAsset != null && prmAsset) ? 1 : 0);

                if (prmRole != null) {
                    pstmt.setString(7, prmRole.opGetOUID());
                } else {
                    pstmt.setNull(7, java.sql.Types.VARCHAR);
                }

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    // Se registra el usuario en el controlador guardando el hash
                    clsController.opGetInstance().opRegisterUser(prmOUID, prmName, prmDescription, prmNickName, prmRole, prmAsset, hashedPassword);

                    if (prmRole != null) {
                        return clsController.opGetInstance().opRegisterUserInRole(prmOUID, prmRole.opGetOUID());
                    }
                    return true;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar el usuario en BD: " + e.getMessage());
        } finally {
            clsConnectionSQL.opCloseConnection(con);
        }
        return false;
    }
}
