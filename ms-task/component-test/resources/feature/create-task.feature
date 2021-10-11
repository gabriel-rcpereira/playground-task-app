Feature: create a task and exploring
  Scenario: client posts a call to /api/v1/tasks
    Given the api "/api/v1/tasks"
    When the client sends a post request and body "task/request/create-with-success"
    Then the client should receive the status code of 201
    And the client should receive response body equal to "empty"