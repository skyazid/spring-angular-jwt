Feature: authentication

  Scenario: authentication

    Given url 'http://localhost:8080/api/login'
    And header Content-Type = 'application/json'
    And request { "username" : "alice" , "password" : "password"}
    When method POST
    Then status 200
    And match header Authorization == "#notnull"
