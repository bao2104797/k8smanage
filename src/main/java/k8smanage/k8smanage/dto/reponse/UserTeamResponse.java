package k8smanage.k8smanage.dto.reponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserTeamResponse {
    Long id;
    Long userId;
    String username;
    Long teamId;
    String teamName;
    LocalDateTime joinedAt;
    String roleInTeam;
    Boolean isActive;
}
