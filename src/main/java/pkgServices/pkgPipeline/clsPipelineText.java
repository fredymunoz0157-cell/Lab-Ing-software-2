package pkgServices.pkgPipeline;

import java.util.List;

/**
 * Tubería que pasa un texto por una cadena de filtros {@link IFilterText}.
 * Se detiene en el primer filtro que falle.
 *
 * @author santi
 */
public class clsPipelineText {

    private final List<IFilterText> attFilters;

    public clsPipelineText(List<IFilterText> prmFilters) {
        attFilters = List.copyOf(prmFilters);
    }

    public boolean opExecuteFilters(String prmInput) {
        for (IFilterText varFilter : attFilters) {
            if (!varFilter.opExecute(prmInput)) {
                return false;
            }
        }
        return true;
    }
}
