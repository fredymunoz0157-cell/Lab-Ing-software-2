package pkgServices.pkgMicrokernel.pkgPlugins;

import pkgDomain.pkgEntities.clsQuestion;
import pkgDomain.pkgEntities.clsUser;
import pkgServices.pkgMicrokernel.pkgKernel.IQuestionPlugin;

/**
 * Plugin para preguntas multimedia: son las únicas que conservan la ruta de
 * la imagen, y la exigen.
 *
 * @author Acer3
 */
public class clsMultimediaQuestion implements IQuestionPlugin {

    public static final String CODE = "MULTIMEDIA";

    @Override
    public String opGetOUID() {
        return CODE;
    }

    @Override
    public clsQuestion opCreateQuestion(String prmOUID, String prmName, String prmDescription,
            String prmOptionA, String prmOptionB, String prmOptionC, String prmOptionD,
            String prmRightAnswer, String prmState, String prmPathImagen, clsUser prmUser) {
        if (prmPathImagen == null || prmPathImagen.isBlank()) {
            System.err.println("Una pregunta multimedia requiere la ruta de una imagen.");
            return null;
        }
        return new clsQuestion(prmOUID, prmName, prmDescription, prmOptionA, prmOptionB, prmOptionC,
                prmOptionD, prmRightAnswer, prmState, CODE, prmPathImagen, prmUser);
    }
}
