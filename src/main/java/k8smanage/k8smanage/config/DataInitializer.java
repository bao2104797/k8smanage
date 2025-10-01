package k8smanage.k8smanage.config;

import k8smanage.k8smanage.entity.RoleEntity;
import k8smanage.k8smanage.entity.UserTeamRoleEntity;
import k8smanage.k8smanage.repository.RoleRepository;
import k8smanage.k8smanage.repository.UserTeamRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserTeamRoleRepository userTeamRoleRepository;
    private final RoleRepository roleRepository;

    public DataInitializer(UserTeamRoleRepository userTeamRoleRepository, RoleRepository roleRepository) {
        this.userTeamRoleRepository = userTeamRoleRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initializeUserTeamRoles();
        initializeRoles();
    }

    private void initializeUserTeamRoles() {
        // Khởi tạo OWNER role nếu chưa có
        if (!userTeamRoleRepository.existsByRoleName("OWNER")) {
            UserTeamRoleEntity ownerRole = UserTeamRoleEntity.builder()
                .roleName("OWNER")
                .description("Owner of the team - Full permissions")
                .build();
            userTeamRoleRepository.save(ownerRole);
            System.out.println("✓ Đã khởi tạo UserTeamRole: OWNER");
        }

        // Khởi tạo MEMBER role nếu chưa có
        if (!userTeamRoleRepository.existsByRoleName("MEMBER")) {
            UserTeamRoleEntity memberRole = UserTeamRoleEntity.builder()
                .roleName("MEMBER")
                .description("Member of the team - Basic permissions")
                .build();
            userTeamRoleRepository.save(memberRole);
            System.out.println("✓ Đã khởi tạo UserTeamRole: MEMBER");
        }
    }

    private void initializeRoles() {
        // Khởi tạo ADMIN role nếu chưa có
        if (!roleRepository.existsByCode("ADMIN")) {
            RoleEntity adminRole = RoleEntity.builder()
                .code("ADMIN")
                .name("Administrator")
                .build();
            roleRepository.save(adminRole);
            System.out.println("✓ Đã khởi tạo Role: ADMIN");
        }

        // Khởi tạo USER role nếu chưa có
        if (!roleRepository.existsByCode("USER")) {
            RoleEntity userRole = RoleEntity.builder()
                .code("USER")
                .name("User")
                .build();
            roleRepository.save(userRole);
            System.out.println("✓ Đã khởi tạo Role: USER");
        }
    }
}

