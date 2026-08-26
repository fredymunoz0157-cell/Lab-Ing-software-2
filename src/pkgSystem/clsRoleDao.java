/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import pkgDomain.clsController;

/**
 *
 * @author Acer3
 */
public class clsRoleDao {

    public static Boolean opSaveRole(String prmOUID, String prmName, String prmDescription) {
        String sql = "INSERT INTO tbl_roles (id_role, role_name, role_description) VALUES (?, ?, ?);";
        Connection con = null;

        try {
            con = clsConnectionSQL.opGetConnection();
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, prmOUID);
                pstmt.setString(2, prmName);
                pstmt.setString(3, prmDescription);

                int affectedRows = pstmt.executeUpdate();

                // Si se guardó en la BD, lo registramos en el Controlador en memoria
                if (affectedRows > 0) {
                    return clsController.opGetInstance().opRegisterRole(prmOUID, prmName, prmDescription);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar el rol en BD: " + e.getMessage());
        } finally {
            clsConnectionSQL.opCloseConnection(con);
        }
        return false;
    }
}
