/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import pkgDomain.clsController;
import pkgDomain.clsRole;

/**
 *
 * @author Acer3
 */
public class clsLoadDao {

    public static void opLoadDatabaseToController() {
        Connection conn = null;

        try {
            conn = clsConnectionSQL.opGetConnection();

            if (conn == null) {
                System.err.println(">>> [ERROR] No se pudo establecer la conexión. Abortando carga.");
                return;
            }

            // Obtener la instancia Singleton de tu controlador
            clsController controller = clsController.opGetInstance();

            // Limpiar registros existentes antes de cargar los datos de la BD
            controller.opGetMyUsers().clear();
            controller.opGetMyRoles().clear();

            // 1. CARGAR TODOS LOS ROLES PRIMERO
            String sqlRoles = "SELECT id_role, role_name, role_description FROM tbl_roles;";
            try (PreparedStatement psRoles = conn.prepareStatement(sqlRoles); ResultSet rsRoles = psRoles.executeQuery()) {

                while (rsRoles.next()) {
                    String idRole = rsRoles.getString("id_role");
                    String roleName = rsRoles.getString("role_name");
                    String roleDesc = rsRoles.getString("role_description");

                    // Registra el rol en el controlador mediante opRegisterRole
                    controller.opRegisterRole(idRole, roleName, roleDesc);
                }
            }

            // 2. CARGAR TODOS LOS USUARIOS Y VINCULARLOS CON SU ROL
            // CORREGIDO: Se cambia id_rol por id_role en el SELECT
            String sqlUsers = "SELECT id_user, user_name, user_description, nickname, password, asset, id_role "
                    + "FROM tbl_users;";
            try (PreparedStatement psUsers = conn.prepareStatement(sqlUsers); ResultSet rsUsers = psUsers.executeQuery()) {

                while (rsUsers.next()) {
                    String idUser = rsUsers.getString("id_user");
                    String name = rsUsers.getString("user_name");
                    String desc = rsUsers.getString("user_description");
                    String nickname = rsUsers.getString("nickname");
                    String password = rsUsers.getString("password");
                    Boolean asset = rsUsers.getBoolean("asset");

                    // CORREGIDO: Se lee id_role en lugar de id_rol
                    String idRole = rsUsers.getString("id_role");

                    // Busca el rol ya instanciado en el controlador mediante opGetRole
                    clsRole roleObj = null;
                    if (idRole != null) {
                        roleObj = controller.opGetRole(idRole);
                    }

                    // Registra el usuario en el controlador mediante opRegisterUser
                    controller.opRegisterUser(idUser, name, desc, nickname, roleObj, asset, password);
                }
            }

            System.out.println(">>> [BD -> Controller] Datos cargados con éxito.");

        } catch (Exception e) {
            System.err.println(">>> Error crítico durante la carga de base de datos a clsController:");
            e.printStackTrace();
        } finally {
            System.out.println(">>> Cerrando conexión...");
            clsConnectionSQL.opCloseConnection(conn);
        }
    }

}
