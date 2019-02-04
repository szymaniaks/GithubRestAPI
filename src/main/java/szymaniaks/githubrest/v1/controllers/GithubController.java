package szymaniaks.githubrest.v1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import szymaniaks.githubrest.services.GithubService;
import szymaniaks.githubrest.v1.model.GithubDTO;

@RestController
@RequestMapping("api/v1/repositories")
public class GithubController {

    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{owner}/{repositoryName}",
            produces = {MediaType.APPLICATION_XML_VALUE}
    )
    public GithubDTO getRepositoryAsXML(@PathVariable String owner, @PathVariable String repositoryName){
        return githubService.getRepository(owner, repositoryName);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{owner}/{repositoryName}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public GithubDTO getRepositoryAsJSON(@PathVariable String owner, @PathVariable String repositoryName){
        return githubService.getRepository(owner, repositoryName);
    }
}
