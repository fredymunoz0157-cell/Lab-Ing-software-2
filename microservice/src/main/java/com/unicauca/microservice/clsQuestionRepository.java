package com.unicauca.microservice;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_question")
public class clsQuestionRepository {
    
    @Id
    @Column(name = "id_question")
    private String attOUID;

    @Column(name = "question_name")
    private String attName;

    @Column(name = "question_description")
    private String attDescription;

    @Column(name = "optionA")
    private String attOptionA;

    @Column(name = "optionB")
    private String attOptionB;

    @Column(name = "optionC")
    private String attOptionC;

    @Column(name = "optionD")
    private String attOptionD;

    @Column(name = "rightAnswer")
    private String attRightAnswer;

    @Column(name = "state")
    private String attState;

    @Column(name = "type_question")
    private String attType;

    @Column(name = "path_imagen")
    private String attPathImagen;

    @Column(name = "id_usuario")
    private String attUser;

    public clsQuestionRepository() {
    }

    public clsQuestionRepository(String prmOUID, String prmName, String prmDescription, String prmOptionA, String prmOptionB, String prmOptionC, String prmOptionD, String prmRightAnswer, String prmState, String prmType, String prmPathImagen, String prmUser) {
        attOUID = prmOUID;
        attName = prmName;
        attDescription = prmDescription;
        attOptionA = prmOptionA;
        attOptionB = prmOptionB;
        attOptionC = prmOptionC;
        attOptionD = prmOptionD;
        attRightAnswer = prmRightAnswer;
        attState = prmState;
        attType = prmType;
        attPathImagen = prmPathImagen;
        attUser = prmUser;
    }

    public String opGetOUID() {
        return attOUID;
    }

    public String opGetName() {
        return attName;
    }

    public String opGetDescription() {
        return attDescription;
    }

    public String opGetOptionA() {
        return attOptionA;
    }

    public String opGetOptionB() {
        return attOptionB;
    }

    public String opGetOptionC() {
        return attOptionC;
    }

    public String opGetOptionD() {
        return attOptionD;
    }

    public String opGetRightAnswer() {
        return attRightAnswer;
    }

    public String opGetState() {
        return attState;
    }

    public String opGetType() {
        return attType;
    }

    public String opGetPathImagen() {
        return attPathImagen;
    }

    public String opGetUser() {
        return attUser;
    }

    public Boolean opModify(
            String prmQuestionName,
            String prmQuestionDescription,
            String prmOptionA,
            String prmOptionB,
            String prmOptionC,
            String prmOptionD,
            String prmRightAnswer,
            String prmState,
            String prmType,
            String prmPathImagen,
            String prmIdUsuario) {
        attName = prmQuestionName;
        attDescription = prmQuestionDescription;
        attOptionA = prmOptionA;
        attOptionB = prmOptionB;
        attOptionC = prmOptionC;
        attOptionD = prmOptionD;
        attRightAnswer = prmRightAnswer;
        attState = prmState;
        attType = prmType;
        attPathImagen = prmPathImagen;
        attUser = prmIdUsuario;
        return true;
    }
}
