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
public class UserCreateRequest {

    @NotBlank(message = "Username không được để trống")
    @Size(max = 150, message = "Username không được vượt quá 150 ký tự")
    String username;

    @NotBlank(message = "Password không được để trống")
    @Size(min = 6, message = "Password phải có ít nhất 6 ký tự")
    String password;

    @Size(max = 255, message = "Full name không được vượt quá 255 ký tự")
    String fullName;

    Long roleId;
}
