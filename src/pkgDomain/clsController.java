/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgDomain;

import java.util.ArrayList;
import java.util.List;
import pkgServices.clsBrokerCrud;

/**
 *
 * @author Acer3
 */
public class clsController {
    
    private static clsController attInstance;
    private final List<clsUser> attMyUsers = new ArrayList<>();
    private final List<clsRole> attMyRoles = new ArrayList<>();
    private final List<clsQuestion> attMyQuestions = new ArrayList<>();
    
    /* Builders */
    
    private clsController() {
    }

    public static clsController opGetInstance() {
        if (attInstance == null) {
            attInstance = new clsController();
        }
        return attInstance;
    }
    
    /* Getters */
    
    public clsUser opGetUser(String prmOUID){
        return clsBrokerCrud.opGetItemType(prmOUID, attMyUsers);
    }
    
    public clsRole opGetRole (String prmOUID){
        return clsBrokerCrud.opGetItemType(prmOUID, attMyRoles);
    }
    
    public clsQuestion opGetQuestion (String prmOUID){
        return clsBrokerCrud.opGetItemType(prmOUID, attMyQuestions);
    }
    
    /* List Getters */
    public List<clsUser> opGetMyUsers() {
        return attMyUsers;
    }

    public List<clsRole> opGetMyRoles() {
        return attMyRoles;
    }
    
    public List<clsQuestion> opGetMyQuestions(){
        return attMyQuestions;
    }
    /* Update */
    
    public Boolean opUpdateUser(String prmOUID, String prmName, String prmDescription, String prmNickName, clsRole prmRole, Boolean prmAsset, String prmPassword){
        clsUser varObj = opGetUser(prmOUID);
        if(varObj == null){
            return false;
        }
        return varObj.opModify(prmName, prmDescription, prmNickName, prmRole, prmAsset, prmPassword);
    }
    
    public Boolean opUpdateRole(String prmOUID, String prmName, String prmDescription){
        clsRole varObj = opGetRole(prmOUID);
        if(varObj == null){
            return false;
        }
        return varObj.opModify(prmName, prmDescription);
    }
    
    public Boolean opUpdateQuestion (String prmOUID, String prmName, String prmDescription,
            String prmOptionA, String prmOptionB, String prmOptionC,
            String prmOptionD, String prmRightAnswer, String prmState, clsUser prmUser)
    {
        clsQuestion varObj = opGetQuestion(prmOUID);
        if (varObj==null) {
            return false;
        }
        return varObj.opModify(prmName, prmDescription, prmOptionA, prmOptionB, prmOptionC, prmOptionD, prmRightAnswer, prmState);
    }
    
    /* Associate */
    
    private Boolean opAssociateUser (clsUser prmUser){
        return clsBrokerCrud.opAssociateItemTo(prmUser, attMyUsers);
    }
    
    private Boolean opAssociateRole(clsRole prmRole){
        return clsBrokerCrud.opAssociateItemTo(prmRole, attMyRoles);
    }
    
    private Boolean opAssociateQuestion(clsQuestion prmQuestion){
        return clsBrokerCrud.opAssociateItemTo(prmQuestion, attMyQuestions);
    }
    
    /* Disassociate */
    
    private Boolean opDisassociateUser (clsUser prmUser){
        return clsBrokerCrud.opDisassociateItemTo(prmUser, attMyUsers);
    }
    
    private Boolean opDisassociateRole (clsRole prmRole){
        return clsBrokerCrud.opDisassociateItemTo(prmRole, attMyRoles);
    }
    
    private Boolean opDisassociateRole (clsQuestion prmQuestion){
        return clsBrokerCrud.opDisassociateItemTo(prmQuestion, attMyQuestions);
    }
    
    /* Register */
    
    public Boolean opRegisterUser (String prmOUID, String prmName, String prmDescription, String prmNickName, clsRole prmRole, Boolean prmAsset, String prmPassword){
        clsUser varObj = opGetUser(prmOUID);
        if (varObj != null) {
            return false;
        }
        return opAssociateUser(new clsUser(prmOUID,prmName,prmDescription, prmNickName,prmRole, prmAsset, prmPassword));
    }
    
    public Boolean opRegisterRole (String prmOUID, String prmName, String prmDescription){
        clsRole varObj = opGetRole(prmOUID);
        if (varObj != null) {
            return false;
        }
        return opAssociateRole(new clsRole(prmOUID, prmName, prmDescription));
    }
    
    public Boolean opRegisterQuestion (String prmOUID, String prmName, String prmDescription,
            String prmOptionA, String prmOptionB, String prmOptionC,
            String prmOptionD, String prmRightAnswer, String prmState, clsUser prmUser)
    {
        clsQuestion varObj = opGetQuestion(prmOUID);
        if (varObj!=null) {
            return false;
        }
        return opAssociateQuestion(new clsQuestion(prmOUID, prmName, prmDescription, prmOptionA, prmOptionB, prmOptionC, prmOptionD, prmRightAnswer, prmState, prmUser));
    }
    
    /* Deletes */
    
    public Boolean opDeleteUser(String prmOUID){
        clsUser varObj = opGetUser(prmOUID);
        if (varObj == null) {
            return false;
        }
        if (!varObj.opDie()) {
            return false;
        }
        opDisassociateUser(varObj);
        varObj = null;
        return true;
    }
    
    public Boolean opDeleteRole (String prmOUID){
        clsRole varObj = opGetRole(prmOUID);
        if (varObj == null) {
            return false;
        }
        if (!varObj.opDie()) {
            return false;
        }
        opDisassociateRole(varObj);
        varObj = null;
        return true;
    }
    
    public Boolean opDeleteQuestion (String prmOUID){
        clsQuestion varObj = opGetQuestion(prmOUID);
        if (varObj== null) {
            return false;
        }
        if (!varObj.opDie()) {
            return false;
        }
        opDisassociateRole(varObj);
        varObj = null;
        return true;
    }
    
    /* Transactions */
    
    public int opGetNumberUsers (){
        if (attMyUsers == null || attMyRoles.isEmpty()) {
            return 0;
        }
        return attMyUsers.size();
    }
    
    public clsRole opGetRoleForName(String prmRoleName){
        return clsBrokerCrud.opGetItemForName(prmRoleName, attMyRoles);
    }
    
    public Boolean opRegisterUserInRole (String prmOUIDUser, String prmOUIDRole){
        clsRole varObjRole = opGetRole(prmOUIDRole);
        clsUser varObjUser = opGetUser(prmOUIDUser);
        if (varObjRole==null && varObjUser == null) {
            return false;
        }
        return varObjRole.opRegisterUserInRole(varObjUser);
    }
    
    public clsUser opGetUserForNickName (String prmNickName){
        return clsBrokerCrud.opGetUserByNickname(prmNickName, attMyUsers);
    }
    
    public String opGetPasswordUser (String prmNickName){
        return opGetUserForNickName(prmNickName).opGetPassword();
    }
    
    public clsQuestion opGetQuestionForName (String prmNameQuestion){
        return clsBrokerCrud.opGetItemForName(prmNameQuestion, attMyQuestions);
    }
}
