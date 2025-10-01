package k8smanage.k8smanage.dto.reponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClusterResponse {
    Long id;
    String name;
    String description;
    String status;
    Long teamId;
    String teamName;
}
