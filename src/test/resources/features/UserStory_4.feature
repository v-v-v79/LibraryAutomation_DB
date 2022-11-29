@wip
Feature: As a data consumer, I want UI and DB book information are match.
  @ui @db
  Scenario: Verify book information with DB
    Given I login as "librarian"
    When I navigate to "Books" page
    When I open book "Chordeiles minor"
    Then book information must match the Database