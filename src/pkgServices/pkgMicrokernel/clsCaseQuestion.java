/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgServices.pkgMicrokernel;

import pkgDomain.clsQuestion;
import pkgDomain.clsUser;

/**
 *
 * @author Acer3
 */
public class clsCaseQuestion implements IQuestionPlugin{

    public clsCaseQuestion() {
    }
    
    @Override
    public String opGetOUID(){
        return "TIPO_CASO";
    }
    
    @Override
    public clsQuestion opCreateQuestion(String prmOUID, String prmName, String prmDescription,String prmOptionA, String prmOptionB, String prmOptionC, String prmOptionD, String prmRightAnswer, String prmState,String prmType, String prmPathImagen, clsUser prmUser){
        return new clsQuestion(prmOUID, prmName, prmDescription, prmOptionA, prmOptionB, prmOptionC, prmOptionD, prmRightAnswer, prmState,prmType, "", prmUser);
    }
}
