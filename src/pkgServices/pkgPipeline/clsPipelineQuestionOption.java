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
public class clsPipelineQuestionOption {
    private final List<clsFilterOption> attQuestionFilter;

    
    public clsPipelineQuestionOption(List<clsFilterOption> attQuestionFilter) {
        this.attQuestionFilter = attQuestionFilter;
    }
    
    public boolean opExecuteFilters(String prmInput1, String prmInput2, String prmInput3, String prmInput4){
        for(clsFilterOption varObject: attQuestionFilter){
            if(!varObject.opExecute(prmInput1, prmInput2, prmInput3, prmInput4)){
                return false;
            }
        }
        return true;
    }
    
    
}
