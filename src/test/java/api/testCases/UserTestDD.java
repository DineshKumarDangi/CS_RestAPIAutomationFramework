package api.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UserTestDD {

	@Test(priority = 1, dataProvider = "allData", dataProviderClass = DataProviders.class)
	public void testCreateUser(String userId, String userName, String fName, String lName, String email, String pwd,
			String phone) throws InterruptedException {

		User userPayload = new User();

		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fName);
		userPayload.setLastName(lName);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phone);

		Response response = UserEndPoints.createUser(userPayload);

		// log response
		response.then().log().all();
		// Validations
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 3, dataProvider = "userNamesData", dataProviderClass = DataProviders.class)
	public void testDeleteUser(String username) {
		Response response = UserEndPoints.deleteUser(username);
		System.out.println("-----------------Delete User Data-------------");
		// log response
		response.then().log().all();
		// Validations
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	@Test(priority = 2, dataProvider = "userNamesData", dataProviderClass = DataProviders.class)
	public void testGetUser(String username) {
		Response response = UserEndPoints.getUser(username);
		System.out.println("-----------------Get User Data-------------");
		// log response
		response.then().log().all();
		// Validations
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}