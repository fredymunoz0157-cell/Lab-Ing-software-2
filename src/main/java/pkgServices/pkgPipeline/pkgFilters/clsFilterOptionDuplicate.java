package pkgServices.pkgPipeline.pkgFilters;

import java.util.HashSet;
import java.util.Set;
import pkgServices.pkgPipeline.IFilterOption;

/**
 * Rechaza preguntas con opciones repetidas (sin distinguir mayúsculas ni
 * espacios al inicio y al final).
 *
 * @author Acer3
 */
public class clsFilterOptionDuplicate implements IFilterOption {

    @Override
    public Boolean opExecute(String prmOptionA, String prmOptionB, String prmOptionC, String prmOptionD) {
        Set<String> varOptions = new HashSet<>();
        for (String varOption : new String[]{prmOptionA, prmOptionB, prmOptionC, prmOptionD}) {
            if (varOption == null) {
                return false;
            }
            varOptions.add(varOption.trim().toLowerCase());
        }
        return varOptions.size() == 4;
    }
}
