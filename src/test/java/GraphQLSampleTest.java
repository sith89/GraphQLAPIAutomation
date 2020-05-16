import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import modal.response.AllPersonsData;
import modal.response.GraphQLGetResponse;
import modal.response.PostsData;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.github.jsonSnapshot.SnapshotMatcher.*;

public class GraphQLSampleTest {
    @BeforeClass
    public static void beforeAll() {
        start();
    }

    @Test
    public void testGraphQLGetRequestWithValidDetails(){
        String body = "{\"query\":\"{\\n "+
                " allPersons {\\n " +
                " name\\n  " +
                " age\\n " +
                " posts {\\n "+
                " title\\n    }\\n  }\\n}\",\"variables\":{}}";

        RestAssured.baseURI = "https://api.graph.cool/simple";
        RestAssured.basePath = "v1/cjrqzet3c0fc30162tgt8wzf4";
        RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
        reqSpecBuilder.setContentType("application/json; charset=UTF-8");
        reqSpecBuilder.setBody(body);

        RequestSpecification requestSpecification = RestAssured.given(reqSpecBuilder.build());
        GraphQLGetResponse graphResponse = requestSpecification.expect().when().post().as(GraphQLGetResponse.class);
        expect(graphResponse).toMatchSnapshot();
        // Additional assertion :- Verify the title of "Sarah"'s created post
        assertAgeForGivenPerson(graphResponse);
    }

    private void assertAgeForGivenPerson(GraphQLGetResponse graphResponse){
        for (AllPersonsData person: graphResponse.getData().getAllPersonData()){
            if(person.getName().equals("Sarah")){
                for (PostsData p: person.getPosts()) {
                    Assert.assertEquals(p.getTitle(), "How to get started with React & GraphQL");
                    System.out.println(p.getTitle());
                }
            }
        }
    }

    @AfterClass
    public static void afterAll() {
        validateSnapshots();
    }
}
