package szymaniaks.githubrest.v1.model;

import lombok.Data;
import java.util.Date;

@Data
public class GithubDTO {
    String fullName;
    String description;
    String cloneUrl;
    String stars;
    Date createdAt;
}

