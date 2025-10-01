package k8smanage.k8smanage.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user_teams")
public class UserTeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    TeamEntity team;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_team_role_id", nullable = false, unique = true)
    UserTeamRoleEntity userTeamRole;

    @Column(name = "is_active")
    @Builder.Default
    Boolean isActive = true;
}
