package pkgServices.pkgPipeline.pkgFilters;

import pkgServices.pkgPipeline.IFilterText;

/**
 * Rechaza textos nulos, vacíos o con solo espacios.
 * (Antes clsFilterQuestionFormat.)
 *
 * @author Acer3
 */
public class clsFilterNotBlank implements IFilterText {

    @Override
    public Boolean opExecute(String prmInput) {
        return prmInput != null && !prmInput.isBlank();
    }
}
