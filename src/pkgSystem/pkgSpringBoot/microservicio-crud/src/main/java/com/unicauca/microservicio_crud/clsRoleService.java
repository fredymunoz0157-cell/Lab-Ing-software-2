package com.unicauca.microservicio_crud;

import com.unicauca.microservicio_crud.clsRoleSpringBoot;
import com.unicauca.microservicio_crud.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class clsRoleService {
    @Autowired
    private IRoleRepository attRoleRepository;

    public List<clsRoleSpringBoot> opGetAllRoles() {
        return attRoleRepository.findAll();
    }
    
    public Optional<clsRoleSpringBoot> opGetRoleById(String id) {
        return attRoleRepository.findById(id);
    }

    public clsRoleSpringBoot opCreateRole(clsRoleSpringBoot role) {
        return attRoleRepository.save(role);
    }

    public clsRoleSpringBoot opUpdateRole(clsRoleSpringBoot role) {
        return attRoleRepository.save(role);
    }

    public void opDeleteRole(String id) {
        attRoleRepository.deleteById(id);
    }


}