/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgServices;

import java.util.UUID;
import pkgDomain.clsController;
import pkgDomain.clsRole;
import pkgSystem.clsSecurityUtils;
import pkgSystem.clsUserDao;

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
        clsRole varObjRole = clsController.opGetInstance().opGetRoleForName(prmRole);
        if (varObjRole==null) {
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
            return clsSecurityUtils.opCheckPassword(prmPassword, clsController.opGetInstance().opGetPasswordUser(prmNickName));
        } catch (Exception e) {
            return false;
        }
    }
}
