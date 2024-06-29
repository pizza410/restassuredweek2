package product2;

import classes.Datum;
import classes.Products;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import testbase.BaseTest;

import static io.restassured.RestAssured.given;

public class Tests extends BaseTest {

    int idNumber;
    @Test
    public void getdata() {
        given().when().get().then().log().all().statusCode(200);
    }
    @Test
    public void storedataintoclasses(){
       Products products= given().when().get().getBody().as(Products.class);
        System.out.println(products.getTotal());
    }

    @Test(priority = 1)
    public void addnewdata(){
        Datum datum=new Datum();
        datum.setName("shubham");
        datum.setType("hghj");
        datum.setPrice(155F);
        datum.setShipping(34);
        datum.setUpc("hjghnknvfhv");
        datum.setDescription("dbhebd");
        datum.setManufacturer("audi");
        datum.setModel("a5");
        datum.setUrl("kjad mllbfsjbg");
        datum.setImage("mn mn ");
        Datum datum1=given().contentType(ContentType.JSON).when()
                .body(datum).post()
                .getBody().as(Datum.class);
        idNumber= datum1.getId();
        System.out.println(idNumber);
    }
    @Test(priority = 2)
    public void getBYid(){
        Datum datum1=given()
                .log().all()
                .pathParam("id", idNumber)
                .when()
                .get("/{id}")
                .getBody()
                .as(Datum.class);
        System.out.println(datum1.getName());

    }

    @Test(priority = 3)
    public void updatebyId(){
        Datum datum=new Datum();
        datum.setPrice(123F);
        datum.setShipping(34);
        datum.setUpc("hjghnknvhv");
        datum.setDescription("desg");
        datum.setManufacturer("audi");
        datum.setModel("tesla");
        Response response=given()
                .header("Content-Type", "application/json")
                .pathParam("id", idNumber)
                .when()
                .body(datum)
                .patch("/{id}");
        response.then().log().all().statusCode(200);

    }

    @Test(priority = 4)
    public void deletebyid(){
        Response response=given()
                .log().all()
                .pathParam("id", idNumber)
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
    }
    @Test(priority = 5)
    public void chechdelete(){
        Response response=given()
                .log().all()
                .pathParam("id", idNumber)
                .when()
                .get("/{id}");
        response.then().statusCode(404);
    }
}
