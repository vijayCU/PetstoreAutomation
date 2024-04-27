package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpointsusingPropertiesfile;
import api.payload.User;
import io.restassured.response.Response;

public class userTestusingPropertiesfile {
	
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setupData()
	{
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//Logs
		logger=LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("******************Creating User*****");
		Response response=UserEndpointsusingPropertiesfile.createuser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******************Created User*****");
	}
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("******************Getting Details User*****");
		Response response = UserEndpointsusingPropertiesfile.readuser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******************Get the Details of User*****");
	}
	
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info("******************Upating User*****");
		//update date using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndpointsusingPropertiesfile.updateuser(this.userPayload.getUsername(),userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("******************User Updated*****");
		//Checking data after update
		Response responseAfterUpdate = UserEndpointsusingPropertiesfile.readuser(this.userPayload.getUsername());
			Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);	
	}
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("******************Deleting User*****");
		Response response = UserEndpointsusingPropertiesfile.deleteuser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******************Deleted User*****");
	} 
}
