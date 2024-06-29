package product5;

import classes.Datum;
import classes.Products;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import product5.testbase.BaseTest;

import static io.restassured.RestAssured.given;

public class Tests extends BaseTest {
    int idNum;
    @Test
    public void getdata(){
        Products products=given().when().get().getBody()
                .as(Products.class);
        System.out.println(products.getTotal());
    }

    @Test(priority = 1)
    public void createdata(){
        Datum datum=new Datum();
        datum.setName("shubham");
        datum.setType("hghj");
        datum.setPrice(123F);
        datum.setShipping(34);
        datum.setUpc("hjghnknvhv");
        datum.setDescription("desg");
        datum.setManufacturer("audi");
        datum.setModel("tesla");
        datum.setUrl("kjad mllbfsjbg");
        datum.setImage("mn mn ");
        Datum datum1=given().contentType(ContentType.JSON).when()
                .body(datum).post().getBody().as(Datum.class);
        idNum=datum1.getId();
        System.out.println(datum1.getId());
    }
    @Test(priority = 2)
    public void updatebyid(){
        Datum datum=new Datum();
        datum.setName("vidhi");
        datum.setType("hghj");
        datum.setPrice(123F);
        datum.setShipping(34);
        datum.setUpc("hjghnknvhv");
        datum.setDescription("desg");
        datum.setManufacturer("audi");
        datum.setModel("tesla");
        datum.setUrl("kjad mllbfsjbg");
        datum.setImage("mn mn ");

       Datum datum1= given().pathParams("id",idNum)
                .when().body(datum).patch("/{id}")
                .getBody().as(Datum.class);
        System.out.println(datum1.getName());
    }

    @Test(priority = 3)
    public void delete(){
        given().pathParams("id",idNum).when()
                .delete("/{id}").then()
                .statusCode(200);
    }

    @Test(priority = 4)
    public void checkafterdelete(){
        given().pathParams("id",idNum)
                .when().get("/{id}").then().log().all().statusCode(404);
    }
}
