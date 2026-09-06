/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgTesting;

import pkgSystem.clsQuestionDao;
import pkgSystem.clsRoleDao;

/**
 *
 * @author Acer3
 */
public class uTestSystem {

    public static void main(String[] args) {
// 1. Rol: Administrador
        clsRoleDao.opSaveRole("ROL-001", "Administrador", "");

// 2. Rol: Autor de preguntas
        clsRoleDao.opSaveRole("ROL-002", "Autor de preguntas", "");

// 3. Rol: Revisor
        clsRoleDao.opSaveRole("ROL-003", "Revisor", "");

// 4. Rol: Docente
        clsRoleDao.opSaveRole("ROL-004", "Docente", "");

// 5. Rol: Estudiante
        clsRoleDao.opSaveRole("ROL-005", "Estudiante", "");
// 6. Question: prueba
        clsQuestionDao.opSaveQuestion(
                "PREG-001", // prmIdQuestion
                "Leyes de Inducción", // prmQuestionName
                "¿Qué ley establece que la FEM inducida es proporcional a la variación del flujo magnético?", // prmQuestionDescription
                "Ley de Ampère", // prmOptionA
                "Ley de Faraday", // prmOptionB (Correcta)
                "Ley de Ohm", // prmOptionC
                "Ley de Gauss", // prmOptionD
                "B", // prmRightAnswer
                "ACTIVA", // prmState
                "51412c00-91f7-454e-a6c9-aff7c1c44dd6" // prmIdUsuario (admin test)
        );
    }
}
