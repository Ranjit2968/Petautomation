package apitest;

import org.testng.Assert;
import org.testng.annotations.Test;

import Apiendpoint.Userendpoints;
import apipayload.User;
import apiutilities.DataProviders;
import io.restassured.response.Response;

public class DDTesting {
	@Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProviders.class)
	public void testpostuser(String userID,String userName,String fname,String lname,String useremail,String pwd,String ph) {
		User UserPayload=new User();
		UserPayload.setId(Integer.parseInt(userID));
		UserPayload.setUsername(userName);
		UserPayload.setEmail(useremail);
		UserPayload.setFirstName(fname);
		UserPayload.setLastName(lname);
		UserPayload.setPassword(pwd);
		UserPayload.setPhone(ph);
		
		
		Response response=Userendpoints.createuser(UserPayload);
		Assert.assertEquals(response.getStatusCode(),200);
		
	}
	
	@Test(priority = 2,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
	public void getuser(String userName) {
		Response response=Userendpoints.getuser(userName);
		response.then().log().all();
	}

}
