Feature: Books module
  As a data consumer, I should be able to see borrowed book numbers are matching with DB
  Scenario: Verify borrowed book numbers are matching with DB
    Given I login as a student
    And I navigate to "Books" page
    And I search book name called "Head First Java"
    When I click Borrow Book
    Then  verify that book is shown in "Borrowing Books"
    And  verify logged student has same book in database