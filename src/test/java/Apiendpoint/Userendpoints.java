package Apiendpoint;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import apipayload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class Userendpoints {
	
      static ResourceBundle getURl(){
		ResourceBundle routes=ResourceBundle.getBundle("urlfile");
		return routes;
		
		
	}
	
	public static Response createuser(User payload){
		//taking url from properties file
		String post_url=getURl().getString("post_url");
     Response response=given()
		     .contentType(ContentType.JSON)
		     .accept(ContentType.JSON)
		     .body(payload)
		.when()
//		     .post(Routes.post_url);
     //taking url from properties file
             .post(post_url);
     
    return response;
	}
	public static Response getuser(String userName){
	     Response response=given()
			     .pathParam("username",userName)
			     
			.when()
			     .get(Routes.get_url);
	    return response;
	}
	public static Response updateuser(String userName,User payload){
	     Response response=given()
			     .contentType(ContentType.JSON)
			     .accept(ContentType.JSON)
			     .pathParam("username",userName)
			     .body(payload)
			     
			.when()
			     .put(Routes.put_url);
	    return response;
	}
	public static Response deleteuser(String userName){
	     Response response=given()
			     .pathParam("username",userName)
			     
			.when()
			     .delete(Routes.delete_url);
	    return response;
	}

}
