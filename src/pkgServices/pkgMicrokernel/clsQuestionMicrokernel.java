/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgServices.pkgMicrokernel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import pkgDomain.clsQuestion;
import pkgDomain.clsUser;

/**
 *
 * @author Acer3
 */
public class clsQuestionMicrokernel {
    
    private Map<String, clsQuestion> attBankQuestions = new HashMap<>();
    
    private Map <String, IQuestionPlugin> attPluginsLoad = new HashMap<>();
    
    public clsQuestionMicrokernel (){
        opLoadQuestion();
    }
    
    private void opLoadQuestion (){
        try (InputStream varInput = new FileInputStream("plugins.properties")) {
            Properties varProp = new Properties();
            varProp.load(varInput);
            
            for(String varOUID : varProp.stringPropertyNames()){
                String varNameClass = varProp.getProperty(varOUID);
                
                Class<?> varClass = Class.forName(varNameClass);
                
                IQuestionPlugin varPlugin = (IQuestionPlugin) varClass.getDeclaredConstructor().newInstance();
                
                attPluginsLoad.put(varPlugin.opGetOUID(), varPlugin);
                
                System.out.println("Plugin cargado con éxito: " + varPlugin.opGetOUID());
            }
        } catch (Exception e) {
            System.out.println("Error al cargar los plugins: " + e.getMessage());
        }
    }
    
    public void opProcessQuestion (String prmCodePlugin, String prmOUID, String prmName, String prmDescription,String prmOptionA, String prmOptionB, String prmOptionC, String prmOptionD, String prmRightAnswer, String prmState,String prmType, String prmPathImagen, clsUser prmUser){
        IQuestionPlugin varPlugin = attPluginsLoad.get(prmCodePlugin);
        if (varPlugin!=null) {
            clsQuestion varObj = varPlugin.opCreateQuestion(prmOUID, prmName, prmDescription, prmOptionA, prmOptionB, prmOptionC, prmOptionD, prmRightAnswer, prmState, prmType, prmPathImagen, prmUser);
            attBankQuestions.put(prmOUID, varObj);
            System.out.println("Pregunta " + prmOUID + " agregada al banco.");
        }else{
            System.out.println("Advertencia: No existe un plugin cargado para el código: " + prmCodePlugin);
        }
    }
}
