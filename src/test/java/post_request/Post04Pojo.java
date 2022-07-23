package post_request;

import baseUrls.DummyRestApiBaseUrl;
import baseUrls.HerOkuAppBaseUrl;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.*;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post04Pojo extends HerOkuAppBaseUrl {
     /*
         Given
          1)  https://restful-booker.herokuapp.com/booking
          2)   {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 }
                 "additionalneeds": "Breakfast with white tea, Dragon juice"
             }
        When
          I send POST Request to the URL
       Then
          Status code is 200
      And
          Response body is like {
                                  "bookingid": 16,
                                  "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast with white tea, Dragon juice"
                                     }
                                  }
     */

    @Test
    public void PostPojo01(){
        //1. Step: Set the Url
        spec.pathParam("first", "booking");

        //2. Step: Set the expected data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2021-09-21", "2021-12-21");
        BookingPojo bookingPojo = new BookingPojo("Ali","Can",999, true,bookingDates,"Breakfast with white tea, Dragon juice");

        //3. Step: Send POST Request and Get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(bookingPojo).when().post("/{first}");
        response.prettyPrint();

        //4. Step: Do Assertion
        BookingResponseBodyPojo actualPojo = response.as(BookingResponseBodyPojo.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(bookingPojo.getFirstname(),actualPojo.getBooking().getFirstname());
        assertEquals(bookingPojo.getLastname(), actualPojo.getBooking().getLastname());
        assertEquals(bookingPojo.getTotalprice(), actualPojo.getBooking().getTotalprice());
        assertEquals(bookingPojo.getDepositpaid(), actualPojo.getBooking().getDepositpaid());

        //1. Yol
        assertEquals(bookingPojo.getBookingdates().getCheckin(), actualPojo.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingPojo.getBookingdates().getCheckout(), actualPojo.getBooking().getBookingdates().getCheckout());

        //2. Yol
        assertEquals(bookingDates.getCheckin(), actualPojo.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(), actualPojo.getBooking().getBookingdates().getCheckout());
        assertEquals(bookingPojo.getAdditionalneeds(), actualPojo.getBooking().getAdditionalneeds());
    }

    public static class Post05 extends DummyRestApiBaseUrl {

         /*
           URL: https://dummy.restapiexample.com/api/v1/create
           HTTP Request Method: POST Request
           Request body:
           Test Case: Type by using Gherkin Language
           Assert:
                        {
                                "employee_name": "Tom Hanks",
                                "employee_salary": 111111,
                                "employee_age": 23,
                                "profile_image": "Perfect image",
                                "id": 4891
                         }
                    i) Status code is 200
                    ii) Response body should be like the following
                        {
                            "status": "success",
                            "data": {
                                "employee_name": "Tom Hanks",
                                "employee_salary": 111111,
                                "employee_age": 23,
                                "profile_image": "Perfect image",
                                "id": 4891
                            },
                            "message": "Successfully! Record has been added."
                        }
         */
        /*
        Given
            https://dummy.restapiexample.com/api/v1/create
             {
             "employee_name": "Tom Hanks",
             "employee_salary": 111111,
             "employee_age": 23,
             "profile_image": "Perfect image",
             "id": 4891
             }
        When
            User sends the POST Request
        Then
            Status code is 200
        And
             {
               "status": "success",
               "data": {
                        "employee_name": "Tom Hanks",
                         "employee_salary": 111111,
                         "employee_age": 23,
                          "profile_image": "Perfect image",
                          "id": 4891
                         },
                "message": "Successfully! Record has been added."
              }
         */

        @Test
        public void post01(){
            spec.pathParam("first","create");
            DummyApiDataPojo dummyApiDataPojo = new DummyApiDataPojo("Tom Hanks",111111,23,"Perfect image");
            DummyApiResponseBodyPojo expectedData = new DummyApiResponseBodyPojo("success",dummyApiDataPojo,"Successfully! Record has been added.");
            Response response = given().spec(spec).contentType(ContentType.JSON).body(dummyApiDataPojo).when().post("/{first}");
            response.prettyPrint();
            DummyApiResponseBodyPojo actualData = JsonUtil.convertJsonToJavaObject(response.asString(),DummyApiResponseBodyPojo.class);
            System.out.println(actualData);

            assertEquals(expectedData.getMessage(),actualData.getMessage());
            assertEquals(expectedData.getStatus(),actualData.getStatus());

            assertEquals(expectedData.getData().getEmployee_name(),actualData.getData().getEmployee_name());
            assertEquals(expectedData.getData().getEmployee_salary(),actualData.getData().getEmployee_salary());
            assertEquals(expectedData.getData().getEmployee_age(),actualData.getData().getEmployee_age());
            assertEquals(expectedData.getData().getProfile_image(),actualData.getData().getProfile_image());

        }
    }
}