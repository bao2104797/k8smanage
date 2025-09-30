package k8smanage.k8smanage.service.impl;

import java.util.List;
import java.util.Optional;
import k8smanage.k8smanage.dto.reponse.RoleResponse;
import k8smanage.k8smanage.dto.request.RoleCreateRequest;
import k8smanage.k8smanage.entity.RoleEntity;
import k8smanage.k8smanage.mapper.RoleMapper;
import k8smanage.k8smanage.repository.RoleRepository;
import k8smanage.k8smanage.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleResponse createRoleFromRequest(RoleCreateRequest request) {
        // Kiểm tra trùng code
        if (roleRepository.existsByCode(request.getCode())) {
            throw new IllegalArgumentException("Role code đã tồn tại: " + request.getCode());
        }
        
        // Chuyển đổi DTO -> Entity
        RoleEntity toSave = roleMapper.toEntity(request);
        
        // Lưu vào database
        RoleEntity saved = roleRepository.save(toSave);
        
        // Chuyển đổi Entity -> Response DTO
        return roleMapper.toResponse(saved);
    }

    @Override
    public RoleEntity createRole(RoleEntity role) {
        if (roleRepository.existsByCode(role.getCode())) {
            throw new IllegalArgumentException("Role code already exists");
        }
        return roleRepository.save(role);
    }

    @Override
    public RoleEntity updateRole(Long id, RoleEntity role) {
        RoleEntity existing = roleRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Role not found"));
        existing.setCode(role.getCode());
        existing.setName(role.getName());
        return roleRepository.save(existing);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RoleEntity> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RoleEntity> getRoleByCode(String code) {
        return roleRepository.findByCode(code);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }
}


