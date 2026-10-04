package pkgServices.pkgPipeline.pkgFilters;

import java.util.regex.Pattern;
import pkgServices.pkgPipeline.IFilterText;

/**
 * Exige que el texto cumpla una expresión regular completa.
 * Reemplaza la validación de contraseña que antes hacía clsValidate.
 *
 * @author Acer3
 */
public class clsFilterRegex implements IFilterText {

    private final Pattern attPattern;

    public clsFilterRegex(String prmRegex) {
        attPattern = Pattern.compile(prmRegex);
    }

    @Override
    public Boolean opExecute(String prmInput) {
        return prmInput != null && attPattern.matcher(prmInput).matches();
    }
}
