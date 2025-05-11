package api.testCases;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;

import io.restassured.response.Response;

public class UserTest {
	Faker faker;
	User userPayload;
	Response response;
	public static Logger logger;

	@BeforeClass
	public void generateTestData() {
		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		//obtain logger
		logger = LogManager.getLogger("RestAssuredAutomationFramework_AID_test");
	}

	@Test(priority=1)
	public void testCreateUser() {
		 response = UserEndPoints.createUser(userPayload);

		System.out.println("-----------------Create User Data-------priority=1------");
		// log response
		response.then().log().all();
		// Validations
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//log
		logger.info("Create user executed.");
	}

	@Test(priority=2)
	public void testGetUserData() throws InterruptedException {

		Thread.sleep(2000);	
		System.out.println("user name for get operation ---- "+this.userPayload.getUsername());		
		 response = UserEndPoints.getUser(this.userPayload.getUsername());		 
	
	   //String username=this.userPayload.getUsername();
		//System.out.println("user name for get operation ---- "+ username);		
		//response = UserEndPoints.getUser(username.trim());	
		
		System.out.println("-----------------Read User Data-----priority=2--------");
		// log response
		response.then().log().all();
		// Validations
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//log
		logger.info("Get user data executed.");
	}

	@Test(priority=3)
	public void testUpdateUser() {
		
		System.out.println("----------First name before update ----------" + userPayload.getFirstName());
		userPayload.setFirstName(faker.name().firstName());

		System.out.println("----------First name after update ----------" + userPayload.getFirstName());
		 response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);

		//log response
		response.then().log().all();
		// Validations
		Assert.assertEquals(response.getStatusCode(), 200);
		
		System.out.println("-----------------Update User Data-------------");
		// Read user data to check if first name is updated
		response = UserEndPoints.getUser(this.userPayload.getUsername());
		response.then().log().all();
		
		//log
		logger.info("Update user executed.");
		
	}

	@Test(priority=4)
	public void testDeleteUser() {

		 response = UserEndPoints.deleteUser(this.userPayload.getUsername());

		System.out.println("-----------------Delete User Data-------------");
		// log response
		response.then().log().all();
		// Validations
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//log
		logger.info("Delete user executed.");
	}
}
