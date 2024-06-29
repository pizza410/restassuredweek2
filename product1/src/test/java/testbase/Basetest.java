package testbase;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class Basetest {

    @BeforeClass
    public void inIt(){
        RestAssured.baseURI = "http://localhost:3030/products";

    }

}
