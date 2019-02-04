package szymaniaks.githubrest.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import szymaniaks.githubrest.exceptions.RepositoryNotFoundException;
import szymaniaks.githubrest.model.GithubInputDTO;
import szymaniaks.githubrest.v1.mapper.GithubDTOMapper;
import szymaniaks.githubrest.v1.model.GithubDTO;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class GithubServiceImpl implements GithubService {

    private final RestTemplate restTemplate;
    private final GithubDTOMapper githubDTOMapper;
    @Value("${api.github.link}")
    private String GITHUB_API_URL;
    @Value("${api.github.mediaType}")
    private String GITHUB_MEDIA_TYPE;
    private final String USERNAME = "username";
    private final String REPOSITORY_NAME ="repositoryName";

    public GithubServiceImpl(RestTemplate restTemplate, GithubDTOMapper githubDTOMapper) {
        this.restTemplate = restTemplate;
        this.githubDTOMapper = githubDTOMapper;
    }

    @Override
    public ResponseEntity<GithubInputDTO> getRepositoryResponseEntity(String username, String repositoryName){
        Map<String,String> urlVariables = new HashMap<>();
        urlVariables.put(USERNAME, username);
        urlVariables.put(REPOSITORY_NAME, repositoryName);
        ResponseEntity<GithubInputDTO> result = null;
        try{
            result = restTemplate.exchange(GITHUB_API_URL, HttpMethod.GET, createHttpEntityWithHeaders(), GithubInputDTO.class, urlVariables);
        } catch (HttpClientErrorException ex){
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new RepositoryNotFoundException("Repository not found");
            }
        }

        return result;
    }
    @Override
    public GithubDTO getRepository(String username, String repositoryName){
        GithubInputDTO githubInputDTO = getRepositoryResponseEntity(username, repositoryName).getBody();
        return githubDTOMapper.githubInputDTOToGithubDTO(githubInputDTO);
    }

    private HttpEntity<String> createHttpEntityWithHeaders(){
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = MediaType.valueOf(GITHUB_MEDIA_TYPE);
        headers.setAccept(Collections.singletonList(mediaType));
        return new HttpEntity<>("parameters", headers);
    }
}
