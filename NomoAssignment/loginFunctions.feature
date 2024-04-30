Feature: Login Functionality

  Scenario Outline: Verify Login Scenarios
    Given the user is on the login page
    When the user enters "<username>" and "<password>"
    Then the user should be "<result>"

    Examples:
      | username           | password   | result         |
      | alice@example.com  | 10203040   | locked         |
      | 1@2.com            | f-o-o      | no match       |
      |                    |            | no user details|
      | bob@example.com    |            | no password    |
      | bob@example.com    | 10203040   | standard login |
