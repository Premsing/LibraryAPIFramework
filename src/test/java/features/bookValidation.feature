Feature: Validating Books API's

@AddBook
Scenario Outline: Verify if we are able to add book using AddBook API
	Given Json payload to add the book with "<name>" "<isbn>" <aisle> "<author>"
	When user calls "AddBookAPI" with "POST" HTTP request
	Then the API call got success with status code 200
	And "Msg" in response body is "successfully added"
	And verify if book_id created maps to "<name>" using "GetBookAPI"
	  	
	 
Examples:
	| name 		    | isbn   | aisle | author 	   |
	| Atomic Habits | atmttd | 22776 | James Clear |
	
@DeleteBook
Scenario: Verify if data is being deleted using DeleteBook API
  	Given Delete method payload
  	When user calls "DeleteBookAPI" with "POST" HTTP request
  	Then the API call got success with status code 200
  	And "msg" in response body is "book is successfully deleted"
  	
  	
