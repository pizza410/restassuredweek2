package testbase;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    @BeforeClass
    public void inIT(){
        RestAssured.basePath="http://localhost";
        RestAssured.basePath="/student";
        RestAssured.port=8080;
    }
}
