	Feature: As a librarian, I want to know what genre of books is being borrowed the most
	@TS4-231 @LibraryCT
	@db
	Scenario: Verify the common book genre that’s being borrowed
		Given Establish the database connection
		    When I execute query to find most popular book genre
		    Then verify "Action and Adventure" is the most popular book genre.