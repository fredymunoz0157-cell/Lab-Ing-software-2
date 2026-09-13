/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgTesting;

import java.util.ArrayList;
import java.util.List;
import pkgDomain.clsController;
import pkgDomain.clsQuestion;
import pkgDomain.clsUser;
import pkgServices.pkgDataBase.clsQuestionDao;
import pkgServices.pkgDataBase.clsRoleDao;
import pkgServices.pkgDataBase.clsUserDao;
import pkgServices.pkgMicrokernel.clsQuestionMicrokernel;
import pkgServices.pkgPipeline.clsFilterQuestion;
import pkgServices.pkgPipeline.clsFilterQuestionBelongsList;
import pkgServices.pkgPipeline.clsFilterQuestionFormat;
import pkgServices.pkgPipeline.clsFilterQuestionSize;
import pkgServices.pkgPipeline.clsPipelineQuestion;

/**
 *
 * @author Acer3
 */
public class uTestServices {

//    public static void main(String[] args) {
    //// 1. Rol: Administrador
//        clsRoleDao.opSaveRole("ROL-001", "Administrador", "");
//
//// 2. Rol: Autor de preguntas
//        clsRoleDao.opSaveRole("ROL-002", "Autor de preguntas", "");
//
//// 3. Rol: Revisor
//        clsRoleDao.opSaveRole("ROL-003", "Revisor", "");
//
//// 4. Rol: Docente
//        clsRoleDao.opSaveRole("ROL-004", "Docente", "");
//
//// 5. Rol: Estudiante
//        clsRoleDao.opSaveRole("ROL-005", "Estudiante", "");
//// 6. Question: prueba
//        clsQuestionDao.opSaveQuestion(
//                "PREG-001", // prmIdQuestion
//                "Leyes de Inducción", // prmQuestionName
//                "¿Qué ley establece que la FEM inducida es proporcional a la variación del flujo magnético?", // prmQuestionDescription
//                "Ley de Ampère", // prmOptionA
//                "Ley de Faraday", // prmOptionB (Correcta)
//                "Ley de Ohm", // prmOptionC
//                "Ley de Gauss", // prmOptionD
//                "B", // prmRightAnswer
//                "ACTIVA", // prmState
//                "51412c00-91f7-454e-a6c9-aff7c1c44dd6",
//                "",
//                ""// prmIdUsuario (admin test)
//        );
//    }
        
    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println(" PATRÓN Micro-Kernel");
        System.out.println("====================================");
        System.out.println();

        clsUser varUser = new clsUser();
        clsQuestionMicrokernel varObject = new clsQuestionMicrokernel();

        System.out.println("Test Multiple Selection Question: ");
        varObject.opProcessQuestion("SELECCION_MULTIPLE", "10000", "Pre", "Una pregunta Test", "Test option 1", "Test option 2", "Test option 3", "Test option 4", "option 4", "Aprobado", "TipoTest", "AddressUrl", varUser);

        System.out.println("Test Case Question ");
        varObject.opProcessQuestion("TIPO_CASO", "10005", "Pre", "Una pregunta Test", "Test option 1", "Test option 2", "Test option 3", "Test option 4", "option 4", "Aprobado", "TipoTest", "AddressUrl", varUser);

        System.out.println("Test Multimedia Question ");
        varObject.opProcessQuestion("MULTIMEDIA", "10004", "Pre", "Una pregunta Test", "Test option 1", "Test option 2", "Test option 3", "Test option 4", "option 4", "Aprobado", "TipoTest", "AddressUrl", varUser);

        System.out.println("Test Fallo");
        varObject.opProcessQuestion(";alskjdf;alskjdf", "10009", "Pre", "Una pregunta Test", "Test option 1", "Test option 2", "Test option 3", "Test option 4", "option 4", "Aprobado", "TipoTest", "AddressUrl", varUser);

        System.out.println();
        System.out.println("====================================");
        System.out.println(" PATRÓN TUBERÍAS Y FILTROS");
        System.out.println("====================================");
        System.out.println();

        ArrayList<String> varList = new ArrayList<>();
        int varInt = 5;
        clsQuestion preguntaPrueba = new clsQuestion(
                "Q-1001",
                "Historia de la Informática",
                "¿Quién es considerada la primera programadora de la historia?",
                "Marie Curie",
                "Ada Lovelace",
                "Grace Hopper",
                "Margaret Hamilton",
                "B",
                "ACTIVO",
                "OPCION_MULTIPLE",
                "/assets/images/preguntas/ada_lovelace.png",
                varUser
        );
        

        System.out.println("Test Belongs List");
        clsFilterQuestion varBelongs = new clsFilterQuestionBelongsList(varList);
        
        
        System.out.println("Test Siize");
        clsFilterQuestion varSize = new clsFilterQuestionSize(varInt);

        System.out.println("Test Format");
        clsFilterQuestion varFormat = new clsFilterQuestionFormat();
        //clsPipelineQuestion varPipelineQuesiton = new clsPipelineQuestion<>(List.of(varFormat, varSize));
        
        

        

    }
;

}
