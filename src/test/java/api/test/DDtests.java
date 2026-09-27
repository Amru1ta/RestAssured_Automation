package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviderMultiple;
import io.restassured.response.Response;

public class DDtests {

	@Test (priority=1, dataProvider="dp", dataProviderClass=DataProviderMultiple.class)
	public void testPostUser(String UserID, String userName, String fname, String lname, String useremail, String pwd, String ph) {
		
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(UserID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response =UserEndPoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test (priority=2, dataProvider="userNames", dataProviderClass=DataProviderMultiple.class)
	public void testDeleteUser(String userName) {
		Response response =UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
}
