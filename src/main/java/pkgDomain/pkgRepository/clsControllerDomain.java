package pkgDomain.pkgRepository;

import java.util.ArrayList;
import java.util.List;
import pkgDomain.pkgEntities.clsQuestion;
import pkgDomain.pkgEntities.clsRole;
import pkgDomain.pkgEntities.clsUser;
import pkgUtil.clsBrokerCrud;

/**
 * Repositorio en memoria del dominio (usuarios, roles y preguntas).
 * Solo depende de las entidades y de utilidades genéricas: no conoce la base
 * de datos ni la interfaz gráfica.
 *
 * @author Acer3
 */
public class clsControllerDomain {

    private static clsControllerDomain attInstance;
    private final List<clsUser> attMyUsers = new ArrayList<>();
    private final List<clsRole> attMyRoles = new ArrayList<>();
    private final List<clsQuestion> attMyQuestions = new ArrayList<>();

    /* Builders */
    private clsControllerDomain() {
    }

    public static clsControllerDomain opGetInstance() {
        if (attInstance == null) {
            attInstance = new clsControllerDomain();
        }
        return attInstance;
    }

    /* Getters */
    public clsUser opGetUser(String prmOUID) {
        return clsBrokerCrud.opGetItemType(prmOUID, attMyUsers);
    }

    public clsRole opGetRole(String prmOUID) {
        return clsBrokerCrud.opGetItemType(prmOUID, attMyRoles);
    }

    public clsQuestion opGetQuestion(String prmOUID) {
        return clsBrokerCrud.opGetItemType(prmOUID, attMyQuestions);
    }

    /* List Getters */
    public List<clsUser> opGetMyUsers() {
        return attMyUsers;
    }

    public List<clsRole> opGetMyRoles() {
        return attMyRoles;
    }

    public List<clsQuestion> opGetMyQuestions() {
        return attMyQuestions;
    }

    /* Clear (se usa antes de recargar desde la base de datos) */
    public void opClear() {
        attMyUsers.clear();
        attMyRoles.clear();
        attMyQuestions.clear();
    }

    /* Update */
    public Boolean opUpdateUser(String prmOUID, String prmName, String prmDescription, String prmNickName, clsRole prmRole, Boolean prmAsset, String prmPassword) {
        clsUser varObj = opGetUser(prmOUID);
        if (varObj == null) {
            return false;
        }
        return varObj.opModify(prmName, prmDescription, prmNickName, prmRole, prmAsset, prmPassword);
    }

    public Boolean opUpdateRole(String prmOUID, String prmName, String prmDescription) {
        clsRole varObj = opGetRole(prmOUID);
        if (varObj == null) {
            return false;
        }
        return varObj.opModify(prmName, prmDescription);
    }

    public Boolean opUpdateQuestion(String prmOUID, String prmName, String prmDescription,
            String prmOptionA, String prmOptionB, String prmOptionC,
            String prmOptionD, String prmRightAnswer, String prmState, String prmType, String prmPathImagen) {
        clsQuestion varObj = opGetQuestion(prmOUID);
        if (varObj == null) {
            return false;
        }
        return varObj.opModify(prmName, prmDescription, prmOptionA, prmOptionB, prmOptionC, prmOptionD, prmRightAnswer, prmState, prmType, prmPathImagen);
    }

    /* Associate */
    private Boolean opAssociateUser(clsUser prmUser) {
        return clsBrokerCrud.opAssociateItemTo(prmUser, attMyUsers);
    }

    private Boolean opAssociateRole(clsRole prmRole) {
        return clsBrokerCrud.opAssociateItemTo(prmRole, attMyRoles);
    }

    private Boolean opAssociateQuestion(clsQuestion prmQuestion) {
        return clsBrokerCrud.opAssociateItemTo(prmQuestion, attMyQuestions);
    }

    /* Disassociate */
    private Boolean opDisassociateUser(clsUser prmUser) {
        return clsBrokerCrud.opDisassociateItemTo(prmUser, attMyUsers);
    }

