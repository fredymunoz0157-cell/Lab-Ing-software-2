/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appLogin;

import pkgDomain.clsController;
import pkgDomain.clsUser;
import pkgServices.pkgMicrokernel.clsQuestionMicrokernel;
import pkgUI.uiLogin;

/**
 *
 * @author Acer3
 */
public class clsMain {

    public static void main(String[] args) {
        /*clsController.opGetInstance().opRaedy();
        uiLogin varObj = new uiLogin();
        varObj.setVisible(true);*/

        clsQuestionMicrokernel varObj = new clsQuestionMicrokernel();

        // 1. Tenemos el usuario (profesor) que está creando la pregunta en el sistema
        clsUser profesor = new clsUser();

// 2. Simulamos que ya tienes instanciada la clase principal de tu núcleo
// MicrokernelPreguntas núcleoEvaluacion = new MicrokernelPreguntas();
        System.out.println("--- Guardando pregunta válida ---");

// 3. Llamamos al método pasándole el código exacto del plugin que necesitamos ("SELECCION_MULTIPLE")
        varObj.opProcessQuestion(
                "SELECCION_MULTIPLE", // prmCodePlugin: El núcleo buscará el plugin con esta llave
                "SABER-PRO-001", // prmOUID: ID único en el banco
                "Principios SOLID", // prmName: Tema de la pregunta
                "¿Qué principio dicta que una clase debe estar abierta a la extensión pero cerrada a la modificación?", // prmDescription
                "a) Responsabilidad Única", // prmOptionA
                "b) Sustitución de Liskov", // prmOptionB
                "c) Abierto/Cerrado (Open/Closed)",// prmOptionC
                "d) Inversión de Dependencias", // prmOptionD
                "c", // prmRightAnswer
                "ACTIVA", // prmState
                "TEORICA", // prmType
                "", // prmPathImagen: Vacío si no hay imagen
                profesor // prmUser: Referencia al creador
        );

        System.out.println("\n--- Probando manejo de errores ---");

// 4. ¿Qué pasa si pedimos un tipo de pregunta para el que no programamos un plugin aún?
        varObj.opProcessQuestion(
                "PREGUNTA_VIRTUAL_3D", // prmCodePlugin: Este plugin NO existe en el properties
                "SABER-PRO-002",
                "Prueba gráfica",
                "Identifique el componente en el modelo 3D.",
                "...", "...", "...", "...", "a", "ACTIVA", "GRAFICA", "/img/modelo.obj",
                profesor
        );

    }
}
