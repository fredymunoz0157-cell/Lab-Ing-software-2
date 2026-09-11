/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgServices.pkgGlobal;

import pkgServices.pkgDataBase.clsQuestionDao;
import java.util.UUID;
import pkgDomain.clsControllerDomain;
import pkgDomain.clsQuestion;
import pkgDomain.clsRole;
import pkgDomain.clsUser;
import pkgServices.pkgDataBase.clsSecurityUtils;
import pkgServices.pkgDataBase.clsUserDao;

/**
 *
 * @author Acer3
 */
public class clsInterfaceBridge {
    
    public static Boolean opValidateRegister(String prmName, String prmNickName, String prmPassword){
        if(!clsValidate.opValidFullName(prmName)){
            return false;
        }
        if (!clsValidate.opValidNickname(prmNickName)) {
            return false;
        }
        if (!clsValidate.opValidUserPassword(prmPassword)) {
            return false;
        }
        return true;
    }
    
    public static Boolean opRegisterUser (String prmName, String prmNickName, String prmRole, String prmPassword){
        clsRole varObjRole = clsControllerDomain.opGetInstance().opGetRoleForName(prmRole);
        if (varObjRole!=null) {
            return false;
        }
        if(!clsUserDao.opSaveUser(UUID.randomUUID().toString(), prmName, "", prmNickName ,varObjRole, true,prmPassword)){
            return false;
        }
        
        return true;
    }
    
    public static Boolean opValidateLogin(String prmNickName, String prmPassword){
        if (!clsValidate.opValidNickname(prmNickName)) {
            return false;
        }
        if (!clsValidate.opValidUserPassword(prmPassword)) {
            return false;
        }
        return true;
    }
    
    public static Boolean opLoginUser(String prmNickName, String prmPassword){
        try {
            clsUser varObj = clsControllerDomain.opGetInstance().opGetUserForNickName(prmNickName);
            if (varObj!=null) {
                clsControllerDomain.opGetInstance().opUpdateUserLogin(varObj);
                return clsSecurityUtils.opCheckPassword(prmPassword, varObj.opGetPassword());
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static Boolean opRegisterQuestion(String prmName, String prmQuestion, String prmOptionA,
            String prmOptionB, String prmOptionC, String prmOptionD, String prmRightAnswer,String prmState, String prmOUIDUser)
    {
        clsQuestion varObjQuestion = clsControllerDomain.opGetInstance().opGetQuestionForName(prmName);
        if (varObjQuestion!=null) {
            return false;
        }
        if (!clsQuestionDao.opSaveQuestion(UUID.randomUUID().toString(), prmName, "", prmOptionA, prmOptionB, prmOptionC, prmOptionD, prmRightAnswer, prmState, prmOUIDUser)) {
            return false;
        }
        clsControllerDomain.opGetInstance().opRegisterQuestion(prmOUIDUser, prmName, prmQuestion, prmOptionA, prmOptionB, prmOptionC, prmOptionD, prmRightAnswer, prmState, clsControllerDomain.opGetInstance().opGetUserLogin());
        return true;
    }
    
    public static Boolean opUpdateQuestion(
            String prmIdQuestion,
            String prmQuestionName,
            String prmQuestionDescription,
            String prmOptionA,
            String prmOptionB,
            String prmOptionC,
            String prmOptionD,
            String prmRightAnswer,
            String prmState,
            String prmIdUsuario)
    {
        clsQuestion varObjQuestion = clsControllerDomain.opGetInstance().opGetQuestionForName(prmQuestionName);
        if (varObjQuestion == null) {
            return false;
        }
        if (!clsQuestionDao.opUpdateQuestion(prmIdQuestion, prmQuestionName, prmQuestionDescription, prmOptionA, prmOptionB, prmOptionC, prmOptionD, prmRightAnswer, prmState, prmIdUsuario)) {
          return false;
        }
        varObjQuestion.opModify(prmQuestionName, prmQuestionDescription, prmOptionA, prmOptionB, prmOptionC, prmOptionD, prmRightAnswer, prmState);
        return true;
    }
    
    public static Boolean opShowUIQuestions (){
        String varRole = clsControllerDomain.opGetInstance().opGetRoleUserLogin();
        if(varRole.equalsIgnoreCase("Administrador")||
                varRole.equalsIgnoreCase("Autor de preguntas")||
                varRole.equalsIgnoreCase("Revisor"))
        {
            return true;
        }
        return false;
    }
}
