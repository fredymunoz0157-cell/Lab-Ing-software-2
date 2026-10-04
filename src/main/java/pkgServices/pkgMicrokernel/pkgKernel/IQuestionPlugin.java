package pkgServices.pkgMicrokernel.pkgKernel;

import pkgDomain.pkgEntities.clsQuestion;
import pkgDomain.pkgEntities.clsUser;

/**
 * Contrato que debe cumplir todo plugin de tipo de pregunta.
 * El núcleo solo conoce esta interfaz, nunca las clases concretas.
 *
 * @author Acer3
 */
public interface IQuestionPlugin {

    /** Código del tipo de pregunta (p. ej. "SELECCION_MULTIPLE"). También es el valor que se guarda como tipo. */
    String opGetOUID();

    clsQuestion opCreateQuestion(String prmOUID, String prmName, String prmDescription,
            String prmOptionA, String prmOptionB, String prmOptionC, String prmOptionD,
            String prmRightAnswer, String prmState, String prmPathImagen, clsUser prmUser);
}
