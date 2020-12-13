Feature: authentication

  Background:
    * url baseUrl = 'http://localhost:8080/api'
    * def username = 'alice'
    * def password = 'password'
    * def getPayload =
      """
      function(token) {
          var Base64 = Java.type('java.util.Base64');
          var String = Java.type('java.lang.String');

          var base64Payload = token.split('.')[1];
          var payload = Base64.getDecoder().decode(base64Payload);
          return new String(payload);
      }
      """

  Scenario: authentication

    Given url baseUrl + '/login'
    And header Content-Type = 'application/json'
    And request { "username" : "#(username)" , "password" : "#(password)"}
    When method POST
    Then status 200
    And def accessToken = responseHeaders['Authorization'][0]
    And match accessToken == "#regex ^Bearer [A-Za-z0-9-_=]+\.[A-Za-z0-9-_=]+\.?[A-Za-z0-9-_.+/=]*$"
    And json payload = getPayload(accessToken)
    And match payload ==
    """
      {
        "sub": "#(username)",
        "exp": "#number",
        "roles": [
          {
            "authority": "ADMIN"
          },
          {
            "authority": "USER"
          }
        ]
      }
    """