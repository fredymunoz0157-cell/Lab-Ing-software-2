package pkgServices.pkgPipeline;

import java.util.List;

/**
 * Tubería que pasa las cuatro opciones de una pregunta por una cadena de
 * filtros {@link IFilterOption}. Se detiene en el primer filtro que falle.
 *
 * @author santi
 */
public class clsPipelineOption {

    private final List<IFilterOption> attFilters;

    public clsPipelineOption(List<IFilterOption> prmFilters) {
        attFilters = List.copyOf(prmFilters);
    }

    public boolean opExecuteFilters(String prmOptionA, String prmOptionB, String prmOptionC, String prmOptionD) {
        for (IFilterOption varFilter : attFilters) {
            if (!varFilter.opExecute(prmOptionA, prmOptionB, prmOptionC, prmOptionD)) {
                return false;
            }
        }
        return true;
    }
}
