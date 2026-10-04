package pkgServices.pkgMicrokernel.pkgPlugins;

import pkgDomain.pkgEntities.clsQuestion;
import pkgDomain.pkgEntities.clsUser;
import pkgServices.pkgMicrokernel.pkgKernel.IQuestionPlugin;

/**
 * Plugin para preguntas tipo caso (sin imagen).
 *
 * @author Acer3
 */
public class clsCaseQuestion implements IQuestionPlugin {

    public static final String CODE = "TIPO_CASO";

    @Override
    public String opGetOUID() {
        return CODE;
    }

    @Override
    public clsQuestion opCreateQuestion(String prmOUID, String prmName, String prmDescription,
            String prmOptionA, String prmOptionB, String prmOptionC, String prmOptionD,
            String prmRightAnswer, String prmState, String prmPathImagen, clsUser prmUser) {
        return new clsQuestion(prmOUID, prmName, prmDescription, prmOptionA, prmOptionB, prmOptionC,
                prmOptionD, prmRightAnswer, prmState, CODE, "", prmUser);
    }
}
