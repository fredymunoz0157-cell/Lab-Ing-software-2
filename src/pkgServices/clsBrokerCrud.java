/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgServices;

import java.util.List;
import pkgDomain.clsUser;

/**
 *
 * @author Acer3
 */
public final class clsBrokerCrud {
    public interface IIdentificable<T> {
        T opGetOUID();
    }
    
    public interface IIdentificableName<T>{
        T opGetName();
    }

    public static <OUIDType extends Comparable<OUIDType>, ItemType extends IIdentificable<OUIDType>> 
    ItemType opGetItemType(OUIDType prmOUID, List<ItemType> prmCollection) {
        if (prmCollection != null) {
            for (ItemType varObj : prmCollection) {
                if (varObj.opGetOUID().compareTo(prmOUID) == 0) {
                    return varObj;
                }
            }
        }
        return null; 
    }

    public static <ItemType> boolean opAssociateItemTo(ItemType prmItem, List<ItemType> prmCollection) {
        prmCollection.add(prmItem);
        return true;
    }

    public static <ItemType> boolean opDisassociateItemTo(ItemType prmItem, List<ItemType> prmCollection) {
        prmCollection.remove(prmItem);
        return true;
    }
    
    public static <OUIDType extends Comparable<OUIDType>, ItemType extends IIdentificableName<OUIDType>>ItemType 
    opGetItemForName(OUIDType prmName, List<ItemType> prmCollection) {

        for (ItemType varObj : prmCollection) {
            if (varObj.opGetName().compareTo(prmName) == 0) {
                return varObj;
            }
        }
        return null; 
    }
    
    public static clsUser opGetUserByNickname(String prmNickName, List<clsUser> prmCollection) {
    if (prmNickName != null && prmCollection != null) {
        for (clsUser varUser : prmCollection) {
            if (varUser.opGetNickName() != null && varUser.opGetNickName().equalsIgnoreCase(prmNickName.trim())) {
                return varUser;
            }
        }
    }
    return null;
}
}
