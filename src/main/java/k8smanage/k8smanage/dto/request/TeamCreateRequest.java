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
public class TeamCreateRequest {

    @NotBlank(message = "Tên team không được để trống")
    @Size(max = 255, message = "Tên team không được vượt quá 255 ký tự")
    String name;

    @Size(max = 500, message = "Mô tả không được vượt quá 500 ký tự")
    String description;

    @NotNull(message = "User ID không được để trống")
    Long userId; // User tạo team sẽ là owner
}
