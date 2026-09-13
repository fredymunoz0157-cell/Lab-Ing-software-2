/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgServices.pkgPipeline;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Acer3
 */
public class clsFilterOptionDuplicate implements clsFilterOption {

    public clsFilterOptionDuplicate() {
    }
    
    @Override
    public Boolean opExecute (String prmOptionA, String prmOptionB, String prmOptionC, String prmOptionD) {
        Set<String> varOptionAvail = new HashSet<>(Arrays.asList(prmOptionA, prmOptionB, prmOptionC, prmOptionD));
        return varOptionAvail.size() == 4;
    }
}
