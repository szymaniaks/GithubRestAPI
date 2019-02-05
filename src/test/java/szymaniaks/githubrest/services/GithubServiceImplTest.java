package szymaniaks.githubrest.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;
import szymaniaks.githubrest.exceptions.RepositoryNotFoundException;
import szymaniaks.githubrest.model.GithubInputDTO;
import szymaniaks.githubrest.v1.model.GithubDTO;

import java.util.Objects;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static szymaniaks.githubrest.v1.controllers.GithubControllerTest.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GithubServiceImplTest {

    @Autowired
    GithubService githubService;

    @Test
    public void shouldReturnResponseEntity() {
        //when
        ResponseEntity<GithubInputDTO> responseEntity = githubService.getRepositoryResponseEntity(USERNAME, REPOSITORY_NAME);
        //then
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getClass(), equalTo(GithubInputDTO.class));
    }

    @Test
    public void shouldReturnRepository() {
        //when
        GithubDTO repository = githubService.getRepository(USERNAME, REPOSITORY_NAME);
        //then
        assertThat(repository.getFullName(), equalTo(FULL_NAME));
        assertThat(repository.getCloneUrl(), equalTo(CLONE_URL));
    }

    @Test(expected = RepositoryNotFoundException.class)
    @Repeat(3)
    public void shouldThrowRepositoryNotFoundException() {
        //when
        githubService.getRepositoryResponseEntity(RandomStringUtils.randomAlphabetic(12), RandomStringUtils.randomAlphabetic(12));
    }
}