package student4.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import student4.studentpojo.StudentPojo;
import student4.utilities.Utilities;
import testbase.BaseTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Tests extends BaseTest {

    static String firstName="Shubham"+ Utilities.getRandomValue();
    static String lastName="Panchal"+Utilities.getRandomValue();
    static String email=Utilities.getRandomValue()+"@gmail.com";
    static String Programe="Electronics Engineering";
    static int StudentID;

    @Test
    public void getstudentlist(){
        given().when().get("/list").then().statusCode(200);
    }
    @Test
    public void getstudentinfobyID(){
        Response response=given().pathParams("id",125).get("/{id}");
    ValidatableResponse validatableResponse=response.then().statusCode(200);
    response.prettyPrint();
    }
    @Test(priority = 1)
    public void createstudent(){
        List<String> course=new ArrayList<>();
        course.add("Social");
        course.add("microprocessor");

        StudentPojo studentpojo=new StudentPojo();
        studentpojo.setFirstName(firstName);
        studentpojo.setLastName(lastName);
        studentpojo.setEmail(email);
        studentpojo.setCourses(course);
        studentpojo.setProgramme(Programe);

        given().contentType(ContentType.JSON).when().body(studentpojo)
                .post()
                .then().log().everything().statusCode(201);

    }
    @Test(priority = 2)
    public void fetchid(){
        HashMap<String, Object> data=given().when().get("/list").then()
                .extract().path("findAll{it.firstName=='"+firstName+"'}[-1]");
        StudentID=(int) data.get("id");
        System.out.println(StudentID);
    }
    @Test(priority = 3)
    public void updatebyID(){
        StudentPojo studentPojo=new StudentPojo();
        studentPojo.setFirstName("shubham");
        studentPojo.setLastName("patel");
        studentPojo.setEmail(email);
        studentPojo.setProgramme(Programe);

        given().pathParams("id",StudentID).contentType(ContentType.JSON).body(studentPojo)
                .put("/{id}").then().log().all().statusCode(200);
    }

    @Test(priority = 4)
    public void getstudentinfobyIDcheck(){
        Response response=given().pathParams("id",StudentID).get("/{id}");
        ValidatableResponse validatableResponse=response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void deletestudentbyid(){
        Response response=given().pathParams("id",StudentID).delete("/{id}");
        ValidatableResponse validatableResponse=response.then().statusCode(204);
        response.prettyPrint();
    }

    @Test
    public void deletecheck(){
        Response response=given().pathParams("id",StudentID).get("/{id}");
        ValidatableResponse validatableResponse=response.then().statusCode(404);
        response.prettyPrint();
    }

}
