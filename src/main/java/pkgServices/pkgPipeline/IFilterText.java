package pkgServices.pkgPipeline;

/**
 * Filtro del pipeline que valida un único texto.
 * Cada implementación revisa una sola regla y responde si el dato la cumple.
 *
 * @author Acer3
 */
public interface IFilterText {

    Boolean opExecute(String prmInput);
}
