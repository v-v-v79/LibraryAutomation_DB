@wip
Feature: Login Functionality
  @db @ui
  Scenario Outline: Login with valid credentials DDT
    Given the user logged in  "<email>" and "<password>"
    When user gets username  from user fields
    Then the username should be same with database
    Examples:
      | email               | password |
      | librarian55@library | 67UQi3Ol |
      | librarian56@library | pBQnq0NN |
      | student5@library    | i1oDgf2d |
      | student6@library    | NXhpXJdC |