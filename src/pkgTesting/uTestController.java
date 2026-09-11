/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgTesting;

import pkgDomain.clsControllerDomain;
import pkgDomain.clsQuestion;
import pkgDomain.clsRole;
import pkgDomain.clsUser;

/**
 *
 * @author Acer3
 */
public class uTestController {
    public static void main(String[] args) {
        for (clsRole varObj : clsControllerDomain.opGetInstance().opGetMyRoles()) {
            System.out.println("" + varObj.opGetName());
        }
        for (clsUser varObj : clsControllerDomain.opGetInstance().opGetMyUsers()) {
            System.out.println("codigo "+varObj.opGetOUID() + " Nombre " + varObj.opGetName());
        }
        for (clsQuestion varObj : clsControllerDomain.opGetInstance().opGetMyQuestions()) {
            System.out.println("" + varObj.opGetName());
        }
    }
}
