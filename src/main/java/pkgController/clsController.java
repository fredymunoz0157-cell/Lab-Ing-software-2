package pkgController;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import pkgDomain.pkgEntities.clsQuestion;
import pkgDomain.pkgEntities.clsRole;
import pkgDomain.pkgEntities.clsUser;
import pkgDomain.pkgRepository.clsControllerDomain;
import pkgPersistence.clsBoardDao;
import pkgPersistence.clsLoadDao;
import pkgPersistence.clsQuestionDao;
import pkgPersistence.clsRoleDao;
import pkgPersistence.clsUserDao;
import pkgServices.pkgMicrokernel.pkgKernel.clsQuestionMicrokernel;
import pkgServices.pkgPipeline.clsPipelineFactory;
import pkgServices.pkgPipeline.clsPipelineOption;
import pkgServices.pkgPipeline.clsPipelineText;
import pkgServices.pkgSecurity.clsSecurityUtils;

/**
 * Fachada de la aplicación (casos de uso). Es la única puerta de entrada que
 * usa la capa de presentación y la que coordina:
 * <ul>
 * <li>validación con el pipeline (pipe and filter),</li>
 * <li>creación de preguntas con el microkernel,</li>
 * <li>persistencia (DAO) y repositorio en memoria (dominio).</li>
 * </ul>
 *
 * @author Acer3
 */
public class clsController {

    /* Roles por defecto: se crean si la base de datos no tiene ninguno. */
    public static final String ROLE_ADMIN = "Administrador";
    public static final String ROLE_AUTHOR = "Autor de preguntas";
    public static final String ROLE_REVIEWER = "Revisor";
    public static final String ROLE_TEACHER = "Docente";
    public static final String ROLE_STUDENT = "Estudiante";

    private static clsController attInstance;

    private final clsControllerDomain attRepository = clsControllerDomain.opGetInstance();
    private final clsQuestionMicrokernel attMicrokernel = new clsQuestionMicrokernel();

    /* Tuberías de validación (pipe and filter) */
    private final clsPipelineText attPipelineRequired = clsPipelineFactory.opCreateRequiredText();
    private final clsPipelineText attPipelinePassword = clsPipelineFactory.opCreatePassword();
    private final clsPipelineText attPipelineQuestionText = clsPipelineFactory.opCreateQuestionText();
    private final clsPipelineOption attPipelineOptions = clsPipelineFactory.opCreateOptions();
    private final clsPipelineText attPipelineState = clsPipelineFactory.opCreateQuestionState();

    private clsUser attUserLogin = new clsUser();

    private clsController() {
    }

    public static clsController opGetInstance() {
        if (attInstance == null) {
            attInstance = new clsController();
        }
        return attInstance;
    }

    /* ===================== Arranque ===================== */

    /** Crea las tablas si no existen y carga la base de datos en memoria. */
    public void opReady() {
        try {
            clsBoardDao.opCreateBoardRole();
            clsBoardDao.opCreateBoardUser();
            clsBoardDao.opCreateBoardQuestion();

            List<clsRole> varRoles = clsLoadDao.opLoadRoles();
            if (varRoles.isEmpty()) {
                opSeedDefaultRoles();
                varRoles = clsLoadDao.opLoadRoles();
            }
            List<clsUser> varUsers = clsLoadDao.opLoadUsers(varRoles);
            List<clsQuestion> varQuestions = clsLoadDao.opLoadQuestions(varUsers);

            attRepository.opClear();
            varRoles.forEach(attRepository::opAddRole);
            for (clsUser varUser : varUsers) {
                attRepository.opAddUser(varUser);
                if (varUser.opGetRole() != null) {
                    attRepository.opRegisterUserInRole(varUser.opGetOUID(), varUser.opGetRole().opGetOUID());
                }
            }
            varQuestions.forEach(attRepository::opAddQuestion);
        } catch (SQLException e) {
            System.err.println("Error al preparar la base de datos: " + e.getMessage());
        }
    }

    private void opSeedDefaultRoles() {
        clsRoleDao.opSaveRole("ROL-001", ROLE_ADMIN, "");
        clsRoleDao.opSaveRole("ROL-002", ROLE_AUTHOR, "");
        clsRoleDao.opSaveRole("ROL-003", ROLE_REVIEWER, "");
        clsRoleDao.opSaveRole("ROL-004", ROLE_TEACHER, "");
        clsRoleDao.opSaveRole("ROL-005", ROLE_STUDENT, "");
    }

