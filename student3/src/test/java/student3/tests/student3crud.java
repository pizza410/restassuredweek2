package student3.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import student3.Studenpojo;
import testbase.BaseTest;
import utilities.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class student3crud extends BaseTest {

    static String firstName="Shubham"+ Utilities.getRandomValue();
    static String lastName="Panchal"+Utilities.getRandomValue();
    static  String email=Utilities.getRandomValue()+"@gmail.com";
    static String programm="Software testing";
    static int studentID;

    @Test
    public void getlist(){
        Response response=given().when().get("/list");
        ValidatableResponse validatableResponse=response.then().statusCode(200);
    }
    @Test
    public void getbyID(){
        given().pathParams("id",125).when().get("/{id}")
                .then().log().all().statusCode(200);
    }
    @Test(priority = 1)
    public void createstudent(){
        List<String> course=new ArrayList<>();
        course.add("maths");
        course.add("English");

        Studenpojo studenpojo=new Studenpojo();
        studenpojo.setFirstName(firstName);
        studenpojo.setLastName(lastName);
        studenpojo.setCourses(course);
        studenpojo.setEmail(email);
        studenpojo.setProgramme(programm);
        given().contentType(ContentType.JSON).when()
                .body(studenpojo).post().then().log().all()
                .statusCode(201);
    }
    @Test(priority=2)
    public void extractID(){
        Studenpojo studenpojo=new Studenpojo();
        HashMap<String, Object>data=given().when().get("/list").then().extract()
                .path("findAll{it.firstName=='"+firstName+"'}[-1]");

        studentID=(int) data.get("id");
        studenpojo.setId(studentID);
        System.out.println(studenpojo.getId());

    }
    @Test(priority = 3)
    public void update(){
        Studenpojo studentmojo=new Studenpojo();
        studentmojo.setFirstName("Shubham");
        studentmojo.setLastName("Panchal");
        studentmojo.setEmail(email);


        given().pathParams("id",studentID).when().contentType(ContentType.JSON).body(studentmojo)
                .patch("/{id}").then().log().all().statusCode(200);
        System.out.println(studentmojo.getFirstName());
    }

    @Test(priority = 4)
    public void deletebyID(){
        given().pathParams("id",studentID).when()
                .delete("/{id}").then().statusCode(204);
    }

    @Test(priority = 5)
    public void recheckbyID(){
        given().pathParams("id",studentID).when().get("/{id}")
                .then().log().all().statusCode(404);
    }

}
