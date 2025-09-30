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
public class SSHCommandRequest {

    @NotNull(message = "Node ID không được để trống")
    Long nodeId;

    @NotBlank(message = "Command không được để trống")
    String command;

    Integer timeout; // Timeout in seconds, default 30s
}
