Feature: As a data consumer, I want the user information are stored in mySql DB correctly in users table.
  @TS4-222 @LibraryCT
  @db
  Scenario: Verify users has unique IDs  and Verify users table columns
    When Execute query to get all IDs from users
    Then verify all users has unique ID
    When Execute query to get all columns
    Then verify the below columns are listed in result
      | id            |
      | full_name     |
      | email         |
      | password      |
      | user_group_id |
      | image         |
      | extra_data    |
      | status        |
      | is_admin      |
      | start_date    |
      | end_date      |
      | address       |