package k8smanage.k8smanage.dto.reponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SSHCommandResponse {
    Boolean success;
    String output;
    String error;
    Integer exitCode;
    Long executionTimeMs;
    String message;
}
