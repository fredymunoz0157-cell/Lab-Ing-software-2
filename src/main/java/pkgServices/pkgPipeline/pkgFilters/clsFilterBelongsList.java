package pkgServices.pkgPipeline.pkgFilters;

import java.util.ArrayList;
import java.util.List;
import pkgServices.pkgPipeline.IFilterText;

/**
 * Exige que el texto sea uno de los valores permitidos.
 * (Antes clsFilterQuestionBelongsList.)
 *
 * @author Acer3
 */
public class clsFilterBelongsList implements IFilterText {

    private final List<String> attAllowedValues;

    public clsFilterBelongsList(List<String> prmAllowedValues) {
        attAllowedValues = new ArrayList<>(prmAllowedValues);
    }

    @Override
    public Boolean opExecute(String prmInput) {
        return prmInput != null && attAllowedValues.contains(prmInput);
    }
}
