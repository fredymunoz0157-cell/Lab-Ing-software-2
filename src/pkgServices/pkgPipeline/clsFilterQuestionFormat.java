/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgServices.pkgPipeline;

/**
 *
 * @author Acer3
 */
public class clsFilterQuestionFormat implements clsFilterQuestion{

    public clsFilterQuestionFormat() {
    }
    
    @Override
    public Boolean opExecute (String prmInptu){
        return !prmInptu.isBlank();
    }
}
