package pkgServices.pkgPipeline;

import java.util.List;
import pkgServices.pkgPipeline.pkgFilters.clsFilterBelongsList;
import pkgServices.pkgPipeline.pkgFilters.clsFilterMinLength;
import pkgServices.pkgPipeline.pkgFilters.clsFilterNotBlank;
import pkgServices.pkgPipeline.pkgFilters.clsFilterOptionDuplicate;
import pkgServices.pkgPipeline.pkgFilters.clsFilterOptionNotBlank;
import pkgServices.pkgPipeline.pkgFilters.clsFilterRegex;

/**
 * Arma las tuberías de validación de la aplicación. Aquí quedan todas las
 * reglas de validación en un solo lugar (reemplaza a clsValidate).
 *
 * @author santi
 */
public final class clsPipelineFactory {

    /** Mínimo 6 caracteres, al menos una mayúscula, un dígito y un carácter especial. */
    public static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{6,}$";

    /** Longitud mínima del enunciado de una pregunta. */
    public static final int QUESTION_MIN_LENGTH = 5;

    /** Estados válidos del ciclo de vida de una pregunta. */
    public static final List<String> QUESTION_STATES = List.of(
            "Borrador",
            "Pendiente de revisión",
            "En revisión",
            "Aprobada",
            "Rechazada",
            "Publicada",
            "Archivada");

    private clsPipelineFactory() {
    }

    /** Nombre completo, nickname, nombre de pregunta: obligatorio. */
    public static clsPipelineText opCreateRequiredText() {
        return new clsPipelineText(List.of(new clsFilterNotBlank()));
    }

    /** Contraseña: obligatoria y con el formato de {@link #PASSWORD_REGEX}. */
    public static clsPipelineText opCreatePassword() {
        return new clsPipelineText(List.of(
                new clsFilterNotBlank(),
                new clsFilterRegex(PASSWORD_REGEX)));
    }

    /** Enunciado de la pregunta: obligatorio y con longitud mínima. */
    public static clsPipelineText opCreateQuestionText() {
        return new clsPipelineText(List.of(
                new clsFilterNotBlank(),
                new clsFilterMinLength(QUESTION_MIN_LENGTH)));
    }

    /** Opciones: las cuatro con contenido y sin repetirse. */
    public static clsPipelineOption opCreateOptions() {
        return new clsPipelineOption(List.of(
                new clsFilterOptionNotBlank(),
                new clsFilterOptionDuplicate()));
    }

    /** Respuesta correcta: debe ser exactamente el texto de una de las opciones. */
    public static clsPipelineText opCreateRightAnswer(String prmOptionA, String prmOptionB, String prmOptionC, String prmOptionD) {
        return new clsPipelineText(List.of(
                new clsFilterNotBlank(),
                new clsFilterBelongsList(List.of(
                        opNullToEmpty(prmOptionA), opNullToEmpty(prmOptionB),
                        opNullToEmpty(prmOptionC), opNullToEmpty(prmOptionD)))));
    }

    /** Estado: debe ser uno de {@link #QUESTION_STATES}. */
    public static clsPipelineText opCreateQuestionState() {
        return new clsPipelineText(List.of(new clsFilterBelongsList(QUESTION_STATES)));
    }

    private static String opNullToEmpty(String prmValue) {
        return prmValue == null ? "" : prmValue;
    }
}
