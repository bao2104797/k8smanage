package k8smanage.k8smanage.service;

import java.util.List;
import java.util.Optional;
import k8smanage.k8smanage.entity.UserEntity;

public interface UserService {
    UserEntity createUser(UserEntity user);
    UserEntity updateUser(Long id, UserEntity user);
    void deleteUser(Long id);
    Optional<UserEntity> getUserById(Long id);
    Optional<UserEntity> getUserByUsername(String username);
    List<UserEntity> getAllUsers();
}


