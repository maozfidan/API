package baseUrls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderBaseUrls {


    protected RequestSpecification spec;
//@Before her test metodundan önce calisir
    @Before

    public void setUp() {


        spec = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();


    }


}
