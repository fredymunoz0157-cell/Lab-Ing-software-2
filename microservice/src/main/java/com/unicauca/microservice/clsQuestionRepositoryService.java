package com.unicauca.microservice;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service

public class clsQuestionRepositoryService {
    
    private final IQuestionRepository questionRepository;

    clsQuestionRepositoryService(IQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<clsQuestionRepository> opGetAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<clsQuestionRepository> opGetQuestionById(String id) {
        return questionRepository.findById(id);
    }

    public clsQuestionRepository opRegisterQuestion(clsQuestionRepository question) {
        return questionRepository.save(question);
    }

    public clsQuestionRepository opUpdateQuestion(clsQuestionRepository question) {
        return questionRepository.findById(question.opGetOUID())
                .map(existingQuestion -> {
                    existingQuestion.opModify(
                            question.opGetName(),
                            question.opGetDescription(),
                            question.opGetOptionA(),
                            question.opGetOptionB(),
                            question.opGetOptionC(),
                            question.opGetOptionD(),
                            question.opGetRightAnswer(),
                            question.opGetState(),
                            question.opGetType(),
                            question.opGetPathImagen(),
                            question.opGetUser()
                    );
                    return questionRepository.save(existingQuestion);
                }).orElse(null);
    }

    public void opDeleteQuestion(String id) {
        questionRepository.deleteById(id);
    }
}
