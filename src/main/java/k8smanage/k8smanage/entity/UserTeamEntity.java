package k8smanage.k8smanage.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

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

    // Các thuộc tính bổ sung cho bảng trung gian
    @Column(name = "joined_at")
    LocalDateTime joinedAt;

    @Column(name = "role_in_team", length = 50)
    String roleInTeam; // Ví dụ: "MEMBER", "ADMIN", "OWNER"

    @Column(name = "is_active")
    @Builder.Default
    Boolean isActive = true;

    @OneToOne(mappedBy = "userTeam", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    UserTeamRoleEntity userTeamRole;
}
