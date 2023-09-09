package ResstAssureLearn;

import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.RestAssured;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
//given - content type, set cookies, add auth, add param, ser headers info
//when - get post put delete
//then - validate status code, extract responce, extract headers cookies/response body

//https://www.youtube.com/watch?v=xIidl6Iua0o&list=PLUDwpEzHYYLvLZX_QEGTNolPvNADXid0I&index=21


public class ReqresSite {

    private static final String blabla = "";// тут можно хранить константу а

    @Test(priority = 1)
    public void firstTest(){
        HashMap data = new HashMap();
        data.put("name", "morpheus");
        data.put("job", "leader");
//        String someArray[]={"java","javascript"};
//        data.put("language", someArray);//na vuhode - "language":["java", "javascript"]

        //если хотим записать данные в переменную тогда перед гивен добавить String token = given()
//        given()
//                .baseUri("https://sm-dev.sandbox.emulatebio.com/api/login/auth/login")
//                //.log().everything()
//                .contentType(ContentType.JSON)
//                .body("{\n" +
//                        "\t\"email\":\"dmitry.myrhorodchenko@itrexgroup.com\",\n" +
//                        "\t\"password\":\"123456q+\"\n" +
//                        "}")
//                .when().post()
//                .then()
//                .statusCode(200)
//                .extract().asString();   позволяет соохранять респонс//так же можем юзать jsonPath().выбрать что именно
//                  extract().response().jsonPath().getString("token") позволяет соохранять респонс//так же можем юзать jsonPath().выбрать что именно
        //если хотим получить что то конкретное тогда .body("data[0].id" , equalTo"234") проверили что соответствует
// потом в след запросе вставить .header('Autorization',"Bearer" + token)
        RestAssured.
                given()//хедеры запроса сюда же вставляются урла
                        .baseUri("https://reqres.in")
                        .basePath("/api/users?page=2")
                        .contentType(ContentType.JSON)
                .when()
                        .get()//or get("https://reqres.in/api/users?page=2")
                .then()
                        .log().everything()
                        .statusCode(200)
                        .assertThat()
                        .body("data", hasSize(6));//проверка что в дата есть 6 вложений//"data[0].username", equalsTO"blabla@.com"-обращаемся к первому елемента масива и ищем там username
                        //.extract().response().jsonPath().getString("token") - вытаскуются данные из ответа
//        "    \"name\": \"morpheus\",\n" +
//                "    \"job\": \"leader\""

        RestAssured.
                given()
                    .baseUri("https://reqres.in")
                    .basePath("/api/users")
                    .contentType(ContentType.JSON)
                    .body(data)
                .when()
                    .post()
                  //  .jsonPath().getInt("id") - можем вытащить данные и тут
                .then()
                    .statusCode(201)
                    .assertThat()
                    //.body("name", equalTo("morpheus"))
                    //.body("job", equalTo("leader"))
                    .log().all()
                    .extract().response().jsonPath().getInt("id");

    };

    @Test(priority = 2)
    public void testPostPojo(){
        Pojo_PostRequest data = new Pojo_PostRequest();
        data.setName("morpheus");
        data.setJob("leader");

//        HashMap data = new HashMap();
//        data.put("name", "morpheus");
//        data.put("job", "leader");
        RestAssured.
                given()
                .baseUri("https://reqres.in")
                .basePath("/api/users")
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post()
                //  .jsonPath().getInt("id") - можем вытащить данные и тут
                .then()
                .statusCode(201)
                .assertThat()
                //.body("name", equalTo("morpheus"))
                //.body("job", equalTo("leader"))
                .log().all()
                .extract().response().jsonPath().getInt("id");

    }

    @Test(priority = 3)
    public void testPostExternalJsonFile() throws FileNotFoundException {
        File f = new File("src/main/java/ResstAssureLearn/body.json");//файл с данными
        FileReader fr = new FileReader(f);// читаем данные из файла
        //JSONTokener jt = new JSONTokener(fr);// не доконца понимаю зачем это
        JSONObject data = new JSONObject(fr);
//        HashMap data = new HashMap();
//        data.put("name", "morpheus");
//        data.put("job", "leader");
        RestAssured.
              given()
                .baseUri("https://reqres.in")
                .basePath("/api/users")
                .contentType(ContentType.JSON)
                .body(data.toString())
              .when()
                .post()
                //  .jsonPath().getInt("id") - можем вытащить данные и тут
              .then()
                .statusCode(201)
                .assertThat()
                //.body("name", equalTo("morpheus"))
                //.body("job", equalTo("leader"))
                .log().all()
                .extract().response().jsonPath().getInt("id");

    }


//если мы хотим вытащить и использовать данные из запроса тогда мы будем распарсивать ответ
    @Test
    public void testParsingResponceBody(){
        Response response =
                RestAssured.given()
                        .baseUri("https://reqres.in")
                        .basePath("/api/users?page=2")
                        .contentType(ContentType.JSON)
                        .when()
                        .get()
                        .then()
                        .log().everything()
                        .statusCode(200)
                        .extract().response();
        Assert.assertEquals(response.getStatusCode(),200);

//        JsonObject jsonResponse = response.getBody().as(JsonObject.class);
//        String firstName = jsonResponse.get("data[0].first_name").getAsString();
//        String lastName = jsonResponse.get("data[0].last_name").getAsString();
//        System.out.println("First Name: " + firstName);
//        System.out.println("Last Name: " + lastName);

        String userlastname= response.jsonPath().get("data[2].last_name").toString();
    }

    @Test
    public void testParsingXMLResponse(){
        
    }





}
