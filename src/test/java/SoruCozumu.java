import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class SoruCozumu {


    // https://restful-booker.herokuapp.com/booking/17379 url'ine bir GET request gonderdigimizde donen Response'un,
    //        status code'unun 200,
    //        ve content type'inin "application/json"; charset=utf-8,
    //        ve Server isimli Header'in degerinin Cowboy,
    //        ve status Line'in HTTP/1.1 200 OK
    //        ve response suresinin 5 sn'den kisa oldugunu manuel olarak test ediniz.


    @Test
    public void testName() {


        String url = "https://restful-booker.herokuapp.com/booking/44";

        Response response = given().when().get(url);

        response.then().assertThat().statusCode(200).contentType("application/json").header("Server", "Cowboy").
                statusLine("HTTP/1.1 200 OK");

        System.out.println(response.getHeader("Server"));

    }
}