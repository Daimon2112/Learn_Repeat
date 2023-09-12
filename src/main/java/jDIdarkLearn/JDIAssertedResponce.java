package jDIdarkLearn;

import com.epam.http.response.RestResponse;

public class JDIAssertedResponce {
    public final RestResponse response;

    public JDIAssertedResponce(RestResponse response){
        this.response = response;
    }

    public <T> T as(String jsonPath, Class<T> clazz){
        return response.getRaResponse().then().extract().body().jsonPath().getObject(jsonPath, clazz);//так мы дейсон в джава клас переобразуем

    }

    public JDIAssertedResponce hasMessage(String expectedMessage){
        Message message = as("data", Message.class);
        return null;
    }

}
