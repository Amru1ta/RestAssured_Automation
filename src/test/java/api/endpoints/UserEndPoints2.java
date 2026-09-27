package api.endpoints;

import java.util.ResourceBundle;
import static io.restassured.RestAssured.given;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {
	
	static ResourceBundle getURL() {
	
	ResourceBundle links = ResourceBundle.getBundle("routes");
	return links;

}
	public static Response createUser (User payload) {
		
		String post_url = getURL().getString("post_url");
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			.when()
				.post(post_url);
		return response;
		
	}
	
	
}