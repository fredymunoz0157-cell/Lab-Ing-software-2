/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgServices.pkgPipeline;

/**
 *
 * @author Acer3
 */
public class clsFilterQuestionSize implements clsFilterQuestion{
    
    private final int attSize;
    
    public clsFilterQuestionSize(int prmSize) {
        attSize= prmSize;
    }
    
    @Override
    public Boolean opExecute (String prmInput){
        if (prmInput.length() >= attSize) {
            return true;
        } else {
            return false;
        }
    }
}
