/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgDomain.pkgEntities;

import java.util.ArrayList;
import java.util.List;
import pkgUtil.clsBrokerCrud;

/**
 *
 * @author Acer3
 */
public class clsRole extends clsEntity{
    
    private List<clsUser> attMyUsers = new ArrayList<clsUser>();
    
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
        // Solo se puede eliminar un rol que no tenga usuarios asociados
        return attMyUsers.isEmpty();
    }
    
    public Boolean opRegisterUserInRole (clsUser prmUser){
        return clsBrokerCrud.opAssociateItemTo(prmUser, attMyUsers);
    }
}
