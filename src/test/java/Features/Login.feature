@Sanity
Feature: Login
  In order to verify the Endpoints of Login

  # if some steps are common between the scenarios, they can be added to Background
  Scenario: In order to verify GET Request for Login Page
    Given the Initial Setup is done for Test Condition "TC01" from "Home" Sheet "In order to verify POST for creating a new employee Page"
    When the "post" Request is Executed
    Then user should get Expected Status code "200" and Response should match with "HTTP/1.1 200 OK"

  Scenario: In order to verify POST Request for Login Page
    Given the Initial Setup is done for Test Condition "TC02" from "Home" Sheet "In order to verify GET All employee details"
    When the "get" Request is Executed
    Then user should get Expected Status code "200" and Response should match with "HTTP/1.1 200 OK"

  Scenario: In order to verify GET Request for Login Page
    Given the Initial Setup is done for Test Condition "TC03" from "Home" Sheet "In order to verify GET Request for a specific employee"
    When the "get" Request is Executed
    Then user should get Expected Status code "200" and Response should match with "HTTP/1.1 200 OK"

  Scenario: In order to verify POST Request for Login Page
    Given the Initial Setup is done for Test Condition "TC04" from "Home" Sheet "In order to verify deletion of an employee"
    When the "Delete" Request is Executed
    Then user should get Expected Status code "200" and Response should match with "HTTP/1.1 200 OK"
