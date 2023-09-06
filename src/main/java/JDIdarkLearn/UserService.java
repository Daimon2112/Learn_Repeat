package JDIdarkLearn;

import com.epam.http.annotations.*;
import com.epam.http.requests.RestMethod;
import static io.restassured.http.ContentType.JSON;


@ServiceDomain("https://reqres.in/api")
public class UserService {

    @POST("/users")
    @ContentType(JSON)
    private RestMethod createNewUser;

    @GET("/users?page=2")
    @ContentType(JSON)
    private RestMethod getAllUsers;

    @PUT("/users/2")
    @ContentType(JSON)
    private RestMethod updateUsers;

    @DELETE("/users/2")
    @ContentType(JSON)
    private RestMethod deleteUsers;


}