    private Boolean opDisassociateRole(clsRole prmRole) {
        return clsBrokerCrud.opDisassociateItemTo(prmRole, attMyRoles);
    }

    private Boolean opDisassociateQuestion(clsQuestion prmQuestion) {
        return clsBrokerCrud.opDisassociateItemTo(prmQuestion, attMyQuestions);
    }

    /* Add (recibe objetos ya construidos, p. ej. por un plugin o por la capa de persistencia) */
    public Boolean opAddUser(clsUser prmUser) {
        if (prmUser == null || opGetUser(prmUser.opGetOUID()) != null) {
            return false;
        }
        return opAssociateUser(prmUser);
    }

    public Boolean opAddRole(clsRole prmRole) {
        if (prmRole == null || opGetRole(prmRole.opGetOUID()) != null) {
            return false;
        }
        return opAssociateRole(prmRole);
    }

    public Boolean opAddQuestion(clsQuestion prmQuestion) {
        if (prmQuestion == null || opGetQuestion(prmQuestion.opGetOUID()) != null) {
            return false;
        }
        return opAssociateQuestion(prmQuestion);
    }

    /* Register */
    public Boolean opRegisterUser(String prmOUID, String prmName, String prmDescription, String prmNickName, clsRole prmRole, Boolean prmAsset, String prmPassword) {
        return opAddUser(new clsUser(prmOUID, prmName, prmDescription, prmNickName, prmRole, prmAsset, prmPassword));
    }

    public Boolean opRegisterRole(String prmOUID, String prmName, String prmDescription) {
        return opAddRole(new clsRole(prmOUID, prmName, prmDescription));
    }

    public Boolean opRegisterQuestion(String prmOUID, String prmName, String prmDescription,
            String prmOptionA, String prmOptionB, String prmOptionC,
            String prmOptionD, String prmRightAnswer, String prmState,
            String prmType, String prmPathImagen, clsUser prmUser) {
        return opAddQuestion(new clsQuestion(prmOUID, prmName, prmDescription, prmOptionA, prmOptionB, prmOptionC, prmOptionD, prmRightAnswer, prmState, prmType, prmPathImagen, prmUser));
    }

    /* Deletes */
    public Boolean opDeleteUser(String prmOUID) {
        clsUser varObj = opGetUser(prmOUID);
        if (varObj == null || !varObj.opDie()) {
            return false;
        }
        return opDisassociateUser(varObj);
    }

    public Boolean opDeleteRole(String prmOUID) {
        clsRole varObj = opGetRole(prmOUID);
        if (varObj == null || !varObj.opDie()) {
            return false;
        }
        return opDisassociateRole(varObj);
    }

    public Boolean opDeleteQuestion(String prmOUID) {
        clsQuestion varObj = opGetQuestion(prmOUID);
        if (varObj == null || !varObj.opDie()) {
            return false;
        }
        return opDisassociateQuestion(varObj);
    }

    /* Transactions */
    public int opGetNumberUsers() {
        return attMyUsers.size();
    }

    public clsRole opGetRoleForName(String prmRoleName) {
        return clsBrokerCrud.opGetItemForName(prmRoleName, attMyRoles);
    }

    public Boolean opRegisterUserInRole(String prmOUIDUser, String prmOUIDRole) {
        clsRole varObjRole = opGetRole(prmOUIDRole);
        clsUser varObjUser = opGetUser(prmOUIDUser);
        if (varObjRole == null || varObjUser == null) {
            return false;
        }
        return varObjRole.opRegisterUserInRole(varObjUser);
    }

    public clsUser opGetUserForNickName(String prmNickName) {
        if (prmNickName == null) {
            return null;
        }
        for (clsUser varUser : attMyUsers) {
            if (varUser.opGetNickName() != null && varUser.opGetNickName().equalsIgnoreCase(prmNickName.trim())) {
                return varUser;
            }
        }
        return null;
    }

    public clsQuestion opGetQuestionForName(String prmNameQuestion) {
        return clsBrokerCrud.opGetItemForName(prmNameQuestion, attMyQuestions);
    }
}
