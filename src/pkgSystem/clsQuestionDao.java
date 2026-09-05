/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgSystem;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author santi
 */
public class clsQuestionDao {

    public static Boolean opSaveQuestion(
            String prmIdQuestion,
            String prmQuestionName,
            String prmQuestionDescription,
            String prmOptionA,
            String prmOptionB,
            String prmOptionC,
            String prmOptionD,
            String prmRightAnswer,
            String prmState,
            String prmIdUsuario) {

        String sql = "INSERT INTO tbl_question "
                + "(id_question, question_name, question_description, "
                + "optionA, optionB, optionC, optionD, rightAnswer, state, id_usuario) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        Connection con = null;

        try {
            con = clsConnectionSQL.opGetConnection();

            try (PreparedStatement pstmt = con.prepareStatement(sql)) {

                pstmt.setString(1, prmIdQuestion);
                pstmt.setString(2, prmQuestionName);
                pstmt.setString(3, prmQuestionDescription);
                pstmt.setString(4, prmOptionA);
                pstmt.setString(5, prmOptionB);
                pstmt.setString(6, prmOptionC);
                pstmt.setString(7, prmOptionD);
                pstmt.setString(8, prmRightAnswer);
                pstmt.setString(9, prmState);
                pstmt.setString(10, prmIdUsuario);

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    return true;
                }
            }

        } catch (SQLException e) {
            System.err.println(
                    "Error al guardar la pregunta en BD: "
                    + e.getMessage()
            );

        } finally {
            clsConnectionSQL.opCloseConnection(con);
        }

        return false;
    }
}
