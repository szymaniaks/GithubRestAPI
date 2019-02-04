package szymaniaks.githubrest.services;

import org.springframework.http.ResponseEntity;
import szymaniaks.githubrest.model.GithubInputDTO;
import szymaniaks.githubrest.v1.model.GithubDTO;

public interface GithubService {
    ResponseEntity<GithubInputDTO> getRepositoryResponseEntity(String username, String repositoryName);
    GithubDTO getRepository(String username, String repositoryName);
}
