package k8smanage.k8smanage.service;

import k8smanage.k8smanage.dto.reponse.NodeResponse;
import k8smanage.k8smanage.dto.request.NodeCreateRequest;

public interface NodeService {
    NodeResponse createNodeFromRequest(NodeCreateRequest request);
}