    /* ===================== Sesión ===================== */

    public clsUser opGetUserLogin() {
        return attUserLogin;
    }

    public String opGetRoleUserLogin() {
        if (attUserLogin == null || attUserLogin.opGetRole() == null) {
            return "";
        }
        String varName = attUserLogin.opGetRole().opGetName();
        return varName == null ? "" : varName;
    }

    public void opUpdateUserLogin(clsUser prmUser) {
        attUserLogin = prmUser;
    }

    /* ===================== Consultas para la UI ===================== */

    public List<clsRole> opGetRoles() {
        return attRepository.opGetMyRoles();
    }

    public List<clsQuestion> opGetQuestions() {
        return attRepository.opGetMyQuestions();
    }

    public clsQuestion opGetQuestionForName(String prmName) {
        return attRepository.opGetQuestionForName(prmName);
    }

    public List<String> opGetQuestionStates() {
        return clsPipelineFactory.QUESTION_STATES;
    }

    /** Tipos de pregunta disponibles = plugins cargados por el microkernel. */
    public List<String> opGetQuestionTypes() {
        return attMicrokernel.opGetPluginCodes();
    }

    /* ===================== Usuarios ===================== */

    public Boolean opValidateRegister(String prmName, String prmNickName, String prmPassword) {
        return attPipelineRequired.opExecuteFilters(prmName)
                && attPipelineRequired.opExecuteFilters(prmNickName)
                && attPipelinePassword.opExecuteFilters(prmPassword);
    }

    public Boolean opRegisterUser(String prmName, String prmNickName, String prmRole, String prmPassword) {
        if (!opValidateRegister(prmName, prmNickName, prmPassword)) {
            return false;
        }
        clsRole varObjRole = attRepository.opGetRoleForName(prmRole);
        if (varObjRole == null) {
            return false;
        }
        if (attRepository.opGetUserForNickName(prmNickName) != null) {
            return false; // nickname ya registrado
        }
        String varOUID = UUID.randomUUID().toString();
        String varHashedPassword = clsSecurityUtils.opHashPassword(prmPassword);
        if (!clsUserDao.opSaveUser(varOUID, prmName, "", prmNickName, varObjRole.opGetOUID(), true, varHashedPassword)) {
            return false;
        }
        attRepository.opRegisterUser(varOUID, prmName, "", prmNickName, varObjRole, true, varHashedPassword);
        return attRepository.opRegisterUserInRole(varOUID, varObjRole.opGetOUID());
    }

    public Boolean opValidateLogin(String prmNickName, String prmPassword) {
        return attPipelineRequired.opExecuteFilters(prmNickName)
                && attPipelinePassword.opExecuteFilters(prmPassword);
    }

