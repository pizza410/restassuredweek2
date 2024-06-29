package product5.testbase;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    @BeforeClass
    public void inIT(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/products";
    }
}
