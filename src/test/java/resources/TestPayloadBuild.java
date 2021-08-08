package resources;

import pojo.AddBook;

public class TestPayloadBuild {
	
	public AddBook addBookPayload(String name, String isbn, int aisle, String author) {
		
		
		AddBook ab = new AddBook();
		ab.setName(name);
		ab.setIsbn(isbn);
		ab.setAisle(aisle);
		ab.setAuthor(author);
		return ab;
	}
	
	public String deleteBookPayload(String bookID) 
	{
		return "{\r\n\r\n    \"ID\" : \""+bookID+"\"\r\n}";
	}
	
}
