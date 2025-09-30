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
public class SSHDirectCommandRequest {

    @NotBlank(message = "IP không được để trống")
    String ip;

    @NotNull(message = "Port không được để trống")
    Integer port;

    @NotBlank(message = "Username không được để trống")
    String username;

    @NotBlank(message = "Password không được để trống")
    String password;

    @NotBlank(message = "Command không được để trống")
    String command;

    Integer timeout; // Timeout in seconds, default 30s
}
