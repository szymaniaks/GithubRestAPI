package szymaniaks.githubrest.v1.mapper;


import org.junit.Test;
import szymaniaks.githubrest.model.GithubInputDTO;
import szymaniaks.githubrest.v1.model.GithubDTO;

import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class GithubDTOMapperTest {

    private static final String FULL_NAME = "Just Name";
    private static final String DESCRIPTION = "Description ...";
    private static final Date CREATED_AT = new Date();

    GithubDTOMapper githubDTOMapper = GithubDTOMapper.INSTANCE;

    @Test
    public void githubInputDTOToGithubDTO(){
        //given
        GithubInputDTO githubInputDTO = new GithubInputDTO();
        githubInputDTO.setFullName(FULL_NAME);
        githubInputDTO.setDescription(DESCRIPTION);
        githubInputDTO.setCreatedAt(CREATED_AT);
        //when
        GithubDTO githubDTO = githubDTOMapper.githubInputDTOToGithubDTO(githubInputDTO);
        //then
        assertThat(githubDTO.getFullName(), equalTo(FULL_NAME));
        assertThat(githubDTO.getDescription(), equalTo(DESCRIPTION));
        assertThat(githubDTO.getCreatedAt(), equalTo(CREATED_AT));
    }
    @Test
    public void githubDTOToGithubInputDTO(){
        //given
        GithubDTO githubDTO = new GithubDTO();
        githubDTO.setFullName(FULL_NAME);
        githubDTO.setDescription(DESCRIPTION);
        githubDTO.setCreatedAt(CREATED_AT);
        //when
        GithubInputDTO githubInputDTO = githubDTOMapper.githubDTOToGithubInputDTO(githubDTO);
        //then
        assertThat(githubInputDTO.getFullName(), equalTo(FULL_NAME));
        assertThat(githubInputDTO.getDescription(), equalTo(DESCRIPTION));
        assertThat(githubInputDTO.getCreatedAt(), equalTo(CREATED_AT));
    }

}