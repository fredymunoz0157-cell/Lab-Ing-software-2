/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgServices.pkgPipeline;

/**
 *
 * @author Acer3
 */
public class clsFilterOptionFormat implements clsFilterOption{
    
    private int attMinimalOptions;

    public clsFilterOptionFormat() {
        attMinimalOptions=4;
    }
    
    @Override
    public Boolean opExecute (String prmOptionA, String prmOptionB, String prmOptionC, String prmOptionD){
        if (prmOptionA.isBlank()) {
            attMinimalOptions-=1;
        }
        if (prmOptionB.isBlank()) {
            attMinimalOptions-=1;
        }
        if (prmOptionC.isBlank()) {
            attMinimalOptions-=1;
        }
        if (prmOptionD.isBlank()) {
            attMinimalOptions-=1;
        }
        if (attMinimalOptions==4) {
            return true;
        } else {
            return false;
        }
    }
}
