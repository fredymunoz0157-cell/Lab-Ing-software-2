package pkgPersistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pkgDomain.pkgEntities.clsQuestion;
import pkgDomain.pkgEntities.clsRole;
import pkgDomain.pkgEntities.clsUser;

/**
 * Lee la base de datos y devuelve entidades del dominio.
 * Ya no escribe en el repositorio en memoria: eso lo decide clsController.
 *
 * @author Acer3
 */
public class clsLoadDao {

    public static List<clsRole> opLoadRoles() throws SQLException {
        List<clsRole> varRoles = new ArrayList<>();
        String sql = "SELECT id_role, role_name, role_description FROM tbl_roles;";
        try (Connection varCon = clsConnectionSQL.opGetConnection();
                PreparedStatement ps = varCon.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                varRoles.add(new clsRole(
                        rs.getString("id_role"),
                        rs.getString("role_name"),
                        rs.getString("role_description")));
            }
        }
        return varRoles;
    }

    /**
     * @param prmRoles roles ya cargados, para enlazar cada usuario con su rol.
     */
    public static List<clsUser> opLoadUsers(List<clsRole> prmRoles) throws SQLException {
        List<clsUser> varUsers = new ArrayList<>();
        String sql = "SELECT id_user, user_name, user_description, nickname, password, asset, id_role FROM tbl_users;";
        try (Connection varCon = clsConnectionSQL.opGetConnection();
                PreparedStatement ps = varCon.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                varUsers.add(new clsUser(
                        rs.getString("id_user"),
                        rs.getString("user_name"),
                        rs.getString("user_description"),
                        rs.getString("nickname"),
                        opFindRole(rs.getString("id_role"), prmRoles),
                        rs.getBoolean("asset"),
                        rs.getString("password")));
            }
        }
        return varUsers;
    }

    /**
     * @param prmUsers usuarios ya cargados, para enlazar cada pregunta con su autor.
     */
    public static List<clsQuestion> opLoadQuestions(List<clsUser> prmUsers) throws SQLException {
        List<clsQuestion> varQuestions = new ArrayList<>();
        String sql = "SELECT id_question, question_name, question_description, optionA, optionB, optionC, optionD, "
                + "rightAnswer, state, type_question, path_imagen, id_usuario FROM tbl_question;";
        try (Connection varCon = clsConnectionSQL.opGetConnection();
                PreparedStatement ps = varCon.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                varQuestions.add(new clsQuestion(
                        rs.getString("id_question"),
                        rs.getString("question_name"),
                        rs.getString("question_description"),
                        rs.getString("optionA"),
                        rs.getString("optionB"),
                        rs.getString("optionC"),
                        rs.getString("optionD"),
                        rs.getString("rightAnswer"),
                        rs.getString("state"),
                        rs.getString("type_question"),
                        rs.getString("path_imagen"),
                        opFindUser(rs.getString("id_usuario"), prmUsers)));
            }
        }
        return varQuestions;
    }

    private static clsRole opFindRole(String prmOUID, List<clsRole> prmRoles) {
        if (prmOUID == null) {
            return null;
        }
        for (clsRole varRole : prmRoles) {
            if (prmOUID.equals(varRole.opGetOUID())) {
                return varRole;
            }
        }
        return null;
    }

    private static clsUser opFindUser(String prmOUID, List<clsUser> prmUsers) {
        if (prmOUID == null) {
            return null;
        }
        for (clsUser varUser : prmUsers) {
            if (prmOUID.equals(varUser.opGetOUID())) {
                return varUser;
            }
        }
        return null;
    }
}
