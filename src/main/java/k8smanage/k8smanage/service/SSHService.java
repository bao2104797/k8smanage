package k8smanage.k8smanage.service;

import k8smanage.k8smanage.dto.reponse.SSHCommandResponse;
import k8smanage.k8smanage.dto.reponse.SSHDirectCommandResponse;
import k8smanage.k8smanage.dto.request.SSHCommandRequest;
import k8smanage.k8smanage.dto.request.SSHConnectionRequest;
import k8smanage.k8smanage.dto.request.SSHDirectCommandRequest;

public interface SSHService {
    SSHCommandResponse executeCommand(SSHCommandRequest request);
    SSHCommandResponse testConnection(Long nodeId);
    SSHCommandResponse testConnectionDirect(SSHConnectionRequest request);
    SSHDirectCommandResponse executeDirectCommand(SSHDirectCommandRequest request);
}
