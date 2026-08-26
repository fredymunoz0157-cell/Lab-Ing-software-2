/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgDomain;

import pkgServices.clsEntity;

/**
 *
 * @author Acer3
 */
public class clsUser extends clsEntity{
    
    private String attNickName;
    private clsRole attRole = new clsRole();
    private Boolean attAsset = true;
    private String attPassword;
    
    public clsUser(){
        super();
    }
    
    public clsUser(String prmOUID, String prmName, String prmDescription, String prmNickName, clsRole prmRole, Boolean prmAsset, String prmPassword){
        super(prmOUID, prmName, prmDescription);
        attNickName = prmNickName;
        attRole = prmRole;
        attAsset = prmAsset;
        attPassword = prmPassword;
    }
    
    public String opGetNickName() {
        return attNickName;
    }

    public clsRole opGetRole() {
        return attRole;
    }

    public Boolean opGetAsset() {
        return attAsset;
    }

    public String opGetPassword() {
        return attPassword;
    }
    
    public Boolean opModify (String prmName, String prmDescription, String prmNickName, clsRole prmRole, Boolean prmAsset, String prmPassword){
        super.opModify(prmName, prmDescription);
        attNickName = prmNickName;
        attRole = prmRole;
        attAsset = prmAsset;
        attPassword = prmPassword;
        return true;
    }
    
    public Boolean opDie(){
        if (attRole != null) {
            return false;
        }
        return true;
    }
    
}
