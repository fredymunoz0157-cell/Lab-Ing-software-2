package pkgServices.pkgPipeline.pkgFilters;

import pkgServices.pkgPipeline.IFilterOption;

/**
 * Exige que las cuatro opciones tengan contenido.
 * (Antes clsFilterOptionFormat; se quitó el contador guardado como atributo,
 * que hacía fallar el filtro al reutilizarlo.)
 *
 * @author Acer3
 */
public class clsFilterOptionNotBlank implements IFilterOption {

    @Override
    public Boolean opExecute(String prmOptionA, String prmOptionB, String prmOptionC, String prmOptionD) {
        return opHasText(prmOptionA) && opHasText(prmOptionB)
                && opHasText(prmOptionC) && opHasText(prmOptionD);
    }

    private boolean opHasText(String prmOption) {
        return prmOption != null && !prmOption.isBlank();
    }
}
