/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgDomain;

import java.util.ArrayList;
import java.util.List;
import pkgServices.clsBrokerCrud;
import pkgServices.clsEntity;

/**
 *
 * @author Acer3
 */
public class clsRole extends clsEntity{
    
    private List<clsUser> attMyUsers = new ArrayList();
    
    public clsRole(){
        super();
    }
    
    public clsRole(String prmOUID, String prmName, String prmDescription){
        super(prmOUID, prmName, prmDescription);
    }
    
    @Override
    public Boolean opModify(String prmName, String prmDescription){
        super.opModify(prmName, prmDescription);
        return true;
    }
    
    public Boolean opDie (){
        if (attMyUsers != null) {
            return false;
        }
        return true;
    }
    
    public Boolean opRegisterUserInRole (clsUser prmUser){
        return clsBrokerCrud.opAssociateItemTo(prmUser, attMyUsers);
    }
}
