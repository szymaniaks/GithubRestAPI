package szymaniaks.githubrest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubInputDTO {
    @JsonProperty("full_name")
    String fullName;
    String description;
    @JsonProperty("clone_url")
    String cloneUrl;
    @JsonProperty("stargazers_count")
    String stars;
    @JsonProperty("created_at")
    Date createdAt;
}
