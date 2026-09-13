/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgServices.pkgPipeline;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer3
 */
public class clsFilterQuestionBelongsList implements clsFilterQuestion{
    
    private List<String> attList = new ArrayList<>();

    public clsFilterQuestionBelongsList(List<String> prmList) {
        attList = prmList;
    }
    
    @Override
    public Boolean opExecute (String prmInput){
        for (String varObj : attList) {
            if (prmInput.equals(varObj)) {
                return true;
            }
        }
        return false;
    }
    
}
