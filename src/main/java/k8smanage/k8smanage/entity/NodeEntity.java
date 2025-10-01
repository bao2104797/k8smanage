package k8smanage.k8smanage.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "node")
public class NodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false, length = 255)
    String name;

    @Column(name = "type", nullable = false, length = 255)
    String type; // MASTER, WORKER

    @Column(name = "ip", nullable = false, length = 50)
    String ip;

    @Column(name = "port", nullable = false)
    Integer port;

    @Column(name = "username", length = 100)
    String username;

    @Column(name = "password", length = 255)
    String password;

    @Column(name = "status", length = 50)
    String status; // ACTIVE, INACTIVE, MAINTENANCE

    @Column(name = "description", length = 500)
    String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cluster_id", nullable = false)
    ClusterEntity cluster;
}
