package pkgDomain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import pkgDomain.pkgEntities.clsRole;
import pkgDomain.pkgRepository.clsControllerDomain;

/**
 * Pruebas del dominio y del repositorio en memoria.
 */
public class uTestDomain {

    private final clsControllerDomain attRepository = clsControllerDomain.opGetInstance();

    @Before
    public void opSetUp() {
        attRepository.opClear();
    }

    @Test
    public void modificarGuardaNombreYDescripcion() {
        clsRole varRole = new clsRole("R1", "Nombre", "Desc");
        varRole.opModify("Nuevo", "Nueva desc");
        assertEquals("Nuevo", varRole.opGetName());
        assertEquals("Nueva desc", varRole.opGetDescription());
    }

    @Test
    public void registrarUsuarioEnRolInexistenteNoRevienta() {
        attRepository.opRegisterRole("R1", "Admin", "");
        attRepository.opRegisterUser("U1", "Ana", "", "ana", null, true, "x");
        assertFalse(attRepository.opRegisterUserInRole("U1", "NO_EXISTE"));
        assertFalse(attRepository.opRegisterUserInRole("NO_EXISTE", "R1"));
        assertTrue(attRepository.opRegisterUserInRole("U1", "R1"));
    }

    @Test
    public void noSeRegistranDuplicadosPorId() {
        assertTrue(attRepository.opRegisterRole("R1", "Admin", ""));
        assertFalse(attRepository.opRegisterRole("R1", "Otro", ""));
        assertEquals(1, attRepository.opGetMyRoles().size());
    }

    @Test
    public void buscarUsuarioPorNicknameIgnoraMayusculas() {
        attRepository.opRegisterUser("U1", "Ana", "", "ana", null, true, "x");
        assertNotNull(attRepository.opGetUserForNickName(" ANA "));
    }

    @Test
    public void eliminarPreguntaUsaSuPropiaColeccion() {
        attRepository.opRegisterQuestion("Q1", "P", "Desc", "a", "b", "c", "d", "a", "Borrador", "TIPO_CASO", "", null);
        assertTrue(attRepository.opDeleteQuestion("Q1"));
        assertEquals(0, attRepository.opGetMyQuestions().size());
    }
}
