package com.unicauca.microservice;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service

public class clsUserRepositoryService {

    private final IUserRepository userRepository;

    clsUserRepositoryService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<clsUserRepository> opGetAllUsers() {
        return userRepository.findAll();
    }

    public Optional<clsUserRepository> opGetUserById(String id) {
        return userRepository.findById(id);
    }

    public clsUserRepository opRegisterUser(clsUserRepository user) {
        return userRepository.save(user);
    }

    public clsUserRepository opUpdateUser(clsUserRepository user) {
        return userRepository.findById(user.opGetOUID())
                .map(existingUser -> {
                    existingUser.opModify(
                            user.opGetName(),
                            user.opGetDescription(),
                            user.opGetNickName(),
                            user.opGetPassword(),
                            user.opGetAsset(),
                            user.opGetRole()
                    );
                    return userRepository.save(existingUser);
                }).orElse(null);
    }

    public void opDeleteUser(String id) {
        userRepository.deleteById(id);
    }
}
