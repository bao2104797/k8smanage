package k8smanage.k8smanage.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserTeamCreateRequest {

    @NotNull(message = "User ID không được để trống")
    Long userId;

    @NotNull(message = "Team ID không được để trống")
    Long teamId;

    @NotBlank(message = "Role name không được để trống")
    String roleName; // OWNER, MEMBER

    Boolean isActive;
}
