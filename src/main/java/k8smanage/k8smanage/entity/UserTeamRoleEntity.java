package k8smanage.k8smanage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user_team_roles")
public class UserTeamRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "role_name", nullable = false, unique = true, length = 100)
    String roleName; // OWNER, MEMBER

    @Column(name = "description", length = 500)
    String description;

    @OneToOne(mappedBy = "userTeamRole", fetch = FetchType.LAZY)
    UserTeamEntity userTeam;
}
