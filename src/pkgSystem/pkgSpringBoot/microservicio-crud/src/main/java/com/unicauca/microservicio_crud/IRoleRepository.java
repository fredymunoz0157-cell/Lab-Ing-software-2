package com.unicauca.microservicio_crud;

import com.unicauca.microservicio_crud.clsRoleSpringBoot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<clsRoleSpringBoot, String> {
    
}