Feature: authentication

  Background:
    * url baseUrl = 'http://localhost:8080/api'
    * def username = 'alice'
    * def password = 'password'

  Scenario: authentication

    Given url baseUrl + '/login'
    And header Content-Type = 'application/json'
    And request { "username" : "#(username)" , "password" : "#(password)"}
    When method POST
    Then status 200
    And match header Authorization == "#regex ^Bearer [A-Za-z0-9-_=]+\.[A-Za-z0-9-_=]+\.?[A-Za-z0-9-_.+/=]*$"