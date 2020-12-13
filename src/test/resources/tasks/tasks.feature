Feature: tasks

  Background:
    * url baseUrl = 'http://localhost:8080/api'

  Scenario Outline: authentication and get/save tasks

    * print Start test with : "<username>"

    Given url baseUrl + '/login'
    And header Content-Type = 'application/json'
    And request
    """
      {
        "username" : "<username>",
        "password" : "<password>"
      }
    """
    When method POST
    Then status 200
    And match header Authorization == "#notnull"
    And def accessToken = responseHeaders['Authorization'][0]

    Given url baseUrl + '/tasks'
    And header Authorization = accessToken
    And request
    """
      {
        "name" : "new Tasks"
      }
    """
    When method POST
    Then status <saveResponse>

    Given url baseUrl + '/tasks?page=0&size=2'
    And header Authorization = accessToken
    When method GET
    Then status 200
    And match response == read('fixtures/tasks.json')

    Examples:
      | username | password | saveResponse |
      | alice    | password | 201          |
      | bob      | password | 403          |