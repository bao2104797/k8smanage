package k8smanage.k8smanage.dto.reponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NodeResponse {
    Long id;
    String name;
    String type;
    String ip;
    Integer port;
    String username;
    String status;
    String description;
    Long clusterId;
    String clusterName;
}

