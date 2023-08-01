package apitest;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import Apiendpoint.Userendpoints;
import apipayload.User;
import io.restassured.response.Response;

public class Usertest {
	
	Faker faker;
	User Userpayload;
	
	@BeforeClass
	public void setupdata() {
		faker=new Faker();
		Userpayload=new User();
		
		Userpayload.setId(20);
		Userpayload.setId(faker.idNumber().hashCode());
		Userpayload.setUsername(faker.name().username());
		Userpayload.setFirstName(faker.name().firstName());
		Userpayload.setLastName(faker.name().lastName());
		Userpayload.setPassword(faker.internet().password(5,10));
		Userpayload.setPhone(faker.phoneNumber().cellPhone());
		Userpayload.setEmail(faker.internet().emailAddress());
}
	
	@Test(priority = 1)
	public void postTestcase() throws JsonProcessingException {

		
//		ObjectMapper map=new ObjectMapper();
//		String a=map.writerWithDefaultPrettyPrinter().writeValueAsString(Userpayload);
		Response response =Userendpoints.createuser(Userpayload);
		response.then().log().all();
		response.then().statusCode(200);
		
		
	}
	@Test(priority =2)
	public void gettestcase() {
		System.out.println("printing the get response");
		Response response=Userendpoints.getuser(this.Userpayload.getUsername());
		response.then().log().all();
		
	}
	@Test(priority = 3)
	public void puttestcase() {
		//updating the user using userpayload
		Userpayload.setFirstName(faker.name().firstName());
		Userpayload.setLastName(faker.name().lastName());
		Userpayload.setEmail(faker.internet().emailAddress());
		Response response=Userendpoints.updateuser(this.Userpayload.getUsername(), Userpayload);
		System.out.println("printing the putresponse/n");
		response.then().and().log().body();
	}
	@Test(priority = 4)
	public void getingupdateddata() {
		System.out.println("printing updatd user _______________________________________");
		Response response=Userendpoints.getuser(this.Userpayload.getUsername());
		response.then().log().all();
		
	}
	@Test(priority = 5)
	public void daletetestcase() {
		System.out.println("delete response*************");
		Response response=Userendpoints.deleteuser(this.Userpayload.getUsername());
		response.then().log().all();
		
	}

}
