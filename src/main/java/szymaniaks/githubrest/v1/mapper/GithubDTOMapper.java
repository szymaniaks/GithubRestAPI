package szymaniaks.githubrest.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import szymaniaks.githubrest.model.GithubInputDTO;
import szymaniaks.githubrest.v1.model.GithubDTO;

@Mapper
public interface GithubDTOMapper {

    GithubDTOMapper INSTANCE = Mappers.getMapper(GithubDTOMapper.class);

    GithubDTO githubInputDTOToGithubDTO(GithubInputDTO githubInputDTO);
    GithubInputDTO githubDTOToGithubInputDTO(GithubDTO githubDTO);
}
