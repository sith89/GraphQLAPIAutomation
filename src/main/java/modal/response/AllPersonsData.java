package modal.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllPersonsData {
    private String name;
    private int age;
    private List<PostsData> posts;
}
