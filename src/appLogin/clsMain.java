/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appLogin;

import java.sql.SQLException;
import pkgSystem.clsBoardDao;
import pkgSystem.clsLoadDao;
import pkgUI.uiLogin;

/**
 *
 * @author Acer3
 */
public class clsMain {
    public static void main(String[] args) {
        try {
            clsBoardDao.opCreateBoardRole();
            clsBoardDao.opCreateBoardUser();
            clsLoadDao.opLoadDatabaseToController();
        } catch (SQLException e) {
            System.err.println("Error al crear la base de datos"+ e.getMessage());
        }
        uiLogin varObj = new uiLogin();
        varObj.setVisible(true);  
    }
}
