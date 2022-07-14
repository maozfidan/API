package get_requests;

import baseUrls.JsonPlaceHolderBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import javax.swing.*;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class SoruCozumu2 extends JsonPlaceHolderBaseUrls {
    /*
        1) Create a class and name it as you wish :)
        2) When
             I send a GET Request to https://jsonplaceholder.typicode.com/todos
           Then
             HTTP Status code should be "200"
             And Content type should be in "JSON" format
             And there should be 200 "title"
             And "dignissimos quo nobis earum saepe" should be one of the "title"s
             And 111, 121, and 131 should be among the "id"s
             And 4th title is "et porro tempora"
             And last title is "ipsam aperiam voluptates qui"
    */

    @Test
    public void name() {

        spec.pathParam("first","todos");
        Response response=given().spec(spec).when().get("/first");

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

        JsonPath jsonPath=response.jsonPath();


        List<String> titles = jsonPath.getList("title");

        assertEquals(200, titles.size());
        assertTrue(titles.contains("dignissimos quo nobis earum saepe"));

        List<Integer> id=jsonPath.getList("id");




    }
}
