package pkgServices.pkgMicrokernel.pkgKernel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import pkgDomain.pkgEntities.clsQuestion;
import pkgDomain.pkgEntities.clsUser;

/**
 * Núcleo del microkernel. Lee plugins.properties desde el classpath
 * (src/main/resources), instancia cada plugin por reflexión y delega en él la
 * creación de preguntas según su tipo. Para agregar un tipo nuevo basta con
 * crear la clase en pkgPlugins y registrarla en plugins.properties: el núcleo
 * no se modifica.
 *
 * @author Acer3
 */
public class clsQuestionMicrokernel {

    public static final String PLUGINS_FILE = "/plugins.properties";

    private final Map<String, IQuestionPlugin> attPluginsLoad = new LinkedHashMap<>();

    public clsQuestionMicrokernel() {
        opLoadPlugins();
    }

    private void opLoadPlugins() {
        try (InputStream varInput = clsQuestionMicrokernel.class.getResourceAsStream(PLUGINS_FILE)) {
            if (varInput == null) {
                System.err.println("No se encontró " + PLUGINS_FILE + " en el classpath.");
                return;
            }
            Properties varProp = new Properties();
            varProp.load(varInput);

            List<String> varKeys = new ArrayList<>(varProp.stringPropertyNames());
            Collections.sort(varKeys);
            for (String varKey : varKeys) {
                String varNameClass = varProp.getProperty(varKey).trim();
                try {
                    Class<?> varClass = Class.forName(varNameClass);
                    IQuestionPlugin varPlugin = (IQuestionPlugin) varClass.getDeclaredConstructor().newInstance();
                    attPluginsLoad.put(varPlugin.opGetOUID(), varPlugin);
                } catch (ReflectiveOperationException | ClassCastException e) {
                    // Un plugin defectuoso no tumba a los demás
                    System.err.println("No se pudo cargar el plugin " + varKey + " (" + varNameClass + "): " + e);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al cargar los plugins: " + e.getMessage());
        }
    }

    public boolean opHasPlugin(String prmCodePlugin) {
        return prmCodePlugin != null && attPluginsLoad.containsKey(prmCodePlugin);
    }

    public List<String> opGetPluginCodes() {
        return new ArrayList<>(attPluginsLoad.keySet());
    }

    /**
     * Crea la pregunta con el plugin correspondiente al tipo.
     *
     * @return la pregunta creada, o null si no hay plugin para ese tipo o el
     * plugin rechazó los datos.
     */
    public clsQuestion opCreateQuestion(String prmCodePlugin, String prmOUID, String prmName, String prmDescription,
            String prmOptionA, String prmOptionB, String prmOptionC, String prmOptionD,
            String prmRightAnswer, String prmState, String prmPathImagen, clsUser prmUser) {
        IQuestionPlugin varPlugin = prmCodePlugin == null ? null : attPluginsLoad.get(prmCodePlugin);
        if (varPlugin == null) {
            System.err.println("Advertencia: no existe un plugin cargado para el tipo: " + prmCodePlugin);
            return null;
        }
        return varPlugin.opCreateQuestion(prmOUID, prmName, prmDescription, prmOptionA, prmOptionB,
                prmOptionC, prmOptionD, prmRightAnswer, prmState, prmPathImagen, prmUser);
    }
}
