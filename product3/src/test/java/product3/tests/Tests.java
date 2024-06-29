package product3.tests;

import classes.Datum;
import classes.Products;
import org.testng.annotations.Test;
import testbase.Basetest;

import static io.restassured.RestAssured.given;

public class Tests extends Basetest {
    int idNumber;
    @Test
    public void getdata(){
        Products products=given().when().get().getBody().as(Products.class);
        System.out.println(products.getTotal());
    }

    @Test(priority = 1)
    public void adddataandgetID(){
        Datum datum=new Datum();
        datum.setName("Shubham");
        datum.setType("dsndj");
        datum.setPrice(333F);
        datum.setShipping(34);
        datum.setUpc("hjghnksdnjdnvhv");
        datum.setDescription("desg");
        datum.setManufacturer("audi");
        datum.setModel("tesla");
        datum.setUrl("kjad mllbfsjbg");
        datum.setImage("mn mn ");
        Datum datum1=given().header("Content-Type","application/json")
                .body(datum).post().getBody().as(Datum.class);
        idNumber=datum1.getId();
        System.out.println(idNumber);
    }
    @Test(priority = 2)
    public void getbyId(){
        given().pathParams("id",idNumber).when()
                .get("/{id}").then().log().all().statusCode(200);
    }
    @Test(priority = 3)
    public void updatebyID(){

        Datum datum=new Datum();
        datum.setName("vidhi");
        datum.setType("dsndj");
        datum.setPrice(333F);
        datum.setShipping(34);
        datum.setUpc("hjghnksdnjdnvhv");
        datum.setDescription("desg");
        datum.setManufacturer("audi");
        datum.setModel("tesla");
        datum.setUrl("kjad mllbfsjbg");
        datum.setImage("mn mn ");
        Datum datum1=given().header("Content-Type","application/json")
                .pathParams("id",idNumber)
                .body(datum).patch("/{id}").getBody().as(Datum.class);
        System.out.println(datum1.getName());
    }

    @Test(priority = 4)
    public void deletebyID(){
        given().pathParams("id",idNumber).when()
                .delete("/{id}").then().log().all()
                .statusCode(200);
    }

    @Test(priority = 5)
    public void checkafterdelete(){
        given().pathParams("id",idNumber).when()
                .get("/{id}").then().log().all().statusCode(404);

    }
}
