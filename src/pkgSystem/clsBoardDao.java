/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgSystem;

import java.sql.SQLException;

/**
 *
 * @author Acer3
 */
public class clsBoardDao {

    public static void opCreateBoardRole() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS tbl_roles ("
                + " id_role TEXT PRIMARY KEY,"
                + " role_name TEXT NOT NULL,"
                + " role_description TEXT"
                + ");";
        clsConnectionSQL.opExecuteDDL(sql);
    }

    public static void opCreateBoardUser() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS tbl_users ("
                + " id_user TEXT PRIMARY KEY,"
                + " user_name TEXT,"
                + " user_description TEXT,"
                + " nickname TEXT NOT NULL,"
                + " password TEXT NOT NULL,"
                + " asset INTEGER NOT NULL,"
                + " id_role TEXT,"
                + " FOREIGN KEY (id_role) REFERENCES tbl_roles(id_role)"
                + ");";
        clsConnectionSQL.opExecuteDDL(sql);
    }

    //Edit Questions
    public static void opCreateBoardQuestion() throws SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS tbl_question ("
                + "id_question TEXT PRIMARY KEY,"
                + "question_name TEXT,"
                + "question_description TEXT,"
                + "optionA TEXT NOT NULL,"
                + "optionB TEXT NOT NULL,"
                + "optionC TEXT NOT NULL,"
                + "optionD TEXT NOT NULL,"
                + "rightAnswer TEXT NOT NULL,"
                + "state TEXT NOT NULL,"
                + "id_usuario TEXT NOT NULL"
                + ");";

        clsConnectionSQL.opExecuteDDL(sql);
    }

}
