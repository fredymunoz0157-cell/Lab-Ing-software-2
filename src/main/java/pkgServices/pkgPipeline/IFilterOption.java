package pkgServices.pkgPipeline;

/**
 * Filtro del pipeline que valida en conjunto las cuatro opciones de una pregunta.
 *
 * @author Acer3
 */
public interface IFilterOption {

    Boolean opExecute(String prmOptionA, String prmOptionB, String prmOptionC, String prmOptionD);
}