    public Boolean opLoginUser(String prmNickName, String prmPassword) {
        try {
            clsUser varObj = attRepository.opGetUserForNickName(prmNickName);
            if (varObj != null && clsSecurityUtils.opCheckPassword(prmPassword, varObj.opGetPassword())) {
                // Solo se guarda la sesión si la contraseña es correcta
                attUserLogin = varObj;
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean opShowUIQuestions() {
        String varRole = opGetRoleUserLogin();
        return varRole.equalsIgnoreCase(ROLE_ADMIN)
                || varRole.equalsIgnoreCase(ROLE_AUTHOR)
                || varRole.equalsIgnoreCase(ROLE_REVIEWER);
    }

    /* ===================== Preguntas ===================== */

    /** Pasa los datos de una pregunta por las tuberías de validación. */
    public Boolean opValidateQuestion(String prmName, String prmDescription,
            String prmOptionA, String prmOptionB, String prmOptionC, String prmOptionD,
            String prmRightAnswer, String prmState) {
        return attPipelineRequired.opExecuteFilters(prmName)
                && attPipelineQuestionText.opExecuteFilters(prmDescription)
                && attPipelineOptions.opExecuteFilters(prmOptionA, prmOptionB, prmOptionC, prmOptionD)
                && clsPipelineFactory.opCreateRightAnswer(prmOptionA, prmOptionB, prmOptionC, prmOptionD)
                        .opExecuteFilters(prmRightAnswer)
                && attPipelineState.opExecuteFilters(prmState);
    }

    /**
     * Registra una pregunta nueva a nombre del usuario que inició sesión.
     * Flujo: pipeline (validar) → microkernel (crear según el tipo) → DAO
     * (guardar) → repositorio (memoria).
     *
     * @param prmType código del plugin: SELECCION_MULTIPLE, TIPO_CASO, MULTIMEDIA...
     */
    public Boolean opRegisterQuestion(String prmName, String prmDescription,
            String prmOptionA, String prmOptionB, String prmOptionC, String prmOptionD,
            String prmRightAnswer, String prmState, String prmType, String prmPathImagen) {
        if (attUserLogin == null || attUserLogin.opGetOUID() == null) {
            return false; // no hay sesión iniciada
        }
        if (!opValidateQuestion(prmName, prmDescription, prmOptionA, prmOptionB, prmOptionC, prmOptionD, prmRightAnswer, prmState)) {
            return false;
        }
        if (attRepository.opGetQuestionForName(prmName) != null) {
            return false; // ya existe una pregunta con ese nombre
        }
        clsQuestion varQuestion = attMicrokernel.opCreateQuestion(prmType, UUID.randomUUID().toString(),
                prmName, prmDescription, prmOptionA, prmOptionB, prmOptionC, prmOptionD,
                prmRightAnswer, prmState, prmPathImagen, attUserLogin);
        if (varQuestion == null) {
            return false; // tipo sin plugin o datos rechazados por el plugin
        }
        if (!clsQuestionDao.opSaveQuestion(varQuestion.opGetOUID(), varQuestion.opGetName(), varQuestion.opGetDescription(),
                varQuestion.opGetOptionA(), varQuestion.opGetOptionB(), varQuestion.opGetOptionC(), varQuestion.opGetOptionD(),
                varQuestion.opGetRightAnswer(), varQuestion.opGetState(), varQuestion.opGetType(), varQuestion.opGetPathImagen(),
                attUserLogin.opGetOUID())) {
            return false;
        }
        return attRepository.opAddQuestion(varQuestion);
    }

    public Boolean opUpdateQuestion(
            String prmIdQuestion,
            String prmQuestionName,
            String prmQuestionDescription,
            String prmOptionA,
            String prmOptionB,
            String prmOptionC,
            String prmOptionD,
            String prmRightAnswer,
            String prmState,
            String prmType,
            String prmPathImagen) {
        // Se busca por ID (antes se buscaba por nombre y fallaba si se renombraba)
        clsQuestion varObjQuestion = attRepository.opGetQuestion(prmIdQuestion);
        if (varObjQuestion == null) {
            return false;
        }
        if (!opValidateQuestion(prmQuestionName, prmQuestionDescription, prmOptionA, prmOptionB, prmOptionC, prmOptionD, prmRightAnswer, prmState)) {
            return false;
        }
        boolean varSameType = prmType != null && prmType.equals(varObjQuestion.opGetType());
        if (!varSameType && !attMicrokernel.opHasPlugin(prmType)) {
            return false; // tipo de pregunta desconocido
        }
        clsQuestion varSameName = attRepository.opGetQuestionForName(prmQuestionName);
        if (varSameName != null && varSameName != varObjQuestion) {
            return false; // otro registro ya usa ese nombre
        }
        // Se conserva el autor original (antes se reemplazaba por quien editaba)
        String varOUIDAuthor = varObjQuestion.opGetUser() != null
                ? varObjQuestion.opGetUser().opGetOUID()
                : attUserLogin.opGetOUID();
        if (!clsQuestionDao.opUpdateQuestion(prmIdQuestion, prmQuestionName, prmQuestionDescription, prmOptionA, prmOptionB,
                prmOptionC, prmOptionD, prmRightAnswer, prmState, prmType, prmPathImagen, varOUIDAuthor)) {
            return false;
        }
        return varObjQuestion.opModify(prmQuestionName, prmQuestionDescription, prmOptionA, prmOptionB, prmOptionC,
                prmOptionD, prmRightAnswer, prmState, prmType, prmPathImagen);
    }
}
