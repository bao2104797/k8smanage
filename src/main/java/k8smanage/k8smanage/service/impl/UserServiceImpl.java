package k8smanage.k8smanage.service.impl;

import java.util.List;
import java.util.Optional;
import k8smanage.k8smanage.dto.reponse.LoginResponse;
import k8smanage.k8smanage.dto.reponse.UserResponse;
import k8smanage.k8smanage.dto.request.LoginRequest;
import k8smanage.k8smanage.dto.request.UserCreateRequest;
import k8smanage.k8smanage.entity.RoleEntity;
import k8smanage.k8smanage.entity.UserEntity;
import k8smanage.k8smanage.mapper.UserMapper;
import k8smanage.k8smanage.repository.RoleRepository;
import k8smanage.k8smanage.repository.UserRepository;
import k8smanage.k8smanage.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse createUserFromRequest(UserCreateRequest request) {
        // Kiểm tra trùng username
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username đã tồn tại: " + request.getUsername());
        }
        
        // Chuyển đổi DTO -> Entity
        UserEntity toSave = userMapper.toEntity(request);
        
        // Mã hóa password trước khi lưu
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        toSave.setPassword(encodedPassword);
        
        // Xử lý role nếu có
        if (request.getRoleId() != null) {
            RoleEntity role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Role không tồn tại với ID: " + request.getRoleId()));
            toSave.setRole(role);
        }
        
        // Lưu vào database
        UserEntity saved = userRepository.save(toSave);
        
        // Chuyển đổi Entity -> Response DTO
        return userMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        // Tìm user theo username
        UserEntity user = userRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> new IllegalArgumentException("Username hoặc password không đúng"));
        
        // So sánh password đã mã hóa
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Username hoặc password không đúng");
        }
        
        // Chuyển đổi Entity -> LoginResponse
        return LoginResponse.builder()
            .userId(user.getId())
            .username(user.getUsername())
            .fullName(user.getFullName())
            .role(user.getRole() != null ? userMapper.toResponse(user).getRole() : null)
            .message("Đăng nhập thành công")
            .build();
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


