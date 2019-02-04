package szymaniaks.githubrest.v1.controllers;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import szymaniaks.githubrest.exceptions.RepositoryNotFoundException;
import szymaniaks.githubrest.services.GithubService;
import szymaniaks.githubrest.v1.model.GithubDTO;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers= GithubController.class)
public class GithubControllerTest {
    public static final String CLONE_URL = "https://github.com/ozonegod/movieapp.git";
    public static final String FULL_NAME = "ozonegod/movieapp";
    public static final String REPOSITORIES_OZONEGOD_MOVIEAPP = "/api/v1/repositories/ozonegod/movieapp";
    public static final String DESCRIPTION = "Movie app using Spring Boot";
    public static final String USERNAME = "ozonegod";
    public static final String REPOSITORY_NAME = "movieapp";
    @Autowired
    private MockMvc mvc;

    @MockBean
    private GithubService githubService;


    @Test
    public void shouldReturnRepositoryAsJSON() throws Exception{
        //given
        GithubDTO githubDTO = new GithubDTO();
        githubDTO.setCloneUrl(CLONE_URL);
        githubDTO.setFullName(FULL_NAME);
        githubDTO.setDescription(DESCRIPTION);
        given(githubService.getRepository(USERNAME, "movieapp")).willReturn(githubDTO);
        //when
        ResultActions perform = mvc.perform(get(REPOSITORIES_OZONEGOD_MOVIEAPP)
                .accept(MediaType.APPLICATION_JSON));
        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is(DESCRIPTION)))
                .andExpect(jsonPath("$.cloneUrl", is(CLONE_URL)))
                .andExpect(jsonPath("$.fullName", is(FULL_NAME)));
    }

    @Test
    public void shouldReturnRepositoryAsXML() throws Exception{
        //given
        GithubDTO githubDTO = new GithubDTO();
        githubDTO.setCloneUrl(CLONE_URL);
        githubDTO.setFullName(FULL_NAME);
        githubDTO.setDescription(DESCRIPTION);
        given(githubService.getRepository(USERNAME, REPOSITORY_NAME)).willReturn(githubDTO);
        //when
        ResultActions perform = mvc.perform(get(REPOSITORIES_OZONEGOD_MOVIEAPP)
                .accept(MediaType.APPLICATION_XML));
        //then
        perform.andExpect(status().isOk())
                .andExpect(xpath("GithubDTO/description").string(is(DESCRIPTION)))
                .andExpect(xpath("GithubDTO/cloneUrl").string(is(CLONE_URL)))
                .andExpect(xpath("GithubDTO/fullName").string(is(FULL_NAME)));
    }

    @Test
    public void shouldNotFindRepository() throws Exception{
        //given
        given(githubService.getRepository(anyString(), anyString())).willThrow(RepositoryNotFoundException.class);
        //when
        ResultActions perform = mvc.perform(get(REPOSITORIES_OZONEGOD_MOVIEAPP)
                .accept(MediaType.APPLICATION_JSON));
        //then
        perform.andExpect(status().isNotFound());
    }
}