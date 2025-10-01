package k8smanage.k8smanage.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NodeCreateRequest {

    @NotBlank(message = "Tên node không được để trống")
    @Size(max = 255, message = "Tên node không được vượt quá 255 ký tự")
    String name;

    @NotBlank(message = "Vai trò node không được để trống")
    @Size(max = 255, message = "Vai trò node không được vượt quá 255 ký tự")
    String type;

    @NotBlank(message = "IP không được để trống")
    @Size(max = 50, message = "IP không được vượt quá 50 ký tự")
    String ip;

    @NotNull(message = "Port không được để trống")
    Integer port;

    @Size(max = 100, message = "Username không được vượt quá 100 ký tự")
    String username;

    @Size(max = 255, message = "Password không được vượt quá 255 ký tự")
    String password;

    @Size(max = 500, message = "Mô tả không được vượt quá 500 ký tự")
    String description;

    @NotNull(message = "Cluster ID không được để trống")
    Long clusterId;
}

