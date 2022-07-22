package get_requests;

import baseUrls.DummyRestApiBaseUrl;

import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyApiDataPojo;
import pojos.ResponseBody;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class Get17 extends DummyRestApiBaseUrl {
     /*
       URL: https://dummy.restapiexample.com/api/v1/employee/1
       HTTP Request Method: GET Request
       Test Case: Type by using Gherkin Language
       Assert: 	i) Status code is 200
               ii) "employee_name" is "Tiger Nixon"
              iii) "employee_salary" is 320800
               iv)  "employee_age" is 61
                v) "status" is "success"
               vi)  "message" is "Successfully! Record has been fetched."
     */

    /*
    Given
        URL: https://dummy.restapiexample.com/api/v1/employee/1
    When
        User sends GET Request
    Then
        Status code is 200
    And
        "employee_name" is "Tiger Nixon"
    And
        "employee_salary" is 320800
    And
        "employee_age" is 61
    And
        "status" is "success"
    And
        "message" is "Successfully! Record has been fetched."
     */
    @Test
    public void get01() {
        spec.pathParams("first", "employee", "second", 1);
        DummyApiDataPojo dataPojo = new DummyApiDataPojo(1, "Tiger Nixon", 320800, 61, "");
        ResponseBody responseBodyPojo = new ResponseBody("success", dataPojo, "Successfully! Record has been fetched.");
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

        ResponseBody responseBody = JsonUtil.convertJsonToJavaObject(response.asString(), ResponseBody.class);
        System.out.println(responseBody);
        DummyApiDataPojo dummyApiDataPojo = JsonUtil.convertJsonToJavaObject(response.asString(), DummyApiDataPojo.class);

        assertEquals(responseBodyPojo.getStatus(), responseBody.getStatus());
        assertEquals(responseBodyPojo.getMessage(), responseBody.getMessage());
        assertEquals(responseBodyPojo.getData().getId(), responseBody.getData().getId());
        assertEquals(responseBodyPojo.getData().getEmployeeName(), dummyApiDataPojo.getEmployeeName());
//    assertEquals(responseBodyPojo.getData().getEmployeeSalary(), responseBody.getData().getEmployeeSalary());
//    assertEquals(responseBodyPojo.getData().getEmployeeAge(), responseBody.getData().getEmployeeAge());
//    assertEquals(responseBodyPojo.getData().getProfileImage(), responseBody.getData().getProfileImage());


    }

}