Feature: Create a new organization user

  Scenario Outline: Positive test cases: Create a new organization user
    Given : I am an admin user
    When : I add a firstName "<firstName>"
    When : I add a lastName "<lastName>"
    When : I add an email "<email>"
    When : Send a PUT HTTP request
    Then : New user is created in the application
    Examples:
      | firstName | lastName | email                |
      | harleen   | Kaur     | harleen@gmail.com    |
      | hello     | world    | helloworld@gmail.com |

  Scenario Outline: Negative cases: Create a new organization user
    Given : I am an admin user
    When : I add a firstName "<firstName>"
    When : I add a lastName "<lastName>"
    When : I add an email "<email>"
    When : Send a PUT HTTP request
    Then : New user is not created in the application
    Examples:
      | firstName | lastName | email                |
      |           | Kaur     | harleen@gmail.com    |
      | hello     |          | helloworld@gmail.com |
      | hello     | hello    |                      |

  Scenario: Get all users
  Given : I am an admin user
  When : Send a GET HTTP request
  Then : I get all the user records for a organisation id.

  Scenario: Get all users with hamcrest
    Given : I am an admin user with hamcrest
    When : Send a GET HTTP request with hamcrest
    Then : I get all the user records for a organisation id with hamcrest.

  Scenario: Delete the recently created user
    Given :I am an admin user to delete user
    When :I send put call for deletion
    Then :the user is successfully deleted from the application.
