package api.endpoints;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {
@Test
public static Response createUser(User payload){
Response response =	given()
	.accept(ContentType.JSON)
	.contentType(ContentType.JSON)
	.body(payload).log().all()
	.when()
	.post(Routes.post_url);

	return response;		
	}

public static Response getUser(String userName){
Response response =	given()
	.accept(ContentType.JSON)
	.pathParam("username",userName)
	.when()
	.get(Routes.get_url);

	return response;		
	}

public static Response updateUser(String userName, User payload){
Response response =	given()
	.accept(ContentType.JSON)
	.contentType(ContentType.JSON)
	.pathParam("username",userName)
	.body(payload)
	.when()
	.put(Routes.put_url);

	return response;		
	}


public static Response deleteUser(String userName){
Response response =	given()
	.accept(ContentType.JSON)
	.pathParam("username",userName)
	.when()
	.delete(Routes.delete_url);

	return response;		
	}

}
