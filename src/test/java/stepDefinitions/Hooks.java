package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeleteBook")
	public void Delete_method_preRequite() throws IOException 
	{
		if(stepDefinition.bookID==null)	
		{
		stepDefinition step = new stepDefinition();
		step.json_payload_to_add_the_book_with("The Ride of a Lifetime", "trotle", 33378, "Robert Iger");
		step.user_calls_with_http_request("AddBookAPI", "POST");
		step.verify_if_book_id_created_maps_to_using("The Ride of a Lifetime", "GetBookAPI");
		}
	}
}
