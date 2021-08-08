package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.TestPayloadBuild;
import resources.Utils;

public class stepDefinition extends Utils {

	RequestSpecification req;
	static Response response;
	TestPayloadBuild data = new TestPayloadBuild();
	static String bookID;

	@Given("Json payload to add the book with {string} {string} {int} {string}")
	public void json_payload_to_add_the_book_with(String name, String isbn, Integer aisle, String author)
			throws IOException {

		req = given().spec(requestSpecification()).body(data.addBookPayload(name, isbn, aisle, author));
	}

	@When("user calls {string} with {string} HTTP request")
	public void user_calls_with_http_request(String resource, String method) {

		APIResources apiEndPoint = APIResources.valueOf(resource);
		System.out.println(apiEndPoint.getResource());

		if (method.equalsIgnoreCase("POST"))
			response = req.when().post(apiEndPoint.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = req.when().get(apiEndPoint.getResource());
	}

	@Then("^the API call got success with status code 200$")
	public void the_api_call_got_success_with_status_code_200() throws Throwable {
		response.then().assertThat().statusCode(200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedValue) {

		String msg = getJsonValue(response.asString(), key);
		assertEquals(msg, expectedValue);
	}

	@Then("verify if book_id created maps to {string} using {string}")
	public void verify_if_book_id_created_maps_to_using(String expectedBookName, String resource) throws IOException {

		bookID = getJsonValue(response.asString(), "ID");
		System.out.println("Book ID from response body-->" + bookID);
		
		req = given().spec(requestSpecification()).queryParam("ID", bookID);

		user_calls_with_http_request(resource, "GET");
		String actualBookName = getJsonValue(response.asString(), "book_name");

		String actualBookNameString = actualBookName.replaceAll("(^\\[|\\]$)", "");

		assertEquals(actualBookNameString, expectedBookName);

	}
	
	@Given("Delete method payload")
	public void delete_method_payload() throws IOException {
	
		req = given().spec(requestSpecification()).body(data.deleteBookPayload(bookID));
		
	}

}