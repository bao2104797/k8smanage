package k8smanage.k8smanage.dto.reponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginResponse {
    Long userId;
    String username;
    String fullName;
    RoleResponse role;
    String message;
}
