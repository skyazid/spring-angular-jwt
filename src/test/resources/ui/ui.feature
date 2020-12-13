Feature: ui

  Background:
    * configure driver = { type: 'chromedriver', showDriverLog: true }
    * def username = 'alice'
    * def password = 'password'
    * def taskName = 'new task'

  Scenario: connect and save task

    Given driver 'http://localhost:8080'
    And input('#username', username)
    And input('#password', password)
    And submit().click("button[id=submit]")
    And input('#task-name', taskName)
    When submit().click("button[id=add-task-button]")
    Then json response = text('div.alert-success')
    And match response ==
    """
      {
        "id": "#number",
        "name": "#(taskName)",
        "_links": {
          "self": {
            "href": "#notnull"
          },
          "task": {
            "href": "#notnull"
          }
        }
      }
    """