package k8smanage.k8smanage.service.impl;

import java.util.List;
import java.util.Optional;
import k8smanage.k8smanage.entity.RoleEntity;
import k8smanage.k8smanage.entity.UserEntity;
import k8smanage.k8smanage.repository.RoleRepository;
import k8smanage.k8smanage.repository.UserRepository;
import k8smanage.k8smanage.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (user.getRole() != null && user.getRole().getId() != null) {
            RoleEntity role = roleRepository.findById(user.getRole().getId())
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
            user.setRole(role);
        }
        return userRepository.save(user);
    }

    @Override
    public UserEntity updateUser(Long id, UserEntity user) {
        UserEntity existing = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        existing.setUsername(user.getUsername());
        existing.setPassword(user.getPassword());
        existing.setFullName(user.getFullName());
        if (user.getRole() != null && user.getRole().getId() != null) {
            RoleEntity role = roleRepository.findById(user.getRole().getId())
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
            existing.setRole(role);
        }
        return userRepository.save(existing);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}


