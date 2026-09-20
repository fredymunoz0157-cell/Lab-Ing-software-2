/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appLogin;

import pkgDomain.clsController;
import pkgDomain.clsUser;
import pkgServices.pkgMicrokernel.clsQuestionMicrokernel;
import pkgUI.uiLogin;

/**
 *
 * @author Acer3
 */
public class clsMain {

    public static void main(String[] args) {
        clsController.opGetInstance().opRaedy();
        uiLogin varObj = new uiLogin();
        varObj.setVisible(true);
    }
}
