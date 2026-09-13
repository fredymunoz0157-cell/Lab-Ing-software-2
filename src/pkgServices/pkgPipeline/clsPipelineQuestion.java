/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgServices.pkgPipeline;

import java.util.List;

/**
 *
 * @author santi
 */
public class clsPipelineQuestion {
    private final List<clsFilterQuestion> attQuestionFilter;

    
    public clsPipelineQuestion(List<clsFilterQuestion> attQuestionFilter) {
        this.attQuestionFilter = attQuestionFilter;
    }
    
    public boolean opExecuteFilters(String prmInput){
        for(clsFilterQuestion varObject: attQuestionFilter){
            if(varObject.opExecute(prmInput)){
                return false;
            }
            
        }
        return true;
    }
    
    
    
    
}
