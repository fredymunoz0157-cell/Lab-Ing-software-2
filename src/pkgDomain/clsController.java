/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgDomain;

import java.sql.SQLException;
import java.util.UUID;
import pkgServices.pkgDataBase.clsBoardDao;
import pkgServices.pkgDataBase.clsLoadDao;
import pkgServices.pkgDataBase.clsQuestionDao;
import pkgServices.pkgDataBase.clsSecurityUtils;
import pkgServices.pkgDataBase.clsUserDao;
import pkgServices.pkgGlobal.clsValidate;

/**
 *
 * @author Acer3
 */
public class clsController {

    private static clsController attInstance;
    private clsUser attUserLogin = new clsUser();

    private clsController() {
    }

    public static clsController opGetInstance() {
        if (attInstance == null) {
            attInstance = new clsController();
        }
        return attInstance;
    }
    
    public void opRaedy (){
        try {
            clsBoardDao.opCreateBoardRole();
            clsBoardDao.opCreateBoardUser();
            clsBoardDao.opCreateBoardQuestion();
            clsLoadDao.opLoadDatabaseToController();
        } catch (SQLException e) {
            System.err.println("Error al crear la base de datos" + e.getMessage());
        }
    }
    
    public clsUser opGetUserLogin(){
        return attUserLogin;
    }
    
    public String opGetRoleUserLogin(){
        return attUserLogin.opGetRole().opGetName();
    }
    
    public void opUpdateUserLogin(clsUser prmUser){
        attUserLogin = prmUser;
    }

    public Boolean opValidateRegister(String prmName, String prmNickName, String prmPassword) {
        if (!clsValidate.opValidFullName(prmName)) {
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

    public Boolean opRegisterUser(String prmName, String prmNickName, String prmRole, String prmPassword) {
        clsRole varObjRole = clsControllerDomain.opGetInstance().opGetRoleForName(prmRole);
        if (varObjRole == null) {
            return false;
        }
        if (!clsUserDao.opSaveUser(UUID.randomUUID().toString(), prmName, "", prmNickName, varObjRole, true, prmPassword)) {
            return false;
        }

        return true;
    }

    public Boolean opValidateLogin(String prmNickName, String prmPassword) {
        if (!clsValidate.opValidNickname(prmNickName)) {
            return false;
        }
        if (!clsValidate.opValidUserPassword(prmPassword)) {
            return false;
        }
        return true;
    }

    public Boolean opLoginUser(String prmNickName, String prmPassword) {
        try {
            clsUser varObj = clsControllerDomain.opGetInstance().opGetUserForNickName(prmNickName);
            if (varObj != null) {
                attUserLogin = varObj;
                return clsSecurityUtils.opCheckPassword(prmPassword, varObj.opGetPassword());
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean opRegisterQuestion(String prmName, String prmQuestion, String prmOptionA, String prmOptionB, String prmOptionC, String prmOptionD, String prmRightAnswer, String prmState,String prmType, String prmPathImagen, String prmOUIDUser) {
        clsQuestion varObjQuestion = clsControllerDomain.opGetInstance().opGetQuestionForName(prmName);
        if (varObjQuestion != null) {
            return false;
        }
        if (!clsQuestionDao.opSaveQuestion(UUID.randomUUID().toString(), prmName, "", prmOptionA, prmOptionB, prmOptionC, prmOptionD, prmRightAnswer, prmState, prmType, prmPathImagen,prmOUIDUser)) {
            return false;
        }
        clsControllerDomain.opGetInstance().opRegisterQuestion(prmOUIDUser, prmName, prmQuestion, prmOptionA, prmOptionB, prmOptionC, prmOptionD, prmRightAnswer, prmState, prmType, prmPathImagen,attUserLogin);
        return true;
    }

    public Boolean opUpdateQuestion(
            String prmIdQuestion,
            String prmQuestionName,
            String prmQuestionDescription,
            String prmOptionA,
            String prmOptionB,
            String prmOptionC,
            String prmOptionD,
            String prmRightAnswer,
            String prmState,
            String prmType,
            String prmPathImagen
    ) {
        clsQuestion varObjQuestion = clsControllerDomain.opGetInstance().opGetQuestionForName(prmQuestionName);
        if (varObjQuestion == null) {
            return false;
        }
        if (!clsQuestionDao.opUpdateQuestion(prmIdQuestion, prmQuestionName, prmQuestionDescription, prmOptionA, prmOptionB, prmOptionC, prmOptionD, prmRightAnswer, prmState, prmType, prmPathImagen,attUserLogin.opGetOUID())) {
            return false;
        }
        varObjQuestion.opModify(prmQuestionName, prmQuestionDescription, prmOptionA, prmOptionB, prmOptionC, prmOptionD, prmRightAnswer, prmState, prmType, prmPathImagen);
        return true;
    }

    public Boolean opShowUIQuestions() {
        String varRole = opGetRoleUserLogin();
        if (varRole.equalsIgnoreCase("Administrador")
                || varRole.equalsIgnoreCase("Autor de preguntas")
                || varRole.equalsIgnoreCase("Revisor")) {
            return true;
        }
        return false;
    }
    
    
}
