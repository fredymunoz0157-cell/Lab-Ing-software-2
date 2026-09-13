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
public class clsMultimediaQuestion implements IQuestionPlugin{

    public clsMultimediaQuestion() {
    }
    
    @Override
    public String opGetOUID(){
        return "MULTIMEDIA";
    }
    
    @Override
    public clsQuestion opCreateQuestion(String prmOUID, String prmName, String prmDescription,String prmOptionA, String prmOptionB, String prmOptionC, String prmOptionD, String prmRightAnswer, String prmState,String prmType, String prmPathImagen, clsUser prmUser){
        return new clsQuestion(prmOUID, prmName, prmDescription, prmOptionA, prmOptionB, prmOptionC, prmOptionD, prmRightAnswer, prmState,prmType, prmPathImagen, prmUser);
    }
}
