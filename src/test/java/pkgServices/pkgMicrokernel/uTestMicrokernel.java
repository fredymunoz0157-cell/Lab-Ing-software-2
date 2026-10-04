package pkgServices.pkgMicrokernel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import pkgDomain.pkgEntities.clsQuestion;
import pkgDomain.pkgEntities.clsUser;
import pkgServices.pkgMicrokernel.pkgKernel.clsQuestionMicrokernel;

/**
 * Pruebas del estilo microkernel. Requiere src/main/resources en el classpath
 * (ahí está plugins.properties).
 */
public class uTestMicrokernel {

    private final clsQuestionMicrokernel attKernel = new clsQuestionMicrokernel();
    private final clsUser attUser = new clsUser();

    private clsQuestion opCreate(String prmType, String prmPath) {
        return attKernel.opCreateQuestion(prmType, "Q-1", "Pregunta", "¿Quién fue la primera programadora?",
                "Marie Curie", "Ada Lovelace", "Grace Hopper", "Margaret Hamilton",
                "Ada Lovelace", "Borrador", prmPath, attUser);
    }

    @Test
    public void cargaLosTresPluginsDesdePropertiesFile() {
        assertEquals(3, attKernel.opGetPluginCodes().size());
        assertTrue(attKernel.opHasPlugin("SELECCION_MULTIPLE"));
        assertTrue(attKernel.opHasPlugin("TIPO_CASO"));
        assertTrue(attKernel.opHasPlugin("MULTIMEDIA"));
    }

    @Test
    public void seleccionMultipleIgnoraLaImagen() {
        clsQuestion varQuestion = opCreate("SELECCION_MULTIPLE", "img.png");
        assertNotNull(varQuestion);
        assertEquals("SELECCION_MULTIPLE", varQuestion.opGetType());
        assertEquals("", varQuestion.opGetPathImagen());
    }

    @Test
    public void tipoCasoSeCreaConSuTipo() {
        clsQuestion varQuestion = opCreate("TIPO_CASO", "");
        assertNotNull(varQuestion);
        assertEquals("TIPO_CASO", varQuestion.opGetType());
    }

    @Test
    public void multimediaConservaLaImagenYLaExige() {
        clsQuestion varQuestion = opCreate("MULTIMEDIA", "img.png");
        assertNotNull(varQuestion);
        assertEquals("img.png", varQuestion.opGetPathImagen());
        assertNull(opCreate("MULTIMEDIA", ""));
    }

    @Test
    public void tipoSinPluginDevuelveNull() {
        assertNull(opCreate("NO_EXISTE", ""));
        assertNull(opCreate(null, ""));
    }
}
