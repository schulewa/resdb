Feature: User authentication

  In order to access the application
  As a potential user
  I want to be able to log in

  Scenario: Should authorise the user on successful authentication
    Given I am not logged in
    When I supply a valid user name and password
    Then I should be authorised

  Scenario: Should not authorise the user if user name and password are invalid
    Given I am not logged in
    When I supply an invalid user name and password
    Then I should not be authorised

