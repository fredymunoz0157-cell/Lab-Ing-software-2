package com.unicauca.microservice;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service

public class clsRoleRepositoryService {

    private final IRoleRepository roleRepository;

    clsRoleRepositoryService(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<clsRoleRepository> opGetAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<clsRoleRepository> opGetRoleById(String id) {
        return roleRepository.findById(id);
    }

    public clsRoleRepository opRegisterRole(clsRoleRepository role) {
        return roleRepository.save(role);
    }

    public clsRoleRepository opUpdateRole(clsRoleRepository role) {
        return roleRepository.findById(role.opGetOUID())
                .map(existingRole -> {
                    existingRole.opModify(role.opGetName(), role.opGetDescription());
                    return roleRepository.save(existingRole);
                }).orElse(null);
    }

    public void opDeleteRole(String id) {
        roleRepository.deleteById(id);
    }

}
