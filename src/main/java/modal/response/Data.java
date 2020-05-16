package modal.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Data {
    @JsonProperty("allPersons")
    private List<AllPersonsData> allPersonData;

}
