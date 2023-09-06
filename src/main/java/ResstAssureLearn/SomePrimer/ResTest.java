package ResstAssureLearn.SomePrimer;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;

public class ResTest {
    //https://www.youtube.com/watch?v=8oqkB9XN8RM

    @Test
    public void restTest(){
        Specs.instalSpec(Specs.requestSpec("odnachastURL",0000, "drugayachastURL"), Specs.responseSpecification());

        AuthReq authReq = new AuthReq("admin", "admin");

        AuthRes authRes = RestAssured
            .given()
            .when()
                .body(authReq)
            .post("/someUrl")
            .then()
                .statusCode(200)
                .extract().response().as(AuthRes.class);//если хотим получить что то конкретное тогда .body("data[0].id" , equalTo"234") проверили что соответствует


//        AuthReq authReq = new AuthReq("admin", "admin");
//        String token = RestAssured
//                .given()
//                .log().all()
//                .when()
//                .contentType(ContentType.JSON)
//                .body(authReq)
//                .post("someUrl")
//                .then()
//                .log().all()
//                .statusCode(200)
//                .extract().response().jsonPath().getString("token");//если хотим получить что то конкретное тогда .body("data[0].id" , equalTo"234") проверили что соответствует

        OrderReq orderReq = new OrderReq("blabla","blabla");

        OrderRes orderRes =RestAssured
                .given()

                .when()
                        .contentType(ContentType.JSON)
                        .header("Autorizaton", "Bearer" + authRes.getToken())
                        .body(orderReq)
                        .post("/blabla1")
                .then()
                        .statusCode(200)
                        .extract().response().as(OrderRes.class);//разпарсиваен ответ и помещаем в переменную


        RestAssured
                .given()
                .when()
                       .header("Autorization", "Bearer" + authRes.getToken())
                       .get("/blabla2" + orderRes.getId())
                .then()
                      .statusCode(200)
                      .body("text", Matchers.equalTo("sometext"));//проверяем что приходит данный текст









//        AuthReq authReq = new AuthReq("admin", "admin");
//
//        AuthRes authRes = RestAssured
//                .given()
//                .log().all()//
//                .when()
//                .contentType(ContentType.JSON)
//                .body(authReq)
//                .post("someUrl")
//                .then()
//                .log().all()
//                .statusCode(200)
//                .extract().response().as(AuthRes.class);//если хотим получить что то конкретное тогда .body("data[0].id" , equalTo"234") проверили что соответствует
//
//
////        AuthReq authReq = new AuthReq("admin", "admin");
////        String token = RestAssured
////                .given()
////                .log().all()
////                .when()
////                .contentType(ContentType.JSON)
////                .body(authReq)
////                .post("someUrl")
////                .then()
////                .log().all()
////                .statusCode(200)
////                .extract().response().jsonPath().getString("token");//если хотим получить что то конкретное тогда .body("data[0].id" , equalTo"234") проверили что соответствует
//
//        OrderReq orderReq = new OrderReq("blabla","blabla");
//
//        OrderRes orderRes =RestAssured
//                .given()
//                .log().all()
//                .when()
//                .contentType(ContentType.JSON)
//                .header("Autorizaton", "Bearer" + authRes.getToken())
//                .body(orderReq)
//                .post("someUrl")
//                .then()
//                .log().all()
//                .statusCode(200)
//                .extract().response().as(OrderRes.class);//разпарсиваен ответ и помещаем в переменную







    }
}

