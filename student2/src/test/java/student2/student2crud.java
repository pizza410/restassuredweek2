package student2;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import testbase.BaseTest;
import utilities.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class student2crud extends BaseTest {
    static String firstName="shubh"+ Utilities.getRandomValue();
    static String lastName="Panchal"+Utilities.getRandomValue();
    static String program="testing";
    static String email=Utilities.getRandomValue()+"@gmail.com";
    static int studentID;

    @Test
    public void getstudentlist(){
        given().when().get("/list").then()
                .log().all().statusCode(200);
    }

    @Test(priority = 1)
    public void getstudentbyID(){
        given().pathParams("id","25").when().get("/{id}")
                .then().log().everything().statusCode(200);
    }

    @Test(priority = 2)
    public void createstudent(){
        List<String> course=new ArrayList<>();
        course.add("selenium");
        course.add("MAths");

        Studentmojo studentmojo=new Studentmojo();
        studentmojo.setFirstName(firstName);
        studentmojo.setLastName(lastName);
        studentmojo.setEmail(email);
        studentmojo.setCourses(course);
        studentmojo.setProgramme(program);

      Response response =given().when().contentType(ContentType.JSON).body(studentmojo)
                .post();
      ValidatableResponse validatableResponse=response.then().log().all().statusCode(201);
    }

    @Test(priority = 3)
    public void extractstudentid(){
        HashMap<String, Object> data=given().when().get("/list").then().extract()
                .path("findAll{it.firstName='"+firstName+"'}[-1]");
        studentID=(int) data.get("id");
        System.out.println(studentID);
    }
    @Test(priority = 4)
    public void updateStudentdata(){
        Studentmojo studentmojo=new Studentmojo();
        studentmojo.setFirstName("Shubham");
        studentmojo.setLastName("Panchal");
        studentmojo.setEmail(email);


        given().pathParams("id",studentID).when().contentType(ContentType.JSON).body(studentmojo)
                .patch("/{id}").then().log().all().statusCode(200);


    }
   /* @Test(priority = 5)
    public void getstudentbyIDcheck(){
        given().pathParams("id",studentID).when().get("/{id}")
                .then().log().everything().statusCode(200);
    }
*/

    @Test
    public void deletestudentdata(){
        given().pathParams("id",studentID).when().delete("/{id}")
                .then().statusCode(204);
    }

    @Test
    public void checkbyid(){
        given().pathParams("id",studentID).when().get("/{id}")
                .then().statusCode(404);
    }
}
