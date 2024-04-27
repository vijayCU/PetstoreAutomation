package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest {
	
	@Test(priority=1,dataProvider = "Data",dataProviderClass =DataProviders.class )
	public void testPostUser(String userID,String userName,String fname,String lname,String email,String password,String phone)
	{
		User userPayload=new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);
		
		Response response=UserEndpoints.createuser(userPayload);	
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority=2,dataProvider = "UserNames",dataProviderClass =DataProviders.class )
	public void testDeleteUserByName(String userName)
	{
		Response response=UserEndpoints.deleteuser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
}
