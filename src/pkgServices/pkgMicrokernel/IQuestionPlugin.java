/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pkgServices.pkgMicrokernel;

import pkgDomain.clsQuestion;
import pkgDomain.clsUser;

/**
 *
 * @author Acer3
 */
public interface IQuestionPlugin {
    String opGetOUID();
    clsQuestion opCreateQuestion(String prmOUID, String prmName, String prmDescription,String prmOptionA, String prmOptionB, String prmOptionC, String prmOptionD, String prmRightAnswer, String prmState,String prmType, String prmPathImagen ,clsUser prmUser);
}
