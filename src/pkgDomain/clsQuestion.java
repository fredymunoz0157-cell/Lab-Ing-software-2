/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgDomain;

import pkgServices.pkgGlobal.clsEntity;

/**
 *
 * @author Acer3
 */
public class clsQuestion extends clsEntity {

    private String attOptionA;
    private String attOptionB;
    private String attOptionC;
    private String attOptionD;
    private String attRightAnswer;
    private String attState;
    private String attType;
    private String attPathImagen;
    private clsUser attUser;

    public clsQuestion() {
        super();
    }

    public clsQuestion(String prmOUID, String prmName, String prmDescription,
            String prmOptionA, String prmOptionB, String prmOptionC,
            String prmOptionD, String prmRightAnswer, String prmState, 
            String prmType, String prmPathImagen, clsUser prmUser) {
        super(prmOUID, prmName, prmDescription);
        attOptionA = prmOptionA;
        attOptionB = prmOptionB;
        attOptionC = prmOptionC;
        attOptionD = prmOptionD;
        attRightAnswer = prmRightAnswer;
        attState = prmState;
        attType= prmType;
        attPathImagen=prmPathImagen;
        attUser = prmUser;
    }
    
    public String opGetOptionA() {
        return attOptionA;
    }

    public String opGetOptionB() {
        return attOptionB;
    }

    public String opGetOptionC() {
        return attOptionC;
    }

    public String opGetOptionD() {
        return attOptionD;
    }

    public String opGetRightAnswer() {
        return attRightAnswer;
    }

    public String opGetState() {
        return attState;
    }
    
    public String opGetType (){
        return attType;
    }
    
    public String opGetPathImagen(){
        return attPathImagen;
    }

    public clsUser opGetUser() {
        return attUser;
    }
    
    public Boolean opDie() {
        if (attUser != null) {
            return false;
        }
        return true;
    }
    public Boolean opModify(String prmName, String prmDescription,
            String prmOptionA, String prmOptionB, String prmOptionC,
            String prmOptionD, String prmRightAnswer, String prmState, 
            String prmType, String prmPathImagen) {
        super.opModify(prmName, prmDescription);
        attOptionA = prmOptionA;
        attOptionB = prmOptionB;
        attOptionC = prmOptionC;
        attOptionD = prmOptionD;
        attRightAnswer = prmRightAnswer;
        attState = prmState;
        attType = prmType;
        attPathImagen = prmPathImagen;
        return true;
    }
}
