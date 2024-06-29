package student1;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import testbase.Basetest;
import utils.Utilities;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class studentcrud1 extends Basetest {

    static String firstName="shubh"+ Utilities.getRandomValue();
    static String lastName="panchal"+Utilities.getRandomValue();
    static String programme="Backend Testing";
    static String email=Utilities.getRandomValue()+"@gmail.com";
    static int studentid;

    @Test(priority = 1)
   public void getstudentinfo() {
       given()
               .when().get("/list").then().log().all()
               .statusCode(200);
    }

    @Test(priority = 2)
    public void getstudentinfobyID(){
      Response response=given().pathParams("id","3").when().get("/{id}");
      response.then().log().all().statusCode(200);
    }


    //creating student data
    @Test(priority = 3)
    public void createstudentinfo(){
        List<String> coursenames=new ArrayList<>();
        coursenames.add("backend");
        coursenames.add("frontend");
        Studentpojo studentpojo=new Studentpojo();
        studentpojo.setCourses(coursenames);
        studentpojo.setFirstName(firstName);
        studentpojo.setLastName(lastName);
        studentpojo.setEmail(email);
        studentpojo.setProgramme(programme);

        Response response=given().when().contentType(ContentType.JSON)
                .body(studentpojo).post();
        response.then().log().all().statusCode(201);
    }

    //get student info id
    @Test(priority = 4)
    public void extractstudentinfo(){
       HashMap<String ,Object> data= given().when().get("/list").then().statusCode(200)
                .extract().path("findAll{it.firstName=='"+firstName+"'}.get(0)");
        studentid=(int) data.get("id");
        System.out.println(studentid);
    }

    @Test(priority = 5)
    public void deletestudentinfo(){
        given().pathParams("id",studentid)
                .when().delete("/{id}").then().log().all().statusCode(204);
    }

    @Test(priority = 6)
    public void chcechstudentdatadelete(){
        Response response=given().pathParams("id",studentid).when().get("/{id}");
        response.then().log().all().statusCode(404);
    }
}
