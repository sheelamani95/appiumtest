Feature: Login to Leeroy Music album mobile application

  @FunctionalTest
  Scenario: As a User, I want to login to Leeroy Music album mobile application.
    Given User launch the Leeroy mobile app
    When User enters the credentials
    And User clicks the Login
    Then User Successfully Logged in to the Leeroy music album
    And User clicks album to see the title and singer
    Then User click back to albums page
    And User clicks logout

  @FunctionalTest
  Scenario: As a User, I want to login to Leeroy Music album mobile application with wrong credentials.
    Given User launch the Leeroy mobile app
    When User enters the wrong credentials
    And User clicks the Login
    Then User stays in login page

