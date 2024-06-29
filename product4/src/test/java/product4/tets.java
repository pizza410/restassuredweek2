package product4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import testbase.Basetest;

import static io.restassured.RestAssured.given;

public class tets extends Basetest {
    int idNumber;
    @Test
    public void getdata(){
        given().when().get().then().log().all().statusCode(200);
    }
    @Test(priority = 1)
    public void createdataandgetid(){
        Datum datum=new Datum();
        datum.setName("shubh");
        datum.setType("hghj");
        datum.setPrice(1255F);
        datum.setShipping(34);
        datum.setUpc("hjghnknvhv");
        datum.setDescription("desg");
        datum.setManufacturer("audi");
        datum.setModel("tesla");
        datum.setUrl("kjad mllbfsjbg");
        datum.setImage("mn mn ");
       Datum datum1= given().contentType(ContentType.JSON).body(datum)
                .post().getBody().as(Datum.class);
       idNumber=datum1.getId();
       System.out.println(idNumber);

    }
    @Test(priority = 2)
    public void getdatabyId(){
        given().pathParams("id",idNumber).when()
                .get("/{id}").then().log().all().statusCode(200);
    }
    @Test(priority = 3)
    public void updatebyid(){
        Datum datum=new Datum();
        datum.setPrice(123F);
        datum.setShipping(34);
        datum.setUpc("hjghnknvhv");
        datum.setDescription("desg");
        datum.setManufacturer("audi");
        datum.setModel("tesla");
        datum.setName("vidhi");


        Response response=given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", idNumber)
                .when()
                .body(datum)
                .patch("/{id}");
        response.then().statusCode(200);

    }
    @Test(priority = 4)
    public void deletebyid(){
        given().pathParams("id",idNumber).when()
                .delete("/{id}").then().log().all().statusCode(200);
    }
    @Test(priority = 5)
    public void checkafterdelet(){
        given().pathParams("id",idNumber).when()
                .get("/{id}").then().log().all().statusCode(404);
    }
}
