package testbase;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;


public class BaseTest {
    @BeforeClass
    public void inIT(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/student";
    }
}
