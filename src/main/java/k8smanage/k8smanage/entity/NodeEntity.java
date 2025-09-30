package k8smanage.k8smanage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

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

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cluster_id", nullable = false)
    ClusterEntity cluster;
}
