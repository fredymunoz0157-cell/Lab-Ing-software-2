package pkgServices.pkgPipeline.pkgFilters;

import pkgServices.pkgPipeline.IFilterText;

/**
 * Exige que el texto tenga al menos una longitud mínima (sin contar espacios
 * al inicio y al final). (Antes clsFilterQuestionSize.)
 *
 * @author Acer3
 */
public class clsFilterMinLength implements IFilterText {

    private final int attMinLength;

    public clsFilterMinLength(int prmMinLength) {
        attMinLength = prmMinLength;
    }

    @Override
    public Boolean opExecute(String prmInput) {
        return prmInput != null && prmInput.trim().length() >= attMinLength;
    }
}
