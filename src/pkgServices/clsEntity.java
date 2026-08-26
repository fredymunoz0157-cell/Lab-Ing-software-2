/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgServices;

/**
 *
 * @author Acer3
 */
public class clsEntity  implements clsBrokerCrud.IIdentificable<String>, clsBrokerCrud.IIdentificableName<String> {
    
    private String attOUID;
    private String attName;
    private String attDescription;
    
    protected clsEntity(){
    }
    
    protected clsEntity (String prmOUID, String prmName, String prmDescription){
        attOUID = prmOUID;
        attName = prmName;
        attDescription = prmDescription;
    }
    
    @Override
    public String opGetOUID(){
        return attOUID;
    }
    
    @Override
    public String opGetName(){
        return attName;
    }
    
    public String opGetDescription(){
        return attDescription;
    }
    
    public Boolean opModify(String prmName, String prmDescription){
        attName=prmName;
        attName=prmDescription;
        return true;
    }
}
