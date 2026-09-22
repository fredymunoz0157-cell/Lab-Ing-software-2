package com.unicauca.microservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuestionRepository extends JpaRepository<clsQuestionRepository, String> {

}
