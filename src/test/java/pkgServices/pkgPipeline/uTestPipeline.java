package pkgServices.pkgPipeline;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.junit.Test;
import pkgServices.pkgPipeline.pkgFilters.clsFilterBelongsList;
import pkgServices.pkgPipeline.pkgFilters.clsFilterMinLength;
import pkgServices.pkgPipeline.pkgFilters.clsFilterNotBlank;
import pkgServices.pkgPipeline.pkgFilters.clsFilterOptionDuplicate;
import pkgServices.pkgPipeline.pkgFilters.clsFilterOptionNotBlank;
import pkgServices.pkgPipeline.pkgFilters.clsFilterRegex;

/**
 * Pruebas del estilo tuberías y filtros.
 */
public class uTestPipeline {

    /* ---------- Filtros individuales ---------- */

    @Test
    public void notBlankRechazaNuloVacioYEspacios() {
        IFilterText varFilter = new clsFilterNotBlank();
        assertFalse(varFilter.opExecute(null));
        assertFalse(varFilter.opExecute(""));
        assertFalse(varFilter.opExecute("   "));
        assertTrue(varFilter.opExecute("Santiago"));
    }

    @Test
    public void minLengthExigeLongitudMinima() {
        IFilterText varFilter = new clsFilterMinLength(5);
        assertFalse(varFilter.opExecute("abc"));
        assertFalse(varFilter.opExecute(null));
        assertTrue(varFilter.opExecute("abcde"));
    }

    @Test
    public void belongsListAceptaSoloValoresPermitidos() {
        IFilterText varFilter = new clsFilterBelongsList(List.of("A", "B"));
        assertTrue(varFilter.opExecute("A"));
        assertFalse(varFilter.opExecute("C"));
        assertFalse(varFilter.opExecute(null));
    }

    @Test
    public void regexValidaFormato() {
        IFilterText varFilter = new clsFilterRegex("\\d+");
        assertTrue(varFilter.opExecute("123"));
        assertFalse(varFilter.opExecute("12a"));
        assertFalse(varFilter.opExecute(null));
    }

    @Test
    public void opcionesVaciasODuplicadasSeRechazan() {
        IFilterOption varNotBlank = new clsFilterOptionNotBlank();
        IFilterOption varDuplicate = new clsFilterOptionDuplicate();
        assertTrue(varNotBlank.opExecute("a", "b", "c", "d"));
        assertFalse(varNotBlank.opExecute("a", "", "c", "d"));
        assertTrue(varDuplicate.opExecute("a", "b", "c", "d"));
        assertFalse(varDuplicate.opExecute("a", "b", "A ", "d"));
    }

    @Test
    public void filtroDeOpcionesSePuedeReutilizar() {
        // Antes el contador se guardaba como atributo: tras una falla, siempre fallaba
        IFilterOption varFilter = new clsFilterOptionNotBlank();
        assertFalse(varFilter.opExecute("a", "", "c", "d"));
        assertTrue(varFilter.opExecute("a", "b", "c", "d"));
    }

    /* ---------- Tuberías armadas por la fábrica ---------- */

    @Test
    public void pipelineSeDetieneEnElPrimerFiltroQueFalla() {
        clsPipelineText varPipeline = new clsPipelineText(List.of(new clsFilterNotBlank(), new clsFilterMinLength(3)));
        assertTrue(varPipeline.opExecuteFilters("abcd"));
        assertFalse(varPipeline.opExecuteFilters("ab"));
        assertFalse(varPipeline.opExecuteFilters(null));
    }

    @Test
    public void pipelineDeContrasena() {
        clsPipelineText varPipeline = clsPipelineFactory.opCreatePassword();
        assertTrue(varPipeline.opExecuteFilters("Clave1!"));
        assertFalse(varPipeline.opExecuteFilters("clave1!"));  // sin mayúscula
        assertFalse(varPipeline.opExecuteFilters("Clave!!"));  // sin dígito
        assertFalse(varPipeline.opExecuteFilters("Clave11"));  // sin carácter especial
        assertFalse(varPipeline.opExecuteFilters("Cl1!"));     // muy corta
        assertFalse(varPipeline.opExecuteFilters(null));
    }

    @Test
    public void pipelineDeRespuestaCorrecta() {
        clsPipelineText varPipeline = clsPipelineFactory.opCreateRightAnswer(
                "Marie Curie", "Ada Lovelace", "Grace Hopper", "Margaret Hamilton");
        assertTrue(varPipeline.opExecuteFilters("Ada Lovelace"));
        assertFalse(varPipeline.opExecuteFilters("Alan Turing"));
        assertFalse(varPipeline.opExecuteFilters(""));
    }

    @Test
    public void pipelineDeEstado() {
        clsPipelineText varPipeline = clsPipelineFactory.opCreateQuestionState();
        assertTrue(varPipeline.opExecuteFilters("Borrador"));
        assertFalse(varPipeline.opExecuteFilters("ACTIVO"));
    }
}
