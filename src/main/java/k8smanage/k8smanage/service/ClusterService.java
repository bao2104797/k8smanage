package k8smanage.k8smanage.service;

import k8smanage.k8smanage.dto.reponse.ClusterResponse;
import k8smanage.k8smanage.dto.request.ClusterCreateRequest;

public interface ClusterService {
    ClusterResponse createClusterFromRequest(ClusterCreateRequest request);
}
