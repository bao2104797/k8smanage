package k8smanage.k8smanage.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleCreateRequest {

    @NotBlank(message = "Role code không được để trống")
    @Size(max = 100, message = "Role code không được vượt quá 100 ký tự")
    String code;

    @NotBlank(message = "Role name không được để trống")
    @Size(max = 255, message = "Role name không được vượt quá 255 ký tự")
    String name;
}
