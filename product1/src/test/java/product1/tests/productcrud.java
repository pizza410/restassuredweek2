package product1.tests;

import classes.Datum;
import classes.Products;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import testbase.Basetest;

import static io.restassured.RestAssured.given;

public class productcrud extends Basetest {

    int idNumber;
 @Test
    public void getdata(){
     given().when()
             .get()
             .then().log().all()
             .statusCode(200);
 }
 @Test
    public void storeinclass(){
   Products products=given().when().get().getBody().as(Products.class);
     System.out.println(products.getTotal());
 }

 @Test(priority = 1)
    public void addingdata(){
     Datum datum=new Datum();
     datum.setName("shubham");
     datum.setType("hghj");
     datum.setPrice(125.25);
     datum.setShipping(34);
     datum.setUpc("hjghnknvhv");
     datum.setDescription("desg");
     datum.setManufacturer("audi");
     datum.setModel("tesla");
     datum.setUrl("kjad mllbfsjbg");
     datum.setImage("mn mn ");
     Response response=given().contentType(ContentType.JSON).when()
             .body(datum).post();
     Datum datum1=response.getBody().as(Datum.class);
     idNumber=datum1.getId();
     System.out.println(idNumber);
 }
@Test(priority = 2)
    public void updateproduct(){
     Datum datum=new Datum();
    datum.setPrice(123D);
    datum.setShipping(34);
    datum.setUpc("hjghnknvhv");
    datum.setDescription("desg");
    datum.setManufacturer("audi");
    datum.setModel("tesla");

    given().contentType(ContentType.JSON).pathParams("id",idNumber).when()
            .body(datum).patch("/{id}").then().log().all().statusCode(200);
}

@Test(priority = 3)
    public void deletebyid(){
     given().pathParams("id",idNumber).when()
             .delete("/{id}").then().log().all().statusCode(200);
}

@Test(priority = 4)
    public void checkaferdelete(){
    Response response=given()
            .log().all()
            .pathParam("id", idNumber)
            .when()
            .get("/{id}");
    response.then().statusCode(404);
}
}
